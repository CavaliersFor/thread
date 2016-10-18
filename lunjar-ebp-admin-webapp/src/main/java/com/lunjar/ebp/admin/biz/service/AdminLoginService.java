package com.lunjar.ebp.admin.biz.service;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ancun.bps.core.log.OperateLogService;
import com.ancun.bps.core.log.dto.LoginDto;
import com.lunjar.ebp.admin.biz.jdbc.AdminUserRoleJdbc;
import com.lunjar.ebp.admin.biz.mappers.AdminUserMapper;
import com.lunjar.ebp.admin.domain.AdminServiceResultCode;
import com.lunjar.ebp.admin.domain.enums.EnumAdminUserStatus;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.AdminLoginLog;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.products.core.service.ServiceResult;
import com.lunjar.products.core.utils.DigestsUtils;

@Service
public class AdminLoginService {
	// 初始密码:123456,passwordEncoder.encodePassword(AdminConstants.INIT_PASSWORD, null)=="7c4a8d09ca3762af61e59520943dc26494f8941b"
	public static final String DEFAULT_PASSWD= "7c4a8d09ca3762af61e59520943dc26494f8941b";
	public static final int LOGIN_ERROR_COUNT=5;	// 登录错误次数，最多5次

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserRoleJdbc adminUserRoleJdbc;
	@Autowired
	private OperateLogService operateLogService;

	@Transactional
	public ServiceResult<AdminAgent> login(LoginDto dto) {
		AdminUser user = loadByAccountOrWorkNo(dto.getAccount());

		ServiceResult<AdminAgent> result = validateUser(user, dto.getSign(), dto.getRandom());

		if (result.isSuccess()) {
			handleSuccessLogin(user);

			AdminAgent agent = createAdminAgent(user);
			result.setData(agent);

			// 写登录日志表 xyy
			operateLogService.writeUpdateLog(agent, user, user.getId());//写登录日志
		}

		return result;
	}

	private ServiceResult<AdminAgent> validateUser(AdminUser user, String sign, String random) {
		ServiceResult<AdminAgent> result = new ServiceResult<AdminAgent>();

		// 账户不存在
		if (user == null) {
			result.setResponseCode(AdminServiceResultCode._2060501);
			return  result;
		}

		// 如果状态为空,则认为是正常,避免空指针错误
		if (user.getStatus() == null) {
			user.setStatus(EnumAdminUserStatus.NORMAL.getValue());
		}

		//  验证用户状态
		if (user.getStatus().intValue() == -1) {// 已删除
			result.setResponseCode(AdminServiceResultCode._2060501);
			return  result;
		} else if (EnumAdminUserStatus.DEPARTURE.getValue() == user.getStatus().intValue()) {// 冻结
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

		}

		// 当用户的密码为空或是初始密码，必须让用户设置密码才能登录
		Boolean firstLogin=user.getLoginCount()==0;
		Boolean isInitPassword = DEFAULT_PASSWD.equals(user.getPwd()) || StringUtils.isBlank(user.getPwd());
		if(firstLogin && isInitPassword){
			result.setResponseCode(AdminServiceResultCode._2060504);
			return  result;
		}

		// 验证用户名密码是否正确
		String s1 = DigestsUtils.md5Hex((user.getAccount() + user.getPwd()).getBytes());
		String sign2 = DigestsUtils.md5Hex((s1 + random).getBytes());
		if (!StringUtils.equals(sign, sign2)) {
			result.setResponseCode(AdminServiceResultCode._2060501);
			handleFailLogin(user,result);
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
	private void handleFailLogin(AdminUser adminUser, ServiceResult<AdminAgent> result){
		Integer loginErrorCount= adminUser.getLoginErrorCount()==null?0: adminUser.getLoginErrorCount()+1;

		// 登录失败次数大于等于3次小于5次
		if(loginErrorCount>=3 && loginErrorCount<5){
			result.setResponseCode(AdminServiceResultCode._2060506);
		}

		// 登录失败次数大于等于5次
		if (loginErrorCount>=LOGIN_ERROR_COUNT){
			loginErrorCount=LOGIN_ERROR_COUNT;
			adminUser.setStatus(EnumAdminUserStatus.LOCK.getValue());	// 账户变成锁定状态
			adminUser.setLatestLockTime(new Date());					// 最后锁定时间
			adminUser.setLatestUnlockTime(DateUtils.addHours(adminUser.getLatestLockTime(), 2));// 设置解锁时间是锁定时间的后2小时

			result.setResponseCode(AdminServiceResultCode._2060502);	// 超过5次被锁定
		}

		adminUser.setLoginErrorCount(loginErrorCount);
		adminUser.setGmtModify(new Date());
		adminUserMapper.update(adminUser);

	}

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
	private void handleSuccessLogin(AdminUser user){
		user.setLoginCount(user.getLoginCount()+1);				// 更新登录成功次数
		user.setLoginErrorCount(0);								// 清空登录失败次数
		user.setStatus(EnumAdminUserStatus.NORMAL.getValue());	// 不锁定
		user.setLastLoginTime(new Date());						// 最后登录时间
		user.setGmtModify(new Date());							// 修改时间

		adminUserMapper.update(user);
	}

	private AdminAgent createAdminAgent(AdminUser user) {
		AdminAgent agent = new AdminAgent();
		agent.setAccount(user.getAccount());
		agent.setId(user.getId());
		agent.setUserName(user.getUserName());

		if (user.getIsSuperAdmin().equals(1)) {
			agent.setSystemAdmin(true);
		} else {
			Set<String> userFunctions = adminUserRoleJdbc.getUserFunctions(user.getId());
			agent.setPermissions(userFunctions);
		}
		return agent;
	}

	private AdminUser loadByAccountOrWorkNo(String accountOrWorkNo) {
		AdminUser user = adminUserMapper.loadByAccount(accountOrWorkNo);
		if (user == null) {
			user = adminUserMapper.loadByWorkNo(accountOrWorkNo);
		}
		return user;
	}

	/**
	 * 写登录日志
	 * 
	 * @param adminUser
	 * @param ip
	 *            <p>
	 *            author: xuyuanyang<br>
	 *            create at 2014年9月23日 上午11:16:31
	 */
	private void writeSysLogLogin(AdminUser adminUser, String ip) {
		AdminLoginLog sysLogLogin = new AdminLoginLog();
		sysLogLogin.setLoginId(adminUser.getId());
		sysLogLogin.setAdminName(adminUser.getUserName());
		sysLogLogin.setAdminWorkNo(adminUser.getWorkNo());
		sysLogLogin.setLoginAccount(adminUser.getAccount());
		sysLogLogin.setLoginTime(new Date());
		sysLogLogin.setLoginIp(ip);
		// sysLogLoginService.writeAdminLoginLog(sysLogLogin);
	}
}
