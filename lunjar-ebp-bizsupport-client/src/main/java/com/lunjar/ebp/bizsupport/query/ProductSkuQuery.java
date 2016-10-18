package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ProductSkuQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14709723165021L;

	private Long[] idArray;// 主键
	private Long productId;// 商品id
	private String properties;// sku的销售属性组合字符串(p1:v1;p2:v2)
	private String propertiesname;// 组合字符串的值
	private Integer quantity;// 数量
	private BigDecimal priceFrom;// 价格
	private BigDecimal priceTo;// 价格
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：使用中 2：停用
	private Integer[] statusArray;// 状态1：使用中 2：停用
	private String enterpriseProductNo;// 商品在商家自己系统中的编号
	private BigDecimal volumeFrom;// 体积
	private BigDecimal volumeTo;// 体积
	private BigDecimal weightFrom;// 重量
	private BigDecimal weightTo;// 重量

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 商品id */
	public Long getProductId() {
		return productId;
	}

	/*** 商品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/*** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public String getProperties() {
		return properties;
	}

	/*** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/*** 组合字符串的值 */
	public String getPropertiesname() {
		return propertiesname;
	}

	/*** 组合字符串的值 */
	public void setPropertiesname(String propertiesname) {
		this.propertiesname = propertiesname;
	}

	/*** 数量 */
	public Integer getQuantity() {
		return quantity;
	}

	/*** 数量 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/*** 价格 */
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	/*** 价格 */
	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	/*** 价格 */
	public BigDecimal getPriceTo() {
		return priceTo;
	}

	/*** 价格 */
	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
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

	/*** 状态1：使用中 2：停用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：使用中 2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：使用中 2：停用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：使用中 2：停用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 商品在商家自己系统中的编号 */
	public String getEnterpriseProductNo() {
		return enterpriseProductNo;
	}

	/*** 商品在商家自己系统中的编号 */
	public void setEnterpriseProductNo(String enterpriseProductNo) {
		this.enterpriseProductNo = enterpriseProductNo;
	}

	/*** 体积 */
	public BigDecimal getVolumeFrom() {
		return volumeFrom;
	}

	/*** 体积 */
	public void setVolumeFrom(BigDecimal volumeFrom) {
		this.volumeFrom = volumeFrom;
	}

	/*** 体积 */
	public BigDecimal getVolumeTo() {
		return volumeTo;
	}

	/*** 体积 */
	public void setVolumeTo(BigDecimal volumeTo) {
		this.volumeTo = volumeTo;
	}

	/*** 重量 */
	public BigDecimal getWeightFrom() {
		return weightFrom;
	}

	/*** 重量 */
	public void setWeightFrom(BigDecimal weightFrom) {
		this.weightFrom = weightFrom;
	}

	/*** 重量 */
	public BigDecimal getWeightTo() {
		return weightTo;
	}

	/*** 重量 */
	public void setWeightTo(BigDecimal weightTo) {
		this.weightTo = weightTo;
	}
}
