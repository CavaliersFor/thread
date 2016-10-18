package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.enums.EnumProductShopStatus;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.webapi.LunjarApiResponse;

public class ProductShopServiceImpTest extends BizTestSupport {
	protected static final Logger logger = LoggerFactory.getLogger(ProductShopServiceImpTest.class);

	@Autowired
	private ProductShopService productShopService;

	@Test
	public void add() throws ServiceException {
		ProductShop productShop = new ProductShop();
		productShop.setCategoryId(1l);
		productShop.setProductId(1l);
		productShop.setShopId(1l);
		productShop.setType(1);
		LunjarApiResponse response = LunjarApiResponse.create(productShopService.add(productShop));
		// Long id = productShopService.add(productShop);
		System.out.println(response + "");
	}

	@Test
	public void update() throws ServiceException {
		ProductShop productShop = new ProductShop();
		productShop.setId(1l);
		productShop.setStatus(EnumProductShopStatus.DELETE.getValue());
		productShopService.update(productShop);
	}

	@Test
	public void getList() throws ServiceException {
		ProductShopQuery query = new ProductShopQuery();
		// Long[] array={1l,2l};
		// query.setIdArray(array);
		query.setStatus(1);
//		query.setCategoryId(2L);
		query.setShopId(1L);
		List<ProductShopDto> list = productShopService.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			for (ProductShopDto p : list) {
				System.out.println(p.getId() + "@@@@@" + p.getProductName());
			}
		}
	}

	@Test
	public void load() throws BizSupportException {
		ProductShop productShop = productShopService.load(1l);
		logger.info(productShop.toString());
	}
	@Test
	public void updateByConditionTest(){
		ProductShop query = new ProductShop();
		query.setStatus(2);
		query.setProductId(1L);
		productShopService.updateByCondition(query);
	}

	@Test
	public void deleteProductShop() {
		ProductShop productShop = new ProductShop();
		productShop.setId(1l);
		productShop.setShopId(1l);
		productShopService.deleteProductShop(productShop);
	}
}
