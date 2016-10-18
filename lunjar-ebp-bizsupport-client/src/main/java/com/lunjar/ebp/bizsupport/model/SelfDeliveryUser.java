package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class SelfDeliveryUser implements Serializable {
	private static final long serialVersionUID = 14713727939472L;

	private Long id;// 主键id
	private String userName;// 账号
	private String passWord;// 密码
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：正常2：停用
	private Long collectPlaceId;// 所属自提点id

	public SelfDeliveryUser() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public SelfDeliveryUser(Long id) {
		this.id = id;
	}

	/** 主键id */
	public Long getId() {
		return id;
	}

	/** 主键id */
	public void setId(Long id) {
		this.id = id;
	}

	/** 账号 */
	public String getUserName() {
		return userName;
	}

	/** 账号 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 密码 */
	public String getPassWord() {
		return passWord;
	}

	/** 密码 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	/** 状态1：正常2：停用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 所属自提点id */
	public Long getCollectPlaceId() {
		return collectPlaceId;
	}

	/** 所属自提点id */
	public void setCollectPlaceId(Long collectPlaceId) {
		this.collectPlaceId = collectPlaceId;
	}

	@Override
	public String toString() {
		return "SelfDeliveryUser [ id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", gmtCreate="
				+ gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status + ", collectPlaceId=" + collectPlaceId
				+ "]";
	}
}
