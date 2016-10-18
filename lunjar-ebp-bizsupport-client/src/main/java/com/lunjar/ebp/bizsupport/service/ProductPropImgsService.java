package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.query.ProductPropImgsQuery;

/**
 * 产品图片服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月12日上午9:29:08
 */
public interface ProductPropImgsService {

	/**
	 * 新增产品图片
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日上午9:32:47
	 * @param productPropImgs
	 * @return
	 */
	Long add(ProductPropImgs productPropImgs);

	/**
	 * 更新产品图片
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日上午9:33:33
	 * @param productPropImgs
	 */
	void update(ProductPropImgs productPropImgs);

	/**
	 * 根据查询条件查询产品图片列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日上午9:34:11
	 * @param query
	 * @return
	 */
	List<ProductPropImgs> queryList(ProductPropImgsQuery query);

	/**
	 * 根据查询条件查询数量
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日上午9:35:21
	 * @param query
	 * @return
	 */
	int queryCount(ProductPropImgsQuery query);

	/**
	 * 根据主键id查询具体信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月12日上午9:36:10
	 * @param id
	 * @return
	 */
	ProductPropImgs load(Long id);
	
	/***
	 * 通过条件删除
	 * @param productPropImgs
	 */
	public void deleteByCondition(ProductPropImgs productPropImgs);
}
