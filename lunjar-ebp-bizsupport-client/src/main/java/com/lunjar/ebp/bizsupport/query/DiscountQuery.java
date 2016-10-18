package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class DiscountQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14749123310511L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：使用中 2：停止使用
	private Integer[] statusArray;// 状态1：使用中 2：停止使用
	private BigDecimal conditionsFrom;// 优惠条件
	private BigDecimal conditionsTo;// 优惠条件
	private BigDecimal discountFeeFrom;// 减免金额
	private BigDecimal discountFeeTo;// 减免金额
	private Long enterpriseId;// 商家id
	private Integer isPost;// 是否包邮 1是包邮
	private String freePostArea;// 包邮地区,多个地区之间用,分隔
	private String name;// 优惠活动名称

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
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

	/*** 状态1：使用中 2：停止使用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：使用中 2：停止使用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：使用中 2：停止使用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：使用中 2：停止使用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 优惠条件 */
	public BigDecimal getConditionsFrom() {
		return conditionsFrom;
	}

	/*** 优惠条件 */
	public void setConditionsFrom(BigDecimal conditionsFrom) {
		this.conditionsFrom = conditionsFrom;
	}

	/*** 优惠条件 */
	public BigDecimal getConditionsTo() {
		return conditionsTo;
	}

	/*** 优惠条件 */
	public void setConditionsTo(BigDecimal conditionsTo) {
		this.conditionsTo = conditionsTo;
	}

	/*** 减免金额 */
	public BigDecimal getDiscountFeeFrom() {
		return discountFeeFrom;
	}

	/*** 减免金额 */
	public void setDiscountFeeFrom(BigDecimal discountFeeFrom) {
		this.discountFeeFrom = discountFeeFrom;
	}

	/*** 减免金额 */
	public BigDecimal getDiscountFeeTo() {
		return discountFeeTo;
	}

	/*** 减免金额 */
	public void setDiscountFeeTo(BigDecimal discountFeeTo) {
		this.discountFeeTo = discountFeeTo;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 是否包邮 1是包邮 */
	public Integer getIsPost() {
		return isPost;
	}

	/*** 是否包邮 1是包邮 */
	public void setIsPost(Integer isPost) {
		this.isPost = isPost;
	}

	/*** 包邮地区,多个地区之间用,分隔 */
	public String getFreePostArea() {
		return freePostArea;
	}

	/*** 包邮地区,多个地区之间用,分隔 */
	public void setFreePostArea(String freePostArea) {
		this.freePostArea = freePostArea;
	}

	/*** 优惠活动名称 */
	public String getName() {
		return name;
	}

	/*** 优惠活动名称 */
	public void setName(String name) {
		this.name = name;
	}
}
