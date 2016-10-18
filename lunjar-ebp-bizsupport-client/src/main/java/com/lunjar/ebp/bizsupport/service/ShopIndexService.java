package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;
import com.lunjar.ebp.bizsupport.model.ShopIndex;
import com.lunjar.ebp.bizsupport.query.ShopIndexQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商铺首页服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月23日下午3:18:10
 */
public interface ShopIndexService {

	/**
	 * 新增
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午3:20:54
	 * @param shopIndex
	 * @return
	 */
	Long add(ShopIndex shopIndex);

	/**
	 * 更新
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午3:22:01
	 * @param shopIndex
	 */
	void update(ShopIndex shopIndex);

	/**
	 * 根据条件查询
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午3:22:37
	 * @param query
	 * @return
	 */
	List<ShopIndex> queryList(ShopIndexQuery query);

	/**
	 * 根据条件查询记录总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午3:23:10
	 * @param query
	 * @return
	 */
	int queryCount(ShopIndexQuery query);

	/**
	 * 根据主键查询
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月23日下午3:23:53
	 * @param id
	 * @return
	 */
	ShopIndex load(Long id);

	/**
	 * 根据商铺id获取商铺首页列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月24日上午9:09:41
	 * @param shopId
	 * @return
	 * @throws ServiceException 
	 */
	List<ShopIndexDto> getShopIndex(Long shopId) throws ServiceException;
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
}
