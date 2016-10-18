package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.model.TradeParams;
import com.lunjar.ebp.bizsupport.model.TradeRefundParams;
import com.lunjar.ebp.bizsupport.query.TradeQuery;

public interface TradeMapper {
	/***/
	Trade load(Long id);

	/***/
	void insert(Trade trade);

	/***/
	int update(Trade trade);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Trade> queryList(TradeQuery tradeQuery);

	/***/
	int queryCount(TradeQuery tradeQuery);

	Trade getTrade(TradeParams params);

	/**
	 * 通过退款状态查询订单
	 * 
	 * @param tradeRefundParams
	 * @return
	 */
	List<Trade> getTradeForRefundStatus(TradeRefundParams tradeRefundParams);

	Trade getTradeInfoBuyTradeNo(String tradeNo);

	List<Trade> getWaitBuyerPayTradeList(String overGmtCreate);
}