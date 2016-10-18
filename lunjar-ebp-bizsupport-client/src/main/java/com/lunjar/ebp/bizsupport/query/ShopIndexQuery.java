package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ShopIndexQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14730979710841L;

	private Long[] idArray;// 主键id
	private String picUrls;// 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
							// 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?categoryId=3
	private Integer status;// 状态1：正常 2：停止
	private Integer[] statusArray;// 状态1：正常 2：停止
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer type;// 类型1：轮播图2：大图3：推荐产品4：热卖产品
	private Long shopId;// 商铺id
	private Integer sortNum;// 排序
	private String name;// 名称(针对类型3来动态设置类型名称)
	private Integer[] typeArray;//类型
	
	
	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/***
	 * 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
	 * 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?
	 * categoryId=3
	 */
	public String getPicUrls() {
		return picUrls;
	}

	/***
	 * 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
	 * 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?
	 * categoryId=3
	 */
	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	/*** 状态1：正常 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常 2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常 2：停止 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常 2：停止 */
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

	/*** 类型1：轮播图2：大图3：推荐产品4：热卖产品 */
	public Integer getType() {
		return type;
	}

	/*** 类型1：轮播图2：大图3：推荐产品4：热卖产品 */
	public void setType(Integer type) {
		this.type = type;
	}

	/*** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/*** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/*** 排序 */
	public Integer getSortNum() {
		return sortNum;
	}

	/*** 排序 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/*** 名称(针对类型3来动态设置类型名称) */
	public String getName() {
		return name;
	}

	/*** 名称(针对类型3来动态设置类型名称) */
	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getTypeArray() {
		return typeArray;
	}

	public void setTypeArray(Integer[] typeArray) {
		this.typeArray = typeArray;
	}
}
