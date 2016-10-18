package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.EnterpriseLogistics;
import com.lunjar.ebp.bizsupport.query.EnterpriseLogisticsQuery;

public interface EnterpriseLogisticsMapper  {
	/***/
	EnterpriseLogistics load(Long id);

	/***/
	void insert(EnterpriseLogistics enterpriseLogistics);

	/***/
	void update(EnterpriseLogistics enterpriseLogistics);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<EnterpriseLogistics> queryList(EnterpriseLogisticsQuery enterpriseLogisticsQuery);

	/***/
	int queryCount(EnterpriseLogisticsQuery enterpriseLogisticsQuery);
}