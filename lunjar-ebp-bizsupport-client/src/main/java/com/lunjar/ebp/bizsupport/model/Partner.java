package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class Partner implements Serializable {
	private static final long serialVersionUID = 14702761021312L;

	private Long id;// 主键
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间
	private Integer status;// 状态1：接入中 2：停止
	private String name;// 合作者平台名称 如：微信公众号，某某APP

	public Partner() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键
	 */
	public Partner(Long id) {
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

	/** 状态1：接入中 2：停止 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：接入中 2：停止 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 合作者平台名称 如：微信公众号，某某APP */
	public String getName() {
		return name;
	}

	/** 合作者平台名称 如：微信公众号，某某APP */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Partner [ id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", status=" + status
				+ ", name=" + name + "]";
	}
}
