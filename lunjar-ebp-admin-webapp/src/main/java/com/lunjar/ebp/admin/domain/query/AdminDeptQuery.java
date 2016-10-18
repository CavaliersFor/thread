package com.lunjar.ebp.admin.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;
import com.lunjar.products.core.query.QueryParam;

/**
 * 部门表
 */
public class AdminDeptQuery extends AbstractQueryParam implements QueryParam, Serializable {
	private static final long serialVersionUID = 13808750852201L;

	private Integer[] idArray;//
	private String parentNamePath;//上级部门名称的完整路径,以'/'记录树节点的dept_name路径   如：科技事业部/电子商务产品一部
	private Integer parentId;//上级部门ID
	private Integer[] parentIdArray;//上级部门ID
	private Date gmtCreateFrom;//
	private Date gmtCreateTo;//
	private String isSystem;//是否系统预设部门  预设部门不能删除   ‘1’ 是  ‘0’  否
	private String shortName;//部门简称
	private String tel;//
	private String deptType;//备用
	private Integer deptLevel;//级别 比如 省级 1，市级 2，区县级 3
	private Integer sortNum;//排序
	private String modifier;//
	private String fax;//
	private String parentIdPath;//上级部门id完整路径   以'/'记录树节点的id路径   格式 1/2/5
	private String creator;//
	private String deptName;//部门名称
	private String address;//地址
	private Date gmtModifyFrom;//
	private Date gmtModifyTo;//
	private Integer status;//1、正常 -1、已删除
	private Integer[] statusArray;//1、正常 -1、已删除

	/****/
	public Integer[] getIdArray() {
		return idArray;
	}

	/****/
	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}

	/*** 上级部门名称的完整路径,以'/'记录树节点的dept_name路径 如：科技事业部/电子商务产品一部 */
	public String getParentNamePath() {
		return parentNamePath;
	}

	/*** 上级部门名称的完整路径,以'/'记录树节点的dept_name路径 如：科技事业部/电子商务产品一部 */
	public void setParentNamePath(String parentNamePath) {
		this.parentNamePath = parentNamePath;
	}

	/*** 上级部门ID */
	public Integer getParentId() {
		return parentId;
	}

	/*** 上级部门ID */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	/*** 是否系统预设部门 预设部门不能删除 ‘1’ 是 ‘0’ 否 */
	public String getIsSystem() {
		return isSystem;
	}

	/*** 是否系统预设部门 预设部门不能删除 ‘1’ 是 ‘0’ 否 */
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	/*** 部门简称 */
	public String getShortName() {
		return shortName;
	}

	/*** 部门简称 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/****/
	public String getTel() {
		return tel;
	}

	/****/
	public void setTel(String tel) {
		this.tel = tel;
	}

	/*** 备用 */
	public String getDeptType() {
		return deptType;
	}

	/*** 备用 */
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	/*** 级别 比如 省级 1，市级 2，区县级 3 */
	public Integer getDeptLevel() {
		return deptLevel;
	}

	/*** 级别 比如 省级 1，市级 2，区县级 3 */
	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	/*** 排序 */
	public Integer getSortNum() {
		return sortNum;
	}

	/*** 排序 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/****/
	public String getModifier() {
		return modifier;
	}

	/****/
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/****/
	public String getFax() {
		return fax;
	}

	/****/
	public void setFax(String fax) {
		this.fax = fax;
	}

	/*** 上级部门id完整路径 以'/'记录树节点的id路径 格式 1/2/5 */
	public String getParentIdPath() {
		return parentIdPath;
	}

	/*** 上级部门id完整路径 以'/'记录树节点的id路径 格式 1/2/5 */
	public void setParentIdPath(String parentIdPath) {
		this.parentIdPath = parentIdPath;
	}

	/****/
	public String getCreator() {
		return creator;
	}

	/****/
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/*** 部门名称 */
	public String getDeptName() {
		return deptName;
	}

	/*** 部门名称 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/*** 地址 */
	public String getAddress() {
		return address;
	}

	/*** 地址 */
	public void setAddress(String address) {
		this.address = address;
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

	public Integer[] getParentIdArray() {
		return parentIdArray;
	}

	public void setParentIdArray(Integer[] parentIdArray) {
		this.parentIdArray = parentIdArray;
	}
}
