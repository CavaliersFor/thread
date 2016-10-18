package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lunjar.ebp.bizsupport.dto.ExpressInfo;

/**
 * 实体类
 */
public class Express implements Serializable {
	private static final long serialVersionUID = 14712457830692L;

	private Long id;// 主键id
	private Long enterpriseId;// 商家id
	private String ecPrice;// 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer ecStatus;// 状态1:正常2:停止
	private Integer valuation;// 计费方式：1：数量2：重量3：体积
	
	private List<ExpressInfo> listInfo;//拆分ecPrice字段
	
	public Express() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public Express(Long id) {
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

	/** 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式 */
	public String getEcPrice() {
		return ecPrice;
	}

	/** 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式 */
	public void setEcPrice(String ecPrice) {
		this.ecPrice = ecPrice;
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

	/** 状态1:正常2:停止 */
	public Integer getEcStatus() {
		return ecStatus;
	}

	/** 状态1:正常2:停止 */
	public void setEcStatus(Integer ecStatus) {
		this.ecStatus = ecStatus;
	}

	/** 计费方式：1：数量2：重量3：体积 */
	public Integer getValuation() {
		return valuation;
	}

	/** 计费方式：1：数量2：重量3：体积 */
	public void setValuation(Integer valuation) {
		this.valuation = valuation;
	}

	@Override
	public String toString() {
		return "Express [ id=" + id + ", enterpriseId=" + enterpriseId + ", ecPrice=" + ecPrice + ", gmtCreate="
				+ gmtCreate + ", gmtModify=" + gmtModify + ", ecStatus=" + ecStatus + ", valuation=" + valuation + "]";
	}

	public List<ExpressInfo> getListInfo() {
		return listInfo;
	}

	public void setListInfo(List<ExpressInfo> listInfo) {
		this.listInfo = listInfo;
	}
}
