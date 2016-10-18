package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class ExpressServiceImplTest extends BizTestSupport {

	@Autowired
	private ExpressService expressService;

	@Test
	public void add() throws ServiceException {
		Express p = new Express();
		p.setEnterpriseId(1231312L);// 商家id
		p.setEcPrice("中山,东莞,顺德:5,长沙;厦门:10");// 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式
		p.setGmtCreate(new Date());// 创建时间
		p.setEcStatus(1);// 状态1:正常2:停止
		p.setValuation(1);// 计费方式：1：数量2：重量3：体积
		System.out.println("保存的id=" + expressService.add(p));
	}

	@Test
	public void load() {
		System.out.println(expressService.load(2L));
	}

	@Test
	public void queryList() {
		ExpressQuery q = new ExpressQuery();
		q.setIdArray(1L);
		List<Express> list = expressService.queryList(q);
		if (list != null && list.size() > 0) {
			for (Express e : list) {
				System.out.println(e);
			}
		}
		int count = expressService.queryCount(q);
		System.out.println("查询记录数：" + count);
	}

	@Test
	public void deleteByIdAndEnterpriseId() {
		Express e = new Express();
		e.setId(1L);
		e.setEnterpriseId(1231312L);
		int num = expressService.deleteByIdAndEnterpriseId(e);
		System.out.println("删除记录数:" + num);
	}

	@Test
	public void update() {
		Express e = new Express();
		e.setId(2L);
		e.setEnterpriseId(12313112311L);
		int num = expressService.update(e);
		System.out.println("更新记录数" + num);
	}

	@Test
	public void delete() {
		expressService.delete(2L);
	}
}
