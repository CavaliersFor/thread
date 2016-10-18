package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 实体类
 */
public class ProductSku implements Serializable {
	private static final long serialVersionUID = 14709723165022L;

	private Long id;// 主键
	private Long productId;// 商品id
	private String properties;// sku的销售属性组合字符串(p1:v1;p2:v2)
	private String propertiesname;// 组合字符串的值
	private Integer quantity;// 数量
	private BigDecimal price;// 价格
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：使用中 2：停用
	private String enterpriseProductNo;// 商品在商家自己系统中的编号
	private BigDecimal volume;// 体积
	private BigDecimal weight;// 重量
	
	private List<String> proNames;
	
	
	public ProductSku() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public ProductSku(Long id) {
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

	/** 商品id */
	public Long getProductId() {
		return productId;
	}

	/** 商品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public String getProperties() {
		return properties;
	}

	/** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/** 组合字符串的值 */
	public String getPropertiesname() {
		return propertiesname;
	}

	/** 组合字符串的值 */
	public void setPropertiesname(String propertiesname) {
		this.propertiesname = propertiesname;
	}

	/** 数量 */
	public Integer getQuantity() {
		return quantity;
	}

	/** 数量 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/** 价格 */
	public BigDecimal getPrice() {
		return price;
	}

	/** 价格 */
	public void setPrice(BigDecimal price) {
		this.price = price;
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

	/** 状态1：使用中 2：停用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：使用中 2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 商品在商家自己系统中的编号 */
	public String getEnterpriseProductNo() {
		return enterpriseProductNo;
	}

	/** 商品在商家自己系统中的编号 */
	public void setEnterpriseProductNo(String enterpriseProductNo) {
		this.enterpriseProductNo = enterpriseProductNo;
	}

	/** 体积 */
	public BigDecimal getVolume() {
		return volume;
	}

	/** 体积 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	/** 重量 */
	public BigDecimal getWeight() {
		return weight;
	}

	/** 重量 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ProductSku [ id=" + id + ", productId=" + productId + ", properties=" + properties + ", propertiesname="
				+ propertiesname + ", quantity=" + quantity + ", price=" + price + ", gmtCreate=" + gmtCreate
				+ ", gmtModify=" + gmtModify + ", status=" + status + ", enterpriseProductNo=" + enterpriseProductNo
				+ ", volume=" + volume + ", weight=" + weight + "]";
	}

	public List<String> getProNames() {
		return proNames;
	}

	public void setProNames(List<String> proNames) {
		this.proNames = proNames;
	}
}
