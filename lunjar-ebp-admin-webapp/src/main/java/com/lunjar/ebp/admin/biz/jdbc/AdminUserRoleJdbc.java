package com.lunjar.ebp.admin.biz.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserRoleJdbc {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Integer[] getUserRoleIds(long userId) {
		String sql = "select ROLE_ID from ADMIN_USER_ROLE where USER_ID=?";

		final List<Integer> roleIds = new ArrayList<Integer>();

		jdbcTemplate.query(sql, new Object[] { userId }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				roleIds.add(rs.getInt("ROLE_ID"));
			}
		});

		return roleIds.toArray(new Integer[0]);
	}

	public void userRoleSave(Long userId, Integer[] roleIds) {
		if (userId != null) {
			jdbcTemplate.update("delete from ADMIN_USER_ROLE where USER_ID=?", new Object[] { userId });

			String sql = "insert into ADMIN_USER_ROLE(USER_ID,ROLE_ID) values(?,?)";
			if (roleIds != null) {
				for (Integer roleId : roleIds) {
					if (roleId != null) {
						jdbcTemplate.update(sql, new Object[] { userId, roleId });
					}
				}
			}
		}
	}

	public Set<String> getUserFunctions(long userId) {
		String sql = "select a.FUNCTION_ID from ADMIN_ROLE_FUNCTION a where exists (select 1 from ADMIN_USER_ROLE b where a.ROLE_ID=b.ROLE_ID and b.USER_ID=?)";
		final Set<String> set = new HashSet<String>(128);
		jdbcTemplate.query(sql, new Object[] { userId }, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				set.add(rs.getString("FUNCTION_ID"));
			}
		});
		return set;
	}
}
