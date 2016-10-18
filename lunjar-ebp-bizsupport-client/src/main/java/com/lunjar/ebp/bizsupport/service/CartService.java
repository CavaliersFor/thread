package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.CartDto;
import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.query.CartQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 购物车服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月15日上午9:37:50
 */
public interface CartService {

	/**
	 * 添加购物车
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月15日上午9:40:56
	 * @param cart
	 * @return
	 * @throws ServiceException 
	 */
	Long add(Cart cart) throws ServiceException;

	/**
	 * 更新购物车
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月15日上午9:41:44
	 * @param cart
	 */
	void update(Cart cart);

	/**
	 * 根据搜索条件搜索购物车列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月15日上午9:45:30
	 * @param query
	 * @return
	 */
	List<CartDto> queryList(CartQuery query);

	/**
	 * 根据搜索条件获取数目
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月15日上午9:46:35
	 * @param query
	 * @return
	 */
	int queryCount(CartQuery query);

	/**
	 * 根据主键id获取对象信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月15日上午9:47:12
	 * @param id
	 * @return
	 */
	Cart load(Long id);
	/**
	 * 删除购物车
	 * @param id
	 */
	void delete(Long id);
	
	void deleteByQuery(CartQuery query) ;
}
