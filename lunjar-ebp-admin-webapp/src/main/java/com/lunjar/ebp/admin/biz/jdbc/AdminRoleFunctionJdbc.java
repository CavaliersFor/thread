package com.lunjar.ebp.admin.biz.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRoleFunctionJdbc {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String[] getFunctionIds(Integer roleId) {
		final List<String> ids = new ArrayList<String>(50);
		jdbcTemplate.query("select FUNCTION_ID from ADMIN_ROLE_FUNCTION where role_id=?", new Object[] { roleId },
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						ids.add(rs.getString("function_id"));
					}
				});
		return ids.toArray(new String[0]);
	}

	public void saveRoleFunctions(Integer roleId, String[] functionIds) {
		jdbcTemplate.update("delete from ADMIN_ROLE_FUNCTION where ROLE_ID=?", new Object[] { roleId });

		String sql = "insert into ADMIN_ROLE_FUNCTION(ROLE_ID,FUNCTION_ID) values(?,?)";
		if (functionIds != null) {
			for (String functionId : functionIds) {
				if (StringUtils.isNotBlank(functionId)) {
					jdbcTemplate.update(sql, new Object[] { roleId, functionId });
				}
			}
		}
	}
}
