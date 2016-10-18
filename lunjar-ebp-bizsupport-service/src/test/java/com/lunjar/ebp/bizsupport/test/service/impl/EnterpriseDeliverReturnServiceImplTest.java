package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.model.EnterpriseDeliverReturn;
import com.lunjar.ebp.bizsupport.query.EnterpriseDeliverReturnQuery;
import com.lunjar.ebp.bizsupport.service.EnterpriseDeliverReturnService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class EnterpriseDeliverReturnServiceImplTest extends BizTestSupport {

	@Autowired
	private EnterpriseDeliverReturnService enterpriseDeliverReturnService;

	@Test
	public void add() throws ServiceException {
		EnterpriseDeliverReturn t = new EnterpriseDeliverReturn();
		t.setEnterpriseId(1234567L);// 商家id
		t.setGmtCreate(new Date());// 创建时间
		t.setStatus(1);// 状态1：使用2：停用
		t.setAddress("广州市");// 退货详细地址
		t.setProvince("广东省");// 省
		t.setCity("广州市");// 市
		t.setRegion("白云区");// 区县
		t.setType(1);// 类型1：发货地址 2：退货地址
		t.setIsDefault(1);// 是否默认地址1:是 2： 否
		t.setLinkname("赵六");// 收件人
		t.setTelephone("123131231");// 联系电话
		System.out.println("添加纪录id=" + enterpriseDeliverReturnService.add(t));
	}

	/**
	 * 通过id查询
	 */
	@Test
	public void load() {
		System.out.println(enterpriseDeliverReturnService.load(1L));
	}

	@Test
	public void update() {
		EnterpriseDeliverReturn t = new EnterpriseDeliverReturn();
		t.setId(1L);
		t.setAddress("谢谢酷酷酷酷酷酷酷酷酷酷");
		int num = enterpriseDeliverReturnService.update(t);
		System.out.println("更新记录数:" + num);
	}

	@Test
	public void queryList() {
		EnterpriseDeliverReturnQuery q = new EnterpriseDeliverReturnQuery();
		q.setIdArray(1L);
		List<EnterpriseDeliverReturn> list = enterpriseDeliverReturnService.queryList(q);
		int count = enterpriseDeliverReturnService.queryCount(q);
		System.out.println("记录数:" + count);
		if (list != null && list.size() > 0) {
			for (EnterpriseDeliverReturn e : list) {
				System.out.println(e);
			}
		}
	}

	@Test
	public void deleteByIdAndEnterpriseId() {
		EnterpriseDeliverReturn q = new EnterpriseDeliverReturn();
		q.setId(1L);
		q.setEnterpriseId(1234567L);
		int num = enterpriseDeliverReturnService.deleteByIdAndEnterpriseId(q);
		System.out.println("删除记录数:" + num);
	}

	@Test
	public void delete() {
		enterpriseDeliverReturnService.delete(2L);

	}
}
