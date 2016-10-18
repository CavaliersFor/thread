package com.lunjar.ebp.admin.biz.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lunjar.ebp.admin.biz.exception.PasswordWrongException;
import com.lunjar.ebp.admin.biz.exception.UserExistsException;
import com.lunjar.ebp.admin.biz.jdbc.AdminRoleFunctionJdbc;
import com.lunjar.ebp.admin.biz.jdbc.AdminUserJdbc;
import com.lunjar.ebp.admin.biz.jdbc.AdminUserRoleJdbc;
import com.lunjar.ebp.admin.biz.mappers.AdminRoleMapper;
import com.lunjar.ebp.admin.biz.mappers.AdminUserMapper;
import com.lunjar.ebp.admin.domain.AdminConstants;
import com.lunjar.ebp.admin.domain.enums.EnumAdminUserStatus;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.AdminRole;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.ebp.admin.domain.query.AdminUserQuery;
import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.config.SysConfig.Profile;
import com.lunjar.products.core.exception.DataNotFindException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.DateUtils;

/**
 * 管理人员管理业务类
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2011-1-16 下午01:31:48
 */
@Service("adminUserService")
public class AdminUserService  {

	//private static final EnumSysLogOpertateObject OPERATE_OBJECT = EnumSysLogOpertateObject.ADMIN_USER;
	private static final int LOCK_PERIOD = 2;// 单位小时、2小时后 登录自动解锁

	@Autowired
	private AdminUserJdbc adminUserJdbc;
	@Autowired
	private AdminUserMapper adminUserMapper;
	//@Autowired
	//private AdminDeptMapper deptMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AdminUserRoleJdbc userRoleDao;
	@Autowired
	private AdminRoleMapper roleMapper;
	@Autowired
	private AdminUserMapper userMapper;
	@Autowired
	private AdminUserRoleJdbc adminUserRoleJdbc;
	@Autowired
	private AdminRoleFunctionJdbc adminRoleFunctionJdbc;
	@Autowired
	private AdminLoginLogService sysLogLoginService;
	@Autowired
	private SysConfig sysConfig;
	//@Autowired
	//private SysLogService sysLogService;

	/**
	 * 添加管理人员
	 * 
	 * @param user
	 * @throws UserExistsException
	 */
	@Transactional
	public void add(AdminUser user) throws UserExistsException {
		int count = adminUserMapper.getCountByAccount(user.getAccount());
		int count4workNO = adminUserMapper.getCountByWorkNo(user.getWorkNo());
		if (count != 0 && count4workNO != 0) {
			throw new UserExistsException(user.getAccount());
		}
		
		// 如果账号为空，则把账号设置为工号
		if (user.getAccount() == null || "".equals(user.getAccount())) {
			user.setAccount(user.getWorkNo());
		}

		// 设置默认密码
		//user.setPwd(passwordEncoder.encodePassword(CoreConstants.INIT_PASSWORD, null));
		user.setPwd(passwordEncoder.encodePassword(AdminConstants.INIT_PASSWORD, null));

		user.setStatus(EnumAdminUserStatus.NORMAL.getValue());
		user.setLoginCount(0);
		user.setIsSuperAdmin(0);
		//查询一下是否有相同工号的用户且是被删除状态的
		AdminUser nUser = adminUserMapper.loadByWorkNo(user.getWorkNo());
		AdminUser aUser = adminUserMapper.loadByAccount(user.getAccount());
		if(nUser != null && nUser.getStatus() == -1){//工号存在就更新工号相同的记录
			user.setId(nUser.getId());
			adminUserMapper.update(user);
		} else if (aUser != null && aUser.getStatus() == -1) {
			user.setId(aUser.getId());
			adminUserMapper.update(user);
		} else{
			adminUserMapper.insert(user);
		}

		//sysLogService.writeLog(OPERATE_OBJECT, String.valueOf(user.getId()), user.getUserName(), "新增");
	}

	/**
	 * 修改管理人员
	 */
	@Transactional
	public void update(AdminUser user) {
		adminUserMapper.update(user);
		//sysLogService.writeLog(OPERATE_OBJECT, String.valueOf(user.getId()), user.getUserName(), "修改");
	}

	/**
	 * 管理人员分页列表
	 * 
	 * @param query
	 * @return <p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月29日上午10:51:18
	 */
	public PageResult<AdminUser> getListPage(AdminUserQuery query) {
		List<AdminUser> data = adminUserMapper.queryList(query);
		int recordCount = adminUserMapper.queryCount(query);
		return PageResult.create(query, data, recordCount);
	}

	/**
	 * 检查账户唯一性
	 */
	public boolean checkAccount(AdminUser adminUser) {
		return adminUserJdbc.checkAccount(adminUser);
	}

	
	public boolean checkWorkNo(AdminUser adminUser) {
		return adminUserJdbc.checkWorkNo(adminUser);
	}

	/**
	 * 删除管理人员
	 * 
	 * @param userId
	 * @param userName
	 */
	@Transactional
	public void remove(Long userId, String userName) {
		adminUserMapper.updateStatus(userId, AdminConstants.STATUS_REMOVED);
		//sysLogService.writeLog(OPERATE_OBJECT, String.valueOf(userId), userName, "删除");
	}

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @param userName
	 */
	@Transactional
	public void resetPwd(Long userId, String userName) {
		AdminUser user = new AdminUser();
		user.setId(userId);
		user.setPwd(passwordEncoder.encodePassword(AdminConstants.INIT_PASSWORD, null));
		// user.setModifier(modifier.toString());
		adminUserMapper.update(user);
		//sysLogService.writeLog(OPERATE_OBJECT, String.valueOf(userId), userName, "重置密码");
	}

