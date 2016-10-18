package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExpressInfo implements Serializable{
	private String addresss;//地址
	private Integer firstNum;//首件个数
	private BigDecimal firstPrice;//首件运费
	private Integer addNum;//续件
	private BigDecimal addPrice;//续件费用
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public Integer getFirstNum() {
		return firstNum;
	}
	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}
	public BigDecimal getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(BigDecimal firstPrice) {
		this.firstPrice = firstPrice;
	}
	public Integer getAddNum() {
		return addNum;
	}
	public void setAddNum(Integer addNum) {
		this.addNum = addNum;
	}
	public BigDecimal getAddPrice() {
		return addPrice;
	}
	public void setAddPrice(BigDecimal addPrice) {
		this.addPrice = addPrice;
	}
	
	
}
