package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;

/**
 * 地区,邮编号码表实体类
 */
public class Region implements Serializable {
	private static final long serialVersionUID = 14703279252222L;

	private String code;// 按照国家规则的统一编码来
	private String parentCode;// 上一级地区的编码
	private String regionName;// 地区名字
	private Integer regionType;// 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区
	private Integer status;// 1、正常 -1、失效
	private String abbname;// 区域别名
	private String zip;// 邮编

	public Region() {
	}

	/**
	 *
	 * @param code
	 *            -- 按照国家规则的统一编码来
	 */
	public Region(String code) {
		this.code = code;
	}

	/** 按照国家规则的统一编码来 */
	public String getCode() {
		return code;
	}

	/** 按照国家规则的统一编码来 */
	public void setCode(String code) {
		this.code = code;
	}

	/** 上一级地区的编码 */
	public String getParentCode() {
		return parentCode;
	}

	/** 上一级地区的编码 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/** 地区名字 */
	public String getRegionName() {
		return regionName;
	}

	/** 地区名字 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/** 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区 */
	public Integer getRegionType() {
		return regionType;
	}

	/** 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区 */
	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	/** 1、正常 -1、失效 */
	public Integer getStatus() {
		return status;
	}

	/** 1、正常 -1、失效 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 区域别名 */
	public String getAbbname() {
		return abbname;
	}

	/** 区域别名 */
	public void setAbbname(String abbname) {
		this.abbname = abbname;
	}

	/** 邮编 */
	public String getZip() {
		return zip;
	}

	/** 邮编 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Region [ code=" + code + ", parentCode=" + parentCode + ", regionName=" + regionName + ", regionType="
				+ regionType + ", status=" + status + ", abbname=" + abbname + ", zip=" + zip + "]";
	}
}
