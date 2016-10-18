package com.lunjar.ebp.bizsupport.test.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.service.ProductPropImgsService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class ProductPropImgsServiceImplTest extends BizTestSupport {
	
	@Autowired
	private ProductPropImgsService productPropImgsService;
	
	
	@Test
	public void testDeleteByCondition(){
		ProductPropImgs p = new ProductPropImgs();
		p.setProductId(1L);
		productPropImgsService.deleteByCondition(p);
	}
}
