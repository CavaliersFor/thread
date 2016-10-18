package com.lunjar.ebp.bizsupport.test.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.enums.EnumPostProduct;
import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;
import com.lunjar.ebp.bizsupport.service.DiscountService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

public class DiscountServiceTest extends BizTestSupport {

	@Autowired
	private DiscountService discountService;

	/**
	 * 根据优惠id查询
	 * 
	 * @param id
	 * @return
	 */
	@Test
	public void load() {
		Discount discount = discountService.load(2L);
		System.out.println(discount);
	}

	/**
	 * 新增优惠信息
	 * 
	 * @param discount
	 * @return
	 * @throws ServiceException
	 */
	@Test
	public void add() throws ServiceException {
		Discount discount = new Discount();
		discount.setGmtCreate(new Date());
		;// 创建时间
		discount.setStatus(1);
		;// 状态1：使用中 2：停止使用
		discount.setConditions(new BigDecimal("211.12"));// 优惠条件
		discount.setDiscountFee(new BigDecimal("10.00"));// 减免金额(如果是类型1，则为0)
		discount.setEnterpriseId(123678L);// 商家id
		discount.setIsPost(EnumPostProduct.NOT_POST.getValue());
		long id = discountService.add(discount);
		System.out.println("添加的id是" + id);
	}

	/**
	 * 更新优惠信息
	 * 
	 * @param discount
	 */
	@Test
	public void update() {
		Discount discount = new Discount();
		discount.setId(2L);
		discount.setIsPost(EnumPostProduct.POST.getValue());
		int count = discountService.update(discount);
		System.out.println("更新行数:" + count);
	}

	/***/
	public void delete() {
		discountService.delete(1L);
	}

	/**
	 * 查询优惠信息
	 * 
	 * @param discountQuery
	 * @return
	 */
	@Test
	public void queryList() {
		DiscountQuery discountQuery = new DiscountQuery();
		discountQuery.setIdArray(1L);
		List<Discount> listDiscount = discountService.queryList(discountQuery);
		if (listDiscount != null && listDiscount.size() > 0) {
			for (Discount d : listDiscount) {
				System.out.println(d);
			}
		}
	}

	/**
	 * 根据条件获得总数
	 * 
	 * @param discountQuery
	 * @return
	 */
	@Test
	public void queryCount() {
		DiscountQuery discountQuery = new DiscountQuery();
		discountQuery.setIdArray(2L);
		int count = discountService.queryCount(discountQuery);
		System.out.println("满足条件数量:" + count);
	}

	/**
	 * 通过id和EnterpriseId(商家id)进行删除
	 * 
	 * @param discount
	 * @return
	 */
	@Test
	public void deleteByIdAndEnterpriseId() {
		Discount discount = new Discount();
		discount.setId(2L);
		discount.setEnterpriseId(123678L);
		int count = discountService.deleteByIdAndEnterpriseId(discount);
		System.out.println("删除记录数:" + count);
	}
	
	@Test
	public void  queryListByPage(){
		DiscountQuery query = new DiscountQuery();
		query.setSort("GMT_CREATE desc");
		query.setEnterpriseId(1L);
		query.setStatus(1);
		query.setPageSize(10);
		PageResult<Discount> data = discountService.queryListByPage(query);
	}
}
