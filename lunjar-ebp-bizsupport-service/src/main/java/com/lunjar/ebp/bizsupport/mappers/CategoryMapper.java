package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;

public interface CategoryMapper {
	/***/
	Category load(Long id);

	/***/
	void insert(Category category);

	/***/
	int update(Category category);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Category> queryList(CategoryQuery categoryQuery);

	/***/
	int queryCount(CategoryQuery categoryQuery);
}