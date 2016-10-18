package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.enums.EnumShopStatus;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.query.ShopQuery;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class ShopServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ShopServiceImpTest.class);

	@Autowired
	private ShopService shopService;

	@Test
	public void add() throws BizSupportException {
		Shop shop = new Shop();
		shop.setGmtCreate(new Date());
		shop.setGmtModify(new Date());
		shop.setPartnerId(1l);
		shop.setSaleId(1l);
		shop.setShopName("农村俱乐部");
		shop.setStatus(EnumShopStatus.NORMAL.getValue());
		shop.setHeadPicUrl("http://img1.gtimg.com/cul/pics/hv1/109/226/2115/137585614.png");
		Long id = shopService.add(shop);
		System.out.println("商品id为：：：：" + id);
	}

	@Test
	public void update() throws BizSupportException {
		Shop shop = new Shop();
		shop.setId(1l);
		shop.setShopName("丑小鸭玩具店-修改");
		shopService.update(shop);
	}

	@Test
	public void getList() throws BizSupportException {
		ShopQuery query = new ShopQuery();
		Long[] array = { 1l, 2l };
		query.setIdArray(array);
		List<Shop> list = shopService.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Shop s : list) {
				System.out.println(s.getId() + "@@@@@" + s.getShopName());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		Shop shop = shopService.load(1l);
		logger.info(shop.toString());
	}

	@Test
	public void getShopInfo() throws BizSupportException {
		String accessKey = "222b05eba107e7bdf32fad2c6b2fffa3";
		ShopInfo shopInfo = shopService.getShopInfo(accessKey);
		System.out.println(shopInfo.toString());
	}
}
