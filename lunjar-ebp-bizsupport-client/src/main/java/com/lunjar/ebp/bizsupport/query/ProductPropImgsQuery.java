package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class ProductPropImgsQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14698403371751L;

	private Long[] idArray;// 主键
	private Long productId;// 产品id
	private Integer status;// 状态1:显示2:屏蔽3:删除
	private Integer[] statusArray;// 状态1:显示2:屏蔽3:删除
	private String picPath;// 图片显示路径
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer sortNum;// 图片显示的顺序,按值由小到大显示
	private Integer type;// 图片类型

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 产品id */
	public Long getProductId() {
		return productId;
	}

	/*** 产品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/*** 状态1:显示2:屏蔽3:删除 */
	public Integer getStatus() {
		return status;
	}

	/*** 状态1:显示2:屏蔽3:删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 状态1:显示2:屏蔽3:删除 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 状态1:显示2:屏蔽3:删除 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 图片显示路径 */
	public String getPicPath() {
		return picPath;
	}

	/*** 图片显示路径 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
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

	/*** 图片显示的顺序,按值由小到大显示 */
	public Integer getSortNum() {
		return sortNum;
	}

	/*** 图片显示的顺序,按值由小到大显示 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/*** 图片类型 */
	public Integer getType() {
		return type;
	}

	/*** 图片类型 */
	public void setType(Integer type) {
		this.type = type;
	}
}
