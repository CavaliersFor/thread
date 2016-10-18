package com.lunjar.ebp.bizsupport.service;

import java.math.BigDecimal;

import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;
import com.lunjar.products.core.exception.ServiceException;
/**
 * 快递费用表服务类
 * @author Administrator
 *
 */
import com.lunjar.products.core.model.PageResult;
public interface ExpressService extends CommonService<Express, ExpressQuery>{
	/**
	 * 通过id和商户id删除
	 * @param express
	 * @return
	 */
	int deleteByIdAndEnterpriseId(Express express);
	
	/**
	 * 获取快递费用
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月19日下午4:10:02
	 * @param enterpriseId 商家id
	 * @param city 收货城市
	 * @param num 商品数量
	 * @param pId 产品id或者skuid
	 * @param type 1：产品id 2：skuId
	 * @return
	 * @throws ServiceException
	 */
	BigDecimal getPostFee(Long enterpriseId, String city, Integer num, Long pId, Integer type) throws ServiceException;
	
	/**
	 * 查询快递带分页
	 * @param query
	 * @return
	 */
	public PageResult<Express> querListPage(ExpressQuery query);

}
