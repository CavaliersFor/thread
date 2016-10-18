package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;

public class LoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423208974274961386L;
	private String account;
	private String random;
	private String validateCode;
	private String sign;

	public LoginDto(){}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
