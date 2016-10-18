package com.lunjar.ebp.bizsupport.exception;

import com.lunjar.products.core.exception.ServiceException;

/**
 * 异常处理
 */
public class PasswordWrongException extends ServiceException {

	private static final long serialVersionUID = -3920122616311002987L;

	public PasswordWrongException() {
		super("操作失败，原密码不正确！");
	}
}