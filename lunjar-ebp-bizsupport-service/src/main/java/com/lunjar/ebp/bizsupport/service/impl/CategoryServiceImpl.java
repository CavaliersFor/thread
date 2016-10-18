package com.lunjar.ebp.bizsupport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.lunjar.ebp.bizsupport.cache.CategoryListCache;
import com.lunjar.ebp.bizsupport.dto.CategoryDto;
import com.lunjar.ebp.bizsupport.mappers.CategoryMapper;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.model.CategoryList;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryListCache categoryListCache;

	@Value("${store.server.url}")
	private String storeUrl;
	
	@Override
	public Category load(Long id) {
		return categoryMapper.load(id);
	}

	@Override
	public Long add(Category t) {
		categoryMapper.insert(t);
		//删除缓存
		categoryListCache.remove(t.getShopId());
		return t.getId();
	}

	@Override
	public int update(Category t) {
		Assert.notNull(t, "category is null");
		Assert.notNull(t.getShopId(), "shop id is null");
		int n = categoryMapper.update(t);
		categoryListCache.remove(t.getShopId());
		return n;
	}

	@Override
	public void delete(Long id, Long shopId) {
		Assert.notNull(id, "id is null");
		Assert.notNull(shopId, "shop id is null");
		categoryMapper.delete(id);
		categoryListCache.remove(shopId);
	}

	@Override
	public List<CategoryDto> getCategoryList(CategoryQuery q) {
		List<CategoryDto> listDto = null;
		Assert.notNull(q.getShopId(), "shop id is null");
		List<Category> list = categoryMapper.queryList(q);
//		categoryListCache.get(q.getShopId());
//		if (list == null) {
//			q.setStatus(1);
//			list = categoryMapper.queryList(q);
//			categoryListCache.put(q.getShopId(), new CategoryList(list));
//		}
		if (CollectionUtils.isNotEmpty(list)) {
			listDto = new ArrayList<>();
			CategoryDto dto = null;
			for(Category c: list) {
				dto = new CategoryDto();
				dto.setGmtCreate(c.getGmtCreate());
				dto.setGmtModify(c.getGmtModify());
				dto.setId(c.getId());
				dto.setName(c.getName());
				dto.setParentId(c.getParentId());
				dto.setShopId(c.getShopId());
				dto.setSortNum(c.getSortNum());
				dto.setStatus(c.getStatus());
				if (StringUtils.isNotBlank(c.getSlidesUrls())) {
					dto.setList(GenerateUtil.createShopIndexList(c.getSlidesUrls(), storeUrl));
				}
				listDto.add(dto);
			}
		}
		return listDto;
	}

	@Override
	public int queryCount(CategoryQuery q) {
		return categoryMapper.queryCount(q);
	}

	@Override
	public List<Category> queryList(CategoryQuery q) {
		Assert.notNull(q.getShopId(), "shop id is null");
		List<Category> list = categoryMapper.queryList(q);
//				categoryListCache.get(q.getShopId());
//		if (list == null) {
//			q.setStatus(1);
//			list = categoryMapper.queryList(q);
//			categoryListCache.put(q.getShopId(), new CategoryList(list));
//		}
		return list;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		//不支持直接根据id删除
	}

}
