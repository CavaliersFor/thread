package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class EnterpriseLogistics implements Serializable {
	private static final long serialVersionUID = 14733777767952L;

	private Long id;// 主键id
	private Long enterpriseId;// 商家id
	private Long logisticsId;// 物流公司id
	private String logisticsCode;// 物流公司编码
	private String logisticsName;// 物流公司名称
	private Integer status;// 状态1：正常2：停用
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间

	public EnterpriseLogistics() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public EnterpriseLogistics(Long id) {
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

	/** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** 物流公司id */
	public Long getLogisticsId() {
		return logisticsId;
	}

	/** 物流公司id */
	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}

	/** 物流公司编码 */
	public String getLogisticsCode() {
		return logisticsCode;
	}

	/** 物流公司编码 */
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	/** 物流公司名称 */
	public String getLogisticsName() {
		return logisticsName;
	}

	/** 物流公司名称 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	/** 状态1：正常2：停用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常2：停用 */
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

	@Override
	public String toString() {
		return "EnterpriseLogistics [ id=" + id + ", enterpriseId=" + enterpriseId + ", logisticsId=" + logisticsId
				+ ", logisticsCode=" + logisticsCode + ", logisticsName=" + logisticsName + ", status=" + status
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + "]";
	}
}
