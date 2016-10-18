package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体类
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 14748397789382L;

	private Long id;// 主键id
	private Long parentId;// 父类id
	private String name;// 类目名称
	private Integer level;// 类目等级
	private Integer status;// 状态
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private List<Item> items;//子集的元素
	
	
	
	public Item() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public Item(Long id) {
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

	/** 父类id */
	public Long getParentId() {
		return parentId;
	}

	/** 父类id */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 类目名称 */
	public String getName() {
		return name;
	}

	/** 类目名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 类目等级 */
	public Integer getLevel() {
		return level;
	}

	/** 类目等级 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/** 状态 */
	public Integer getStatus() {
		return status;
	}

	/** 状态 */
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
		return "Item [ id=" + id + ", parentId=" + parentId + ", name=" + name + ", level=" + level + ", status="
				+ status + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + "]";
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
