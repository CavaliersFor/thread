package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;

public interface DiscountMapper {
	/***/
	Discount load(Long id);

	/***/
	void insert(Discount discount);

	/***/
	int update(Discount discount);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Discount> queryList(DiscountQuery discountQuery);

	/***/
	int queryCount(DiscountQuery discountQuery);

	int deleteByIdAndEnterpriseId(Discount discount);
}