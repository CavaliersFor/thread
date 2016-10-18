package com.lunjar.ebp.admin.domain.column;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色表字段
 */
public enum AdminRoleColumns {
	/***/
	ID("id", "ID"),
	/** 1、正常 -1、已删除 */
	STATUS("status", "STATUS"),
	/***/
	GMT_CREATE("gmtCreate", "GMT_CREATE"),	
	/***/
	GMT_MODIFY("gmtModify", "GMT_MODIFY"),	
	/** 角色名称 */
	ROLE_NAME("roleName", "ROLE_NAME"),
	/** 角色描述 */
	DESCRIPTION("description", "DESCRIPTION"), ;

	private String property;
	private String column;
	private String alias;

	private static Map<String, AdminRoleColumns> pool = new HashMap<String, AdminRoleColumns>();

	static {
		for (AdminRoleColumns each : AdminRoleColumns.values()) {
			AdminRoleColumns defined = pool.get(each.getProperty());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString()
						+ " defined as same code with " + each.toString());
			}
			pool.put(each.getProperty(), each);
		}
	}

	private AdminRoleColumns(String property, String column) {
		this.property = property;
		this.column = "ar." + column;
		this.alias = this.column + " as ADMIN_ROLE_" + column;
	}

	/** 表别名.列名 */
	public String getColumn() {
		return column;
	}

	public String getProperty() {
		return property;
	}

	/** 表别名.列名 as 表名_列名 */
	public String getAlias() {
		return alias;
	}

	public static AdminRoleColumns valueOfProperty(String property) {
		return pool.get(property);
	}
}