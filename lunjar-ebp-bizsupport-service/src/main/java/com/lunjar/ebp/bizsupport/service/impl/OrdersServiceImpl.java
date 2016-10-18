package com.lunjar.ebp.bizsupport.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.mappers.OrdersMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.mappers.TradeMapper;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.products.core.exception.ServiceException;

@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService {
	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	@Value("${store.server.url}")
	private String storeUrl;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductSkuMapper productSkuMapper;

	@Override
	public Orders load(Long id) {
		return ordersMapper.load(id);
	}

	@Transactional
	@Override
	public Long add(Orders orders) throws ServiceException {
		Assert.notNull(orders, "object orders is null");
		if (orders.getTradeId() == null) {
			logger.warn(BizSupportCode._2001022.getMsg());
			throw new ServiceException(BizSupportCode._2001022.getCode(), BizSupportCode._2001022.getMsg());
		}
		if (orders.getNum() == null) {
			logger.warn(BizSupportCode._2001023.getMsg());
			throw new ServiceException(BizSupportCode._2001023.getCode(), BizSupportCode._2001023.getMsg());
		}
		// if (orders.getTotalRealPrice() == null) {
		// logger.warn(BizSupportCode._2001024.getMsg());
		// throw new ServiceException(BizSupportCode._2001024.getCode(),
		// BizSupportCode._2001024.getMsg());
		// }
		if (orders.getProductId() == null) {
			logger.warn(BizSupportCode._2001015.getMsg());
			throw new ServiceException(BizSupportCode._2001015.getCode(), BizSupportCode._2001015.getMsg());
		}
		Product product = productMapper.load(orders.getProductId());
		if (product == null) {
			logger.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		if (orders.getSkuId() != null) {
			ProductSku productSku = productSkuMapper.load(orders.getSkuId());
			if (productSku == null) {
				logger.warn(BizSupportCode._3000017.getMsg());
				throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
			}
			orders.setProperties(productSku.getProperties());
			orders.setPropertiesname(productSku.getPropertiesname());
		}
		orders.setProductName(product.getName());
		orders.setProductPicPath(product.getPathUrl());
		orders.setProductRealPrice(product.getSalePrice());
		BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(orders.getNum()));
		BigDecimal totalDiscountPrice = totalPrice.subtract(orders.getTotalRealPrice());
		orders.setTotalPrice(totalPrice);
		orders.setTotalDiscountPrice(totalDiscountPrice);
		orders.setStatus(EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		orders.setGmtCreate(new Date());
		orders.setGmtModify(new Date());
		ordersMapper.insert(orders);
		return orders.getId();
	}

	@Override
	public int update(Orders orders) {
		Assert.notNull(orders, "object orders is null");
		Assert.notNull(orders.getId(), "required orders id is null");
		return ordersMapper.update(orders);
	}

	@Override
	public void delete(Long id) {
		ordersMapper.delete(id);
	}

	@Override
	public List<Orders> queryList(OrdersQuery q) {
		List<Orders> list = ordersMapper.queryList(q);
		if (CollectionUtils.isNotEmpty(list)) {
			for(Orders o: list) {
				o.setProductPicPath(storeUrl + o.getProductPicPath());
			}
		}
		return list;
	}

	@Override
	public int queryCount(OrdersQuery q) {
		return ordersMapper.queryCount(q);
	}
	
	@Override
	public void updateByQuery(Orders orders, OrdersQuery query) {
		Assert.notNull(orders, "orders is not null");
		Assert.notNull(orders, "orders is not null");
		ordersMapper.updateByQuery(orders, query);
	}
	
	@Override
	public void updateStatusByTradeId(Long tradId, int status) {
		ordersMapper.updateStatusByTradeId(tradId, status);
	}
}
