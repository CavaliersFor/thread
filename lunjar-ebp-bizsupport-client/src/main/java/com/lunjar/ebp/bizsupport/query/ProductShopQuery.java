package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ProductShopQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14702488723501L;

	private Long[] idArray;// 主键
	private Long productId;// 商品id
	private Long shopId;// 商铺id
	private Long categoryId;// 自定义类id
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：正常2：删除
	private Integer[] statusArray;// 状态1：正常2：删除
	private Integer type;// 商品类型 1：普通商品 2：组合商品
	private Long[] pIdArray;//产品id数组

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

	/*** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/*** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/*** 自定义类id */
	public Long getCategoryId() {
		return categoryId;
	}

	/*** 自定义类id */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	/*** 状态1：正常2：删除 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常2：删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常2：删除 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常2：删除 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 商品类型 1：普通商品 2：组合商品 */
	public Integer getType() {
		return type;
	}

	/*** 商品类型 1：普通商品 2：组合商品 */
	public void setType(Integer type) {
		this.type = type;
	}

	public Long[] getpIdArray() {
		return pIdArray;
	}

	public void setpIdArray(Long[] pIdArray) {
		this.pIdArray = pIdArray;
	}
}
