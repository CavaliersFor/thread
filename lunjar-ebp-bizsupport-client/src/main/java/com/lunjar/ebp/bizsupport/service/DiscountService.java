package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.model.Discount;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.DiscountQuery;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;

/**
 * 优惠信息服务类
 * @author Administrator
 *
 */
public interface DiscountService extends CommonService<Discount, DiscountQuery>{
	/**
	 * 通过id和EnterpriseId(商家id)进行删除
	 * @param discount
	 * @return
	 */
	int deleteByIdAndEnterpriseId(Discount discount);

	/**
	 * 优惠活动处理
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月18日下午7:54:25
	 * @param DiscountDto
	 * @throws ServiceException 
	 */
	void doDiscount(DiscountDto dto) throws ServiceException;
	
	/**
	 * 查询结果通过分页显示
	 * @param query
	 * @return
	 */
	PageResult<Discount> queryListByPage(DiscountQuery query);
	
	/**
	 * 批量增加优惠信息
	 * @param list
	 */
	public void addDiscount(List<Discount> list);
	
}
