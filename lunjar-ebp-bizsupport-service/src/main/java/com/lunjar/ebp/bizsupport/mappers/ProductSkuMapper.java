package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.query.ProductSkuQuery;

public interface ProductSkuMapper {
	/***/
	ProductSku load(Long id);

	/***/
	void insert(ProductSku productSku);

	/***/
	int update(ProductSku productSku);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<ProductSku> queryList(ProductSkuQuery productSkuQuery);

	/***/
	int queryCount(ProductSkuQuery productSkuQuery);
	/**
	 * 通过条件删除
	 * @param productSku
	 */
	void deleteByCondition(ProductSku productSku);
}