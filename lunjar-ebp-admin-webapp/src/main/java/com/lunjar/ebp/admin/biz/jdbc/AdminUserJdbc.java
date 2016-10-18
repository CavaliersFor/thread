package com.lunjar.ebp.admin.biz.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lunjar.ebp.admin.domain.model.AdminUser;

/**
 * 管理人员表
 */
@Repository
public class AdminUserJdbc {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean checkAccount(AdminUser user) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(128);
		sql.append("select count(1) from ADMIN_USER where account=?");
		list.add(user.getAccount());
		if (user.getId() != null) {
			sql.append(" and id<>?");
			list.add(user.getId());
		}
		if (user.getStatus() != null) {
			sql.append(" and status<>?");
			list.add(user.getStatus());
		}
		long count = jdbcTemplate.queryForObject(sql.toString(), list.toArray(), java.lang.Long.class);
		return count == 0;
	}
	
	public boolean checkWorkNo(AdminUser user) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(128);
		sql.append("select count(1) from ADMIN_USER where work_No=?");
		list.add(user.getWorkNo());
		if (user.getId() != null) {
			sql.append(" and id<>?");
			list.add(user.getId());
		}
		if (user.getStatus() != null) {
			sql.append(" and status<>?");
			list.add(user.getStatus());
		}
		long count = jdbcTemplate.queryForObject(sql.toString(), list.toArray(), java.lang.Long.class);
		return count == 0;
	}
}