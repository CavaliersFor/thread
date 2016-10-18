package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.ebp.bizsupport.model.ProductSku;

public class CartDto implements Serializable {

	private static final long serialVersionUID = -401884842761517855L;


	private Long id;// 主键id
	private Long buyerId;// 买家id
	private Long productId;// 商品id
	private Integer status;// 状态 1：未结算 2：已结算
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Long skuId;// sku id
	private Long shopId;// 店铺id
	private Integer num;// 数量
	
	private String productName;//商品名称
	private BigDecimal productRealPrice;//商品价格
	private String picPath;//商品主图
	private String categoryName;//自定义类目名称
	private String properties;// sku的销售属性组合字符串(p1:v1;p2:v2)
	private String propertiesname;// 组合字符串的值
	private String shopName;//店铺名称
	private String headPicUrl;//店铺图片
	private ProductSku productSku;//sku信息
	private String enterpiseName; //商家名称
	private Long enterpiseId;//商家id
	private Integer productStatus;//产品状态1：销售中2：仓库中(下架)
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getProductRealPrice() {
		return productRealPrice;
	}
	public void setProductRealPrice(BigDecimal productRealPrice) {
		this.productRealPrice = productRealPrice;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getPropertiesname() {
		return propertiesname;
	}
	public void setPropertiesname(String propertiesname) {
		this.propertiesname = propertiesname;
	}
	public ProductSku getProductSku() {
		return productSku;
	}
	public void setProductSku(ProductSku productSku) {
		this.productSku = productSku;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	public String getEnterpiseName() {
		return enterpiseName;
	}
	public void setEnterpiseName(String enterpiseName) {
		this.enterpiseName = enterpiseName;
	}
	public Long getEnterpiseId() {
		return enterpiseId;
	}
	public void setEnterpiseId(Long enterpiseId) {
		this.enterpiseId = enterpiseId;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
}
