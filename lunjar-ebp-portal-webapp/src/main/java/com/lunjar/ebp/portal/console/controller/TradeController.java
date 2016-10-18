package com.lunjar.ebp.portal.console.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lunjar.ebp.bizsupport.dto.CombinDto;
import com.lunjar.ebp.bizsupport.dto.DiscountDto;
import com.lunjar.ebp.bizsupport.dto.EnterpriseProModel;
import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.dto.TradeInfoDto;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.model.CombinationProduct;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.OutTradeNo;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.BuyerAddressService;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.ebp.bizsupport.service.CartService;
import com.lunjar.ebp.bizsupport.service.CollectPlaceService;
import com.lunjar.ebp.bizsupport.service.CombinationProductService;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.ebp.bizsupport.service.ExpressService;
import com.lunjar.ebp.bizsupport.service.OrdersService;
import com.lunjar.ebp.bizsupport.service.OutTradeNoService;
import com.lunjar.ebp.bizsupport.service.ProductService;
import com.lunjar.ebp.bizsupport.service.ProductSkuService;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.ebp.portal.console.constants.Constants;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.JsonUtils;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 订单控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping(value = "trade")
@Controller
public class TradeController extends BaseController {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TradeController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductSkuService productSkuService;

	@Autowired
	private BuyerAddressService buyerAddressService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private TradeService tradeService;

	@Autowired
	private CollectPlaceService collectPlaceService;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private ExpressService expressService;

	@Autowired
	private OrdersService ordersService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private CombinationProductService combinationProductService;

	@Autowired
	private OutTradeNoService outTradeNoService;

	@Autowired
	private PortalAgentSession portalAgentSession;

