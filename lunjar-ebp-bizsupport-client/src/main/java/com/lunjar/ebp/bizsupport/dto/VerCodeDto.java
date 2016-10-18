package com.lunjar.ebp.bizsupport.dto;

public class VerCodeDto implements java.io.Serializable{
	private String verCode;
	private Long timeNum;
	private String message;
	
	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public Long getTimeNum() {
		return timeNum;
	}

	public void setTimeNum(Long timeNum) {
		this.timeNum = timeNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "VerCodeDto [verCode=" + verCode + ", timeNum=" + timeNum + ", message=" + message + "]";
	}
}