	/**
	 * 修改状态
	 * 
	 * @param userId
	 * @param newStatus
	 *            <p>
	 *            author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *            create at: 2014年4月29日上午10:52:20
	 * @throws DataNotFindException 
	 */
	@Transactional
	public void updateStatus(Long userId, Integer newStatus) throws DataNotFindException {
		AdminUser adminUser = adminUserMapper.load(userId);
		if (adminUser == null) {
			throw new DataNotFindException();
		}

		EnumAdminUserStatus oldStatus = EnumAdminUserStatus.valueOf(adminUser.getStatus());
		EnumAdminUserStatus newStatus2 = EnumAdminUserStatus.valueOf(newStatus);

		adminUserMapper.updateStatus(userId, newStatus);

		////sysLogService.writeLog(OPERATE_OBJECT, String.valueOf(userId), adminUser.getUserName(),
		////"状态由[" + oldStatus.getText() + "]变更成[" + newStatus2.getText() + "]");
	}

	/**
	 * 根据管理人员id获取管理人员
	 * 
	 * @param id
	 * @return <p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月29日上午10:52:30
	 */
	public AdminUser getById(Long id) {
		AdminUser user = adminUserMapper.load(id);
		// if (user != null) {
		// AdminDept dept = deptMapper.load(user.getDeptId());
		// user.setDept(dept);
		// }
		return user;
	}

	/**
	 * 修改自己的密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @throws PasswordWrongException
	 *             <p>
	 *             author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *             create at: 2014年4月29日上午10:52:39
	 */
	public void updateMyPassword(String oldPassword, String newPassword,AdminAgent agent) throws PasswordWrongException {
		
		long userId = agent.getId();
		AdminUser user = adminUserMapper.load(userId);

		if (!Profile.DEVELOP.equals(sysConfig.getProfile()) //
				&& !StringUtils.equals(passwordEncoder.encodePassword(oldPassword, null), user.getPwd())) {
			throw new PasswordWrongException();
		}

		AdminUser user1 = new AdminUser(userId);
		user1.setPwd(passwordEncoder.encodePassword(newPassword, null));

		adminUserMapper.update(user1);
	}

	/**
	 * 获取管理人员已分配的角色
	 * 
	 * @param userId
	 * @return <p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月29日上午10:54:31
	 */
	public List<AdminRole> getOfUser(long userId) {
		return roleMapper.getOfRoles(userId);
	}

	/**
	 * 获取管理人员未分配的角色
	 * 
	 * @param userId
	 * @return <p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月29日上午10:54:51
	 */
	public List<AdminRole> getNotOfUser(long userId) {
		return roleMapper.getNotOfRoles(userId);
	}

	/**
	 * 保存管理人员角色
	 * 
	 * @param userId
	 * @param roleIds
	 *            <p>
	 *            author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *            create at: 2014年4月29日上午10:53:08
	 */
	@Transactional
	public void saveUserRoles(long userId, Integer[] roleIds) {
		userRoleDao.userRoleSave(userId, roleIds);

		AdminUser user = new AdminUser(userId);
		adminUserMapper.update(user);
	}

	
	

	
	public Set<String> getUserFunctions(long userId) {
		return adminUserRoleJdbc.getUserFunctions(userId);
	}

	
	public void saveUserFunctions(long userId, String[] functions) {
		Integer[] userRoles = userRoleDao.getUserRoleIds(userId);

		Integer roleId = null;
		if (userRoles.length == 0) {
			AdminRole adminRole = new AdminRole();
			adminRole.setRoleName("自动角色" + DateUtils.getNow("yyyyMMddHHmm"));
			roleMapper.insert(adminRole);
			roleId = adminRole.getId();

			Integer[] roleIds = new Integer[] { roleId };
			userRoleDao.userRoleSave(userId, roleIds);
		} else {
			roleId = userRoles[0];
		}

		adminRoleFunctionJdbc.saveRoleFunctions(roleId, functions);
	}

	
	public void updateData(AdminUser user,AdminAgent agent)  {
		
		long userId = agent.getId();
		AdminUser user1 = new AdminUser(userId);
		user1.setAddress(user.getAddress());
		user1.setEmail(user.getEmail());
		user1.setIdCard(user.getIdCard());
		user1.setPhone(user.getPhone());
		user1.setUserName(user.getUserName());
		adminUserMapper.update(user1);

	}

	

	
	public AdminUser getAdminUserByAccount(String account) {
		AdminUser user = userMapper.loadByAccount(account);
		return user;
	}

	
	public void updateMyPasswords(String oldPassword, String newPassword, String account) throws PasswordWrongException {

		AdminUser user = adminUserMapper.loadByAccount(account);
		Assert.isTrue(user != null, "用户名不存在");
		if (!Profile.DEVELOP.equals(sysConfig.getProfile())
				&& !StringUtils.equals(passwordEncoder.encodePassword(oldPassword, null), user.getPwd())) {
			throw new PasswordWrongException();
		}
		user.setPwd(passwordEncoder.encodePassword(newPassword, null));

		adminUserMapper.update(user);
	}
}
