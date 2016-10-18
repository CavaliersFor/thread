package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class OutTradeNo implements Serializable {
	private static final long serialVersionUID = 14713827375502L;

	private String outTradeNo;// 主键
	private BigDecimal totalFee;// 支付金额
	private Integer status;// 状态
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private String tradeIds;// 订单tradeIds，多个订单用;(分号)隔开

	public OutTradeNo() {
	}

	/**
	 *
	 * @param outTradeNo
	 *            -- 主键
	 */
	public OutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/** 主键 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/** 主键 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/** 支付金额 */
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	/** 支付金额 */
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	/** 状态 */
	public Integer getStatus() {
		return status;
	}

	/** 状态 */
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

	/** 结束取货时间 */
	public String getTradeIds() {
		return tradeIds;
	}

	/** 结束取货时间 */
	public void setTradeIds(String tradeIds) {
		this.tradeIds = tradeIds;
	}

	@Override
	public String toString() {
		return "OutTradeNo [ outTradeNo=" + outTradeNo + ", totalFee=" + totalFee + ", status=" + status
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", tradeIds=" + tradeIds + "]";
	}
}
