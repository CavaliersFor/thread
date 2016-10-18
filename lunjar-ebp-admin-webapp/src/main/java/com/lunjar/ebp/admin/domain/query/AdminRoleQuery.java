package com.lunjar.ebp.admin.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;
import com.lunjar.products.core.query.QueryParam;

/**
 * 角色表查询对象
 */
public class AdminRoleQuery extends AbstractQueryParam implements QueryParam, Serializable {
	private static final long serialVersionUID = 13970975924901L;

	private Integer[] idArray;//
	private Integer status;//1、正常 -1、已删除
	private Integer[] statusArray;//1、正常 -1、已删除
	private Date gmtCreateFrom;//
	private Date gmtCreateTo;//
	private String creator;//
	private Date gmtModifyFrom;//
	private Date gmtModifyTo;//
	private String modifier;//
	private String roleName;//角色名称
	private String description;//角色描述

	/****/
	public Integer[] getIdArray() {
		return idArray;
	}

	/****/
	public void setIdArray(Integer... idArray) {
		this.idArray = idArray;
	}

	/*** 1、正常 -1、已删除 */
	public Integer getStatus() {
		return status;
	}

	/*** 1、正常 -1、已删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 1、正常 -1、已删除 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 1、正常 -1、已删除 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/****/
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/****/
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/****/
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/****/
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/****/
	public String getCreator() {
		return creator;
	}

	/****/
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/****/
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/****/
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/****/
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/****/
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/****/
	public String getModifier() {
		return modifier;
	}

	/****/
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/*** 角色名称 */
	public String getRoleName() {
		return roleName;
	}

	/*** 角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/*** 角色描述 */
	public String getDescription() {
		return description;
	}

	/*** 角色描述 */
	public void setDescription(String description) {
		this.description = description;
	}
}
