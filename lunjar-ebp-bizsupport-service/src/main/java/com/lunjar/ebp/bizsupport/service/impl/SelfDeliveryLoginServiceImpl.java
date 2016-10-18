package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.enums.EnumAdminUserStatus;
import com.lunjar.ebp.bizsupport.enums.EnumSelfDeliveryResultStatus;
import com.lunjar.ebp.bizsupport.mappers.SelfDeliveryUserMapper;
import com.lunjar.ebp.bizsupport.model.AdminAgent;
import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.service.SelfDeliveryLoginService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.service.ServiceResult;
import com.lunjar.products.core.utils.DigestsUtils;

/***
 * 登录状态实现类
 * 
 * @author Administrator
 *
 */
@Service(value = "selfDeliveryLoginService")
public class SelfDeliveryLoginServiceImpl implements SelfDeliveryLoginService {
	// 初始密码:123456,passwordEncoder.encodePassword(AdminConstants.INIT_PASSWORD, null)=="7c4a8d09ca3762af61e59520943dc26494f8941b"
	public static final String DEFAULT_PASSWD = "7c4a8d09ca3762af61e59520943dc26494f8941b";
	public static final int LOGIN_ERROR_COUNT=5;	// 登录错误次数，最多5次

	@Autowired
	private SelfDeliveryUserMapper selfDeliveryUserMapper;

	@Override
	public AdminAgent login(String account, String sign, String random) throws ServiceException {
		SelfDeliveryUser user = loadByUserName(account);

		ServiceResult<AdminAgent> result = validateUser(user, sign, random);
		AdminAgent agent = null;
		if (result.isSuccess()) {
			handleSuccessLogin(user);

			agent = createAdminAgent(user);
			result.setData(agent);

			// 写登录日志表 xyy
			//operateLogService.writeUpdateLog(agent, user, user.getId());//写登录日志
		}

		return agent;
	}

	/**
	 * 验证用户状态及密码是否正确
	 * @param user 用户信息
	 * @param sign
	 * @param random
	 * @return
	 * @throws ServiceException 
	 */
	private ServiceResult<AdminAgent> validateUser(SelfDeliveryUser selfDeliveryUser, String sign, String random) throws ServiceException {
		ServiceResult<AdminAgent> result = new ServiceResult<AdminAgent>();

		// 账户不存在
		if (selfDeliveryUser == null) {
			throw new ServiceException(EnumSelfDeliveryResultStatus._2060501.getCode(), EnumSelfDeliveryResultStatus._2060501.getMsg());
		}

		// 如果状态为空,则认为是正常,避免空指针错误（1:正常 2:停用）
		if (selfDeliveryUser.getStatus() == null) {
			selfDeliveryUser.setStatus(EnumAdminUserStatus.NORMAL.getValue());
		}
		
		//  验证用户状态
		if (EnumAdminUserStatus.DEPARTURE.getValue() == selfDeliveryUser.getStatus().intValue()) {// 停用
			throw new ServiceException(EnumSelfDeliveryResultStatus._2060503.getCode(), EnumSelfDeliveryResultStatus._2060503.getMsg());
		} 

		/*//  验证用户状态
		if (user.getStatus().intValue() == -1) {// 已删除
			result.setResponseCode(AdminServiceResultCode._2060501);
			return  result;
		} else if (EnumAdminUserStatus.DEPARTURE.getValue() == user.getStatus().intValue()) {// 停用
			result.setResponseCode(AdminServiceResultCode._2060503);
			return  result;
		} else if (EnumAdminUserStatus.LOCK.getValue() == user.getStatus().intValue()) {// 锁定

			// 如果未超过解锁时间账户更新账户状态为正常
			if (new Date().after(user.getLatestUnlockTime())) {// 解锁
				user.setStatus(EnumAdminUserStatus.NORMAL.getValue());
				adminUserMapper.update(user);
			} else {//
				result.setResponseCode(AdminServiceResultCode._2060502);// 登录失败账户被锁定
				return  result;
			}

		}*/

		// 当用户的密码为空或是初始密码，必须让用户设置密码才能登录
		/*Boolean firstLogin=user.getLoginCount()==0;
		Boolean isInitPassword = DEFAULT_PASSWD.equals(user.getPwd()) || StringUtils.isBlank(user.getPwd());
		if(firstLogin && isInitPassword){
			result.setResponseCode(AdminServiceResultCode._2060504);
			return  result;
		}*/

		// 验证用户名密码是否正确
		String s1 = DigestsUtils.md5Hex((selfDeliveryUser.getUserName() + selfDeliveryUser.getPassWord()).getBytes());
		String sign2 = DigestsUtils.md5Hex((s1 + random).getBytes());
		if (!StringUtils.equals(sign, sign2)) {
//			handleFailLogin(selfDeliveryUser,result);
			throw new ServiceException(EnumSelfDeliveryResultStatus._2060501.getCode(), EnumSelfDeliveryResultStatus._2060501.getMsg());
		}

		// 验证通过
		return result;
	}

