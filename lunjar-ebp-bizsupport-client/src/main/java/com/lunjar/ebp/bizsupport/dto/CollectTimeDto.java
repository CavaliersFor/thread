package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.ebp.bizsupport.model.ProductSku;

public class CollectTimeDto implements Serializable {

	private static final long serialVersionUID = 7210457772528197887L;

//	private Long CollectPlaceId;//自提点id
	private Long tradeId;//订单id
//	private Long enterpriseId;//商家id
	private Date startTime;//最早时间
	private Date endTime;//最晚时间
//	public Long getCollectPlaceId() {
//		return CollectPlaceId;
//	}
//	public void setCollectPlaceId(Long collectPlaceId) {
//		CollectPlaceId = collectPlaceId;
//	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
//	public Long getEnterpriseId() {
//		return enterpriseId;
//	}
//	public void setEnterpriseId(Long enterpriseId) {
//		this.enterpriseId = enterpriseId;
//	}
}
