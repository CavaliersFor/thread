package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;
import com.lunjar.ebp.bizsupport.service.LogisticsCompanyService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;

public class LogisticsCompanyServiceImplTest extends BizTestSupport {
	@Autowired
	private LogisticsCompanyService logisticsCompanyService;

//	@Test
//	public void add() throws ServiceException {
//		for (int i = 0; i < 20; i++) {
//			Category category = new Category();
//			category.setGmtCreate(new Date());// 创建时间
//			category.setStatus(1);// 状态1：使用中2：停止使用
//			category.setName("精选");// 类目名称
//			category.setShopId(111L);// 商铺id
//			category.setSortNum(1);// 排序(按此顺序)
//			category.setParentId(123L);// 父类目id
//			categoryService.add(category);
//		}
//	}

	@Test
	public void load() {
		LogisticsCompany logisticsCompany = logisticsCompanyService.load(1L);
		System.out.println(logisticsCompany);
	}

	@Test
	public void update() {
		LogisticsCompany logisticsCompany = new LogisticsCompany();
		logisticsCompany.setId(1l);
		logisticsCompany.setStatus(1);
		 logisticsCompanyService.update(logisticsCompany);
		System.out.println("更新结果:" + logisticsCompanyService.load(1L));
	}

	@Test
	public void queryList() {
		LogisticsCompanyQuery query = new LogisticsCompanyQuery();
		query.setStatus(2);
		List<LogisticsCompany> list = logisticsCompanyService.queryList(query);
		for (LogisticsCompany c : list) {
			System.out.println(c.getName());
		}
	}
	
	@Test
	public void getAllList() {
		List<LogisticsCompany> list = logisticsCompanyService.getAllList();
		for (LogisticsCompany c : list) {
			System.out.println(c.getName());
		}
	}
}
