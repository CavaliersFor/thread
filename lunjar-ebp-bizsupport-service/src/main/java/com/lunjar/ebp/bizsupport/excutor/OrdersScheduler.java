package com.lunjar.ebp.bizsupport.excutor;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductSku;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.products.core.utils.DateUtils;

/**
 * 定时处理订单
 * 
 */

@EnableScheduling
@Component
public class OrdersScheduler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TradeService tradeService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private ProductService productService;
	
	// 订单过期时间，单位小时
	@Value("${orders.over.time}")
	private String overTime;

	// 每天晚上12点执行
	@Scheduled(cron = "0 0 0  * * ? ")
	public void execute() throws ParseException {
		logger.debug("=================开始执行超时未支付订单====================");
		Date date = DateUtils.addHouer(new Date(), -Integer.parseInt(overTime));
		List<Trade> list = tradeService.getWaitBuyerPayTradeList(DateUtils.format(date));
		List<Orders> ordersList = null;
		OrdersQuery query = null;
		ProductSku sku = null;
		Product product = null;
		for(Trade t: list) {
			t.setStatus(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue());//到时间未支付自动关闭订单
			t.setGmtModify(new Date());
			t.setEndTime(new Date());
			tradeService.update(t);
			query = new OrdersQuery();
			query.setTradeId(t.getId());
			ordersList = ordersService.queryList(query);
			if (CollectionUtils.isNotEmpty(ordersList)) {
				for(Orders o: ordersList) {
					o.setGmtModify(new Date());
					o.setStatus(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue());
					ordersService.update(o);
					if (o.getSkuId() != null) {
						sku = productSkuService.load(o.getSkuId());
						if (sku != null) {
							sku.setGmtModify(new Date());
							sku.setQuantity(sku.getQuantity() + o.getNum());//还原库存
							productSkuService.update(sku);
						}
					}
					product = productService.load(o.getProductId());
					if (product != null) {
						product.setGmtModify(new Date());
						product.setSaleNum((product.getSaleNum() - o.getNum()) > 0 ? (product.getSaleNum() - o.getNum()):0);//还原销量
						product.setProductNum(product.getProductNum() + o.getNum());//还原库存
						productService.update(product);
					}
				}
			}
		}
	}
	
}
