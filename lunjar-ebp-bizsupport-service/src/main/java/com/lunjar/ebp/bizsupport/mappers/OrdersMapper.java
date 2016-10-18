package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;

public interface OrdersMapper {
	/***/
	Orders load(Long id);

	/***/
	void insert(Orders orders);

	/***/
	int update(Orders orders);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Orders> queryList(OrdersQuery ordersQuery);

	/***/
	int queryCount(OrdersQuery ordersQuery);

	void updateByQuery(Orders orders, OrdersQuery query);

	Trade getTradeInfoBuyTradeNo(String tradeNo);
	
	/***/
	void updateStatusByTradeId(@Param("tradeId") Long tradeId, @Param("status") Serializable status);
}