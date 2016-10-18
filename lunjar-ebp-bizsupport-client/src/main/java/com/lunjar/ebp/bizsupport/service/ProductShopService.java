package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.exception.BizSupportException;
import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 产品商品关系服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月13日下午4:32:22
 */
public interface ProductShopService {

	/**
	 * 新增商铺与商品关系
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月13日下午4:34:45
	 * @param productShop
	 * @return
	 * @throws ServiceException 
	 */
	Long add(ProductShop productShop) throws  ServiceException;

	/**
	 * 更新商铺商品关系表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月13日下午4:35:42
	 * @param productShop
	 * @throws ServiceException 
	 */
	void update(ProductShop productShop) throws ServiceException;

	/**
	 * 根据查询条件查询商品商铺列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月13日下午4:36:28
	 * @param query
	 * @return
	 * @throws ServiceException 
	 */
	List<ProductShopDto> queryList(ProductShopQuery query) throws ServiceException;

	/**
	 * 根据查询条件查询商品商铺关系的数量
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月13日下午4:37:23
	 * @param query
	 * @return
	 * @throws ServiceException 
	 */
	int queryCount(ProductShopQuery query) throws ServiceException;

	/**
	 * 根据主键id查询对象
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月13日下午4:38:36
	 * @param id
	 * @return
	 */
	ProductShop load(Long id);
	/**
	 * 通过条件更新
	 * @param query
	 */
	void updateByCondition(ProductShop productShop);

	/**
	 * 删除
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月30日下午2:51:20
	 * @param productShop
	 */
	void deleteProductShop(ProductShop productShop);

	List<ProductShop> queryLists(ProductShopQuery query);
}
