package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class CollectPlaceQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14739768438881L;

	private Long[] idArray;// 主键id
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer clpStatus;// 状态1：使用中 2：停止使用
	private String clpName;// 领取点名称
	private String clpAddress;// 领取点详细地址
	private String clpTelephone;// 领取点联系电话
	private Long enterpriseId;// 商家id
	private Integer collectTime;// 多少时间能取货单位小时，0就是可以及时取货
	private BigDecimal feeFrom;// 自提费用
	private BigDecimal feeTo;// 自提费用
	private String startTime;// 开始取货时间
	private String endTime;// 结束取货时间
	private Integer maxDepositDays;// 货物最多存放时间
	private String province;// 所属省
	private String city;// 所属市
	private String region;// 所属区

	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
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

	/*** 修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 状态1：使用中 2：停止使用 */
	public Integer getClpStatus() {
		return clpStatus;
	}

	/*** 状态1：使用中 2：停止使用 */
	public void setClpStatus(Integer clpStatus) {
		this.clpStatus = clpStatus;
	}

	/*** 领取点名称 */
	public String getClpName() {
		return clpName;
	}

	/*** 领取点名称 */
	public void setClpName(String clpName) {
		this.clpName = clpName;
	}

	/*** 领取点详细地址 */
	public String getClpAddress() {
		return clpAddress;
	}

	/*** 领取点详细地址 */
	public void setClpAddress(String clpAddress) {
		this.clpAddress = clpAddress;
	}

	/*** 领取点联系电话 */
	public String getClpTelephone() {
		return clpTelephone;
	}

	/*** 领取点联系电话 */
	public void setClpTelephone(String clpTelephone) {
		this.clpTelephone = clpTelephone;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 多少时间能取货单位小时，0就是可以及时取货 */
	public Integer getCollectTime() {
		return collectTime;
	}

	/*** 多少时间能取货单位小时，0就是可以及时取货 */
	public void setCollectTime(Integer collectTime) {
		this.collectTime = collectTime;
	}

	/*** 自提费用 */
	public BigDecimal getFeeFrom() {
		return feeFrom;
	}

	/*** 自提费用 */
	public void setFeeFrom(BigDecimal feeFrom) {
		this.feeFrom = feeFrom;
	}

	/*** 自提费用 */
	public BigDecimal getFeeTo() {
		return feeTo;
	}

	/*** 自提费用 */
	public void setFeeTo(BigDecimal feeTo) {
		this.feeTo = feeTo;
	}

	/*** 开始取货时间 */
	public String getStartTime() {
		return startTime;
	}

	/*** 开始取货时间 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/*** 结束取货时间 */
	public String getEndTime() {
		return endTime;
	}

	/*** 结束取货时间 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/*** 货物最多存放时间 */
	public Integer getMaxDepositDays() {
		return maxDepositDays;
	}

	/*** 货物最多存放时间 */
	public void setMaxDepositDays(Integer maxDepositDays) {
		this.maxDepositDays = maxDepositDays;
	}

	/*** 所属省 */
	public String getProvince() {
		return province;
	}

	/*** 所属省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/*** 所属市 */
	public String getCity() {
		return city;
	}

	/*** 所属市 */
	public void setCity(String city) {
		this.city = city;
	}

	/*** 所属区 */
	public String getRegion() {
		return region;
	}

	/*** 所属区 */
	public void setRegion(String region) {
		this.region = region;
	}
}
