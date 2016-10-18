package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class ProductShop implements Serializable {
	private static final long serialVersionUID = 14702488723502L;

	private Long id;// 主键
	private Long productId;// 商品id
	private Long shopId;// 商铺id
	private Long categoryId;// 自定义类id
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：正常2：删除
	private Integer type;// 商品类型 1：普通商品 2：组合商品

	public ProductShop() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public ProductShop(Long id) {
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

	/** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/** 自定义类id */
	public Long getCategoryId() {
		return categoryId;
	}

	/** 自定义类id */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	/** 状态1：正常2：删除 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常2：删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 商品类型 1：普通商品 2：组合商品 */
	public Integer getType() {
		return type;
	}

	/** 商品类型 1：普通商品 2：组合商品 */
	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductShop [ id=" + id + ", productId=" + productId + ", shopId=" + shopId + ", categoryId="
				+ categoryId + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", type=" + type + "]";
	}
}
