package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.dto.LoginDto;
import com.lunjar.ebp.bizsupport.enums.EnumAdminUserStatus;
import com.lunjar.ebp.bizsupport.enums.EnumSelfDeliveryResultStatus;
import com.lunjar.ebp.bizsupport.exception.PasswordWrongException;
import com.lunjar.ebp.bizsupport.mappers.SelfDeliveryUserMapper;
import com.lunjar.ebp.bizsupport.model.AdminAgent;
import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.query.SelfDeliveryUserQuery;
import com.lunjar.ebp.bizsupport.service.SelfDeliveryUserService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.service.ServiceResult;
import com.lunjar.products.core.utils.DigestsUtils;

@Service(value = "selfDeliveryUserService")
public class SelfDeliveryUserServiceImpl implements SelfDeliveryUserService{

	private static final int LOCK_PERIOD = 2;// 单位小时、2小时后 登录自动解锁

	@Autowired
	private SelfDeliveryUserMapper selfDeliveryUserMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * 通过名称获取用户信息
	 * @param accountOrWorkNo
	 * @return
	 */
	@Override
	public SelfDeliveryUser loadByUserName(String userName) {
		SelfDeliveryUser user = selfDeliveryUserMapper.loadByUserName(userName);
		return user;
	}
	private void handleSuccessLogin(SelfDeliveryUser selfDeliveryUser){
//		selfDeliveryUser.setLoginCount(user.getLoginCount()+1);				// 更新登录成功次数
//		selfDeliveryUser.setLoginErrorCount(0);								// 清空登录失败次数
		selfDeliveryUser.setStatus(EnumAdminUserStatus.NORMAL.getValue());	// 不锁定
//		selfDeliveryUser.setLastLoginTime(new Date());						// 最后登录时间
		selfDeliveryUser.setGmtModify(new Date());							// 修改时间

		selfDeliveryUserMapper.update(selfDeliveryUser);
	}
	private ServiceResult<AdminAgent> validateUser(SelfDeliveryUser selfDeliveryUser, String sign, String random) {
		ServiceResult<AdminAgent> result = new ServiceResult<AdminAgent>();

		// 账户不存在
		if (selfDeliveryUser == null) {
			result.setResponseCode(EnumSelfDeliveryResultStatus._2060501);
			return  result;
		}

		// 如果状态为空,则认为是正常,避免空指针错误（1:正常 2:停用）
		if (selfDeliveryUser.getStatus() == null) {
			selfDeliveryUser.setStatus(EnumAdminUserStatus.NORMAL.getValue());
		}
		
		//  验证用户状态
		if (EnumAdminUserStatus.DEPARTURE.getValue() == selfDeliveryUser.getStatus().intValue()) {// 停用
			result.setResponseCode(EnumSelfDeliveryResultStatus._2060503);
			return  result;
		} 

		// 验证用户名密码是否正确
		String s1 = DigestsUtils.md5Hex((selfDeliveryUser.getUserName() + selfDeliveryUser.getPassWord()).getBytes());
		String sign2 = DigestsUtils.md5Hex((s1 + random).getBytes());
		if (!StringUtils.equals(sign, sign2)) {
			result.setResponseCode(EnumSelfDeliveryResultStatus._2060501);
//			handleFailLogin(selfDeliveryUser,result);
		}

		// 验证通过
		return result;
	}
	private AdminAgent createAdminAgent(SelfDeliveryUser selfDeliveryUser) {
		AdminAgent agent = new AdminAgent();
		agent.setUserName(selfDeliveryUser.getUserName());
		agent.setId(selfDeliveryUser.getId());
		return agent;
	}


	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param id
	 * @return <p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月29日上午10:52:30
	 */
	@Override
	public SelfDeliveryUser getById(Long id) {
		SelfDeliveryUser user = selfDeliveryUserMapper.load(id);
		return user;
	}

	/**
	 * 修改自己的密码
	 * 
	 * @param oldPassword  原密码
	 * @param newPassword  新密码
	 * @throws PasswordWrongException
	 */
	@Override
	public void updateMyPassword(String oldPassword, String newPassword,AdminAgent agent) throws PasswordWrongException {
		
		long userId = agent.getId();
		SelfDeliveryUser user = selfDeliveryUserMapper.load(userId);
		
		//if (!Profile.DEVELOP.equals(sysConfig.getProfile()) 
		if(!StringUtils.equals(passwordEncoder.encodePassword(oldPassword, null), user.getPassWord())) {
			throw new PasswordWrongException();
		}

		SelfDeliveryUser user1 = new SelfDeliveryUser(userId);
		user1.setPassWord(passwordEncoder.encodePassword(newPassword, null));

		selfDeliveryUserMapper.update(user1);
	}

	/**
	 * 修改/更新资料
	 * @param user
	 * @param agent
	 */
	@Override
	public void updateData(SelfDeliveryUser selfDeliveryUser,AdminAgent agent)  {
		
		long userId = agent.getId();
		SelfDeliveryUser user1 = new SelfDeliveryUser(userId);
		user1.setUserName(selfDeliveryUser.getUserName());
		selfDeliveryUserMapper.update(user1);

	}
	
	/**
	 * 管理人员分页列表
	 * 
	 * @param query
	 */
	@Override
	public PageResult<SelfDeliveryUser> getListPage(SelfDeliveryUserQuery query) {
		List<SelfDeliveryUser> data = selfDeliveryUserMapper.queryList(query);
		int recordCount = selfDeliveryUserMapper.queryCount(query);
		return PageResult.create(query, data, recordCount);
	}

	
	/*public SelfDeliveryUser getAdminUserByUserName(String userName) {
		SelfDeliveryUser user = selfDeliveryUserMapper.loadByUserName(userName);
		return user;
	}*/

	
	/*public void updateMyPasswords(String oldPassword, String newPassword, String account) throws PasswordWrongException {

		AdminUser user = adminUserMapper.loadByAccount(account);
		Assert.isTrue(user != null, "用户名不存在");
		if (!Profile.DEVELOP.equals(sysConfig.getProfile())
				&& !StringUtils.equals(passwordEncoder.encodePassword(oldPassword, null), user.getPwd())) {
			throw new PasswordWrongException();
		}
		user.setPwd(passwordEncoder.encodePassword(newPassword, null));

		adminUserMapper.update(user);
	}*/

}
