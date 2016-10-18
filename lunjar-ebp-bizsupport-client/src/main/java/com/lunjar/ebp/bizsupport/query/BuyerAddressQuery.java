package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class BuyerAddressQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14704414775651L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 更新时间
	private Date gmtModifyTo;// 更新时间
	private Long buyerId;// 买家id
	private String province;// 买家省份
	private String city;// 买家城市
	private String region;// 买家区县
	private Integer status;// 状态1：正常2：停止
	private Integer[] statusArray;// 状态1：正常2：停止
	private String buyerName;// 收货人姓名
	private String buyerPhone;// 收货人手机号码
	private String buyerAddress;// 收货人详细地址
	private String buyerPostCode;// 收货人邮编
	private Integer isDefault;// 是否默认地址1:是2:否

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

	/*** 更新时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 更新时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 更新时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 更新时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/*** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/*** 买家省份 */
	public String getProvince() {
		return province;
	}

	/*** 买家省份 */
	public void setProvince(String province) {
		this.province = province;
	}

	/*** 买家城市 */
	public String getCity() {
		return city;
	}

	/*** 买家城市 */
	public void setCity(String city) {
		this.city = city;
	}

	/*** 买家区县 */
	public String getRegion() {
		return region;
	}

	/*** 买家区县 */
	public void setRegion(String region) {
		this.region = region;
	}

	/*** 状态1：正常2：停止 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常2：停止 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常2：停止 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 收货人姓名 */
	public String getBuyerName() {
		return buyerName;
	}

	/*** 收货人姓名 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/*** 收货人手机号码 */
	public String getBuyerPhone() {
		return buyerPhone;
	}

	/*** 收货人手机号码 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	/*** 收货人详细地址 */
	public String getBuyerAddress() {
		return buyerAddress;
	}

	/*** 收货人详细地址 */
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	/*** 收货人邮编 */
	public String getBuyerPostCode() {
		return buyerPostCode;
	}

	/*** 收货人邮编 */
	public void setBuyerPostCode(String buyerPostCode) {
		this.buyerPostCode = buyerPostCode;
	}

	/*** 是否默认地址1:是2:否 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/*** 是否默认地址1:是2:否 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
