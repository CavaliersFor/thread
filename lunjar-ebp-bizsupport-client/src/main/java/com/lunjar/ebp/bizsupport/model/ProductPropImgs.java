package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class ProductPropImgs implements Serializable {
	private static final long serialVersionUID = 14698403371752L;

	private Long id;// 主键
	private Long productId;// 产品id
	private Integer status;// 状态1:显示2:屏蔽3:删除
	private String picPath;// 图片显示路径
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer sortNum;// 图片显示的顺序,按值由小到大显示
	private Integer type;// 图片类型
	private String relativePath;//相对路径
	
	public ProductPropImgs() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public ProductPropImgs(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return id;
	}

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 产品id */
	public Long getProductId() {
		return productId;
	}

	/** 产品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/** 状态1:显示2:屏蔽3:删除 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1:显示2:屏蔽3:删除 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 图片显示路径 */
	public String getPicPath() {
		return picPath;
	}

	/** 图片显示路径 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
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

	/** 图片显示的顺序,按值由小到大显示 */
	public Integer getSortNum() {
		return sortNum;
	}

	/** 图片显示的顺序,按值由小到大显示 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/** 图片类型 */
	public Integer getType() {
		return type;
	}

	/** 图片类型 */
	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductPropImgs [ id=" + id + ", productId=" + productId + ", status=" + status + ", picPath=" + picPath
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", sortNum=" + sortNum + ", type=" + type
				+ "]";
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
}
