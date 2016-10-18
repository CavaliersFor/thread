package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 登录管理员信息
 * 
 */
public class AdminAgent implements Serializable {

	private static final long serialVersionUID = -5298453475203136821L;

	public static final String REQUEST_ATTR_NAME = "loginUser";

	private Long id;
	private String userName;
	private String ip;
	private Set<String> permissions = new HashSet<String>();

	public AdminAgent(){}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
		return "AdminAgent [id=" + id + ", userName=" + userName + ", ip=" + ip + "]";
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions.clear();
		this.permissions.addAll(permissions);
	}
}
