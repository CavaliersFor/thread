package com.lunjar.ebp.portal.console.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunjar.ebp.bizsupport.enums.EnumOrdersRefundStatus;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.query.OrdersQuery;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;

@Controller
@RequestMapping(value = "buyer")
public class BuyerController extends BaseController {

	// public Long getBuyerId() {
	// return new Long(1);
	// }

	// TODO 获取商铺id
	// public Long getShopId() {
	// return new Long(1);
	// }

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PortalAgentSession portalAgentSession;

	@RequestMapping(value = "info/{shopId}")
	public String info(Model model, @PathVariable("shopId") String shopId, HttpServletRequest request) {
		PortalAgent agent = portalAgentSession.get(request);
		Buyer buyer = buyerService.load(agent.getBuyerId());
		model.addAttribute("buyer", buyer);

		TradeQuery tradeQuery = new TradeQuery();
		tradeQuery.setBuyerId(agent.getBuyerId());
		// 待支付
		tradeQuery.setStatusArray(EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue(),
				EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		int waitPay = tradeService.queryCount(tradeQuery);
		// 待发货
		tradeQuery.setStatusArray(EnumTradeStatus.SELLER_CONSIGNED_PART.getValue(),
				EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
		int waitSend = tradeService.queryCount(tradeQuery);
		// 待收货
		tradeQuery.setStatusArray(EnumTradeStatus.WAIT_BUYER_CONFIRM_GOODS.getValue());
		int waitDelivery = tradeService.queryCount(tradeQuery);
		// 已完成
		tradeQuery.setStatusArray(EnumTradeStatus.TRADE_FINISHED.getValue());
		int completed = tradeService.queryCount(tradeQuery);

		// 查询已退款的
		int refunded = 0;
		List<Trade> list = tradeService.getTradeForRefundStatus(agent.getBuyerId(),
				EnumOrdersRefundStatus.SUCCESS.getValue());
		if (list != null) {
			refunded = list.size();
		}

		model.addAttribute("waitPay", waitPay);
		model.addAttribute("waitSend", waitSend);
		model.addAttribute("completed", completed);
		model.addAttribute("waitDelivery", waitDelivery);
		model.addAttribute("refunded", refunded);

		// 底部菜单查询
		CategoryQuery query = new CategoryQuery();
		query.setShopId(agent.getShopId());
		query.setSort("sort_num asc");
		// 查询使用中的
		query.setStatus(1);
		List<Category> listCategory = categoryService.queryList(query);
		model.addAttribute("categorys", listCategory);

		model.addAttribute("title", "个人中心");
		// 店铺名称
		model.addAttribute("shopName", agent.getShopName());
		// 店铺地址
		model.addAttribute("shopUrl", agent.getUrl());
		
		model.addAttribute("shopId", shopId);
		return "pages/center";
	}
}
