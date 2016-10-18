package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;

public class TradeRefundParams implements Serializable {

	private Long buyerId;// 买家id
	private Integer refundStatus;// 退款状态

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public TradeRefundParams(Long buyerId, Integer refundStatus) {
		this.buyerId = buyerId;
		this.refundStatus = refundStatus;
	}

	public TradeRefundParams() {
	}

}
