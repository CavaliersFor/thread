package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 */
public class LogisticsCompany implements Serializable {
	private static final long serialVersionUID = 14741378167402L;

	private Long id;// 主键id
	private String code;// 编码
	private String name;// 公司名称
	private Integer status;// 状态1：正常2：停用
	private Date gmtCreate;// 创建时间
	private Date gmtModify;// 修改时间

	public LogisticsCompany() {
	}

	/**
	 *
	 * @param id
	 *            -- 主键id
	 */
	public LogisticsCompany(Long id) {
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

	/** 编码 */
	public String getCode() {
		return code;
	}

	/** 编码 */
	public void setCode(String code) {
		this.code = code;
	}

	/** 公司名称 */
	public String getName() {
		return name;
	}

	/** 公司名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 状态1：正常2：停用 */
	public Integer getStatus() {
		return status;
	}

	/** 状态1：正常2：停用 */
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
		return "LogisticsCompany [ id=" + id + ", code=" + code + ", name=" + name + ", status=" + status
				+ ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + "]";
	}
}
