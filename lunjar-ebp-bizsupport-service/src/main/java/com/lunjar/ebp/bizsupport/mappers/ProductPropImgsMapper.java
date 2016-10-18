package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.query.ProductPropImgsQuery;

public interface ProductPropImgsMapper {
	/***/
	ProductPropImgs load(Long id);

	/***/
	void insert(ProductPropImgs productPropImgs);

	/***/
	void update(ProductPropImgs productPropImgs);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<ProductPropImgs> queryList(ProductPropImgsQuery productPropImgsQuery);

	/***/
	int queryCount(ProductPropImgsQuery productPropImgsQuery);
	
	/**
	 * 通过条件删除
	 * @param productPropImgs
	 */
	public void deleteByCondition(ProductPropImgs productPropImgs);
}