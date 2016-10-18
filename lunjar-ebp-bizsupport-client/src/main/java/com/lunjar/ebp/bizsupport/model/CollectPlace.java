package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 */
public class CollectPlace implements Serializable {
	private static final long serialVersionUID = 14739768438882L;

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
	private String province;// 所属省
	private String city;// 所属市
	private String region;// 所属区

	public CollectPlace() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public CollectPlace(Long id) {
		this.id = id;
	}

	/** 主键id */
	public Long getId() {
		return id;
	}

	/** 主键id */
	public void setId(Long id) {
		this.id = id;
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

	/** 状态1：使用中 2：停止使用 */
	public Integer getClpStatus() {
		return clpStatus;
	}

	/** 状态1：使用中 2：停止使用 */
	public void setClpStatus(Integer clpStatus) {
		this.clpStatus = clpStatus;
	}

	/** 领取点名称 */
	public String getClpName() {
		return clpName;
	}

	/** 领取点名称 */
	public void setClpName(String clpName) {
		this.clpName = clpName;
	}

	/** 领取点详细地址 */
	public String getClpAddress() {
		return clpAddress;
	}

	/** 领取点详细地址 */
	public void setClpAddress(String clpAddress) {
		this.clpAddress = clpAddress;
	}

	/** 领取点联系电话 */
	public String getClpTelephone() {
		return clpTelephone;
	}

	/** 领取点联系电话 */
	public void setClpTelephone(String clpTelephone) {
		this.clpTelephone = clpTelephone;
	}

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 多少时间能取货单位小时，0就是可以及时取货 */
	public Integer getCollectTime() {
		return collectTime;
	}

	/** 多少时间能取货单位小时，0就是可以及时取货 */
	public void setCollectTime(Integer collectTime) {
		this.collectTime = collectTime;
	}

	/** 自提费用 */
	public BigDecimal getFee() {
		return fee;
	}

	/** 自提费用 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/** 开始取货时间 */
	public String getStartTime() {
		return startTime;
	}

	/** 开始取货时间 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/** 结束取货时间 */
	public String getEndTime() {
		return endTime;
	}

	/** 结束取货时间 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/** 货物最多存放时间 */
	public Integer getMaxDepositDays() {
		return maxDepositDays;
	}

	/** 货物最多存放时间 */
	public void setMaxDepositDays(Integer maxDepositDays) {
		this.maxDepositDays = maxDepositDays;
	}

	/** 所属省 */
	public String getProvince() {
		return province;
	}

	/** 所属省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 所属市 */
	public String getCity() {
		return city;
	}

	/** 所属市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 所属区 */
	public String getRegion() {
		return region;
	}

	/** 所属区 */
	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "CollectPlace [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", clpStatus="
				+ clpStatus + ", clpName=" + clpName + ", clpAddress=" + clpAddress + ", clpTelephone=" + clpTelephone
				+ ", enterpriseId=" + enterpriseId + ", collectTime=" + collectTime + ", fee=" + fee + ", startTime="
				+ startTime + ", endTime=" + endTime + ", maxDepositDays=" + maxDepositDays + ", province=" + province
				+ ", city=" + city + ", region=" + region + "]";
	}
}
