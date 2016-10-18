package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class EnterpriseDeliverReturn implements Serializable {
	private static final long serialVersionUID = 14721308097542L;

	private Long id;// 主键
	private Long enterpriseId;// 商家id
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：使用2：停用
	private String address;// 退货详细地址
	private String province;// 省
	private String city;// 市
	private String region;// 区县
	private Integer type;// 类型1：发货地址 2：退货地址
	private Integer isDefault;// 是否默认地址1:是 2： 否
	private String linkname;// 收件人
	private String telephone;// 联系电话

	public EnterpriseDeliverReturn() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public EnterpriseDeliverReturn(Long id) {
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

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 创建时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 创建时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 修改时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 修改时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 状态1：使用2：停用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：使用2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 退货详细地址 */
	public String getAddress() {
		return address;
	}

	/** 退货详细地址 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** 省 */
	public String getProvince() {
		return province;
	}

	/** 省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 市 */
	public String getCity() {
		return city;
	}

	/** 市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 区县 */
	public String getRegion() {
		return region;
	}

	/** 区县 */
	public void setRegion(String region) {
		this.region = region;
	}

	/** 类型1：发货地址 2：退货地址 */
	public Integer getType() {
		return type;
	}

	/** 类型1：发货地址 2：退货地址 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 是否默认地址1:是 2： 否 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/** 是否默认地址1:是 2： 否 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/** 收件人 */
	public String getLinkname() {
		return linkname;
	}

	/** 收件人 */
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	/** 联系电话 */
	public String getTelephone() {
		return telephone;
	}

	/** 联系电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "EnterpriseDeliverReturn [ id=" + id + ", enterpriseId=" + enterpriseId + ", gmtCreate=" + gmtCreate
				+ ", gmtModify=" + gmtModify + ", status=" + status + ", address=" + address + ", province=" + province
				+ ", city=" + city + ", region=" + region + ", type=" + type + ", isDefault=" + isDefault
				+ ", linkname=" + linkname + ", telephone=" + telephone + "]";
	}
}
