package com.lunjar.ebp.admin.domain.dto;

import java.io.Serializable;

public class ProductPValue implements Serializable{
	// [{pValue: 'p1', vValue: 'v1', vName: '卡奇色'
	
	private String pValue;
	private String vValue;
	private String vName;
	
	public String getpValue() {
		return pValue;
	}
	public void setpValue(String pValue) {
		this.pValue = pValue;
	}
	public String getvValue() {
		return vValue;
	}
	public void setvValue(String vValue) {
		this.vValue = vValue;
	}
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	public ProductPValue(String pValue, String vValue, String vName) {
		this.pValue = pValue;
		this.vValue = vValue;
		this.vName = vName;
	}
	public ProductPValue() {
	}
}
