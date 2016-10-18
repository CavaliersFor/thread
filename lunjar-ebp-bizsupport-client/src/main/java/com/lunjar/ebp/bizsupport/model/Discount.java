package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class Discount implements Serializable {
	private static final long serialVersionUID = 14749123310512L;

	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：使用中 2：停止使用
	private BigDecimal conditions;// 优惠条件
	private BigDecimal discountFee;// 减免金额
	private Long enterpriseId;// 商家id
	private Integer isPost;// 是否包邮 1是包邮
	private String freePostArea;// 包邮地区,多个地区之间用,分隔
	private String name;// 优惠活动名称

	public Discount() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Discount(Long id) {
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

	/** 状态1：使用中 2：停止使用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：使用中 2：停止使用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 优惠条件 */
	public BigDecimal getConditions() {
		return conditions;
	}

	/** 优惠条件 */
	public void setConditions(BigDecimal conditions) {
		this.conditions = conditions;
	}

	/** 减免金额 */
	public BigDecimal getDiscountFee() {
		return discountFee;
	}

	/** 减免金额 */
	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 是否包邮 1是包邮 */
	public Integer getIsPost() {
		return isPost;
	}

	/** 是否包邮 1是包邮 */
	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	/** 包邮地区,多个地区之间用,分隔 */
	public String getFreePostArea() {
		return freePostArea;
	}

	/** 包邮地区,多个地区之间用,分隔 */
	public void setFreePostArea(String freePostArea) {
		this.freePostArea = freePostArea;
	}

	/** 优惠活动名称 */
	public String getName() {
		return name;
	}

	/** 优惠活动名称 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Discount [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", conditions=" + conditions + ", discountFee=" + discountFee + ", enterpriseId=" + enterpriseId
				+ ", isPost=" + isPost + ", freePostArea=" + freePostArea + ", name=" + name + "]";
	}
}
