package com.lunjar.ebp.admin.domain.model;

import java.io.Serializable;

import com.lunjar.products.core.model.OperatorAgent;

public class EnterpriseAgent implements OperatorAgent, Serializable{
	
	public static final String REQUEST_ATTR_NAME = "loginUser";
	
	private Long id;// 商家id
	private String enterpiseName;// 企业名称
	private String account;// 注册账号
	private String ip;//ip地址
	private Integer distributionMode;// 配送方式1：快递2：自取
	private String absoluteHeadPicUrl;//头像路径
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public String getIp() {
		return ip;
	}

	public String getEnterpiseName() {
		return enterpiseName;
	}

	public void setEnterpiseName(String enterpiseName) {
		this.enterpiseName = enterpiseName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getDistributionMode() {
		return distributionMode;
	}

	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}

	public String getAbsoluteHeadPicUrl() {
		return absoluteHeadPicUrl;
	}

	public void setAbsoluteHeadPicUrl(String absoluteHeadPicUrl) {
		this.absoluteHeadPicUrl = absoluteHeadPicUrl;
	}

}
