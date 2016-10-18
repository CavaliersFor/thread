package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.lunjar.products.core.model.OperatorAgent;

/**
 * 登录管理员信息
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2011-3-12 下午07:53:30
 */
public class AdminAgent implements OperatorAgent, Serializable {
	private static final long serialVersionUID = -1830859706881331312L;

	public static final String REQUEST_ATTR_NAME = "loginUser";

	private Long id;
	private String account;
	private String userName;
	private String ip;
	private boolean isSystemAdmin;
	private Set<String> permissions = new HashSet<String>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 是否超级管理员
	 */
	public boolean isSystemAdmin() {
		return isSystemAdmin;
	}

	public void setSystemAdmin(boolean isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}

	@Override
	public String getType() {
		return "admin";
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean havePermission(String functionCode) {
		return permissions.contains(functionCode);
	}

	public boolean haveOneOfPermissions(Set<String> functionCodes) {
		return !Collections.disjoint(permissions, functionCodes);
	}

	@Override
	public String toString() {
		return "AdminAgent [id=" + id + ", account=" + account + ", userName=" + userName + ", ip=" + ip
				+ ", isSystemAdmin=" + isSystemAdmin + "]";
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions.clear();
		this.permissions.addAll(permissions);
	}
}
