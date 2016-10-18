package com.lunjar.ebp.bizsupport.test.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.VerCodeDto;
import com.lunjar.ebp.bizsupport.service.CreateVerCodeService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class CreateVerCodeServiceImplTest extends BizTestSupport{
	
	
	@Autowired
	private CreateVerCodeService createVerCodeService;
	
	@Test
	public void testCreateVerCode(){
		VerCodeDto v = createVerCodeService.createVerCode(4, "15210532063");
		System.out.println(v);
	}
}
