package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class LogisticsQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14730495270781L;

	private Long[] idArray;// 主键
	private Long tradeId;// 订单id
	private String logisticsName;// 物流公司名称
	private String logisticsNo;// 物流单号
	private Integer status;// 物流状态1：未开始2：运输中3：已到达
	private Integer[] statusArray;// 物流状态1：未开始2：运输中3：已到达
	private Date sendTimeFrom;// 发货时间
	private Date sendTimeTo;// 发货时间
	private String station;// 货物目前所处地点
	private Date arriveTimeFrom;// 预计到达时间
	private Date arriveTimeTo;// 预计到达时间
	private Date gmtCreateFrom;// 物流起始时间
	private Date gmtCreateTo;// 物流起始时间
	private Date gmtModifyFrom;// 到达现在地点的时间
	private Date gmtModifyTo;// 到达现在地点的时间
	private String logisticsPhone;// 查询货物的电话

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 订单id */
	public Long getTradeId() {
		return tradeId;
	}

	/*** 订单id */
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	/*** 物流公司名称 */
	public String getLogisticsName() {
		return logisticsName;
	}

	/*** 物流公司名称 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	/*** 物流单号 */
	public String getLogisticsNo() {
		return logisticsNo;
	}

	/*** 物流单号 */
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	/*** 物流状态1：未开始2：运输中3：已到达 */
	public Integer getStatus() {
		return status;
	}

	/*** 物流状态1：未开始2：运输中3：已到达 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 物流状态1：未开始2：运输中3：已到达 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 物流状态1：未开始2：运输中3：已到达 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 发货时间 */
	public Date getSendTimeFrom() {
		return sendTimeFrom;
	}

	/*** 发货时间 */
	public void setSendTimeFrom(Date sendTimeFrom) {
		this.sendTimeFrom = sendTimeFrom;
	}

	/*** 发货时间 */
	public Date getSendTimeTo() {
		return sendTimeTo;
	}

	/*** 发货时间 */
	public void setSendTimeTo(Date sendTimeTo) {
		this.sendTimeTo = sendTimeTo;
	}

	/*** 货物目前所处地点 */
	public String getStation() {
		return station;
	}

	/*** 货物目前所处地点 */
	public void setStation(String station) {
		this.station = station;
	}

	/*** 预计到达时间 */
	public Date getArriveTimeFrom() {
		return arriveTimeFrom;
	}

	/*** 预计到达时间 */
	public void setArriveTimeFrom(Date arriveTimeFrom) {
		this.arriveTimeFrom = arriveTimeFrom;
	}

	/*** 预计到达时间 */
	public Date getArriveTimeTo() {
		return arriveTimeTo;
	}

	/*** 预计到达时间 */
	public void setArriveTimeTo(Date arriveTimeTo) {
		this.arriveTimeTo = arriveTimeTo;
	}

	/*** 物流起始时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 物流起始时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 物流起始时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 物流起始时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 到达现在地点的时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 到达现在地点的时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 到达现在地点的时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 到达现在地点的时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 查询货物的电话 */
	public String getLogisticsPhone() {
		return logisticsPhone;
	}

	/*** 查询货物的电话 */
	public void setLogisticsPhone(String logisticsPhone) {
		this.logisticsPhone = logisticsPhone;
	}
}
