package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.ebp.bizsupport.model.CollectPlace;

public class CollectPlaceDto implements Serializable {

	private static final long serialVersionUID = -7667293712112680997L;
	
	private Long id;// 主键id
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer clpStatus;// 状态1：使用中 2：停止使用
	private String clpName;// 领取点名称
	private String clpAddress;// 领取点详细地址
	private String clpTelephone;// 领取点联系电话
	private Long enterpriseId;// 商家id
	private Integer collectTime;// 多少时间能取货单位小时，0就是可以及时取货
	private BigDecimal fee;// 自提费用
	private String startTime;// 开始取货时间
	private String endTime;// 结束取货时间
	private Integer maxDepositDays;// 货物最多存放时间
	private Date collectStartTime;//提货最早时间
	private Date collectEndTime;//提货最晚时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	public Integer getClpStatus() {
		return clpStatus;
	}
	public void setClpStatus(Integer clpStatus) {
		this.clpStatus = clpStatus;
	}
	public String getClpName() {
		return clpName;
	}
	public void setClpName(String clpName) {
		this.clpName = clpName;
	}
	public String getClpAddress() {
		return clpAddress;
	}
	public void setClpAddress(String clpAddress) {
		this.clpAddress = clpAddress;
	}
	public String getClpTelephone() {
		return clpTelephone;
	}
	public void setClpTelephone(String clpTelephone) {
		this.clpTelephone = clpTelephone;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Integer collectTime) {
		this.collectTime = collectTime;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getMaxDepositDays() {
		return maxDepositDays;
	}
	public void setMaxDepositDays(Integer maxDepositDays) {
		this.maxDepositDays = maxDepositDays;
	}
	public Date getCollectStartTime() {
		return collectStartTime;
	}
	public void setCollectStartTime(Date collectStartTime) {
		this.collectStartTime = collectStartTime;
	}
	public Date getCollectEndTime() {
		return collectEndTime;
	}
	public void setCollectEndTime(Date collectEndTime) {
		this.collectEndTime = collectEndTime;
	}
	
}
