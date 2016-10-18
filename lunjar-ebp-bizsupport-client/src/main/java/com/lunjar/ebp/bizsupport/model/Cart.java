package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = 14702440737442L;

	private Long id;// 主键id
	private Long buyerId;// 买家id
	private Long productId;// 商品id
	private Integer status;// 状态 1：未结算 2：已结算
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Long skuId;// sku id
	private Long shopId;// 店铺id
	private Integer num;// 数量

	public Cart() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public Cart(Long id) {
		this.id = id;
	}

	/** 主键id */
	public Long getId() {
		return id;
	}

	/** 主键id */
	public void setId(Long id) {
		this.id = id;
	}

	/** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 商品id */
	public Long getProductId() {
		return productId;
	}

	/** 商品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 状态 1：未结算 2：已结算 */
	public Integer getStatus() {
		return status;
	}

	/** 状态 1：未结算 2：已结算 */
	public void setStatus(Integer status) {
		this.status = status;
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

	/** sku id */
	public Long getSkuId() {
		return skuId;
	}

	/** sku id */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/** 店铺id */
	public Long getShopId() {
		return shopId;
	}

	/** 店铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/** 数量 */
	public Integer getNum() {
		return num;
	}

	/** 数量 */
	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Cart [ id=" + id + ", buyerId=" + buyerId + ", productId=" + productId + ", status=" + status
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", skuId=" + skuId + ", shopId=" + shopId
				+ ", num=" + num + "]";
	}
}
