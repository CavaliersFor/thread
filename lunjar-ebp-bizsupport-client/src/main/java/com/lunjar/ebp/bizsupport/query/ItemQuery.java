package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ItemQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14748397789381L;

	private Long[] idArray;// 主键id
	private Long parentId;// 父类id
	private String name;// 类目名称
	private Integer level;// 类目等级
	private Integer status;// 状态
	private Integer[] statusArray;// 状态
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间

	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 父类id */
	public Long getParentId() {
		return parentId;
	}

	/*** 父类id */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/*** 类目名称 */
	public String getName() {
		return name;
	}

	/*** 类目名称 */
	public void setName(String name) {
		this.name = name;
	}

	/*** 类目等级 */
	public Integer getLevel() {
		return level;
	}

	/*** 类目等级 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/*** 状态 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 创建时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 创建时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 创建时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 创建时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}
}
