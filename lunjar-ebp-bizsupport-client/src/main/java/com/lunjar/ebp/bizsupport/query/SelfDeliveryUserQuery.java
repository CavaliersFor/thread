package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class SelfDeliveryUserQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14713727939471L;

	private Long[] idArray;// 主键id
	private String userName;// 账号
	private String passWord;// 密码
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：正常2：停用
	private Integer[] statusArray;// 状态1：正常2：停用
	private Long collectPlaceId;// 所属自提点id

	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 账号 */
	public String getUserName() {
		return userName;
	}

	/*** 账号 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*** 密码 */
	public String getPassWord() {
		return passWord;
	}

	/*** 密码 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	/*** 状态1：正常2：停用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常2：停用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常2：停用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 所属自提点id */
	public Long getCollectPlaceId() {
		return collectPlaceId;
	}

	/*** 所属自提点id */
	public void setCollectPlaceId(Long collectPlaceId) {
		this.collectPlaceId = collectPlaceId;
	}
}
