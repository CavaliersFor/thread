package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.query.ShopQuery;

/**
 * 商铺服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午11:10:59
 */
public interface ShopService {

	/**
	 * 新增商铺
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午11:13:27
	 * @param shop
	 * @return
	 */
	Long add(Shop shop);

	/**
	 * 更新商铺信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午11:14:12
	 * @param shop
	 */
	void update(Shop shop);

	/**
	 * 根据查询条件查询商品列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午11:14:48
	 * @param query
	 * @return
	 */
	List<Shop> queryList(ShopQuery query);

	/**
	 * 根据查询条件查询总数
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午11:15:43
	 * @param query
	 * @return
	 */
	int queryCount(ShopQuery query);

	/**
	 * 根据主键查询详情
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日上午11:17:23
	 * @param id
	 * @return
	 */
	Shop load(Long id);

	/**
	 * 根据accessKey获取商铺信息，先从缓存取，如果缓存没有就去数据库去，然后放到缓存中
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日下午3:09:03
	 * @param accessKey
	 * @return
	 */
	ShopInfo getShopInfo(String accessKey);
	
	/**
	 * 根据accessKey更新商铺信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日下午3:13:01
	 * @param accessKey
	 * @return
	 */
	ShopInfo updateShopInfo(String accessKey);
	
	/**
	 * 根据accessKey删除商铺信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月11日下午3:13:33
	 * @param accessKey
	 */
	void removeShopInfo(String accessKey);

	Shop getShopByAccessKey(String accessKey);
}
