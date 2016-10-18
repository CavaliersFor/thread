package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;

import com.lunjar.products.core.model.TableEntity;

/**
 * 角色功能对应表
 */
public class AdminRoleFunction  implements Serializable, TableEntity {
	private static final long serialVersionUID = 13826787525582L;

	private Integer roleId;//角色id		
	private String functionId;//功能Id，对应functionResource.xml中的<menu>节的id		

	public AdminRoleFunction() {
	}

	/**
	 * 
	 * @param roleId
	 *            -- 角色id
	 * @param functionId
	 *            -- 功能Id，对应functionResource.xml中的<menu>节的id
	 */
	public AdminRoleFunction(Integer roleId, String functionId) {
		this.roleId = roleId;
		this.functionId = functionId;
	}

	/** 角色id */
	public Integer getRoleId() {
		return roleId;
	}

	/** 角色id */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/** 功能Id，对应functionResource.xml中的<menu>节的id */
	public String getFunctionId() {
		return functionId;
	}

	/** 功能Id，对应functionResource.xml中的<menu>节的id */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Override
	public String toString() {
		return "AdminRoleFunction [ roleId=" + roleId + ", functionId=" + functionId + "]";
	}
}
