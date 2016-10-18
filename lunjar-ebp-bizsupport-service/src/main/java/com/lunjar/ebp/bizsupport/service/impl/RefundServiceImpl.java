package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumRefundType;
import com.lunjar.ebp.bizsupport.mappers.RefundMapper;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.Refund;
import com.lunjar.ebp.bizsupport.query.RefundQuery;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.service.RefundService;
import com.lunjar.products.core.exception.ServiceException;

@Service(value = "refundService")
public class RefundServiceImpl implements RefundService {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RefundServiceImpl.class);

	@Autowired
	private RefundMapper refundMapper;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private ProductService productService;

	@Override
	public Refund load(Long id) {
		return refundMapper.load(id);
	}

	@Override
	public Long add(Refund t) throws ServiceException {
		refundMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(Refund t) {
		return refundMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		refundMapper.delete(id);
	}

	@Override
	public List<Refund> queryList(RefundQuery q) {
		return refundMapper.queryList(q);
	}

	@Override
	public int queryCount(RefundQuery q) {
		return refundMapper.queryCount(q);
	}

	//退款状态改变
	@Override
	public int updateRefundStatus(Refund refund) throws ServiceException {
		// 同意退款
		if (EnumRefundType.REFUND_SUCCESS.equals(refund.getType())) {
			Orders orders = ordersService.load(refund.getOrdersId());
			if (orders == null) {
				logger.warn(BizSupportCode._3000027.getMsg());
				throw new ServiceException(BizSupportCode._3000027.getCode(), BizSupportCode._3000027.getMsg());
			}
			//同意退款，库存、销量都要回调
			if (orders.getSkuId() == null) {
				Product product = productService.load(orders.getProductId());
				if (product== null) {
					logger.warn(BizSupportCode._2001017.getMsg());
					throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
				}
				product.setProductNum(product.getProductNum() + orders.getNum());
				product.setSaleNum(product.getSaleNum() - orders.getNum());
				productService.update(product);
			}else {
				ProductSku sku = productSkuService.load(orders.getSkuId());
				if (sku == null) {
					logger.warn(BizSupportCode._3000017.getMsg());
					throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
				}
				sku.setQuantity(sku.getQuantity() + orders.getNum());
				productSkuService.update(sku);
			}
			//执行微信退款操作
			refund();
		}
		return refundMapper.update(refund);
	}

	private void refund() {
		// TODO Auto-generated method stub
		
	}
}
