package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.query.CombinationProductQuery;
import com.lunjar.products.core.exception.ServiceException;
/**
 * 组合商品服务类
 * @author Administrator
 *
 */
import com.lunjar.products.core.model.PageResult;
public interface CombinationProductService extends CommonService<CombinationProduct, CombinationProductQuery>{
	
	/**
	 * 通过商品id查询组合商品
	 * @param combinationProductQuery
	 * @return
	 */
	public List<CombinationProduct> queryByProductId(CombinationProductQuery combinationProductQuery,Long shopId) throws ServiceException;
	
	/**
	 * 组合商品计算邮费、返回邮费价格、总的需要支付价格
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月26日下午5:22:34
	 * @param combinationId
	 * @param dto
	 * @throws ServiceException 
	 */
	DiscountDto doCombinationDiscount(Long combinationId, DiscountDto dto) throws ServiceException;
	
	/**
	 * 查询含有分页
	 * @param combinationProductQuery
	 * @return
	 */
	public PageResult<CombinationProduct> queryListForPage(CombinationProductQuery combinationProductQuery);
	
	/**
	 * 更新不进行非空判断
	 * @param combinationProduct
	 */
	public void updateNoDecide(CombinationProduct combinationProduct);
	
	/**
	 * 通过商品id查询有那些组合
	 * @param combinationProductQuery
	 * @return
	 */
	public List<CombinationProduct> queryByProductId(CombinationProductQuery combinationProductQuery);
}
