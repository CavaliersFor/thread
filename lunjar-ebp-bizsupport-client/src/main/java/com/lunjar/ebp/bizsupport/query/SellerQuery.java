package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class SellerQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14690818444531L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 注册时间
	private Date gmtCreateTo;// 注册时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销
	private Integer[] statusArray;// 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销
	private String registerName;// 用户名(手机号码)
	private String mobile;// 电话号码
	private Integer loginCount;// 登录总次数
	private Integer loginErrorCount;// 登录错误次数
	private Date lastLoginTimeFrom;// 最近一次登录时间
	private Date lastLoginTimeTo;// 最近一次登录时间
	private Date lastFreezeTimeFrom;// 最近一次冻结时间
	private Date lastFreezeTimeTo;// 最近一次冻结时间
	private Date lastLockTimeFrom;// 最近一次被锁时间
	private Date lastLockTimeTo;// 最近一次被锁时间
	private Date destroyTimeFrom;// 注销时间
	private Date destroyTimeTo;// 注销时间
	private Integer userType;// 用户类型 1个人 2企业 默认为1
	private Integer checkStatus;// 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 注册时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 注册时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 注册时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 注册时间 */
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

	/*** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态: 1 未激活 2 正常 3 锁定 4 冻结 5 注销 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 用户名(手机号码) */
	public String getRegisterName() {
		return registerName;
	}

	/*** 用户名(手机号码) */
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	/*** 电话号码 */
	public String getMobile() {
		return mobile;
	}

	/*** 电话号码 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/*** 登录总次数 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/*** 登录总次数 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/*** 登录错误次数 */
	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	/*** 登录错误次数 */
	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	/*** 最近一次登录时间 */
	public Date getLastLoginTimeFrom() {
		return lastLoginTimeFrom;
	}

	/*** 最近一次登录时间 */
	public void setLastLoginTimeFrom(Date lastLoginTimeFrom) {
		this.lastLoginTimeFrom = lastLoginTimeFrom;
	}

	/*** 最近一次登录时间 */
	public Date getLastLoginTimeTo() {
		return lastLoginTimeTo;
	}

	/*** 最近一次登录时间 */
	public void setLastLoginTimeTo(Date lastLoginTimeTo) {
		this.lastLoginTimeTo = lastLoginTimeTo;
	}

	/*** 最近一次冻结时间 */
	public Date getLastFreezeTimeFrom() {
		return lastFreezeTimeFrom;
	}

	/*** 最近一次冻结时间 */
	public void setLastFreezeTimeFrom(Date lastFreezeTimeFrom) {
		this.lastFreezeTimeFrom = lastFreezeTimeFrom;
	}

	/*** 最近一次冻结时间 */
	public Date getLastFreezeTimeTo() {
		return lastFreezeTimeTo;
	}

	/*** 最近一次冻结时间 */
	public void setLastFreezeTimeTo(Date lastFreezeTimeTo) {
		this.lastFreezeTimeTo = lastFreezeTimeTo;
	}

	/*** 最近一次被锁时间 */
	public Date getLastLockTimeFrom() {
		return lastLockTimeFrom;
	}

	/*** 最近一次被锁时间 */
	public void setLastLockTimeFrom(Date lastLockTimeFrom) {
		this.lastLockTimeFrom = lastLockTimeFrom;
	}

	/*** 最近一次被锁时间 */
	public Date getLastLockTimeTo() {
		return lastLockTimeTo;
	}

	/*** 最近一次被锁时间 */
	public void setLastLockTimeTo(Date lastLockTimeTo) {
		this.lastLockTimeTo = lastLockTimeTo;
	}

	/*** 注销时间 */
	public Date getDestroyTimeFrom() {
		return destroyTimeFrom;
	}

	/*** 注销时间 */
	public void setDestroyTimeFrom(Date destroyTimeFrom) {
		this.destroyTimeFrom = destroyTimeFrom;
	}

	/*** 注销时间 */
	public Date getDestroyTimeTo() {
		return destroyTimeTo;
	}

	/*** 注销时间 */
	public void setDestroyTimeTo(Date destroyTimeTo) {
		this.destroyTimeTo = destroyTimeTo;
	}

	/*** 用户类型 1个人 2企业 默认为1 */
	public Integer getUserType() {
		return userType;
	}

	/*** 用户类型 1个人 2企业 默认为1 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/*** 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化 */
	public Integer getCheckStatus() {
		return checkStatus;
	}

	/*** 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
}
