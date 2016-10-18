package com.lunjar.ebp.admin.biz.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.admin.domain.model.AdminLoginLog;
import com.lunjar.ebp.admin.domain.query.AdminLoginLogQuery;



public interface AdminLoginLogMapper  {
	/***/
	AdminLoginLog load(Long id);

	/***/
	void insert(AdminLoginLog sysLogLogin);

	/***/
	void update(AdminLoginLog sysLogLogin);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<AdminLoginLog> queryList(AdminLoginLogQuery sysLogLoginQuery);

	/***/
	int queryCount(AdminLoginLogQuery sysLogLoginQuery);
}