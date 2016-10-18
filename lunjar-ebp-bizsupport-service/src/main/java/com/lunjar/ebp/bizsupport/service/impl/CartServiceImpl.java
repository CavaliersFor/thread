package com.lunjar.ebp.bizsupport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumCartStatus;
import com.lunjar.ebp.bizsupport.mappers.CartMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductMapper;
import com.lunjar.ebp.bizsupport.mappers.ProductSkuMapper;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.query.ProductQuery;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 购物车实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月15日上午9:38:30
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	CartMapper cartMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductSkuMapper productSkuMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductSkuService productSkuService;

	@Transactional
	@Override
	public Long add(Cart cart) throws ServiceException {
		Assert.notNull(cart, "object cart is null");
		Assert.notNull(cart.getProductId(), BizSupportCode._2001015.getMsg());
		Assert.notNull(cart.getBuyerId(), BizSupportCode._2001018.getMsg());
		Assert.notNull(cart.getShopId(), BizSupportCode._2001016.getMsg());
		Assert.notNull(cart.getNum(), BizSupportCode._2001023.getMsg());
		Product product = productMapper.load(cart.getProductId());
		if (product == null) {
			log.warn(BizSupportCode._2001017.getMsg());
			throw new ServiceException(BizSupportCode._2001017.getCode(), BizSupportCode._2001017.getMsg());
		}
		//判断商品存储是否大于或者等于购物车的数量
		if (cart.getSkuId() == null) {
			if (product.getProductNum() < cart.getNum()) {
				log.warn(BizSupportCode._3000030.getMsg());
				throw new ServiceException(BizSupportCode._3000030.getCode(), BizSupportCode._3000030.getMsg());
			}
		}else {
			ProductSku sku = productSkuService.load(cart.getSkuId());
			if (sku == null) {
				log.warn(BizSupportCode._3000017.getMsg());
				throw new ServiceException(BizSupportCode._3000017.getCode(), BizSupportCode._3000017.getMsg());
			}
			if (sku.getQuantity() < cart.getNum()) {
				log.warn(BizSupportCode._3000030.getMsg());
				throw new ServiceException(BizSupportCode._3000030.getCode(), BizSupportCode._3000030.getMsg());
			}
		}
		cart.setStatus(EnumCartStatus.NOTSETTLED.getValue());
		cartMapper.insert(cart);
		return cart.getId();

	}

	@Transactional
	@Override
	public void update(Cart cart) {
		Assert.notNull(cart, "object cart is null");
		Assert.notNull(cart.getId(), "required cart id is null");
		Assert.notNull(cart.getBuyerId(), BizSupportCode._2001018.getMsg());
		cartMapper.update(cart);
	}

	@Override
	public List<CartDto> queryList(CartQuery query) {
		List<CartDto> list = null;
		Assert.notNull(query.getBuyerId(), BizSupportCode._2001018.getMsg());
		List<Cart> cartList = cartMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(cartList)) {
			list = new ArrayList<>();
			CartDto dto = null;
			Cart cart = null;
			int len = cartList.size();
			Long[] productIds = new Long[len];
			Long[] skuIds = new Long[len];
			for (int i = 0; i < len; i++) {
				dto = new CartDto();
				cart = cartList.get(i);
				productIds[i] = cart.getProductId();
				skuIds[i] = cart.getSkuId();
				dto.setId(cart.getId());
				dto.setBuyerId(cart.getBuyerId());
				dto.setGmtCreate(cart.getGmtCreate());
				dto.setGmtModify(cart.getGmtModify());
				dto.setProductId(cart.getProductId());
				dto.setNum(cart.getNum());
				dto.setShopId(cart.getShopId());
				dto.setStatus(cart.getStatus());
				if (cart.getSkuId() != null) {
					dto.setSkuId(cart.getSkuId());
				}
				list.add(dto);
			}
			// 根据productIds查询productList
			ProductQuery productQuery = new ProductQuery();
			productQuery.setIdArray(productIds);
			List<Product> products = productService.queryList(productQuery);
			Map<Long, Object> productMap = new HashMap<>();
			if (CollectionUtils.isNotEmpty(products)) {
				for (Product p : products) {
					productMap.put(p.getId(), p);
				}
			}
			// 根据skuIds查询skuList
			ProductSkuQuery skuQuery = new ProductSkuQuery();
			skuQuery.setIdArray(skuIds);
			List<ProductSku> productSkus = productSkuMapper.queryList(skuQuery);
			Map<Long, Object> skuMap = new HashMap<>();
			if (CollectionUtils.isNotEmpty(productSkus)) {
				for (ProductSku ps : productSkus) {
					skuMap.put(ps.getId(), ps);
				}
			}
			if (CollectionUtils.isNotEmpty(list)) {
				Product product = null;
				ProductSku productSku = null;
				for (CartDto d : list) {
					product = (Product) productMap.get(d.getProductId());
					if (product != null) {
						d.setPicPath(product.getPathUrl());
						d.setProductName(product.getName());
						d.setProductRealPrice(product.getSalePrice());
						d.setCategoryName(product.getCatName());
						d.setProductStatus(product.getStatus());
					}
					productSku = (ProductSku) skuMap.get(d.getSkuId());
					if (productSku != null) {
						d.setProperties(productSku.getProperties());
						d.setPropertiesname(productSku.getPropertiesname());
					}
				}
			}
		}
		return list;
	}

	@Override
	public int queryCount(CartQuery query) {
		Assert.notNull(query.getBuyerId(), BizSupportCode._2001018.getMsg());
		return cartMapper.queryCount(query);
	}

	@Override
	public Cart load(Long id) {
		Assert.notNull(id, "object id is null");
		return cartMapper.load(id);
	}

	@Override
	public void delete(Long id) {
		Assert.notNull(id, "object id is null");
		cartMapper.delete(id);
	}

	@Override
	public void deleteByQuery(CartQuery query) {
		// TODO Auto-generated method stub
		cartMapper.deleteByQuery(query);
	}
}
