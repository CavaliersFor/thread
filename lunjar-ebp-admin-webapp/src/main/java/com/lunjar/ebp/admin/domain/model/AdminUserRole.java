package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;

import com.lunjar.products.core.model.TableEntity;

/**
 * 管理人员角色对应表
 */
public class AdminUserRole implements Serializable, TableEntity {
	private static final long serialVersionUID = 13792466321042L;

	private Long userId;//管理人员Id		
	private Integer roleId;//角色Id	

	public AdminUserRole() {
	}

	/**
	 * 
	 * @param userId
	 *            -- 管理人员Id
	 * @param roleId
	 *            -- 角色Id
	 */
	public AdminUserRole(Long userId, Integer roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	/** 管理人员Id */
	public Long getUserId() {
		return userId;
	}

	/** 管理人员Id */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/** 角色Id */
	public Integer getRoleId() {
		return roleId;
	}

	/** 角色Id */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "AdminUserRole [ userId=" + userId + ", roleId=" + roleId + "]";
	}
}
