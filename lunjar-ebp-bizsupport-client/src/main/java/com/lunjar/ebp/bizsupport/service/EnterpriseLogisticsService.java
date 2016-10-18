package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.EnterpriseLogistics;
import com.lunjar.ebp.bizsupport.query.EnterpriseLogisticsQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商家-物流公司服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年9月22日下午5:07:29
 */
public interface EnterpriseLogisticsService {

	/**
	 * 新增商家-物流公司(商家选择几家常用的物流公司)
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:14:51
	 * @param enterpriseLogistics
	 * @return
	 * @throws ServiceException 
	 */
	Long add(EnterpriseLogistics enterpriseLogistics) throws ServiceException;

	/**
	 * 更新商家-物流公司
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:16:10
	 * @param enterpriseLogistics
	 */
	void update(EnterpriseLogistics enterpriseLogistics);

	/**
	 * 根据查询条件查询商家-物流公司列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:17:27
	 * @param query
	 * @return
	 */
	List<EnterpriseLogistics> queryList(EnterpriseLogisticsQuery query);

	/**
	 * 根据查询条件查询总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:19:50
	 * @param query
	 * @return
	 */
	int queryCount(EnterpriseLogisticsQuery query);

	/**
	 * 根据id查询对象
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:20:38
	 * @param id
	 * @return
	 */
	EnterpriseLogistics load(Long id);

	/**
	 * 删除
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月22日下午5:22:27
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据商家id查询该商家所有关联的物流公司(带缓存)
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日上午9:03:27
	 * @param id
	 * @return
	 */
	List<EnterpriseLogistics> queryListByEnterpriseId(Long id);

}
