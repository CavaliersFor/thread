package com.lunjar.ebp.admin.biz.exception;

import com.lunjar.products.core.exception.ServiceException;


public class PasswordWrongException extends ServiceException {

	private static final long serialVersionUID = -3920122616311002987L;

	public PasswordWrongException() {
		super("操作失败，原密码不正确！");
	}
}
