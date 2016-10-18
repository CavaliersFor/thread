package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;

/**
 * 物流公司服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年9月22日下午5:07:29
 */
public interface LogisticsCompanyService {

	/**
	 * 新增物流公司
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:10:25
	 * @param logisticsCompany
	 * @return
	 */
	Long add(LogisticsCompany logisticsCompany);

	/**
	 * 更新物流公司
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:11:11
	 * @param logisticsCompany
	 */
	void update(LogisticsCompany logisticsCompany);

	/**
	 * 根据查询条件查询物流公司列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:12:05
	 * @param query
	 * @return
	 */
	List<LogisticsCompany> queryList(LogisticsCompanyQuery query);

	/**
	 * 根据查询条件查询总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:13:29
	 * @param query
	 * @return
	 */
	int queryCount(LogisticsCompanyQuery query);

	/**
	 * 根据主键id查询对象
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:15:00
	 * @param id
	 * @return
	 */
	LogisticsCompany load(Long id);

	/**
	 * 查询所有物流公司列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:33:09
	 * @return
	 */
	List<LogisticsCompany> getAllList();

}
