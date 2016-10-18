package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class PartnerQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14702761021311L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：接入中 2：停止
	private Integer[] statusArray;// 状态1：接入中 2：停止
	private String name;// 合作者平台名称 如：微信公众号，某某APP

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
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

	/*** 状态1：接入中 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：接入中 2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：接入中 2：停止 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：接入中 2：停止 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 合作者平台名称 如：微信公众号，某某APP */
	public String getName() {
		return name;
	}

	/*** 合作者平台名称 如：微信公众号，某某APP */
	public void setName(String name) {
		this.name = name;
	}
}
