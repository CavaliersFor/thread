package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.AdminAgent;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 登录状态服务类
 * @author Administrator
 *
 */
public interface SelfDeliveryLoginService{
	/**
	 * 登陆验证
	 * @param account 用户名
	 * @param sign	加密密码
	 * @param random	随机数
	 * @return
	 * @throws ServiceException 
	 */
	AdminAgent login(String account, String sign, String random) throws ServiceException;
}
