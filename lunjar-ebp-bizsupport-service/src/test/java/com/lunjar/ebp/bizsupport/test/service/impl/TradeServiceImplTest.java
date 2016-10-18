package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.dto.TradeInfoDto;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class TradeServiceImplTest extends BizTestSupport {

	@Autowired
	private TradeService tradeService;

	@Test
	public void add() throws ServiceException {
		TradeInfoDto dto = new TradeInfoDto();
		dto.setAddressId(1l);
		dto.setBuyerRemarks("能打折吗");
		dto.setEnterpriseId(1l);
		dto.setSaleId(1l);
		/*dto.setBuyerName("lx");
		dto.setBuyerPhone("123");*/
		dto.setIsInvoice(2);
//		dto.setShopId(1l);
//		dto.setBuyerId(1l);
		List<Long> list = new ArrayList<>();
		list.add(18l);
		dto.setCartIdList(list);
		Long id = tradeService.add(dto);
		System.out.println("添加的订单id=" + id);
	}

	@Test
	public void load() {
		Long id = 1L;
		Trade t = tradeService.load(id);
		System.out.println(t);
	}

	@Test
	public void update() {
		Long id = 1L;
		Trade t = new Trade();
		t.setId(id);
		t.setTradeDesc("asdgasdgasdgasdg描述");
		int num = tradeService.update(t);
		System.out.println("更新记录数：" + num);
	}

	@Test
	public void queryListAndQueryCount() {
		TradeQuery query = new TradeQuery();
		//query.setProductName("葡萄酒");
		Map<String,Object> properties = new HashMap<>();
		//properties.put("productName", "葡萄酒");
		properties.put("startTime", new Date());
		properties.put("endTimer", new Date());
		//query.setProperties(properties);
		query.setGmtCreateFrom(new Date());
		query.setGmtCreateTo(new Date());
		java.util.List<Trade> list = tradeService.queryList(query);
		if (list != null && list.size() > 0) {
			for (Trade t : list) {
				System.out.println(t);
			}
		}
		/*int count = tradeService.queryCount(query);
		System.out.println("总记录数：" + count);*/
	}

	@Test
	public void delete() {
		Long id = 1L;
		tradeService.delete(id);
		;
	}

	@Test
	public void getTradeInfo() throws ServiceException {
		Long tradeId = 12L;
		Long buyerId = 12313L;
		Integer type = 1;
		TradeDto dto = tradeService.getTradeInfo(tradeId, buyerId, type);
		System.out.println(dto);
	}
	
	@Test
	public void addCom() throws ServiceException {
		TradeInfoDto dto = new TradeInfoDto();
		dto.setAddressId(1l);
		dto.setBuyerRemarks("能打折吗");
		dto.setEnterpriseId(1l);
		dto.setSaleId(1l);
		/*dto.setBuyerName("lx");
		dto.setBuyerPhone("123");*/
		dto.setIsInvoice(2);
		dto.setShopId(1l);
		dto.setBuyerId(1l);
		dto.setCombinationId(1l);
		List<CombinDto> list = new ArrayList<>();
		CombinDto cDto = new  CombinDto();
		cDto.setNum(2);
		cDto.setProductId(1l);
		cDto.setRealPrice(new BigDecimal("333"));
		cDto.setSkuId(1l);
		list.add(cDto);
		dto.setListCombinDto(list);
		Long id = tradeService.addCombinationTrade(dto);
		System.out.println("添加的订单id=" + id);
	}
}
