package com.lunjar.ebp.portal.console.model;

import java.io.Serializable;

import com.lunjar.products.core.model.OperatorAgent;

/**
 * 买家进入商铺缓存信息
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月29日下午8:15:27
 */
public class PortalAgent implements OperatorAgent, Serializable {
	
	private static final long serialVersionUID = 1491718988874943767L;
	private Long buyerId;// 买家主键
	private Long partnerId;// 所属合作者平台id
	private String openId;// 所属合作者平台用户唯一标识
	private String nickname;// 用户昵称
	private String phone;// 手机号码
	
	private Long shopId;// 主键id
	private Long saleId;// 卖家id
	private String shopName;// 店铺名称
	private String accessKey;// 接入者key
	private String secretKey;// 接入者秘钥
	private String url;// 访问地址
	private String headPicUrl;// 商铺头像图片
	
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
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
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAccount() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getIp() {
		// TODO Auto-generated method stub
		return null;
	}
}
