package com.lunjar.ebp.admin.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunjar.ebp.admin.biz.mappers.AdminLoginLogMapper;
import com.lunjar.ebp.admin.domain.model.AdminLoginLog;
import com.lunjar.ebp.admin.domain.query.AdminLoginLogQuery;
import com.lunjar.products.core.model.PageResult;

/**
 * 
 * 
 * <p>
 * create at 2014年9月23日 上午10:26:39
 * 
 * @author xuyuanyang
 * @version %I%, %G%
 * @see
 */
@Service
public class AdminLoginLogService {
	@Autowired
	private AdminLoginLogMapper sysLogLoginMapper;

	public PageResult<AdminLoginLog> getListPage(AdminLoginLogQuery query) {
		List<AdminLoginLog> data = sysLogLoginMapper.queryList(query);
		int recordCount = sysLogLoginMapper.queryCount(query);
		return PageResult.create(query, data, recordCount);
	}

	public void writeAdminLoginLog(AdminLoginLog sysLogLogin) {
		sysLogLoginMapper.insert(sysLogLogin);
	}

}
