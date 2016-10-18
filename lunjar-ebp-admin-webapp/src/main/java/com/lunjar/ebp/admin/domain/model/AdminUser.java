package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.lunjar.products.core.model.TableEntity;

/**
 * 管理人员表
 */
public class AdminUser implements Serializable, TableEntity {
	private static final long serialVersionUID = 13806449092742L;

	private Long id;//
	private String postcode;//
	private String mobile;//
	private String account;// 登录账号
	private String modifier;//
	private String phone;//
	private int loginCount;// 登录次数
	private Date gmtModify;//
	private Integer isSuperAdmin;//是否超级管理员
	private Date gmtCreate;//
	private String idCard;// 身份证号码
	private String userName;// 管理人员姓名
	private String email;//
	private Integer deptId;// 部门
	private String remark;// 备注
	private Date birthday;// 出生日期
	private Date lastLoginTime;// 最后登录时间
	private String pwd;// 登录密码
	private String sex;// 性别 1男 2女
	private String creator;//
	private String address;//
	private Integer status;// 1:正常 2:冻结 3:锁定-1:已删除
	private String workNo;//工号
	private Integer loginErrorCount;//登录错误次数，最多5次		
	private Date latestLockTime;//最后锁定时间
	private Date latestUnlockTime;//解锁时间
	
	
	public Date getLatestUnlockTime() {
		return latestUnlockTime;
	}

	public void setLatestUnlockTime(Date latestUnlockTime) {
		this.latestUnlockTime = latestUnlockTime;
	}

	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Date getLatestLockTime() {
		return latestLockTime;
	}

	public void setLatestLockTime(Date latestLockTime) {
		this.latestLockTime = latestLockTime;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public AdminUser() {
	}

	/**
	 * 
	 * @param id
	 *            --
	 */
	public AdminUser(Long id) {
		this.id = id;
	}

	/***/
	public Long getId() {
		return id;
	}

	/***/
	public void setId(Long id) {
		this.id = id;
	}

	/***/
	public String getPostcode() {
		return postcode;
	}

	/***/
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/***/
	public String getMobile() {
		return mobile;
	}

	/***/
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** 登录账号 */
	//@NotBlank
	public String getAccount() {
		return account;
	}

	/** 登录账号 */
	public void setAccount(String account) {
		this.account = account;
	}

	/***/
	public String getModifier() {
		return modifier;
	}

	/***/
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/***/
	public String getPhone() {
		return phone;
	}

	/***/
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 登录次数 */
	public int getLoginCount() {
		return loginCount;
	}

	/** 登录次数 */
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	/***/
	public Date getGmtModify() {
		return gmtModify;
	}

	/***/
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 是否超级管理员 */
	public Integer getIsSuperAdmin() {
		return isSuperAdmin;
	}

	/** 是否超级管理员 */
	public void setIsSuperAdmin(Integer isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	/***/
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/***/
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 身份证号码 */
	public String getIdCard() {
		return idCard;
	}

	/** 身份证号码 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/** 管理人员姓名 */
	@NotBlank
	public String getUserName() {
		return userName;
	}

	/** 管理人员姓名 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/***/
	public String getEmail() {
		return email;
	}

	/***/
	public void setEmail(String email) {
		this.email = email;
	}

	/** 所属处室Id 关联到表SYS_DEPT中dept_type为doccol的id */
	public Integer getDeptId() {
		return deptId;
	}

	/** 所属处室Id 关联到表SYS_DEPT中dept_type为doccol的id */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/** 备注 */
	public String getRemark() {
		return remark;
	}

	/** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 出生日期 */
	public Date getBirthday() {
		return birthday;
	}

	/** 出生日期 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/** 最后登录时间 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/** 最后登录时间 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/** 登录密码 */
	public String getPwd() {
		return pwd;
	}

	/** 登录密码 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/** 性别 1男 2女 */
	public String getSex() {
		return sex;
	}

	/** 性别 1男 2女 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/***/
	public String getCreator() {
		return creator;
	}

	/***/
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/***/
	public String getAddress() {
		return address;
	}

	/***/
	public void setAddress(String address) {
		this.address = address;
	}

	/** 1:正常 2:冻结 -1:已删除 */
	public Integer getStatus() {
		return status;
	}

	/** 1:正常 2:冻结 -1:已删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdminUser [ id=" + id + ", postcode=" + postcode + ", mobile=" + mobile + ", account=" + account
				+ ", modifier=" + modifier + ", phone=" + phone + ", loginCount=" + loginCount + ", gmtModify="
				+ gmtModify + ", isSuperAdmin=" + isSuperAdmin + ", gmtCreate=" + gmtCreate + ", idCard=" + idCard
				+ ", userName=" + userName + ", email=" + email + ", deptId=" + deptId + ", remark=" + remark
				+ ", birthday=" + birthday + ", lastLoginTime=" + lastLoginTime + ", pwd=" + pwd + ", sex=" + sex
				+ ", creator=" + creator + ", address=" + address + ", status=" + status + "]";
	}
}
