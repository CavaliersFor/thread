package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Seller implements Serializable {
	private static final long serialVersionUID = 14690818444532L;

	private Long id;// 主键
	private Date gmtCreate;// 注册时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销
	private String registerName;// 用户名(手机号码)
	private String mobile;// 电话号码
	private Integer loginCount;// 登录总次数
	private Integer loginErrorCount;// 登录错误次数
	private Date lastLoginTime;// 最近一次登录时间
	private Date lastFreezeTime;// 最近一次冻结时间
	private Date lastLockTime;// 最近一次被锁时间
	private Date destroyTime;// 注销时间
	private Integer userType;// 用户类型 1个人 2企业 默认为1
	private Integer checkStatus;// 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化

	public Seller() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Seller(Long id) {
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

	/** 注册时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 注册时间 */
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

	/** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public Integer getStatus() {
		return status;
	}

	/** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 用户名(手机号码) */
	public String getRegisterName() {
		return registerName;
	}

	/** 用户名(手机号码) */
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	/** 电话号码 */
	public String getMobile() {
		return mobile;
	}

	/** 电话号码 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 登录总次数 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/** 登录总次数 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/** 登录错误次数 */
	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	/** 登录错误次数 */
	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	/** 最近一次登录时间 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/** 最近一次登录时间 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/** 最近一次冻结时间 */
	public Date getLastFreezeTime() {
		return lastFreezeTime;
	}

	/** 最近一次冻结时间 */
	public void setLastFreezeTime(Date lastFreezeTime) {
		this.lastFreezeTime = lastFreezeTime;
	}

	/** 最近一次被锁时间 */
	public Date getLastLockTime() {
		return lastLockTime;
	}

	/** 最近一次被锁时间 */
	public void setLastLockTime(Date lastLockTime) {
		this.lastLockTime = lastLockTime;
	}

	/** 注销时间 */
	public Date getDestroyTime() {
		return destroyTime;
	}

	/** 注销时间 */
	public void setDestroyTime(Date destroyTime) {
		this.destroyTime = destroyTime;
	}

	/** 用户类型 1个人 2企业 默认为1 */
	public Integer getUserType() {
		return userType;
	}

	/** 用户类型 1个人 2企业 默认为1 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/** 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化 */
	public Integer getCheckStatus() {
		return checkStatus;
	}

	/** 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return "Seller [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", registerName=" + registerName + ", mobile=" + mobile + ", loginCount=" + loginCount
				+ ", loginErrorCount=" + loginErrorCount + ", lastLoginTime=" + lastLoginTime + ", lastFreezeTime="
				+ lastFreezeTime + ", lastLockTime=" + lastLockTime + ", destroyTime=" + destroyTime + ", userType="
				+ userType + ", checkStatus=" + checkStatus + "]";
	}
}
