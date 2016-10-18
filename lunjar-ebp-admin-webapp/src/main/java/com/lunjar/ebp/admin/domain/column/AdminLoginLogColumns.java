package com.lunjar.ebp.admin.domain.column;


import java.util.HashMap;
import java.util.Map;

/**
*字段
*/
public enum AdminLoginLogColumns {
			/***/
		ID("id", "ID"),			
			/**登录用户的ID*/
		LOGIN_ID("loginId", "LOGIN_ID"),			
			/**登录用户的账号*/
		LOGIN_ACCOUNT("loginAccount", "LOGIN_ACCOUNT"),			
			/**登录用户的IP*/
		LOGIN_IP("loginIp", "LOGIN_IP"),			
			/**登录时间*/
		LOGIN_TIME("loginTime", "LOGIN_TIME"),			
			/**后台用户的工号*/
		ADMIN_WORK_NO("adminWorkNo", "ADMIN_WORK_NO"),			
			/**后台用户的姓名*/
		ADMIN_NAME("adminName", "ADMIN_NAME"),			
		;

	private String property;
	private String column;
	private String alias;

	private static Map<String, AdminLoginLogColumns> pool = new HashMap<String, AdminLoginLogColumns>();

	static {
		for (AdminLoginLogColumns each : AdminLoginLogColumns.values()) {
			AdminLoginLogColumns defined = pool.get(each.getProperty());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getProperty(), each);
		}
	}
	private AdminLoginLogColumns(String property, String column) {		
		this.property = property;
		this.column = "sll." + column;
		this.alias = this.column + " as sys_log_login_" + column;
	}
	
	/**表别名.列名*/
	public String getColumn() {
		return column;
	}
	
	public String getProperty() {
		return property;
	}
	
	/**表别名.列名 as 表名_列名*/
	public String getAlias() {
		return alias;
	}		
	public static AdminLoginLogColumns valueOfProperty(String property) {
		return pool.get(property);
	}
}