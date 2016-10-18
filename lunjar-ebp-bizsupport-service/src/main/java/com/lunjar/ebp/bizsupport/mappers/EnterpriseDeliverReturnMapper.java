package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.EnterpriseDeliverReturn;
import com.lunjar.ebp.bizsupport.query.EnterpriseDeliverReturnQuery;

public interface EnterpriseDeliverReturnMapper {
	/***/
	EnterpriseDeliverReturn load(Long id);

	/***/
	void insert(EnterpriseDeliverReturn enterpriseDeliverReturn);

	/***/
	int update(EnterpriseDeliverReturn enterpriseDeliverReturn);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<EnterpriseDeliverReturn> queryList(EnterpriseDeliverReturnQuery enterpriseDeliverReturnQuery);

	/***/
	int queryCount(EnterpriseDeliverReturnQuery enterpriseDeliverReturnQuery);

	int deleteByIdAndEnterpriseId(EnterpriseDeliverReturn enterpriseDeliverReturn);
}