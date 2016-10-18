package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;

public interface CombinationProductMapper {
	/***/
	CombinationProduct load(Long id);

	/***/
	void insert(CombinationProduct combinationProduct);

	/***/
	int update(CombinationProduct combinationProduct);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<CombinationProduct> queryList(CombinationProductQuery combinationProductQuery);

	/***/
	int queryCount(CombinationProductQuery combinationProductQuery);

	/**
	 * 通过商品id查询组合商品
	 * 
	 * @param combinationProductQuery
	 * @return
	 */
	List<CombinationProduct> queryByProductId(CombinationProductQuery combinationProductQuery);
	
	/**
	 * 更新不进行非空判断
	 * @param combinationProduct
	 */
	public void updateNoDecide(CombinationProduct combinationProduct);
	
	
}