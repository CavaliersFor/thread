package com.lunjar.ebp.admin.biz.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.admin.domain.model.AdminRole;
import com.lunjar.ebp.admin.domain.query.AdminRoleQuery;

public interface AdminRoleMapper {
	/***/
	AdminRole load(Integer id);

	/***/
	void insert(AdminRole adminRole);

	/***/
	void update(AdminRole adminRole);

	/***/
	void updateStatus(@Param("id") Integer id, @Param("status") Serializable status);

	/***/
	void delete(Integer id);

	/***/
	List<AdminRole> queryList(AdminRoleQuery adminRoleQuery);

	/***/
	int queryCount(AdminRoleQuery adminRoleQuery);

	List<AdminRole> getOfRoles(long userId);

	List<AdminRole> getNotOfRoles(long userId);
}