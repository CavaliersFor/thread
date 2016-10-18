package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;

public class ShopIndexParams implements Serializable, Comparable<ShopIndexParams> {

	private static final long serialVersionUID = 5801693130986197661L;

	private String url;//图片路径
	private Integer sortNum;//排序
	private String skipUrl;//跳转地址，如果为空就是调到产品详情
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	public String getSkipUrl() {
		return skipUrl;
	}
	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}
	/**
	 * 按照sortNum排序
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午4:11:27
	 * @param arg0
	 * @return
	 */
	public int compareTo(ShopIndexParams arg0) {
		// TODO Auto-generated method stub
		return this.getSortNum().compareTo(arg0.getSortNum());
	}
	
}
