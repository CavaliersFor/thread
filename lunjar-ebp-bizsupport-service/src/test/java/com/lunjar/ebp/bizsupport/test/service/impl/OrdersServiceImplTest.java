package com.lunjar.ebp.bizsupport.test.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;
import com.lunjar.ebp.bizsupport.model.ShopIndex;
import com.lunjar.ebp.bizsupport.service.FileUploadService;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class OrdersServiceImplTest extends BizTestSupport {

	@Autowired
	private OrdersService ordersService;

	@Test
	public void updateStatusByTradeId() {
		ordersService.updateStatusByTradeId(1l, 9);
	}
}
