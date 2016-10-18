package com.lunjar.ebp.bizsupport.test.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.service.OutTradeNoService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class OutTradeNoServiceImplTest extends BizTestSupport {

	@Autowired
	private OutTradeNoService outTradeNoService;

	@Test
	public void add() throws ServiceException {
		OutTradeNo tradeNo = new OutTradeNo();
		tradeNo.setTradeIds("1;2;3");
		String outTradeNo = outTradeNoService.add(tradeNo);
		System.out.println("新的订单编号为：" + outTradeNo);
	}

	@Test
	public void load() {
		String  outTradeNo = "201608301002";
		OutTradeNo tradeNo = outTradeNoService.load(outTradeNo);
		System.out.println(tradeNo.toString());
	}
}
