package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class RefundQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14715586476731L;

	private Long[] idArray;// 主键
	private Long ordersId;// 订单id
	private BigDecimal priceFrom;// 退款金额
	private BigDecimal priceTo;// 退款金额
	private Integer type;// 状态1：退款申请中2：拒绝退款 3：退款完成
	private Long enterpriseId;// 商家id
	private String enterpriseName;// 商家名称
	private Long buyerId;// 买家id
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 更新时间
	private Date gmtModifyTo;// 更新时间
	private String reason;// 退款理由
	private String refuseReason;// 商家拒绝原因
	private String buyerPhone;// 买家联系电话

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 订单id */
	public Long getOrdersId() {
		return ordersId;
	}

	/*** 订单id */
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	/*** 退款金额 */
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	/*** 退款金额 */
	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	/*** 退款金额 */
	public BigDecimal getPriceTo() {
		return priceTo;
	}

	/*** 退款金额 */
	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	/*** 状态1：退款申请中2：拒绝退款 3：退款完成 */
	public Integer getType() {
		return type;
	}

	/*** 状态1：退款申请中2：拒绝退款 3：退款完成 */
	public void setType(Integer type) {
		this.type = type;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 商家名称 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/*** 商家名称 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/*** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/*** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
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

	/*** 更新时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 更新时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 更新时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 更新时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 退款理由 */
	public String getReason() {
		return reason;
	}

	/*** 退款理由 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/*** 商家拒绝原因 */
	public String getRefuseReason() {
		return refuseReason;
	}

	/*** 商家拒绝原因 */
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	/*** 买家联系电话 */
	public String getBuyerPhone() {
		return buyerPhone;
	}

	/*** 买家联系电话 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
}
