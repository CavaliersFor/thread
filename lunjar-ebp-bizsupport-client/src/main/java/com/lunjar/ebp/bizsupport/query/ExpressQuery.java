package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ExpressQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14712457830691L;

	private Long[] idArray;// 主键id
	private Long enterpriseId;// 商家id
	private String ecPrice;// 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer ecStatus;// 状态1:正常2:停止
	private Integer valuation;// 计费方式：1：数量2：重量3：体积

	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式 */
	public String getEcPrice() {
		return ecPrice;
	}

	/*** 快递费用字符串 中山,东莞,顺德:5,长沙;厦门:10这种方式 */
	public void setEcPrice(String ecPrice) {
		this.ecPrice = ecPrice;
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

	/*** 状态1:正常2:停止 */
	public Integer getEcStatus() {
		return ecStatus;
	}

	/*** 状态1:正常2:停止 */
	public void setEcStatus(Integer ecStatus) {
		this.ecStatus = ecStatus;
	}

	/*** 计费方式：1：数量2：重量3：体积 */
	public Integer getValuation() {
		return valuation;
	}

	/*** 计费方式：1：数量2：重量3：体积 */
	public void setValuation(Integer valuation) {
		this.valuation = valuation;
	}
}
