package com.lunjar.ebp.bizsupport.service;

import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
/**
 * 子订单服务类
 * @author Administrator
 *
 */
public interface OrdersService extends CommonService<Orders, OrdersQuery>{

	void updateByQuery(Orders orders, OrdersQuery query);

	void updateStatusByTradeId(Long tradeId, int status);
	
}
