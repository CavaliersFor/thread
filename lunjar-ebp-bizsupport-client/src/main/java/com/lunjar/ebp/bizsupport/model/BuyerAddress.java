package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class BuyerAddress implements Serializable {
	private static final long serialVersionUID = 14704362023192L;

	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 更新时间
	private Long buyerId;// 买家id
	private String province;// 买家省份
	private String city;// 买家城市
	private String region;// 买家区县
	private Integer status;// 状态1：正常2：停止
	private String buyerName;// 收货人姓名
	private String buyerPhone;// 收货人手机号码
	private String buyerAddress;// 收货人详细地址
	private String buyerPostCode;// 收货人邮编
	private Integer isDefault;// 是否默认地址1:是2:否

	public BuyerAddress() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public BuyerAddress(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return id;
	}

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 创建时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 创建时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 更新时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 更新时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家省份 */
	public String getProvince() {
		return province;
	}

	/** 买家省份 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 买家城市 */
	public String getCity() {
		return city;
	}

	/** 买家城市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 买家区县 */
	public String getRegion() {
		return region;
	}

	/** 买家区县 */
	public void setRegion(String region) {
		this.region = region;
	}

	/** 状态1：正常2：停止 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 收货人姓名 */
	public String getBuyerName() {
		return buyerName;
	}

	/** 收货人姓名 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/** 收货人手机号码 */
	public String getBuyerPhone() {
		return buyerPhone;
	}

	/** 收货人手机号码 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	/** 收货人详细地址 */
	public String getBuyerAddress() {
		return buyerAddress;
	}

	/** 收货人详细地址 */
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	/** 收货人邮编 */
	public String getBuyerPostCode() {
		return buyerPostCode;
	}

	/** 收货人邮编 */
	public void setBuyerPostCode(String buyerPostCode) {
		this.buyerPostCode = buyerPostCode;
	}

	/** 是否默认地址1:是2:否 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/** 是否默认地址1:是2:否 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "BuyerAddress [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", buyerId="
				+ buyerId + ", province=" + province + ", city=" + city + ", region=" + region + ", status=" + status
				+ ", buyerName=" + buyerName + ", buyerPhone=" + buyerPhone + ", buyerAddress=" + buyerAddress
				+ ", buyerPostCode=" + buyerPostCode + ", isDefault=" + isDefault + "]";
	}
}
