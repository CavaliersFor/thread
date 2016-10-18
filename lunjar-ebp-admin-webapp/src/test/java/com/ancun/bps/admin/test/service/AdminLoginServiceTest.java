package com.ancun.bps.admin.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ancun.bps.core.log.dto.LoginDto;
import com.lunjar.ebp.admin.biz.service.AdminLoginService;
import com.lunjar.products.core.utils.DigestsUtils;

public class AdminLoginServiceTest extends TestSupport {
	@Autowired
	private AdminLoginService adminLoginService;

	@Test
	public void testLogin() {
		LoginDto dto = new LoginDto();

		String account = "shenwei";
		String random = "12asd3423256";
		String pwd = "123456";
		String s1 = account + DigestsUtils.sha1Hex(pwd.getBytes());
		String s2 = DigestsUtils.md5Hex(s1.getBytes());
		String sign = DigestsUtils.md5Hex((s2 + random).getBytes());

		dto.setAccount(account);
		dto.setRandom(random);
		dto.setSign(sign);

		adminLoginService.login(dto);
	}

}
