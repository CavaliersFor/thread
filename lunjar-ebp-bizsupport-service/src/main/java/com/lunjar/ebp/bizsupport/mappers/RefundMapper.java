package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Refund;
import com.lunjar.ebp.bizsupport.query.RefundQuery;

public interface RefundMapper {
	/***/
	Refund load(Long id);

	/***/
	void insert(Refund refund);

	/***/
	int update(Refund refund);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Refund> queryList(RefundQuery refundQuery);

	/***/
	int queryCount(RefundQuery refundQuery);
}