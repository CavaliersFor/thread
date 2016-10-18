package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;

public interface BuyerAddressMapper {
	/***/
	BuyerAddress load(Long id);

	/***/
	void insert(BuyerAddress buyerAddress);

	/***/
	int update(BuyerAddress buyerAddress);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<BuyerAddress> queryList(BuyerAddressQuery buyerAddressQuery);

	/***/
	int queryCount(BuyerAddressQuery buyerAddressQuery);

	/****
	 * 通过id和buyerId删除
	 * 
	 * @param buyerAddress
	 */
	int deleteByIdAndBuyerId(BuyerAddress buyerAddress);
}