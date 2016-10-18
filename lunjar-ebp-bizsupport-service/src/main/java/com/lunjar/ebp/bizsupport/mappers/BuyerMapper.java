package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.query.BuyerQuery;

public interface BuyerMapper {
	/***/
	Buyer load(Long id);

	/***/
	void insert(Buyer buyer);

	/***/
	int update(Buyer buyer);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Buyer> queryList(BuyerQuery buyerQuery);

	/***/
	int queryCount(BuyerQuery buyerQuery);

}