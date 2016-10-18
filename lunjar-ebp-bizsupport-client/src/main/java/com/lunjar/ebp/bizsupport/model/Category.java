package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Category implements Serializable {
	private static final long serialVersionUID = 14708643535342L;

	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：使用中2：停止使用
	private String name;// 类目名称
	private Long shopId;// 商铺id
	private Integer sortNum;// 排序(按此顺序)
	private Long parentId;// 父类目id
	private String slidesUrls;//图片url地址以及排序url地址与排序值和产品id,图片跳转地址之间用,号隔开，然后每个之间用;隔开

	public Category() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Category(Long id) {
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

	/** 状态1：使用中2：停止使用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：使用中2：停止使用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 类目名称 */
	public String getName() {
		return name;
	}

	/** 类目名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/** 排序(按此顺序) */
	public Integer getSortNum() {
		return sortNum;
	}

	/** 排序(按此顺序) */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/** 父类目id */
	public Long getParentId() {
		return parentId;
	}

	/** 父类目id */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/***/
	public String getSlidesUrls() {
		return slidesUrls;
	}

	/***/
	public void setSlidesUrls(String slidesUrls) {
		this.slidesUrls = slidesUrls;
	}

	@Override
	public String toString() {
		return "Category [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", name=" + name + ", shopId=" + shopId + ", sortNum=" + sortNum + ", parentId=" + parentId
				+ ", slidesUrls=" + slidesUrls + "]";
	}
}