	/**
	 * 跳转到提交订单页面
	 * 
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = {"commitPage"})
	public String commitPage(String ids,String shopId, Model model, HttpServletRequest request) throws ServiceException {
		logger.debug("------visit TradeController.commitPage method start---------");
		if (StringUtils.isBlank(shopId)) {
			logger.warn("shopId 不能为空!");
			throw new ServiceException("shopId 不能为空!");
		}
		PortalAgent agent = portalAgentSession.get(request);
		if (agent == null) {
			Shop shop = shopService.load(Long.parseLong(shopId));
			if (shop != null) {
//				String visitUrl = request.getRequestURL().toString();
//				String shortUrl = request.getRequestURI();
//				request.setAttribute(Constants.VISIT_URL, visitUrl);
//				request.setAttribute(Constants.SHORT_URL, shortUrl);
//				logger.info("tradeAction 获取到的访问地址是============" + request.getAttribute(Constants.VISIT_URL));
				return "redirect:" + shop.getUrl();
			}
		}
		// 查询所有地址
		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		// buyerAddressQuery.setBuyerId(getBuyerId());
		buyerAddressQuery.setBuyerId(agent.getBuyerId());
		buyerAddressQuery.setStatus(1);
		buyerAddressQuery.setSort("GMT_MODIFY desc");
		List<BuyerAddress> buyerAddressList = buyerAddressService.queryList(buyerAddressQuery);
		model.addAttribute("buyerAddresss", buyerAddressList);

		String city = null;
		if (buyerAddressList != null && buyerAddressList.size() > 0) {
			for (BuyerAddress b : buyerAddressList) {
				if (1 == b.getIsDefault()) {
					city = b.getCity();
				}
			}
		}

		// List<EnterpriseProModel> list = productService.getCartList(ids,
		// getBuyerId(), city);
		List<EnterpriseProModel> list = productService.getCartList(ids, agent.getBuyerId(), city);

		BigDecimal total = new BigDecimal("0");

		String postFlag = "2";

		if (list != null && list.size() > 0) {
			for (EnterpriseProModel e : list) {
				// 等于1表示快递
				total = total.add(e.getDiscountDto().getRealPrice());
				if (e.getDistributionMode().equals(1)) {
					postFlag = "1";
				}
				Enterprise ese = enterpriseService.load(e.getEnterpriseId());
				if (ese != null) {
					e.setHeadPicUrl(ese.getHeadPicUrl());
				}
			}
		}

		if (list != null && list.size() > 0) {
			model.addAttribute("cartDtos", JsonUtils.beanToJSON(list));
		}

		model.addAttribute("total", total);
		// 是否需要展现地址
		model.addAttribute("postFlag", postFlag);

		model.addAttribute("title", "结算");

		model.addAttribute("openId", agent.getOpenId());

		model.addAttribute("shopId", shopId);

		logger.debug("------visit TradeController.commitPage method end---------");

		return "pages/billing";
	}

	/**
	 * 在订单提交页面改变地址执行的方法
	 * 
	 * @param ids
	 *            购物车id
	 * @param addressId
	 *            地址id
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = {"asynCommitPage"})
	@ResponseBody
	public LunjarApiResponse asynCommitPage(String ids, String addressId, String shopId, HttpServletRequest request)
			throws ServiceException {
		if (StringUtils.isBlank(shopId)) {
			logger.warn("shopId 不能为空!");
			throw new ServiceException("shopId 不能为空!");
		}
		PortalAgent agent = portalAgentSession.get(request);
		if (ids == null || "".equals(ids)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "购物车编号为空");
		}
		if (addressId == null || "".equals(addressId)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "地址编号为空");
		}

		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		// buyerAddressQuery.setBuyerId(getBuyerId());
		buyerAddressQuery.setStatus(1);
		buyerAddressQuery.setIdArray(new Long(addressId));
		List<BuyerAddress> buyerAddressList = buyerAddressService.queryList(buyerAddressQuery);

		if (buyerAddressList == null || buyerAddressList.size() <= 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "地址查询错误");
		}
		BuyerAddress b = buyerAddressList.get(0);

		String city = b.getCity();

		// List<EnterpriseProModel> list = productService.getCartList(ids,
		// getBuyerId(), city);
		List<EnterpriseProModel> list = productService.getCartList(ids, agent.getBuyerId(), city);
		Map<String, Object> map = new HashMap<>();
		map.put("cartDtos", list);

		return LunjarApiResponse.create(map);
	}

	/**
	 * 订单列表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = { "list" })
	public String tradeList(String shopId, TradeQuery tradeQuery, Model model,
			HttpServletRequest request) throws ServiceException {
		logger.debug("------visit TradeController.tradeList method start---------");

		if (StringUtils.isBlank(shopId)) {
			logger.warn("shopId 不能为空!");
			throw new ServiceException("shopId 不能为空!");
		}
		PortalAgent agent = portalAgentSession.get(request);
		if (agent == null) {
			Shop shop = shopService.load(Long.parseLong(shopId));
			if (shop != null) {
				return "redirect:" + shop.getUrl();
			}
		}
		PageResult<TradeDto> listTrade = tradeList(tradeQuery, agent.getBuyerId());

		model.addAttribute("trades", listTrade);
		model.addAttribute("tradeQuery", tradeQuery);
		/**
		 * 1: '待付款', 2: '待付款', 3: '部分发货', 4: '待发货', 5: '已发货', 6: '已签收', 7:
		 * '交易成功', 8: '已关闭', 9: '已关闭', 10: '待付款确认中付款', 11: '0元购合约中'
		 */
		if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue())) {
			model.addAttribute("title", "待付款");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.WAIT_BUYER_PAY.getValue())) {
			model.addAttribute("title", "待付款");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.SELLER_CONSIGNED_PART.getValue())) {
			model.addAttribute("title", "待发货");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue())) {
			model.addAttribute("title", "待发货");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.WAIT_BUYER_CONFIRM_GOODS.getValue())) {
			model.addAttribute("title", "已发货");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.TRADE_BUYER_SIGNED.getValue())) {
			model.addAttribute("title", "已签收");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.TRADE_FINISHED.getValue())) {
			model.addAttribute("title", "交易成功");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.TRADE_CLOSED.getValue())) {
			model.addAttribute("title", "已关闭");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue())) {
			model.addAttribute("title", "已关闭");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.PAY_PENDING.getValue())) {
			model.addAttribute("title", "待付款确认中付款");
		} else if (tradeQuery.getStatus() != null
				&& tradeQuery.getStatus().equals(EnumTradeStatus.WAIT_PRE_AUTH_CONFIRM.getValue())) {
			model.addAttribute("title", "0元购合约中");
		}
		 else if(tradeQuery.getStatus() == null && tradeQuery.getRefundStatus() != null){
		 model.addAttribute("title", "已退款");
		 }
		else {
			model.addAttribute("title", "所有订单");
		}

		// model.addAttribute("openId", getOpenId());
		model.addAttribute("openId", agent.getOpenId());
		model.addAttribute("shopId", shopId);
		logger.debug("------visit TradeController.tradeList method end---------");
		return "pages/tradelist";
	}

	// TODO
	/**
	 * 获取OpenId
	 * 
	 * @return
	 * @throws ServiceException
	 */
	// public String getOpenId() throws ServiceException {
	// Buyer buyer = buyerService.getBuyerInfo(Constants.BUYER_TOKEN_ID, null,
	// null);
	// String openId = "op7OrsxvUMvaS-AP-wPYxcz92RuU";
	// if (buyer != null) {
	// openId = buyer.getBuyerId();
	// }
	// return openId;
	// }

	/**
	 * 查询列表的公共方法
	 * 
	 * @param tradeQuery
	 * @return
	 */
	private PageResult<TradeDto> tradeList(TradeQuery tradeQuery, Long buyerId) {

		tradeQuery.setSort("GMT_CREATE desc");

		tradeQuery.setBuyerId(buyerId);
		Integer status = tradeQuery.getStatus();
		if (EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue().equals(tradeQuery.getStatus())
				|| EnumTradeStatus.WAIT_BUYER_PAY.getValue().equals(tradeQuery.getStatus())) {
			tradeQuery.setStatusArray(EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue(),
					EnumTradeStatus.WAIT_BUYER_PAY.getValue());
			tradeQuery.setStatus(null);
		} else if (EnumTradeStatus.SELLER_CONSIGNED_PART.getValue().equals(tradeQuery.getStatus())
				|| EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue().equals(tradeQuery.getStatus())) {
			tradeQuery.setStatusArray(EnumTradeStatus.SELLER_CONSIGNED_PART.getValue(),
					EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
			tradeQuery.setStatus(null);
		}

		PageResult<TradeDto> listTrade = tradeService.quyerTradeList(tradeQuery);
		if (listTrade != null) {
			for (TradeDto t : listTrade.getData()) {
				judgeTime(t);
			}
		}
		tradeQuery.setStatus(status);
		return listTrade;
	}

	/**
	 * 查询订单列表返回json
	 * 
	 * @param tradeQuery
	 */
	@ResponseBody
	@RequestMapping(value = "listJson/{shopId}")
	public LunjarApiResponse tradeListJson(TradeQuery tradeQuery, HttpServletRequest request) {
		PortalAgent agent = portalAgentSession.get(request);
		return LunjarApiResponse.create(tradeList(tradeQuery, agent.getBuyerId()));
	}

	/**
	 * 订单详情页面
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = {"detail"})
	public String tradeDetail(String tradeId, String shopId,
			Model model, HttpServletRequest request) throws ServiceException {
		if (StringUtils.isBlank(shopId)) {
			logger.warn("shopId 不能为空!");
			throw new ServiceException("shopId 不能为空!");
		}
		PortalAgent agent = portalAgentSession.get(request);
		if (agent == null) {
			Shop shop = shopService.load(Long.parseLong(shopId));
			if (shop != null) {
				return "redirect:" + shop.getUrl();
			}
		}
		TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), agent.getBuyerId(), 1);
		if (dto != null && dto.getTrade() != null) {
			judgeTime(dto);
		}

		model.addAttribute("trade", dto);
		// model.addAttribute("groomShops",
		// productService.getGroomProduct(getShopId()));
		model.addAttribute("groomShops", productService.getGroomProduct(agent.getShopId()));
		model.addAttribute("title", "订单详情");

		// model.addAttribute("openId", getOpenId());
		model.addAttribute("openId", agent.getOpenId());
		model.addAttribute("shopId", shopId);
		return "pages/tradedetail";
	}

	/**
	 * 判断显示退款按钮的时间
	 * 
	 * @param tradeDto
	 */
	private void judgeTime(TradeDto tradeDto) {
		if (tradeDto.getTrade() != null && tradeDto.getTrade().getEndTime() != null
				&& tradeDto.getTrade().getStatus() != null
				&& tradeDto.getTrade().getStatus().equals(EnumTradeStatus.TRADE_FINISHED.getValue())) {

			Date endTime = tradeDto.getTrade().getEndTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endTime);
			calendar.add(Calendar.DAY_OF_MONTH, 15);
			Date end = calendar.getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.debug("申请退款的最晚时间：{}", format.format(end));
			if (new Date().compareTo(end) > 0) {
				// 不显示当前时间比最晚时间大，过了最晚的退款申请时间
				tradeDto.setShowRefundBtn("1");
			} else {
				// 显示
				tradeDto.setShowRefundBtn("2");
			}
		}
	}

	/**
	 * 提交订单方法
	 * 
	 * @throws ServiceException
	 *
	 */
	@RequestMapping(value = "commit/{shopId}")
	@ResponseBody
	public LunjarApiResponse commit(String value, String combinValue, Long combinationId, HttpServletRequest request)
			throws ServiceException {
		/**
		 * 如果是快递
		 * [{"addressId":1,"cartIdList":[9],"enterpriseId":1,"isInvoice":0,
		 * "buyerRemarks":'啊是干撒的'}]
		 * 
		 * 如果是自提：
		 * [{"addressId":1,"cartIdList":[9],"enterpriseId":1,"isInvoice":0,
		 * "buyerRemarks":'啊是干撒的',"buyerName":'自提',"buyerPhone":1231312}]
		 */
		PortalAgent agent = portalAgentSession.get(request);
		List<TradeInfoDto> ts = null;
		List<Trade> trades = new ArrayList<>();
		StringBuilder ids = new StringBuilder();
		try {
			ts = JSONArray.parseArray(value, TradeInfoDto.class);
		} catch (Exception e) {
			logger.error("解析json错误", e);
			throw new ServiceException("订单数据错误");
		}

		if (ts != null && ts.size() > 0 && !(combinValue != null && !combinValue.trim().equals(""))) {
			// 非组合商品提交
			for (TradeInfoDto t : ts) {
				// t.setSaleId(getSaleId());
				t.setSaleId(agent.getSaleId());
				Long id = tradeService.add(t);
				ids.append(id + ";");
				// 查询返回,支付要使用
				Trade trade = tradeService.load(id);
				trades.add(trade);
			}

			OutTradeNo outTradeNo = new OutTradeNo();
			outTradeNo.setTradeIds(ids.toString());
			String payNo = outTradeNoService.add(outTradeNo);
			Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("payNo", payNo);
			hashMap.put("trades", trades);

			return LunjarApiResponse.create(hashMap);
		} else {
			// 组合商品提交
			List<CombinDto> combinDtos = null;
			try {
				combinDtos = JSONArray.parseArray(combinValue, CombinDto.class);
			} catch (Exception e) {
				logger.error("解析json错误", e);
				throw new ServiceException("组合订单数据错误");
			}

			if (combinDtos.size() < 2) {
				// 说明数据有问题，组合最少两个商品
				throw new ServiceException("组合商品数据错误");
			} else {
				CombinationProduct c = combinationProductService.load(combinationId);
				if (c == null) {
					// 查询组合商品为空
					logger.error("查询组合商品为空，组合id为{}", combinationId);
					throw new ServiceException("组合商品为空");
				}
				for (CombinDto c1 : combinDtos) {
					if (c.getProduct1Id() != null && c.getProduct1Id().equals(c1.getProductId())) {
						c1.setRealPrice(c.getProduct1RealPrice());
						continue;
					}

					if (c.getProduct2Id() != null && c.getProduct2Id().equals(c1.getProductId())) {
						c1.setRealPrice(c.getProduct2RealPrice());
						continue;
					}

					if (c.getProduct3Id() != null && c.getProduct3Id().equals(c1.getProductId())) {
						c1.setRealPrice(c.getProduct3RealPrice());
						continue;
					}

					if (c.getProduct4Id() != null && c.getProduct4Id().equals(c1.getProductId())) {
						c1.setRealPrice(c.getProduct4RealPrice());
						continue;
					}

					if (c.getProduct5Id() != null && c.getProduct5Id().equals(c1.getProductId())) {
						c1.setRealPrice(c.getProduct5RealPrice());
						continue;
					}
				}
			}
			// Shop shop = shopService.load(getShopId());
			Shop shop = shopService.load(agent.getShopId());
			if (ts != null) {
				for (TradeInfoDto t : ts) {
					t.setListCombinDto(combinDtos);
					t.setSaleId(shop.getSaleId());
					// t.setShopId(getShopId());
					// t.setBuyerId(getBuyerId());
					t.setShopId(agent.getShopId());
					t.setBuyerId(agent.getBuyerId());
					t.setCombinationId(combinationId);
				}
			}

			// 调用组合商品提交订单方法
			Long id = tradeService.addCombinationTrade(ts.get(0));
			// 查询返回,支付要使用
			Trade trade = tradeService.load(id);
			trades.add(trade);
			return LunjarApiResponse.create(trades);
		}
	}

	/**
	 * 付款前取消订单
	 * 
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "cancel/{shopId}")
	@ResponseBody
	public LunjarApiResponse cancelTrade(Trade trade, HttpServletRequest request) throws ServiceException {
		logger.debug("---------TradeController.cancelTrade start-----------");
		PortalAgent agent = portalAgentSession.get(request);
		Long tradeId = trade.getId();

		List<Integer> listStatus = new ArrayList<>();
		listStatus.add(EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue());
		listStatus.add(EnumTradeStatus.WAIT_BUYER_PAY.getValue());
		logger.debug("---------TradeController.cancelTrade end-----------");

		return updateTrade(tradeId, agent.getBuyerId(), 1, EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue(),
				listStatus);
		// return LunjarApiResponse.create();
	}

	/**
	 * 确认收货
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "confirmReceipt/{shopId}")
	@ResponseBody
	public LunjarApiResponse confirmReceipt(Trade trade, HttpServletRequest request) throws ServiceException {
		Long tradeId = trade.getId();
		PortalAgent agent = portalAgentSession.get(request);
		if (tradeId == null) {
			logger.error("---订单号为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单编号不能为空");
		}

		TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), agent.getBuyerId(), 1);

		if (dto == null || dto.getTrade() == null) {
			logger.error("---订单为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单不存在");
		}
		if (!EnumTradeStatus.WAIT_BUYER_CONFIRM_GOODS.getValue().equals(dto.getTrade().getStatus())) {
			logger.error("---订单状态不对---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单状态不对");
		}
		Trade t = new Trade();
		t.setId(dto.getTrade().getId());
		t.setStatus(EnumTradeStatus.TRADE_FINISHED.getValue());
		t.setEndTime(new Date());
		int num = tradeService.update(t);
		dto = tradeService.getTradeInfo(new Long(tradeId), agent.getBuyerId(), 1);
		return LunjarApiResponse.create(dto);
	}

	/**
	 * 订单支付
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "pay/{shopId}")
	@ResponseBody
	public LunjarApiResponse payTrade(Trade trade, String payNo, HttpServletRequest request) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		Long tradeId = trade.getId();

		String[] arrayIds = null;

		if (payNo != null) {
			OutTradeNo outTradeNo = outTradeNoService.load(payNo);
			if (outTradeNo != null) {
				String ids = outTradeNo.getTradeIds();
				if (ids != null && ids.contains(";")) {
					arrayIds = ids.split(";");
				}
			}
		}

		if (tradeId != null) {
			arrayIds = new String[1];
			arrayIds[1] = tradeId.toString();
		}

		if (arrayIds == null) {
			logger.error("---订单号为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单编号不能为空");
		}

		for (int i = 0; i < arrayIds.length; i++) {

			TradeDto dto = tradeService.getTradeInfo(new Long(arrayIds[i]), agent.getBuyerId(), 1);

			if (dto == null || dto.getTrade() == null) {
				logger.error("---订单为空---");
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单不存在");
			}
			if (!(EnumTradeStatus.WAIT_BUYER_PAY.getValue().equals(dto.getTrade().getStatus())
					|| EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue().equals(dto.getTrade().getStatus()))) {
				logger.error("---订单状态不对---");
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单状态不对");
			}

			Trade t = new Trade();
			t.setId(trade.getId());
			t.setStatus(EnumTradeStatus.WAIT_SELLER_SEND_GOODS.getValue());
			int num = tradeService.update(t);
		}
		return LunjarApiResponse.create();
	}

	/**
	 * 申请退款
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@ResponseBody
	@RequestMapping(value = "applyRefund/{shopId}")
	public LunjarApiResponse applyRefund(Orders orders, String reason, HttpServletRequest request)
			throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		Long id = orders.getId();
		if (id == null) {
			logger.error("---退款订单号为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "退款订单号为空");
		}
		Orders dto = ordersService.load(id);
		if (dto == null) {
			logger.error("---退款订单为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "退款订单不存在");
		} else {
			Long tradeId = dto.getTradeId();
			TradeDto tradeDto = tradeService.getTradeInfo(new Long(tradeId), agent.getBuyerId(), 1);
			if (tradeDto == null) {
				logger.error("---退款订单有问题---");
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "退款订单有问题");
			}
		}

		if (reason == null || "".equals(reason.trim())) {
			logger.error("---退款原因不能为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "退款原因不能为空");
		}

		tradeService.addRefund(id, agent.getBuyerId(), reason);

		return LunjarApiResponse.create();
	}

	/**
	 * 
	 * @param tradeId
	 *            更新的订单id
	 * @param ownerId
	 *            所有者id
	 * @param ownerType
	 *            所有者类型 1：买家 2：商铺3：商家
	 * @param updateStatus
	 *            订单需要更新的状态
	 * @param listStatus
	 *            需要更新状态前的订单状态，也就是订单要更新成待发货，那订单更新前的状态必须是已支付
	 * @return
	 * @throws ServiceException
	 */
	private LunjarApiResponse updateTrade(Long tradeId, Long ownerId, Integer ownerType, Integer updateStatus,
			List<Integer> listStatus) throws ServiceException {

		if (tradeId == null) {
			logger.error("---订单号为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单编号不能为空");
		}

		TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), ownerId, ownerType);

		if (dto == null || dto.getTrade() == null) {
			logger.error("---订单为空---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单不存在");
		}

		if (!tradeStatusValidate(dto.getTrade().getStatus(), listStatus)) {
			logger.error("---订单状态有误---");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单状态有误");
		}

		Trade t = new Trade();
		t.setId(new Long(tradeId));
		t.setStatus(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue());
		t.setEndTime(new Date());
		int num = tradeService.update(t);
		dto = tradeService.getTradeInfo(new Long(tradeId), ownerId, ownerType);
		return LunjarApiResponse.create(dto);
	}

	/**
	 * 作为更新订单时订单状态的一个校验
	 * 
	 * @return
	 */
	private boolean tradeStatusValidate(Integer tradeStatus, List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			if (tradeStatus.equals(list.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 商品组合提交页面
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "commitCombinPage/{shopId}")
	public String commitCombinPage(Long id, String value, Model model, HttpServletRequest request)
			throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		List<CombinDto> ts = null;
		try {
			ts = JSONArray.parseArray(value, CombinDto.class);
		} catch (Exception e) {
			logger.error("解析json错误", e);
			throw new ServiceException("订单数据错误");
		}
		if (ts.size() < 2) {
			// 说明数据有问题，组合最少两个商品
			throw new ServiceException("组合商品数据错误");
		}

		// EnterpriseProModel e = productService.getCombin(id, ts, getShopId(),
		// getBuyerId(), null);
		EnterpriseProModel e = productService.getCombin(id, ts, agent.getShopId(), agent.getBuyerId(), null);
		String postFlag = "2";
		// 1表示快递
		if (e.getDistributionMode() == 1) {
			postFlag = "1";
		}
		// 是否需要展现地址
		model.addAttribute("postFlag", postFlag);

		// 查询地址
		Enterprise enterprise = enterpriseService.load(e.getEnterpriseId());
		// 快递
		if (enterprise.getDistributionMode() == 1) {
			BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
			buyerAddressQuery.setBuyerId(agent.getBuyerId());
			buyerAddressQuery.setStatus(1);
			buyerAddressQuery.setSort("GMT_MODIFY desc");
			List<BuyerAddress> buyerAddressList = buyerAddressService.queryList(buyerAddressQuery);
			model.addAttribute("buyerAddress", buyerAddressList);
		}
		// 查询商家头像
		e.setHeadPicUrl(enterprise.getHeadPicUrl());

		DiscountDto dto = new DiscountDto();
		dto.setEnterpriseId(e.getEnterpriseId());
		DiscountDto d = combinationProductService.doCombinationDiscount(id, dto);
		e.setDiscountDto(d);
		if (e != null) {
			List<EnterpriseProModel> list = new ArrayList<>();
			list.add(e);
			model.addAttribute("cartDtos", JsonUtils.beanToJSON(list));
		}

		if (d != null) {
			model.addAttribute("total", d.getRealPrice());
		}
		// 返回请求的值
		model.addAttribute("value", value);
		model.addAttribute("id", id);

		model.addAttribute("openId", agent.getOpenId());
		return "pages/billing";
	}

	/**
	 * 组合商品选择切换地址调用方法
	 * 
	 * @param id
	 *            组合id
	 * @param value
	 *            组合的商品
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "asynCommitCombinPage/{shopId}")
	@ResponseBody
	public LunjarApiResponse asynCommitCombinPage(Long id, Long enterpriseId, String value, String addressId,
			HttpServletRequest request) throws ServiceException {
		PortalAgent agent = portalAgentSession.get(request);
		if (addressId == null || "".equals(addressId)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "地址编号为空");
		}

		BuyerAddressQuery buyerAddressQuery = new BuyerAddressQuery();
		buyerAddressQuery.setBuyerId(agent.getBuyerId());
		buyerAddressQuery.setStatus(1);
		buyerAddressQuery.setIdArray(new Long(addressId));
		List<BuyerAddress> buyerAddressList = buyerAddressService.queryList(buyerAddressQuery);

		if (buyerAddressList == null || buyerAddressList.size() <= 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "地址查询错误");
		}
		BuyerAddress b = buyerAddressList.get(0);
		// 拿到city
		String city = b.getCity();

		// 调用修改地址改变运费方法
		DiscountDto dto = new DiscountDto();
		// 商户id
		dto.setEnterpriseId(enterpriseId);
		dto.setCity(city);
		DiscountDto d = combinationProductService.doCombinationDiscount(id, dto);

		// 封装成EnterpriseProModel返回
		List<EnterpriseProModel> list = new ArrayList<>();

		EnterpriseProModel e = new EnterpriseProModel();
		e.setEnterpriseId(enterpriseId);
		e.setDiscountDto(d);
		list.add(e);
		Map<String, Object> map = new HashMap<>();
		map.put("cartDtos", list);

		return LunjarApiResponse.create(map);
	}

}
