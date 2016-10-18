package com.lunjar.ebp.bizsupport.enums;

/**
 * 物流状态1：未开始2：运输中3：已到达
 * 
 * @author Administrator
 *
 */
public enum EnumLogistics {
	/**
	 * 未开始
	 */
	WAIT_START(1,"未开始"),
	
	/**
	 * 运输中
	 */
	STARTING(2,"运输中"),
	/**
	 * 已到达
	 */
	END(3,"已到达");
	
	
	EnumLogistics(Integer value,String text){
		this.value = value;
		this.text = text;
	}
	
	
	private Integer value;
	private String text;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
