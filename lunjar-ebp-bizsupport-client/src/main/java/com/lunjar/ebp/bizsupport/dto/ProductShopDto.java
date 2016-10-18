package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductShopDto implements Serializable {

	private static final long serialVersionUID = 4410469894509154126L;
	private Long id;// 主键
	private Long productId;// 商品id
	private Long shopId;// 商铺id
	private Long categoryId;// 自定义类id
	private Integer categorySort;//自定义类排序值
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：正常2：删除
	private Integer type;// 商品类型 1：普通商品 2：组合商品
	
	private String categoryName; //自定义类目名称
	private String productName; //商品名称',
	private String productPicPath;//'商品主图图片地址',
	private BigDecimal price;// '商品价格',
	private BigDecimal realPrice;//商品实际销售价格',
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPicPath() {
		return productPicPath;
	}
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getCategorySort() {
		return categorySort;
	}
	public void setCategorySort(Integer categorySort) {
		this.categorySort = categorySort;
	}
}
