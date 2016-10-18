package com.lunjar.ebp.admin.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;
import com.lunjar.products.core.query.QueryParam;

/**
 * 管理人员表
 */
public class AdminUserQuery extends AbstractQueryParam implements QueryParam, Serializable {
	private static final long serialVersionUID = 13806449860871L;

	private Long[] idArray;//
	private String postcode;//
	private String mobile;//
	private String account;//登录账号
	private String modifier;//
	private String phone;//
	private Long loginCount;//登录次数
	private Date gmtModifyFrom;//
	private Date gmtModifyTo;//
	private String isDeleted;//删除标记 0 未删除1 删除
	private String duty;//职务代码 关联到sys_resource中res_type='duty'
	private Integer isSuperAdmin;//是否超级管理员
	private Date gmtCreateFrom;//
	private Date gmtCreateTo;//
	private Long orgId;//所属单位Id  关联到表SYS_DEPT中dept_type为org的id
	private String idCard;//身份证号码
	private String userName;//管理人员姓名
	private String email;//
	private Long deptId;//所属处室Id  关联到表SYS_DEPT中dept_type为doccol的id
	private String remark;//备注
	private Date birthdayFrom;//出生日期
	private Date birthdayTo;//出生日期
	private Date lastLoginTimeFrom;//最后登录时间
	private Date lastLoginTimeTo;//最后登录时间
	private String pwd;//登录密码
	private String sex;//性别 1男 2女
	private String creator;//
	private String address;//
	private Integer status;//正常
	private Integer[] statusArray;//正常
	private String workNo;//工号
	private Integer loginErrorCount;//登录错误次数，最多5次
	private Date latestLockTimeFrom;//最后锁定时间
	private Date latestLockTimeTo;//最后锁定时间
	private Date latestUnlockTimeFrom;//解锁时间
	private Date latestUnlockTimeTo;//解锁时间
	
	
	public Date getLatestUnlockTimeFrom() {
		return latestUnlockTimeFrom;
	}

	public void setLatestUnlockTimeFrom(Date latestUnlockTimeFrom) {
		this.latestUnlockTimeFrom = latestUnlockTimeFrom;
	}

	public Date getLatestUnlockTimeTo() {
		return latestUnlockTimeTo;
	}

	public void setLatestUnlockTimeTo(Date latestUnlockTimeTo) {
		this.latestUnlockTimeTo = latestUnlockTimeTo;
	}

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Date getLatestLockTimeFrom() {
		return latestLockTimeFrom;
	}

	public void setLatestLockTimeFrom(Date latestLockTimeFrom) {
		this.latestLockTimeFrom = latestLockTimeFrom;
	}

	public Date getLatestLockTimeTo() {
		return latestLockTimeTo;
	}

	public void setLatestLockTimeTo(Date latestLockTimeTo) {
		this.latestLockTimeTo = latestLockTimeTo;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	/****/
	public Long[] getIdArray() {
		return idArray;
	}

	/****/
	public void setIdArray(Long[] idArray) {
		this.idArray = idArray;
	}

	/****/
	public String getPostcode() {
		return postcode;
	}

	/****/
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/****/
	public String getMobile() {
		return mobile;
	}

	/****/
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/*** 登录账号 */
	public String getAccount() {
		return account;
	}

	/*** 登录账号 */
	public void setAccount(String account) {
		this.account = account;
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
	public String getPhone() {
		return phone;
	}

	/****/
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*** 登录次数 */
	public Long getLoginCount() {
		return loginCount;
	}

	/*** 登录次数 */
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
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

	/*** 删除标记 0 未删除1 删除 */
	public String getIsDeleted() {
		return isDeleted;
	}

	/*** 删除标记 0 未删除1 删除 */
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	/*** 职务代码 关联到sys_resource中res_type='duty' */
	public String getDuty() {
		return duty;
	}

	/*** 职务代码 关联到sys_resource中res_type='duty' */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/*** 是否超级管理员 */
	public Integer getIsSuperAdmin() {
		return isSuperAdmin;
	}

	/*** 是否超级管理员 */
	public void setIsSuperAdmin(Integer isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
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

	/*** 所属单位Id 关联到表SYS_DEPT中dept_type为org的id */
	public Long getOrgId() {
		return orgId;
	}

	/*** 所属单位Id 关联到表SYS_DEPT中dept_type为org的id */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/*** 身份证号码 */
	public String getIdCard() {
		return idCard;
	}

	/*** 身份证号码 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/*** 管理人员姓名 */
	public String getUserName() {
		return userName;
	}

	/*** 管理人员姓名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/****/
	public String getEmail() {
		return email;
	}

	/****/
	public void setEmail(String email) {
		this.email = email;
	}

	/*** 所属处室Id 关联到表SYS_DEPT中dept_type为doccol的id */
	public Long getDeptId() {
		return deptId;
	}

	/*** 所属处室Id 关联到表SYS_DEPT中dept_type为doccol的id */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/*** 备注 */
	public String getRemark() {
		return remark;
	}

	/*** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*** 出生日期 */
	public Date getBirthdayFrom() {
		return birthdayFrom;
	}

	/*** 出生日期 */
	public void setBirthdayFrom(Date birthdayFrom) {
		this.birthdayFrom = birthdayFrom;
	}

	/*** 出生日期 */
	public Date getBirthdayTo() {
		return birthdayTo;
	}

	/*** 出生日期 */
	public void setBirthdayTo(Date birthdayTo) {
		this.birthdayTo = birthdayTo;
	}

	/*** 最后登录时间 */
	public Date getLastLoginTimeFrom() {
		return lastLoginTimeFrom;
	}

	/*** 最后登录时间 */
	public void setLastLoginTimeFrom(Date lastLoginTimeFrom) {
		this.lastLoginTimeFrom = lastLoginTimeFrom;
	}

	/*** 最后登录时间 */
	public Date getLastLoginTimeTo() {
		return lastLoginTimeTo;
	}

	/*** 最后登录时间 */
	public void setLastLoginTimeTo(Date lastLoginTimeTo) {
		this.lastLoginTimeTo = lastLoginTimeTo;
	}

	/*** 登录密码 */
	public String getPwd() {
		return pwd;
	}

	/*** 登录密码 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/*** 性别 1男 2女 */
	public String getSex() {
		return sex;
	}

	/*** 性别 1男 2女 */
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getAddress() {
		return address;
	}

	/****/
	public void setAddress(String address) {
		this.address = address;
	}

	/*** 正常 */
	public Integer getStatus() {
		return status;
	}

	/*** 正常 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 正常 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 正常 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}
}
