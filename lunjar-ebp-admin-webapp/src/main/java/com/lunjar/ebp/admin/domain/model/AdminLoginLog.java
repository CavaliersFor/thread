package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
*实体类
*/
public class AdminLoginLog implements Serializable {
	private static final long serialVersionUID = 14102795431032L;

	
	
						private Long id;//		
					private Long loginId;//登录用户的ID		
					private String loginAccount;//登录用户的账号		
					private String loginIp;//登录用户的IP		
					private Date loginTime;//登录时间		
					private String adminWorkNo;//后台用户的工号		
					private String adminName;//后台用户的姓名		
		
		

		public AdminLoginLog() {}

		/**
	*
		 *@param id -- 
		*/
	public AdminLoginLog(Long id) {
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
		/**登录用户的ID*/
	public Long getLoginId() {
		return loginId;
	}
	/**登录用户的ID*/
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
		/**登录用户的账号*/
	public String getLoginAccount() {
		return loginAccount;
	}
	/**登录用户的账号*/
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
		/**登录用户的IP*/
	public String getLoginIp() {
		return loginIp;
	}
	/**登录用户的IP*/
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
		/**登录时间*/
	public Date getLoginTime() {
		return loginTime;
	}
	/**登录时间*/
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
		/**后台用户的工号*/
	public String getAdminWorkNo() {
		return adminWorkNo;
	}
	/**后台用户的工号*/
	public void setAdminWorkNo(String adminWorkNo) {
		this.adminWorkNo = adminWorkNo;
	}
		/**后台用户的姓名*/
	public String getAdminName() {
		return adminName;
	}
	/**后台用户的姓名*/
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
		
	@Override
	public String toString() {
		return "SysLogLogin [ id=" + id + ", loginId=" + loginId + ", loginAccount=" + loginAccount + ", loginIp=" + loginIp + ", loginTime=" + loginTime + ", adminWorkNo=" + adminWorkNo + ", adminName=" + adminName + "]";
	}
}
