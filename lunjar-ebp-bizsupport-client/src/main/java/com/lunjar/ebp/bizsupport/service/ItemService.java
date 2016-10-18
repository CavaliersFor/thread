package com.lunjar.ebp.bizsupport.service;

import java.util.List;
import java.util.Map;

import com.lunjar.ebp.bizsupport.model.Item;
import com.lunjar.ebp.bizsupport.query.ItemQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 产品类目(行业标准)
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年10月9日下午3:10:45
 */
public interface ItemService {

	/**
	 * 新增产品类目
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:13:54
	 * @param item
	 * @return
	 * @throws ServiceException
	 */
	Long add(Item item) throws ServiceException;

	/**
	 * 更新产品类目
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:14:41
	 * @param item
	 */
	void update(Item item);

	/**
	 * 根据查询条件查询产品类目列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:15:46
	 * @param query
	 * @return
	 */
	List<Item> queryList(ItemQuery query);

	/**
	 * 根据查询条件查询产品类目总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:16:57
	 * @param query
	 * @return
	 */
	int queryCount(ItemQuery query);

	/**
	 * 根据主键id查询对象
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:17:40
	 * @param id
	 * @return
	 */
	Item load(Long id);

	/**
	 * 根据主键id删除产品类目
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月9日下午3:18:15
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 根据父类id获取所有下级目录
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年10月10日上午10:00:27
	 * @return
	 */
	List<Item> getChildItemByParentId(Long parentId);

	/**
	 * 通过id获取父类的名称
	 * @param id
	 * @return
	 */
	public Map<String,String> getItemNameAndParentName(Long id);
	
}