	/**
	  * 处理用户名密码错误的情况
	  *
	  * 1.登录失败次数累加1,最多5次
	  * 3.如果登录失败3次，则弹出验证码
	  * 2.如果失败次数超过5次就要锁定账户,解锁时间是锁定时间的后2小时
	  *
	  * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/5 15:57
	  * @version %I%,%G%
	  * @see
	  */
	/*private void handleFailLogin(SelfDeliveryUser selfDeliveryUser, ServiceResult<AdminAgent> result){
//		Integer loginErrorCount= selfDeliveryUser.getLoginErrorCount()==null?0: selfDeliveryUser.getLoginErrorCount()+1;

		if (loginErrorCount>=LOGIN_ERROR_COUNT){
			loginErrorCount = 3;
			adminUser.setLatestLockTime(null);
			adminUser.setLatestUnlockTime(null);
		}
		// 登录失败次数大于等于3次小于5次
//		if(loginErrorCount>=3 && loginErrorCount<5){
		if(loginErrorCount>=3){
			result.setResponseCode(EnumSelfDeliveryResultStatus._2060506);
		}

		// 登录失败次数大于等于5次
		if (loginErrorCount>=LOGIN_ERROR_COUNT){
			loginErrorCount=LOGIN_ERROR_COUNT;
			adminUser.setStatus(EnumAdminUserStatus.LOCK.getValue());	// 账户变成锁定状态
			adminUser.setLatestLockTime(new Date());					// 最后锁定时间
			adminUser.setLatestUnlockTime(DateUtils.addHours(adminUser.getLatestLockTime(), 2));// 设置解锁时间是锁定时间的后2小时

			result.setResponseCode(AdminServiceResultCode._2060502);	// 超过5次被锁定
		}

//		adminUser.setLoginErrorCount(loginErrorCount);
		selfDeliveryUser.setGmtModify(new Date());
		selfDeliveryUserMapper.update(selfDeliveryUser);

	}
*/
	 /**
	  * 处理用户登录成功
	  *
	  * 1.清空登录失败次数
	  * 2.更新登录成功次数
	  *
	  *
	  * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/5 16:19
	  * @version %I%,%G%
	  * @see
	  */
	private void handleSuccessLogin(SelfDeliveryUser selfDeliveryUser){
//		selfDeliveryUser.setLoginCount(user.getLoginCount()+1);				// 更新登录成功次数
//		selfDeliveryUser.setLoginErrorCount(0);								// 清空登录失败次数
		selfDeliveryUser.setStatus(EnumAdminUserStatus.NORMAL.getValue());	// 不锁定
//		selfDeliveryUser.setLastLoginTime(new Date());						// 最后登录时间
		selfDeliveryUser.setGmtModify(new Date());							// 修改时间

		selfDeliveryUserMapper.update(selfDeliveryUser);
	}

	private AdminAgent createAdminAgent(SelfDeliveryUser selfDeliveryUser) {
		AdminAgent agent = new AdminAgent();
		agent.setUserName(selfDeliveryUser.getUserName());
		agent.setId(selfDeliveryUser.getId());

		/*if (user.getIsSuperAdmin().equals(1)) {
			agent.setSystemAdmin(true);
		} else {
			Set<String> userFunctions = adminUserRoleJdbc.getUserFunctions(user.getId());
			agent.setPermissions(userFunctions);
		}*/
		return agent;
	}

	/**
	 * 通过名称获取用户信息
	 * @param accountOrWorkNo
	 * @return
	 */
	private SelfDeliveryUser loadByUserName(String userName) {
		SelfDeliveryUser user = selfDeliveryUserMapper.loadByUserName(userName);
		return user;
	}

	/**
	 * 写登录日志
	 * 
	 * @param adminUser
	 * @param ip
	 *//*
	private void writeSysLogLogin(AdminUser adminUser, String ip) {
		AdminLoginLog sysLogLogin = new AdminLoginLog();
		sysLogLogin.setLoginId(adminUser.getId());
		sysLogLogin.setAdminName(adminUser.getUserName());
		sysLogLogin.setAdminWorkNo(adminUser.getWorkNo());
		sysLogLogin.setLoginAccount(adminUser.getAccount());
		sysLogLogin.setLoginTime(new Date());
		sysLogLogin.setLoginIp(ip);
		// sysLogLoginService.writeAdminLoginLog(sysLogLogin);
	}*/
}
