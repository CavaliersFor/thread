package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;

public class TradeParams implements Serializable {

	private static final long serialVersionUID = -1279382498407607874L;

	private Long tradeId; // 订单id
	private Long ownerId;// 拥有者id
	private Integer ownerType;// 拥有者类型 1：买家 2：商铺3：商家

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}
}
