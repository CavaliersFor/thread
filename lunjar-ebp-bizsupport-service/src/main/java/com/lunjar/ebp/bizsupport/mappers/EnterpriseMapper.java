package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.query.EnterpriseQuery;

public interface EnterpriseMapper {
	/***/
	Enterprise load(Long id);

	/***/
	void insert(Enterprise enterprise);

	/***/
	int update(Enterprise enterprise);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Enterprise> queryList(EnterpriseQuery enterpriseQuery);

	/***/
	int queryCount(EnterpriseQuery enterpriseQuery);
}