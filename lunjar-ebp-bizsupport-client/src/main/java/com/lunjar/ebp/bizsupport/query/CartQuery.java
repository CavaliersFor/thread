package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class CartQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14702440737441L;

	private Long[] idArray;// 主键id
	private Long buyerId;// 买家id
	private Long productId;// 商品id
	private Integer status;// 状态 1：未结算 2：已结算
	private Integer[] statusArray;// 状态 1：未结算 2：已结算
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Long skuId;// sku id
	private Long shopId;// 店铺id
	private Integer num;// 数量

	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/*** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/*** 商品id */
	public Long getProductId() {
		return productId;
	}

	/*** 商品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/*** 状态 1：未结算 2：已结算 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态 1：未结算 2：已结算 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态 1：未结算 2：已结算 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态 1：未结算 2：已结算 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
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

	/*** sku id */
	public Long getSkuId() {
		return skuId;
	}

	/*** sku id */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/*** 店铺id */
	public Long getShopId() {
		return shopId;
	}

	/*** 店铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/*** 数量 */
	public Integer getNum() {
		return num;
	}

	/*** 数量 */
	public void setNum(Integer num) {
		this.num = num;
	}
}
