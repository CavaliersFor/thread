package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ancun.bps.core.region.BpsRegionService;
import com.ancun.bps.core.region.model.PubRegion;
import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.enums.EnumCartStatus;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.mappers.CartMapper;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class CartServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(CartServiceImpTest.class);

	@Autowired
	private CartService cartService;

	@Test
	public void add() throws ServiceException {
		Cart cart = new Cart();
		cart.setBuyerId(1l);
		cart.setGmtCreate(new Date());
		cart.setGmtModify(new Date());
		cart.setProductId(1l);
		cart.setShopId(1l);
		cart.setNum(1);
		cart.setStatus(EnumCartStatus.SETTLED.getValue());
		Long id = cartService.add(cart);
		logger.info("购物车id为：：：：" + id);
	}

	@Test
	public void update() throws BizSupportException {
		Cart cart = new Cart();
		cart.setId(1l);
		cart.setStatus(EnumCartStatus.SETTLED.getValue());
		cartService.update(cart);
	}

	@Test
	public void getList() throws BizSupportException {
		CartQuery query = new CartQuery();
		Long[] array = { 1l, 2l };
		query.setIdArray(array);
		query.setBuyerId(1l);
		List<CartDto> list = cartService.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (CartDto c : list) {
				System.out.println(c.getId() + "@@@@@" + c.getProductName());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		Cart cart = cartService.load(1l);
		logger.info(cart.toString());
	}
	
	@Test
	public void deleteCartBuQuery() {
		Long[] ids = new Long[2];
		ids[0] = 19l;
		ids[1] = 20l;
		CartQuery query = new CartQuery();
		query.setIdArray(ids);
		cartService.deleteByQuery(query);
	}
}
