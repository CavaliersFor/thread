package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Logistics implements Serializable {
	private static final long serialVersionUID = 14730495270782L;

	private Long id;// 主键
	private Long tradeId;// 订单id
	private String logisticsName;// 物流公司名称
	private String logisticsNo;// 物流单号
	private Integer status;// 物流状态1：未开始2：运输中3：已到达
	private Date sendTime;// 发货时间
	private String station;// 货物目前所处地点
	private Date arriveTime;// 预计到达时间
	private Date gmtCreate;// 物流起始时间
	private Date gmtModify;// 到达现在地点的时间
	private String logisticsPhone;// 查询货物的电话

	public Logistics() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Logistics(Long id) {
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
	public Long getTradeId() {
		return tradeId;
	}

	/** 订单id */
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	/** 物流公司名称 */
	public String getLogisticsName() {
		return logisticsName;
	}

	/** 物流公司名称 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	/** 物流单号 */
	public String getLogisticsNo() {
		return logisticsNo;
	}

	/** 物流单号 */
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	/** 物流状态1：未开始2：运输中3：已到达 */
	public Integer getStatus() {
		return status;
	}

	/** 物流状态1：未开始2：运输中3：已到达 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 发货时间 */
	public Date getSendTime() {
		return sendTime;
	}

	/** 发货时间 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/** 货物目前所处地点 */
	public String getStation() {
		return station;
	}

	/** 货物目前所处地点 */
	public void setStation(String station) {
		this.station = station;
	}

	/** 预计到达时间 */
	public Date getArriveTime() {
		return arriveTime;
	}

	/** 预计到达时间 */
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	/** 物流起始时间 */
	public Date getGmtCreate() {
		return gmtCreate;
	}

	/** 物流起始时间 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 到达现在地点的时间 */
	public Date getGmtModify() {
		return gmtModify;
	}

	/** 到达现在地点的时间 */
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	/** 查询货物的电话 */
	public String getLogisticsPhone() {
		return logisticsPhone;
	}

	/** 查询货物的电话 */
	public void setLogisticsPhone(String logisticsPhone) {
		this.logisticsPhone = logisticsPhone;
	}

	@Override
	public String toString() {
		return "Logistics [ id=" + id + ", tradeId=" + tradeId + ", logisticsName=" + logisticsName + ", logisticsNo="
				+ logisticsNo + ", status=" + status + ", sendTime=" + sendTime + ", station=" + station
				+ ", arriveTime=" + arriveTime + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify
				+ ", logisticsPhone=" + logisticsPhone + "]";
	}
}
