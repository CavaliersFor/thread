package com.lunjar.ebp.bizsupport.exception;

import com.lunjar.products.core.exception.ServiceException;

/**
 * 异常处理
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月10日下午3:20:05
 */
public class BizSupportException extends ServiceException {
	private static final long serialVersionUID = 7623788772408639282L;
	private int resCode;	

	public BizSupportException(int resCode, Throwable cause) {
		super(resCode, cause);
	}

	public BizSupportException(int resCode, String message, Throwable cause) {
		super(resCode, message);
	}

	public BizSupportException(int resCode, String message) {
		super(message);
		setResCode(resCode);
	}

	public String getMessage() {
		StringBuilder msg = new StringBuilder(256);
		msg.append(super.getMessage());
		return msg.toString();
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}
	
}
