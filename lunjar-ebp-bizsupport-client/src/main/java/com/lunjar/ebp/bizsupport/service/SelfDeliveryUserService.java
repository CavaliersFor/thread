package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.LoginDto;
import com.lunjar.ebp.bizsupport.exception.PasswordWrongException;
import com.lunjar.ebp.bizsupport.model.AdminAgent;
import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.query.SelfDeliveryUserQuery;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.service.ServiceResult;

/**
 * 管理人员信息服务类
 * @author Administrator
 *
 */
public interface SelfDeliveryUserService{
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	SelfDeliveryUser loadByUserName(String userName);
	/**
	 * 根据管理人员id获取管理人员
	 * @param id
	 * @return
	 */
	SelfDeliveryUser getById(Long id);

	/**
	 * 修改/更改资料
	 * @param selfDeliveryUser
	 * @param agent
	 */
	void updateData(SelfDeliveryUser selfDeliveryUser,AdminAgent agent);

	/**
	 * 修改自己的密码
	 * @param pwd
	 * @param newPwd
	 * @param agent
	 * @throws PasswordWrongException
	 */
	void updateMyPassword(String pwd, String newPwd, AdminAgent agent)throws PasswordWrongException;
	
	/**
	 * 管理人员分页列表
	 * 
	 * @param query
	 */
	PageResult<SelfDeliveryUser> getListPage(SelfDeliveryUserQuery query);
}
