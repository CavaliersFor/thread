package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.query.OutTradeNoQuery;

public interface OutTradeNoMapper {
	/***/
	OutTradeNo load(String id);

	/***/
	void insert(OutTradeNo outTradeNo);

	/***/
	void update(OutTradeNo outTradeNo);

	/***/
	void updateStatus(@Param("id") String id, @Param("status") Serializable status);

	/***/
	void delete(String id);

	/***/
	List<OutTradeNo> queryList(OutTradeNoQuery outTradeNoQuery);

	/***/
	int queryCount(OutTradeNoQuery outTradeNoQuery);
}