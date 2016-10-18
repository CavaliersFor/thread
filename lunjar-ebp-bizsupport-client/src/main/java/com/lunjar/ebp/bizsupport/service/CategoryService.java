package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.CategoryDto;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;

public interface CategoryService extends CommonService<Category, CategoryQuery>{
	
	List<CategoryDto> getCategoryList(CategoryQuery query);

	void delete(Long id, Long shopId);
}
