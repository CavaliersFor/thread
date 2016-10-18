package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class BuyerQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14697520850171L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：正常 2：停止
	private Integer[] statusArray;// 状态1：正常 2：停止
	private Long partnerId;// 所属合作者平台id
	private String buyerId;// 所属合作者平台用户唯一标识
	private String nickname;// 用户昵称
	private String phone;// 手机号码

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

	/*** 状态1：正常 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常 2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常 2：停止 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常 2：停止 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 所属合作者平台id */
	public Long getPartnerId() {
		return partnerId;
	}

	/*** 所属合作者平台id */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/*** 所属合作者平台用户唯一标识 */
	public String getBuyerId() {
		return buyerId;
	}

	/*** 所属合作者平台用户唯一标识 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	/*** 用户昵称 */
	public String getNickname() {
		return nickname;
	}

	/*** 用户昵称 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/*** 手机号码 */
	public String getPhone() {
		return phone;
	}

	/*** 手机号码 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
