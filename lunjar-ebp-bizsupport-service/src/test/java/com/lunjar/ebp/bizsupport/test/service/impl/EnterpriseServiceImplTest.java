package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.EnterpriseDto;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.DigestsUtils;

public class EnterpriseServiceImplTest extends BizTestSupport {

	@Autowired
	private EnterpriseService enterpriseService;

	@Test
	public void load() throws ServiceException {
		Long id = 1l;
		Enterprise enterprise = enterpriseService.load(id);
		System.out.println(enterprise.toString());
	}
	
	
	@Test
	public void testLogin(){
		System.out.println(DigestsUtils.sha1Hex("1234".getBytes()));
		/*EnterpriseDto dto = new EnterpriseDto();
		dto.setAccount("admin");
		dto.setRandom("1473760193292");
		dto.setSign("e2d05f7a53d72cb3f91f5b51bf6a723c");
		try {
			String s1 = DigestsUtils.md5Hex((dto.getAccount() + "[B@59360e80").getBytes());
			String sign2 = DigestsUtils.md5Hex((s1 + dto.getRandom()).getBytes());
			System.out.println("sign2:"+sign2);
			//enterpriseService.login(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}
