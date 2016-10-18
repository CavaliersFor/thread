package com.lunjar.ebp.admin.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunjar.ebp.admin.biz.jdbc.AdminRoleFunctionJdbc;
import com.lunjar.ebp.admin.biz.mappers.AdminRoleMapper;
import com.lunjar.ebp.admin.domain.model.AdminRole;
import com.lunjar.ebp.admin.domain.query.AdminRoleQuery;
import com.lunjar.products.core.model.PageResult;

/**
 * 角色管理业务层
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2011-1-16 下午01:31:31
 */

@Service
public class AdminRoleService {

	@Autowired
	private AdminRoleMapper roleMapper;
	@Autowired
	private AdminFunctionResourceService functionResourceService;
	@Autowired
	private AdminRoleFunctionJdbc roleFunctionDao;

	/**
	 * 添加或修改
	 * 
	 * @param role
	 *            <p>
	 *            author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *            create at: 2014年4月28日下午3:48:31
	 */
	@Transactional
	public void save(AdminRole role) {
		if (role.getId() == null) {
			// role.setCreator(user.getId().toString());
			// role.setStatusEnum(EnumRoleStatus.AUDIT_NORMAL);
			roleMapper.insert(role);
		} else {
			// role.setModifier(user.getId().toString());
			roleMapper.update(role);
		}
	}

	/**
	 * 获取角色列表
	 * 
	 * @param query
	 * @return
	 * 		<p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月28日下午3:48:46
	 */
	public PageResult<AdminRole> getRoles(AdminRoleQuery query) {
		// query.setStatus(EnumRoleStatus.AUDIT_NORMAL.getValue());

		List<AdminRole> data = roleMapper.queryList(query);
		int recordCount = roleMapper.queryCount(query);

		return PageResult.create(query, data, recordCount);
	}

	/**
	 * 获取一个角色
	 * 
	 * @param id
	 * @return
	 * 		<p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月28日下午3:48:38
	 */
	public AdminRole load(Integer id) {
		return roleMapper.load(id);
	}

	/**
	 * 删除角色
	 * 
	 * @param role
	 *            <p>
	 *            author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *            create at: 2014年4月28日下午3:48:23
	 */
	@Transactional
	public void delete(AdminRole role) {
		// roleMapper.updateStatus(role.getId(),
		// EnumRoleStatus.AUDIT_SUCCESS.getValue());
	}

	/**
	 * 获取一个角色的权限项
	 * 
	 * @param roleId
	 * @return
	 * 		<p>
	 *         author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *         create at: 2014年4月28日下午3:48:07
	 */
	public String[] getRoleFunctionIds(Integer roleId) {
		return roleFunctionDao.getFunctionIds(roleId);
	}

	/**
	 * 保存一个角色的权限项
	 * 
	 * @param roleId
	 * @param functionIds
	 *            <p>
	 *            author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 *            create at: 2014年4月28日下午3:48:18
	 */
	@Transactional
	public void saveRoleFunctions(Integer roleId, String[] functionIds) {
		roleFunctionDao.saveRoleFunctions(roleId, functionIds);
		// 更新GMT_MODIFY字段
		AdminRole adminRole = new AdminRole(roleId);
		roleMapper.update(adminRole);
	}

}
