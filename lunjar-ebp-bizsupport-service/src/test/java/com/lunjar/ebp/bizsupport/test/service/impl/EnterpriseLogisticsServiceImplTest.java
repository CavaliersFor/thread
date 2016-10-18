package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.EnterpriseLogistics;
import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.query.EnterpriseLogisticsQuery;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseLogisticsService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class EnterpriseLogisticsServiceImplTest extends BizTestSupport {
	@Autowired
	private EnterpriseLogisticsService enterpriseLogisticsService;

	@Test
	public void add() throws ServiceException {
		EnterpriseLogistics enterpriseLogistics = new EnterpriseLogistics();
		enterpriseLogistics.setEnterpriseId(1l);
		enterpriseLogistics.setLogisticsId(1l);
		enterpriseLogisticsService.add(enterpriseLogistics);
	}

	@Test
	public void load() {
		EnterpriseLogistics enterpriseLogistics = enterpriseLogisticsService.load(1L);
		System.out.println(enterpriseLogistics);
	}

	@Test
	public void update() {
		EnterpriseLogistics enterpriseLogistics = new EnterpriseLogistics();
		enterpriseLogistics.setId(1l);
		enterpriseLogistics.setStatus(1);
		enterpriseLogisticsService.update(enterpriseLogistics);
		System.out.println("更新结果:" + enterpriseLogisticsService.load(1L));
	}

	@Test
	public void queryList() {
		EnterpriseLogisticsQuery query = new EnterpriseLogisticsQuery();
		query.setEnterpriseId(1l);
		query.setStatus(2);
		List<EnterpriseLogistics> list = enterpriseLogisticsService.queryList(query);
		for (EnterpriseLogistics c : list) {
			System.out.println(c.getLogisticsName());
		}
	}
	
	@Test
	public void queryListByEnterpriseId() {
		List<EnterpriseLogistics> list = enterpriseLogisticsService.queryListByEnterpriseId(1l);
		for (EnterpriseLogistics c : list) {
			System.out.println(c.getLogisticsCode() + "," + c.getLogisticsName());
		}
	}
}
