package com.lunjar.ebp.admin.domain.column;

import java.util.HashMap;
import java.util.Map;

public enum AdminUserColumns {
	/***/
	ID("id", "ID"),
	/***/
	POSTCODE("postcode", "POSTCODE"),
	/***/
	MOBILE("mobile", "MOBILE"),
	/***/
	IS_SUPER_ADMIN("isSuperAdmin", "IS_SUPER_ADMIN"),
	/***/
	GMT_CREATE("gmtCreate", "GMT_CREATE"),
	/***/
	ID_CARD("idCard", "ID_CARD"),
	/***/
	USER_NAME("userName", "USER_NAME"),
	/***/
	ACCOUNT("account", "ACCOUNT"),
	/***/
	EMAIL("email", "EMAIL"),
	/***/
	WORK_NO("workNo", "WORK_NO"),
	/***/
	DEPT_ID("deptId", "DEPT_ID"),
	/***/
	REMARK("remark", "REMARK"),
	/***/
	BIRTHDAY("birthday", "BIRTHDAY"),
	/***/
	MODIFIER("modifier", "MODIFIER"),
	/***/
	LAST_LOGIN_TIME("lastLoginTime", "LAST_LOGIN_TIME"),
	/***/
	PWD("pwd", "PWD"),
	/***/
	SEX("sex", "SEX"),
	/***/
	CREATOR("creator", "CREATOR"),
	/***/
	ADDRESS("address", "ADDRESS"),
	/***/
	PHONE("phone", "PHONE"),
	/***/
	LOGIN_COUNT("loginCount", "LOGIN_COUNT"),
	/***/
	GMT_MODIFY("gmtModify", "GMT_MODIFY"),
	/***/
	STATUS("status", "STATUS"), ;

	private String property;
	private String column;
	private String alias;

	private static Map<String, AdminUserColumns> pool = new HashMap<String, AdminUserColumns>();

	static {
		for (AdminUserColumns each : AdminUserColumns.values()) {
			AdminUserColumns defined = pool.get(each.getProperty());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getProperty(), each);
		}
	}

	private AdminUserColumns(String property, String column) {
		this.property = property;
		this.column = "adminUser." + column;
		this.alias = this.column + " as ADMIN_USER_" + column;
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

	public static AdminUserColumns valueOfProperty(String property) {
		return pool.get(property);
	}
}