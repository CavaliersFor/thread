package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class EnterpriseQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14715643588181L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private String account;// 注册账号
	private String password;// 密码
	private String enterpiseName;// 企业名称
	private String contactPhone;// 联系人电话
	private String orgNo;// 组织机构代码
	private String businessNo;// 工商注册编号
	private String taxNo;// 税务登记号
	private String legalName;// 法人名称
	private String email;// 公司邮箱
	private String fax;// 传真
	private String province;// 省
	private String city;// 市
	private String region;// 区(县)
	private String address;// 公司所在地址
	private String postCode;// 邮编
	private Integer enterpriseType;// 企业类型1：民营2：国企3：合资
	private String bussinessScope;// 经营范围
	private Integer trade;// 行业1：互联网2：食品3：金融 ...
	private String businessLicenceUrl;// 营业执照副本地址
	private String telephone;// 企业电话
	private String enterpiseAlias;// 企业简称
	private String linkMan;// 联系人
	private Integer distributionMode;// 配送方式1：快递2：自取
	private String deliveryDeadline;// 每日发货截止时间
	private String headPicUrl;// 商家头像图片

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 创建时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 创建时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 创建时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 创建时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 注册账号 */
	public String getAccount() {
		return account;
	}

	/*** 注册账号 */
	public void setAccount(String account) {
		this.account = account;
	}

	/*** 密码 */
	public String getPassword() {
		return password;
	}

	/*** 密码 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*** 企业名称 */
	public String getEnterpiseName() {
		return enterpiseName;
	}

	/*** 企业名称 */
	public void setEnterpiseName(String enterpiseName) {
		this.enterpiseName = enterpiseName;
	}

	/*** 联系人电话 */
	public String getContactPhone() {
		return contactPhone;
	}

	/*** 联系人电话 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/*** 组织机构代码 */
	public String getOrgNo() {
		return orgNo;
	}

	/*** 组织机构代码 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	/*** 工商注册编号 */
	public String getBusinessNo() {
		return businessNo;
	}

	/*** 工商注册编号 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	/*** 税务登记号 */
	public String getTaxNo() {
		return taxNo;
	}

	/*** 税务登记号 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	/*** 法人名称 */
	public String getLegalName() {
		return legalName;
	}

	/*** 法人名称 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	/*** 公司邮箱 */
	public String getEmail() {
		return email;
	}

	/*** 公司邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*** 传真 */
	public String getFax() {
		return fax;
	}

	/*** 传真 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/*** 省 */
	public String getProvince() {
		return province;
	}

	/*** 省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/*** 市 */
	public String getCity() {
		return city;
	}

	/*** 市 */
	public void setCity(String city) {
		this.city = city;
	}

	/*** 区(县) */
	public String getRegion() {
		return region;
	}

	/*** 区(县) */
	public void setRegion(String region) {
		this.region = region;
	}

	/*** 公司所在地址 */
	public String getAddress() {
		return address;
	}

	/*** 公司所在地址 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*** 邮编 */
	public String getPostCode() {
		return postCode;
	}

	/*** 邮编 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/*** 企业类型1：民营2：国企3：合资 */
	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	/*** 企业类型1：民营2：国企3：合资 */
	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	/*** 经营范围 */
	public String getBussinessScope() {
		return bussinessScope;
	}

	/*** 经营范围 */
	public void setBussinessScope(String bussinessScope) {
		this.bussinessScope = bussinessScope;
	}

	/*** 行业1：互联网2：食品3：金融 ... */
	public Integer getTrade() {
		return trade;
	}

	/*** 行业1：互联网2：食品3：金融 ... */
	public void setTrade(Integer trade) {
		this.trade = trade;
	}

	/*** 营业执照副本地址 */
	public String getBusinessLicenceUrl() {
		return businessLicenceUrl;
	}

	/*** 营业执照副本地址 */
	public void setBusinessLicenceUrl(String businessLicenceUrl) {
		this.businessLicenceUrl = businessLicenceUrl;
	}

	/*** 企业电话 */
	public String getTelephone() {
		return telephone;
	}

	/*** 企业电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/*** 企业简称 */
	public String getEnterpiseAlias() {
		return enterpiseAlias;
	}

	/*** 企业简称 */
	public void setEnterpiseAlias(String enterpiseAlias) {
		this.enterpiseAlias = enterpiseAlias;
	}

	/*** 联系人 */
	public String getLinkMan() {
		return linkMan;
	}

	/*** 联系人 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/*** 配送方式1：快递2：自取 */
	public Integer getDistributionMode() {
		return distributionMode;
	}

	/*** 配送方式1：快递2：自取 */
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}

	/*** 每日发货截止时间 */
	public String getDeliveryDeadline() {
		return deliveryDeadline;
	}

	/*** 每日发货截止时间 */
	public void setDeliveryDeadline(String deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}

	/*** 商家头像图片 */
	public String getHeadPicUrl() {
		return headPicUrl;
	}

	/*** 商家头像图片 */
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
}
