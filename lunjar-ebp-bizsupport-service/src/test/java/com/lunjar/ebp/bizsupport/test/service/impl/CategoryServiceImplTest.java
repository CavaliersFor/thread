package com.lunjar.ebp.bizsupport.test.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lunjar.ebp.bizsupport.dto.CategoryDto;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.test.support.BizTestSupport;
import com.lunjar.products.core.exception.ServiceException;

public class CategoryServiceImplTest extends BizTestSupport {
	@Autowired
	private CategoryService categoryService;

	@Test
	public void add() throws ServiceException {
		for (int i = 0; i < 20; i++) {
			Category category = new Category();
			category.setGmtCreate(new Date());// 创建时间
			category.setStatus(1);// 状态1：使用中2：停止使用
			category.setName("精选");// 类目名称
			category.setShopId(111L);// 商铺id
			category.setSortNum(1);// 排序(按此顺序)
			category.setParentId(123L);// 父类目id
			categoryService.add(category);
		}
	}

	@Test
	public void load() {
		Category c = categoryService.load(1L);
		System.out.println(c);
	}

	@Test
	public void update() {
		Category category = new Category();
		category.setGmtCreate(new Date());// 创建时间
		category.setStatus(1);// 状态1：使用中2：停止使用
		category.setName("精选2222");// 类目名称
		category.setShopId(111L);// 商铺id
		category.setSortNum(1);// 排序(按此顺序)
		category.setParentId(123L);// 父类目id
		category.setId(1L);
		int num = categoryService.update(category);
		System.out.println("更新记录数:" + num);
	}

	@Test
	public void querListAndQueryConut() {
		CategoryQuery q = new CategoryQuery();
		q.setIdArray(1L);
		List<Category> list = categoryService.queryList(q);
		if (list != null && list.size() > 0) {
			for (Category c : list) {
				System.out.println(c);
			}
		}
		int num = categoryService.queryCount(q);
		System.out.println("查询条件的总数为:" + num);
	}

	@Test
	public void delete() {
		categoryService.delete(1L);
	}

	@Test
	public void queryList() {
		CategoryQuery query = new CategoryQuery();
		query.setShopId(1231l);
		List<Category> list = categoryService.queryList(query);
		for (Category c : list) {
			System.out.println(c.getName());
		}
	}
	
	@Test
	public void queryGategoryList() {
		CategoryQuery query = new CategoryQuery();
		query.setShopId(1l);
		List<CategoryDto> list = categoryService.getCategoryList(query);
		for (CategoryDto c : list) {
			System.out.println(c.getName());
		}
	}
}
