package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class Refund implements Serializable {
	private static final long serialVersionUID = 14715586476732L;

	private Long id;// 主键
	private Long ordersId;// 订单id
	private BigDecimal price;// 退款金额
	private Integer type;// 状态1：退款申请中2：拒绝退款 3：退款完成
	private Long enterpriseId;// 商家id
	private String enterpriseName;// 商家名称
	private Long buyerId;// 买家id
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 更新时间
	private String reason;// 退款理由
	private String refuseReason;// 商家拒绝原因
	private String buyerPhone;// 买家联系电话

	public Refund() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Refund(Long id) {
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

	/** 订单id */
	public Long getOrdersId() {
		return ordersId;
	}

	/** 订单id */
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	/** 退款金额 */
	public BigDecimal getPrice() {
		return price;
	}

	/** 退款金额 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/** 状态1：退款申请中2：拒绝退款 3：退款完成 */
	public Integer getType() {
		return type;
	}

	/** 状态1：退款申请中2：拒绝退款 3：退款完成 */
	public void setType(Integer type) {
		this.type = type;
	}

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 商家名称 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/** 商家名称 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/** 创建时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 创建时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 更新时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 更新时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 退款理由 */
	public String getReason() {
		return reason;
	}

	/** 退款理由 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/** 商家拒绝原因 */
	public String getRefuseReason() {
		return refuseReason;
	}

	/** 商家拒绝原因 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	/** 买家联系电话 */
	public String getBuyerPhone() {
		return buyerPhone;
	}

	/** 买家联系电话 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	@Override
	public String toString() {
		return "Refund [ id=" + id + ", ordersId=" + ordersId + ", price=" + price + ", type=" + type
				+ ", enterpriseId=" + enterpriseId + ", enterpriseName=" + enterpriseName + ", buyerId=" + buyerId
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", reason=" + reason + ", refuseReason="
				+ refuseReason + ", buyerPhone=" + buyerPhone + "]";
	}
}
