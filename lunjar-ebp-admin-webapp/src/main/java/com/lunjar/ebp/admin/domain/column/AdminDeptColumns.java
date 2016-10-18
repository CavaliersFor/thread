package com.lunjar.ebp.admin.domain.column;

import java.util.HashMap;
import java.util.Map;

public enum AdminDeptColumns {
	/***/
	ID("id", "ID"),
	/** 上级部门名称的完整路径,以'/'记录树节点的dept_name路径 如：科技事业部/电子商务产品一部 */
	PARENT_NAME_PATH("parentNamePath", "PARENT_NAME_PATH"),
	/** 上级部门ID */
	PARENT_ID("parentId", "PARENT_ID"),
	/***/
	GMT_CREATE("gmtCreate", "GMT_CREATE"),
	/** 是否系统预设部门 预设部门不能删除 ‘1’ 是 ‘0’ 否 */
	IS_SYSTEM("isSystem", "IS_SYSTEM"),
	/** 部门简称 */
	SHORT_NAME("shortName", "SHORT_NAME"),
	/***/
	TEL("tel", "TEL"),
	/** 备用 */
	DEPT_TYPE("deptType", "DEPT_TYPE"),
	/** 级别 比如 省级 1，市级 2，区县级 3 */
	DEPT_LEVEL("deptLevel", "DEPT_LEVEL"),
	/** 排序 */
	SORT_NUM("sortNum", "SORT_NUM"),
	/***/
	MODIFIER("modifier", "MODIFIER"),
	/***/
	FAX("fax", "FAX"),
	/** 上级部门id完整路径 以'/'记录树节点的id路径 格式 1/2/5 */
	PARENT_ID_PATH("parentIdPath", "PARENT_ID_PATH"),
	/***/
	CREATOR("creator", "CREATOR"),
	/** 部门名称 */
	DEPT_NAME("deptName", "DEPT_NAME"),
	/** 地址 */
	ADDRESS("address", "ADDRESS"),
	/***/
	GMT_MODIFY("gmtModify", "GMT_MODIFY"),
	/** 1、正常 9、已删除 */
	STATUS("status", "STATUS"), ;

	private String property;
	private String column;
	private String alias;

	private static Map<String, AdminDeptColumns> pool = new HashMap<String, AdminDeptColumns>();

	static {
		for (AdminDeptColumns each : AdminDeptColumns.values()) {
			AdminDeptColumns defined = pool.get(each.getProperty());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getProperty(), each);
		}
	}

	private AdminDeptColumns(String property, String column) {
		this.property = property;
		this.column = "adminDept." + column;
		this.alias = this.column + " as ADMIN_DEPT_" + column;
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

	public static AdminDeptColumns valueOfProperty(String property) {
		return pool.get(property);
	}
}