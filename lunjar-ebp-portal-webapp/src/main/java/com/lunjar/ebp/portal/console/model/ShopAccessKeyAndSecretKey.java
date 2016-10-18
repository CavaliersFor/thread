package com.lunjar.ebp.portal.console.model;

import java.io.Serializable;

import com.lunjar.products.core.model.OperatorAgent;

/**
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年10月8日上午10:12:13
 */

public class ShopAccessKeyAndSecretKey implements Serializable {
	
	private static final long serialVersionUID = -449817437546453594L;
	
	private String accessKey;
	private String secretKey;
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
