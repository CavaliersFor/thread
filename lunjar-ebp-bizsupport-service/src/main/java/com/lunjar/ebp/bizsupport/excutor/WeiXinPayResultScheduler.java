package com.lunjar.ebp.bizsupport.excutor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.constants.Constants;
import com.lunjar.ebp.bizsupport.constants.SendMessageConstants;
import com.lunjar.ebp.bizsupport.dto.SendMessageDto;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.OutTradeNoService;
import com.lunjar.ebp.bizsupport.service.SendMessageService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.bizsupport.weixin.PayResultMsgResponse;
import com.lunjar.products.core.utils.DateUtils;
import com.lunjar.products.core.utils.JsonUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 定时刷新微信支付结果
 * 
 */

@EnableScheduling
@Component
public class WeiXinPayResultScheduler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String SUCCESS = "SUCCESS";
	private static final String FORMAT = "yyyyMMddHHmmss";

	@Autowired
	private TradeService tradeService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OutTradeNoService outTradeNoService;
	@Autowired
	private SendMessageService sendMessageService;

	// aliRedis服务器IP
	@Value("${redis.host1}")
	private String host;
	// aliRedis的端口号
	@Value("${redis.port}")
	private int port;
	// aliRedis的连接超时
	@Value("${redis.timeout}")
	private int timeout;
	// aliRedis的密码
	@Value("${redis.password}")
	private String passowrd;
	@Value("${redis.pool.maxIdle}")
	private int maxIdle;// = 200;
	@Value("${redis.index}")
	private int redisIndex;//3
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	@Value("${redis.pool.maxTotal}")
	private int maxTotal;// = 10000;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private boolean testOnBorrow = true;
	private boolean testOnReturn = true;
	private JedisPoolConfig config;
	private JedisPool jedisPool = null;
	private Jedis jedis;

	/**
	 * 初始化Redis配置
	 */
	@PostConstruct
	void init() {
		// 获取初始配置
		config = new JedisPoolConfig();
		// 最大空闲连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
		config.setMaxIdle(maxIdle);
		// 最大连接数, 应用自己评估，不要超过AliCloudDB for Redis每个实例最大的连接数
		config.setMaxTotal(maxTotal);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		jedisPool = new JedisPool(config, host, port, timeout, passowrd);
		jedis = jedisPool.getResource();
		jedis.select(redisIndex);
	}

	/**
	 * 释放jedis资源
	 * 
	 */
	public void destory() {
		if (jedis != null) {
			jedis.close();//
		}
		jedisPool.destroy();// j
	}

	public String lpop(int selectIndex, String key) {
		try {
			jedis.select(selectIndex);
			return jedis.lpop(key);
		} finally {
			jedisPool.destroy();//
		}
	}

	// 每2秒执行一次
	@Scheduled(cron = "0/2 * *  * * ? ")
	public void execute() throws ParseException {
		logger.debug("=================开始执行支付操作回调改变支付状态====================");
		String payResultMsgJsonStr = lpop(Constants.PAY_RESULT_INDEX, Constants.PAY_RESULT_QUEUE);
		if (StringUtils.isNotBlank(payResultMsgJsonStr)) {
			System.out.println(payResultMsgJsonStr);
			// PayResultMsgResponse payResultMsgRsp =
			// JSON.parseObject(payResultMsgJsonStr,
			// PayResultMsgResponse.class);
			PayResultMsgResponse payResult = JsonUtils.jsonToBean(payResultMsgJsonStr, PayResultMsgResponse.class);
			if (SUCCESS.equals(payResult.getReturnCode())) {
				if (SUCCESS.equals(payResult.getResultCode())) {
					if (StringUtils.isNotBlank(payResult.getOutTradeNo())) {
						logger.debug("////////////////////outTradeNo is//////////////////" +payResult.getOutTradeNo() );
						//查询outTradeNo表是否有数据
						OutTradeNo outTradeNo = outTradeNoService.load(payResult.getOutTradeNo());
						if (outTradeNo  != null) {
							String s = outTradeNo.getTradeIds().substring(0, outTradeNo.getTradeIds().lastIndexOf(";"));
							String[] tradeIds = s.split(";");
							int len = tradeIds.length;
							Trade trade = null;
							for(int i = 0; i <len; i++) {
								if (StringUtils.isNotBlank(tradeIds[i])) {
									trade = tradeService.load(Long.parseLong(tradeIds[i]));
									doPaySuccess(trade, payResult);
								}
							}
							outTradeNo.setGmtModify(new Date());
							outTradeNo.setTotalFee(new BigDecimal(payResult.getTotalFee()));// 订单支付总金额);
							outTradeNo.setStatus(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
							outTradeNoService.update(outTradeNo);
						}else {
							Trade trade = tradeService.getTradeInfoBuyTradeNo(payResult.getOutTradeNo());
							doPaySuccess(trade, payResult);
						}
					}
				}
			}
		}else {
			logger.debug("=================获取到的缓存数据为空====================");
		}
		logger.debug("=================结束执行支付操作回调改变支付状态====================");
	}
	
	private void doPaySuccess(Trade trade, PayResultMsgResponse payResult ) throws ParseException {
		if (trade != null) {
			logger.debug("////////////////////开始处理支付回调//////////////////" );
			trade.setPaymentNo(payResult.getTransactionId());// 微信订单号
//			trade.setTradeNo(payResult.getOutTradeNo());// 商家订单编号
//			trade.setEndTime(new Date());// 订单结束时间
			trade.setBuyerPayTime(
					DateUtils.convertStringToDate(FORMAT, payResult.getTimeEnd()));// 支付成功时间
			trade.setPaymentType(1);// 支付类型 1:微信2：支付宝3：网银
//			trade.setRealPrice(new BigDecimal(payResult.getTotalFee()));// 订单支付总金额
			trade.setStatus(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
			tradeService.update(trade);
//			Orders orders = new Orders();
//			orders.setStatus(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
			OrdersQuery ordersQuery = new OrdersQuery();
			ordersQuery.setTradeId(trade.getId());
//			ordersService.updateStatusByTradeId(trade.getId(), EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
			List<Orders> list = ordersService.queryList(ordersQuery);
			if (CollectionUtils.isNotEmpty(list)) {
//				Long[] ids = new Long[list.size()];
//				for(int i = 0; i < list.size(); i ++) {
//					ids[i] = list.get(i).getId();
//				}
//				ordersQuery.setIdArray(ids);
//				ordersService.updateByQuery(orders, ordersQuery);
				Orders orders = null;
				for(Orders o: list) {
					orders = new Orders();
					orders.setId(o.getId());
					orders.setStatus(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
					ordersService.update(orders);
				}
			}
			//给买家发短信
			logger.debug("////////////////////开始发短信//////////////////" );
			Map<String,String> content = new HashMap<String,String>();
			content.put(SendMessageConstants.SendPickUpGoods.CODE, trade.getPickUpNo());
			content.put(SendMessageConstants.SendPickUpGoods.TRADENO, trade.getTradeNo());
			content.put(SendMessageConstants.SendPickUpGoods.ADDRESS, "省站一号售票口旁");
			content.put(SendMessageConstants.SendPickUpGoods.TIME, trade.getPickUpTime().substring(trade.getPickUpTime().length() -2)+"日");
			SendMessageDto sendMessageDto = new SendMessageDto();
			sendMessageDto.setTplId(SendMessageConstants.SendPickUpGoods.TPID);
			sendMessageDto.setPhoneNum(trade.getBuyerPhone());
			sendMessageDto.setContent(content);
			boolean isSuccess = sendMessageService.sendCodeByTpl(sendMessageDto);
			logger.debug("短信发送状态：" + isSuccess);
			logger.debug("////////////////////结束发短信//////////////////" );
		}
	}
	
	public static void main(String[] args) {
		String l = "106;";
		String s = l.substring(0, l.lastIndexOf(";"));
		System.out.println(s);
	}
}
