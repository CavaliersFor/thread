package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class OutTradeNoQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14713827375501L;

	private String[] outTradeNoArray;// 主键
	private BigDecimal totalFeeFrom;// 支付金额
	private BigDecimal totalFeeTo;// 支付金额
	private Integer status;// 状态
	private Integer[] statusArray;// 状态
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private String tradeIds;// 结束取货时间

	/*** 主键 */
	public String[] getOutTradeNoArray() {
		return outTradeNoArray;
	}

	/*** 主键 */
	public void setOutTradeNoArray(String... outTradeNoArray) {
		this.outTradeNoArray = outTradeNoArray;
	}

	/*** 支付金额 */
	public BigDecimal getTotalFeeFrom() {
		return totalFeeFrom;
	}

	/*** 支付金额 */
	public void setTotalFeeFrom(BigDecimal totalFeeFrom) {
		this.totalFeeFrom = totalFeeFrom;
	}

	/*** 支付金额 */
	public BigDecimal getTotalFeeTo() {
		return totalFeeTo;
	}

	/*** 支付金额 */
	public void setTotalFeeTo(BigDecimal totalFeeTo) {
		this.totalFeeTo = totalFeeTo;
	}

	/*** 状态 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态 */
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

	/*** 结束取货时间 */
	public String getTradeIds() {
		return tradeIds;
	}

	/*** 结束取货时间 */
	public void setTradeIds(String tradeIds) {
		this.tradeIds = tradeIds;
	}
}
