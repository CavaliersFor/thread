package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表实体类
 */
public class AdminRole implements Serializable {
	private static final long serialVersionUID = 13970975924902L;

	private Integer id;//
	private Integer status = 1;// 1、正常 -1、已删除
	
	private Date gmtCreate;//
	private Date gmtModify;//
	private String roleName;// 角色名称
	private String description;// 角色描述

	public AdminRole() {
	}

	/**
	 * 
	 * @param id
	 *            --
	 */
	public AdminRole(Integer id) {
		this.id = id;
	}

	/***/
	public Integer getId() {
		return id;
	}

	/***/
	public void setId(Integer id) {
		this.id = id;
	}

	/** 1、正常 -1、已删除 */
	public Integer getStatus() {
		return status;
	}

	/** 1、正常 -1、已删除 */
	public void setStatus(Integer status) {
		this.status = status;
//		if (this.status != null) {
//			this.statusEnum = EnumRoleStatus.valueOf(this.status);
//		}
	}

	/***/
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/***/
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/***/
	public Date getGmtModify() {
		return gmtModify;
	}

	/***/
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 角色名称 */
	public String getRoleName() {
		return roleName;
	}

	/** 角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** 角色描述 */
	public String getDescription() {
		return description;
	}

	/** 角色描述 */
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "AdminRole [id=" + id + ", status=" + status + ",  gmtCreate=" + gmtCreate
				+ ", gmtModify=" + gmtModify + ", roleName=" + roleName + ", description=" + description + "]";
	}
}
