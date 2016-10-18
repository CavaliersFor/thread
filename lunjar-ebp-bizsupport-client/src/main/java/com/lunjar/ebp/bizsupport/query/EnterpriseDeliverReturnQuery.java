package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class EnterpriseDeliverReturnQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14713723602221L;

	private Long[] idArray;// 主键
	private Long enterpriseId;// 商家id
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：使用2：停用
	private Integer[] statusArray;// 状态1：使用2：停用
	private String address;// 退货详细地址
	private String province;// 省
	private String city;// 市
	private String region;// 区县
	private Integer type;// 类型1：发货地址 2：退货地址
	private Integer isDefault;// 是否默认地址1:是 2： 否
	private String linkname;// 收件人
	private String telephone;// 联系电话

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	/*** 状态1：使用2：停用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：使用2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：使用2：停用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：使用2：停用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 退货详细地址 */
	public String getAddress() {
		return address;
	}

	/*** 退货详细地址 */
	public void setAddress(String address) {
		this.address = address;
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

	/*** 区县 */
	public String getRegion() {
		return region;
	}

	/*** 区县 */
	public void setRegion(String region) {
		this.region = region;
	}

	/*** 类型1：发货地址 2：退货地址 */
	public Integer getType() {
		return type;
	}

	/*** 类型1：发货地址 2：退货地址 */
	public void setType(Integer type) {
		this.type = type;
	}

	/*** 是否默认地址1:是 2： 否 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/*** 是否默认地址1:是 2： 否 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/*** 收件人 */
	public String getLinkname() {
		return linkname;
	}

	/*** 收件人 */
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}

	/*** 联系电话 */
	public String getTelephone() {
		return telephone;
	}

	/*** 联系电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
