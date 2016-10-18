package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.query.SellerQuery;

/**
 * 卖家服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午10:04:06
 */
public interface SellerService {

	/**
	 * 新增卖家
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午10:06:50
	 * @param seller
	 * @return
	 */
	Long add(Seller seller);

	/**
	 * 更新卖家信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午10:07:58
	 * @param seller
	 */
	void update(Seller seller);

	/**
	 * 根据查询条件查询卖家信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午10:08:40
	 * @param query
	 * @return
	 */
	List<Seller> queryList(SellerQuery query);

	/**
	 * 根据查询条件查询总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午10:09:44
	 * @param query
	 * @return
	 */
	int queryCount(SellerQuery query);

	/**
	 * 根据主键查询具体信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午10:10:44
	 * @param id
	 * @return
	 */
	Seller load(Long id);
}
