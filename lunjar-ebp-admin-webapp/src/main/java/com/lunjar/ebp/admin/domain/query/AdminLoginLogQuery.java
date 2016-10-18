package com.lunjar.ebp.admin.domain.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;
import com.lunjar.products.core.query.QueryParam;

/**
 * 查询对象
 */
public class AdminLoginLogQuery extends AbstractQueryParam implements QueryParam, Serializable {
	private static final long serialVersionUID = 14102795431031L;

	private Long[] idArray;//
	private Long loginId;// 登录用户的ID
	private String loginAccount;// 登录用户的账号
	private String loginIp;// 登录用户的IP
	private Date loginTimeFrom;// 登录时间
	private Date loginTimeTo;// 登录时间
	private String adminWorkNo;// 后台用户的工号
	private String adminName;// 后台用户的姓名

	/****/
	public Long[] getIdArray() {
		return idArray;
	}

	/****/
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 登录用户的ID */
	public Long getLoginId() {
		return loginId;
	}

	/*** 登录用户的ID */
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	/*** 登录用户的账号 */
	public String getLoginAccount() {
		return loginAccount;
	}

	/*** 登录用户的账号 */
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	/*** 登录用户的IP */
	public String getLoginIp() {
		return loginIp;
	}

	/*** 登录用户的IP */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/*** 登录时间 */
	public Date getLoginTimeFrom() {
		return loginTimeFrom;
	}

	/*** 登录时间 */
	public void setLoginTimeFrom(Date loginTimeFrom) {
		this.loginTimeFrom = loginTimeFrom;
	}

	/*** 登录时间 */
	public Date getLoginTimeTo() {
		return loginTimeTo;
	}

	/*** 登录时间 */
	public void setLoginTimeTo(Date loginTimeTo) {
		this.loginTimeTo = loginTimeTo;
	}

	/*** 后台用户的工号 */
	public String getAdminWorkNo() {
		return adminWorkNo;
	}

	/*** 后台用户的工号 */
	public void setAdminWorkNo(String adminWorkNo) {
		this.adminWorkNo = adminWorkNo;
	}

	/*** 后台用户的姓名 */
	public String getAdminName() {
		return adminName;
	}

	/*** 后台用户的姓名 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
