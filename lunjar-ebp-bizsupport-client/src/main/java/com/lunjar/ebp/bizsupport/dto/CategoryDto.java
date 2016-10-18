package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = -4146477195292542954L;
	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：使用中2：停止使用
	private String name;// 类目名称
	private Long shopId;// 商铺id
	private Integer sortNum;// 排序(按此顺序)
	private Long parentId;// 父类目
	private List<ShopIndexParams> list;// 轮转图属性list

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<ShopIndexParams> getList() {
		return list;
	}

	public void setList(List<ShopIndexParams> list) {
		this.list = list;
	}
}
