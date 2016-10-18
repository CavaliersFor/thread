package com.lunjar.ebp.portal.console.model;

import java.io.Serializable;
import java.util.List;

public class SkuName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyEN;
	private String keyZN;
	private List<SkuValue> listSkuValue;
	public SkuName(String keyEN, String keyZN) {
		this.keyEN = keyEN;
		this.keyZN = keyZN;
	}
	public SkuName() {
	}
	public String getKeyEN() {
		return keyEN;
	}
	public void setKeyEN(String keyEN) {
		this.keyEN = keyEN;
	}
	public String getKeyZN() {
		return keyZN;
	}
	public void setKeyZN(String keyZN) {
		this.keyZN = keyZN;
	}
	public List<SkuValue> getListSkuValue() {
		return listSkuValue;
	}
	public void setListSkuValue(List<SkuValue> listSkuValue) {
		this.listSkuValue = listSkuValue;
	}
}
