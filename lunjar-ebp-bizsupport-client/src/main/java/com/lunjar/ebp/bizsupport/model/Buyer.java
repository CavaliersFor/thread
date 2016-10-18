package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Buyer implements Serializable {
	private static final long serialVersionUID = 14697520850172L;

	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：正常 2：停止
	private Long partnerId;// 所属合作者平台id
	private String buyerId;// 所属合作者平台用户唯一标识
	private String nickname;// 用户昵称
	private String phone;// 手机号码

	public Buyer() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Buyer(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return id;
	}

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 创建时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 创建时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 修改时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 修改时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 状态1：正常 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常 2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 所属合作者平台id */
	public Long getPartnerId() {
		return partnerId;
	}

	/** 所属合作者平台id */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/** 所属合作者平台用户唯一标识 */
	public String getBuyerId() {
		return buyerId;
	}

	/** 所属合作者平台用户唯一标识 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	/** 用户昵称 */
	public String getNickname() {
		return nickname;
	}

	/** 用户昵称 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** 手机号码 */
	public String getPhone() {
		return phone;
	}

	/** 手机号码 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Buyer [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", partnerId=" + partnerId + ", buyerId=" + buyerId + ", nickname=" + nickname + ", phone=" + phone
				+ "]";
	}
}
