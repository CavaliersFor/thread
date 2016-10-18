package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class EnterpriseLogisticsQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14733777767951L;

	private Long[] idArray;// 主键id
	private Long enterpriseId;// 商家id
	private Long logisticsId;// 物流公司id
	private String logisticsCode;// 物流公司编码
	private String logisticsName;// 物流公司名称
	private Integer status;// 状态1：正常2：停用
	private Integer[] statusArray;// 状态1：正常2：停用
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间

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

	/*** 物流公司id */
	public Long getLogisticsId() {
		return logisticsId;
	}

	/*** 物流公司id */
	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}

	/*** 物流公司编码 */
	public String getLogisticsCode() {
		return logisticsCode;
	}

	/*** 物流公司编码 */
	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	/*** 物流公司名称 */
	public String getLogisticsName() {
		return logisticsName;
	}

	/*** 物流公司名称 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	/*** 状态1：正常2：停用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：正常2：停用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：正常2：停用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：正常2：停用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
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
}
