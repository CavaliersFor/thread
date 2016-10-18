package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.products.core.exception.ServiceException;

public interface RegionService {
	/**
	 * 根据parentCode获取下级地区
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月17日下午7:20:35
	 * @param parentCode
	 * @return
	 * @throws ServiceException 
	 */
	List<Region> getByParentCode(String parentCode) throws ServiceException;

	/**
	 * 根据code获取地区名称
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月17日下午7:20:19
	 * @param code
	 * @return
	 * @throws ServiceException 
	 */
	Region getByCode(String code) throws ServiceException;

	/**
	 * 获取所有的省
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月17日下午7:17:50
	 * @return
	 */
	List<Region> getAllProvince();

	/**
	 * 根据根code获取第几级城市列表 1：国家2：省份3：城市4：区县
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月17日下午7:18:31
	 * @param rootCode
	 * @param level
	 * @return
	 * @throws ServiceException s
	 */
	List<Region> getByParentCodeForTree(String rootCode, Integer level) throws ServiceException;

	/**
	 * 根据code获取父级code
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月17日下午7:20:58
	 * @param code
	 * @return
	 * @throws ServiceException 
	 */
	String getParentCode(String code) throws ServiceException;
	
}
