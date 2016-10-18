package com.lunjar.ebp.admin.biz.exception;

import com.lunjar.products.core.exception.ServiceException;


public class UserExistsException extends ServiceException {
	private static final long serialVersionUID = 8567828274353357958L;

	public UserExistsException(String account) {
		super("非常抱歉，已经存在账号为 <span style='color:#ff3300;'>[" + account + "]</span> 的用户，<br/>请使用其他账号！");
	}
}
