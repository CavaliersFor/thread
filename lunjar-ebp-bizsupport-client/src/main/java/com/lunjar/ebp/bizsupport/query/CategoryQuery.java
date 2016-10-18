package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class CategoryQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14708643535341L;

	private Long[] idArray;// 主键
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 状态1：使用中2：停止使用
	private Integer[] statusArray;// 状态1：使用中2：停止使用
	private String name;// 类目名称
	private Long shopId;// 商铺id
	private Integer sortNum;// 排序(按此顺序)
	private Long parentId;// 父类目id
	private String slidesUrls;//

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
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

	/*** 状态1：使用中2：停止使用 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1：使用中2：停止使用 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1：使用中2：停止使用 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1：使用中2：停止使用 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 类目名称 */
	public String getName() {
		return name;
	}

	/*** 类目名称 */
	public void setName(String name) {
		this.name = name;
	}

	/*** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/*** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/*** 排序(按此顺序) */
	public Integer getSortNum() {
		return sortNum;
	}

	/*** 排序(按此顺序) */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/*** 父类目id */
	public Long getParentId() {
		return parentId;
	}

	/*** 父类目id */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/****/
	public String getSlidesUrls() {
		return slidesUrls;
	}

	/****/
	public void setSlidesUrls(String slidesUrls) {
		this.slidesUrls = slidesUrls;
	}
}
