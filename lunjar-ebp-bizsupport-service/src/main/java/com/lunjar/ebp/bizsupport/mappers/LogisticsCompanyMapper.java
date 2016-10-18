package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;

public interface LogisticsCompanyMapper  {
	/***/
	LogisticsCompany load(Long id);

	/***/
	void insert(LogisticsCompany logisticsCompany);

	/***/
	void update(LogisticsCompany logisticsCompany);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<LogisticsCompany> queryList(LogisticsCompanyQuery logisticsCompanyQuery);

	/***/
	int queryCount(LogisticsCompanyQuery logisticsCompanyQuery);
}