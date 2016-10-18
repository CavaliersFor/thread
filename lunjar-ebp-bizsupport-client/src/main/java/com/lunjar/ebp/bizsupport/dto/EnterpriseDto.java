package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;

public class EnterpriseDto implements Serializable{
	private String account;
	private String random;
	private String validateCode;
	private String sign;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "EnterpriseDto [account=" + account + ", random=" + random + ", validateCode=" + validateCode + ", sign="
				+ sign + "]";
	}
}
