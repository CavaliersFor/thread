package com.lunjar.ebp.bizsupport.service;

import java.util.Date;
import java.util.List;

import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.dto.TradeInfoDto;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
/**
 * 订单服务类
 * @author Administrator
 *
 */
public interface TradeService extends CommonService<Trade, TradeQuery>{
	
	/**
	 * 查询订单列表和关联的子订单
	 * @param tradeQuery
	 * @return
	 */
	PageResult<TradeDto> quyerTradeList(TradeQuery tradeQuery);

	 /**
	  * 新增订单
	  *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	  *2016年8月18日下午3:16:42
	  * @param tradeDto
	  * @return
	  * @throws ServiceException
	  */
	Long add(TradeInfoDto tradeDto) throws ServiceException;
	
	/**
	 * 根据订单id、订单拥有者id来获取单条订单信息
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月19日上午9:07:58
	 * @param tradeId 订单id
	 * @param ownerId 拥有者id
	 * @param ownerType 拥有者类型 1：买家 2：商铺3：商家
	 * @return
	 * @throws ServiceException
	 */
	TradeDto getTradeInfo(Long tradeId, Long ownerId, Integer ownerType) throws ServiceException;
	
	
	/**
	 * 通过退款状态查询订单
	 * @param tradeRefundParams
	 * @return
	 */
	List<Trade> getTradeForRefundStatus(Long buyerId,Integer refundStatus);
	/**
	 * 申请退款操作
	 * @param ordersId
	 * @param buyerId
	 */
	public void addRefund(Long ordersId, Long buyerId,String reason) throws ServiceException;

	/**
	 * 根据订单编号查询订单
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月26日下午2:13:26
	 * @param outTradeNo
	 * @return
	 */
	Trade getTradeInfoBuyTradeNo(String outTradeNo);

	/**
	 * 新增组合订单
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月26日下午2:58:09
	 * @return
	 */
	 Long addCombinationTrade(TradeInfoDto tradeInfoDto) throws ServiceException;
	 
	 /**
	  * 订单发货方法
	  */
	void sendGoogs(Long[] tradeIds, String[] companys, String[] codes, Integer distributionMode);

	/**
	 * 根据订单的状态过期时间来查询代付款订单
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月23日下午5:43:05
	 * @param date
	 * @return
	 */
	List<Trade> getWaitBuyerPayTradeList(String date);
}
