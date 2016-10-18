package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class ShopIndex implements Serializable {
	private static final long serialVersionUID = 14730979710842L;

	private Long id;// 主键id
	private String picUrls;// 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
							// 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?categoryId=3
	private Integer status;// 状态1：正常 2：停止
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer type;// 类型1：轮播图2：大图3：推荐产品4：热卖产品5:产品类目(自定义)
	private Long shopId;// 商铺id
	private Integer sortNum;// 排序
	private String name;// 名称(针对类型3来动态设置类型名称)

	public ShopIndex() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public ShopIndex(Long id) {
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

	/**
	 * 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
	 * 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?
	 * categoryId=3
	 */
	public String getPicUrls() {
		return picUrls;
	}

	/**
	 * 图片url地址以及排序url地址与排序值和商铺id之间用,号隔开，然后每个之间用;隔开.
	 * 例如：0/1/0/1473211400641.jpg,1;0/1/0/1473211497188.jpg,2,/product/list?
	 * categoryId=3
	 */
	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	/** 状态1：正常 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常 2：停止 */
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

	/** 类型1：轮播图2：大图3：推荐产品4：热卖产品 */
	public Integer getType() {
		return type;
	}

	/** 类型1：轮播图2：大图3：推荐产品4：热卖产品 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/** 排序 */
	public Integer getSortNum() {
		return sortNum;
	}

	/** 排序 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/** 名称(针对类型3来动态设置类型名称) */
	public String getName() {
		return name;
	}

	/** 名称(针对类型3来动态设置类型名称) */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ShopIndex [ id=" + id + ", picUrls=" + picUrls + ", status=" + status + ", gmtCreate=" + gmtCreate
				+ ", gmtModify=" + gmtModify + ", type=" + type + ", shopId=" + shopId + ", sortNum=" + sortNum
				+ ", name=" + name + "]";
	}
}
