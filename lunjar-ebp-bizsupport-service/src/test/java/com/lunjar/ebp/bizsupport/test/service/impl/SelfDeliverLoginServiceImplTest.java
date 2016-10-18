package com.lunjar.ebp.bizsupport.test.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.LoginDto;
import com.lunjar.ebp.bizsupport.model.AdminAgent;
import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.SelfDeliveryUserQuery;
import com.lunjar.ebp.bizsupport.service.SelfDeliveryLoginService;
import com.lunjar.ebp.bizsupport.service.SelfDeliveryUserService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.service.ServiceResult;

public class SelfDeliverLoginServiceImplTest extends BizTestSupport{
	@Autowired
    private SelfDeliveryLoginService selfDeliveryLoginService;
	@Autowired
    private SelfDeliveryUserService selfDeliveryUserService;
	
	@Autowired
    private TradeService tradeService;
	
	@Test
	public void loginSubmit() throws ServiceException{
		LoginDto loginDto = new LoginDto();
		loginDto.setAccount("admin");
		loginDto.setRandom("1473041953497");
		loginDto.setSign("7c3f9c51df4773a936852eae4bab09aa");
		// 验证用户密码是否正确
		AdminAgent loginResult = selfDeliveryLoginService.login(loginDto.getAccount(),loginDto.getSign(),loginDto.getRandom());
        System.out.println("--------------------"+loginResult.toString());
	}
	@Test
	public void getId(){
		long i = 1L;
		SelfDeliveryUser user = selfDeliveryUserService.getById(i);
        System.out.println("*****************"+user.toString());
	}
	
	@Test
	public void getIds(){
		long i = 1L;
		Trade user = tradeService.load(i);
        System.out.println("&&&&&&&&&&&&&&"+user.toString());
	}
	
	@Test
	public void getList(){
		SelfDeliveryUserQuery query = new SelfDeliveryUserQuery();
		query.setPageNo(1);
		query.setPageSize(10);
		query.setUserName("admin");
		PageResult<SelfDeliveryUser> list = selfDeliveryUserService.getListPage(query);
		System.out.println(list);
	}
}
