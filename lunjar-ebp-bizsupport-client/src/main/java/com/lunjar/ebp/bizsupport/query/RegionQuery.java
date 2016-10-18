package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 地区,邮编号码表查询对象
 */
public class RegionQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14703279252221L;

	private String[] codeArray;// 按照国家规则的统一编码来
	private String parentCode;// 上一级地区的编码
	private String regionName;// 地区名字
	private Integer regionType;// 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区
	private Integer status;// 1、正常 -1、失效
	private Integer[] statusArray;// 1、正常 -1、失效
	private String abbname;// 区域别名
	private String zip;// 邮编

	/*** 按照国家规则的统一编码来 */
	public String[] getCodeArray() {
		return codeArray;
	}

	/*** 按照国家规则的统一编码来 */
	public void setCodeArray(String... codeArray) {
		this.codeArray = codeArray;
	}

	/*** 上一级地区的编码 */
	public String getParentCode() {
		return parentCode;
	}

	/*** 上一级地区的编码 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/*** 地区名字 */
	public String getRegionName() {
		return regionName;
	}

	/*** 地区名字 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	/*** 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区 */
	public Integer getRegionType() {
		return regionType;
	}

	/*** 地区类型，0：国家，1： 省份/直辖市，2：市，3：县/区 */
	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	/*** 1、正常 -1、失效 */
	public Integer getStatus() {
		return status;
	}

	/*** 1、正常 -1、失效 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/*** 1、正常 -1、失效 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/*** 1、正常 -1、失效 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 区域别名 */
	public String getAbbname() {
		return abbname;
	}

	/*** 区域别名 */
	public void setAbbname(String abbname) {
		this.abbname = abbname;
	}

	/*** 邮编 */
	public String getZip() {
		return zip;
	}

	/*** 邮编 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
