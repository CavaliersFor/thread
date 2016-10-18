package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.query.OutTradeNoQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 多个订单支付获取订单编号服务类
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月30日下午12:06:38
 */
public interface OutTradeNoService {

	/**
	 * 根据id获取对象
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月30日下午12:09:11
	 * @param outTradeNo
	 * @return
	 */
	OutTradeNo load(String outTradeNo);

	/**
	 * 新增
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月30日下午12:10:49
	 * @param t
	 * @return
	 * @throws ServiceException
	 */
	String add(OutTradeNo t) throws ServiceException;

	/**
	 * 更新
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月30日下午12:11:22
	 * @param t
	 */
	void update(OutTradeNo t);
	
	/**
	 * 根据查询条件查询列表
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月30日下午12:12:10
	 * @param q
	 * @return
	 */
	List<OutTradeNo> queryList(OutTradeNoQuery q);

	/**
	 * 根据查询条件查询数量
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月30日下午12:13:00
	 * @param q
	 * @return
	 */
	int queryCount(OutTradeNoQuery q);
}
