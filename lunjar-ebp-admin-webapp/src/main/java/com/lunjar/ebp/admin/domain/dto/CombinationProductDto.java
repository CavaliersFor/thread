package com.lunjar.ebp.admin.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CombinationProductDto implements Serializable{
	
	private Long productId;// 商品id
	private String productName;// 商品名称
	private BigDecimal productPrice;// 商品原价格
	private BigDecimal productRealPrice;// 商品实际销售价格
	private String productPicPath;// 商品图片路径
	private String productAbsPicPath;//商品图片绝对路径
	private Integer productNum;// 商品数量
	private Integer status;//商品状态 产品状态1：销售中2：仓库中 3删除
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public BigDecimal getProductRealPrice() {
		return productRealPrice;
	}
	public void setProductRealPrice(BigDecimal productRealPrice) {
		this.productRealPrice = productRealPrice;
	}
	public String getProductPicPath() {
		return productPicPath;
	}
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}
	public String getProductAbsPicPath() {
		return productAbsPicPath;
	}
	public void setProductAbsPicPath(String productAbsPicPath) {
		this.productAbsPicPath = productAbsPicPath;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
