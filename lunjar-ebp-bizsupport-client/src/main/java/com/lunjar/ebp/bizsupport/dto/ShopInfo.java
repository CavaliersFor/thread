package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;

public class ShopInfo implements Serializable{
	
	private static final long serialVersionUID = 4818827235389968990L;

	private Long sellerId;//卖家id
	private String registerName;//卖家用户名
	private String mobile;//卖家手机号码
	
	private Long id;// 主键id
	private Integer status;// 状态1:正常 2:停止3:删除
	private Long partnerId;// 合作者平台id
	private String shopName;// 店铺名称
	private String accessKey;// 接入者key
	private String secretKey;// 接入者秘钥
	private String url;// 访问地址
	private String viewIndex;// 首页html页面
	
	public ShopInfo(){
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getViewIndex() {
		return viewIndex;
	}

	public void setViewIndex(String viewIndex) {
		this.viewIndex = viewIndex;
	}
}
