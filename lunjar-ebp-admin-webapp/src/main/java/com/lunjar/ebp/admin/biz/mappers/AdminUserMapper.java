package com.lunjar.ebp.admin.biz.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.ebp.admin.domain.query.AdminUserQuery;

public interface AdminUserMapper {
	/***/
	AdminUser load(Long id);

	/***/
	void insert(AdminUser entity);

	/***/
	void update(AdminUser entity);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<AdminUser> queryList(AdminUserQuery query);

	/***/
	int queryCount(AdminUserQuery query);

	AdminUser loadByAccount(String account);
	
	/**
	 * 根据工号查询用户
	* @param workNo
	* @return
	* <p>
	* author: xuyuanyang<br>
	* create at 2014年9月22日 上午11:35:44
	 */
	AdminUser loadByWorkNo(String workNo);

	void updateLoginInfo(long userId);

	int getCountByAccount(String account);
	
	/**
	 * 根据工号取记录数，工号也是唯一标识
	* @param workNo
	* @return
	* <p>
	* author: xuyuanyang<br>
	* create at 2014年9月22日 上午10:47:25
	 */
	int getCountByWorkNo(String workNo);
}