package com.lunjar.ebp.admin.web.controller.trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.dto.TradeDto;
import com.lunjar.ebp.bizsupport.enums.EnumTradeStatus;
import com.lunjar.ebp.bizsupport.model.LogisticsCompany;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.LogisticsCompanyQuery;
import com.lunjar.ebp.bizsupport.query.TradeQuery;
import com.lunjar.ebp.bizsupport.service.LogisticsCompanyService;
import com.lunjar.ebp.bizsupport.service.TradeService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@Controller
@RequestMapping(value = "trade")
public class TradeController extends ControllerSupport {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TradeController.class);

	@Autowired
	private TradeService tradeService;

	@Resource(name = "enterpriseAgentSession")
	private EnterpriseAgentSession enterpriseAgentSession;
	
	@Autowired
	private LogisticsCompanyService logisticsCompanyService;
	
	
	/**
	 * 获取商家id
	 * 
	 * @return
	 */
	public Long getId(HttpServletRequest request) {
		// 登陆信息
		EnterpriseAgent agent = enterpriseAgentSession.get(request);
		return agent.getId();
	}

	@RequestMapping(value = "index")
	public String index() throws ServiceException {

		return "/pages/index";
	}

	@RequestMapping(value = "table")
	public String table() throws ServiceException {

		return "/pages/table";
	}

	/**
	 * 订单列表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "list")
	public String tradeList(TradeQuery tradeQuery, String productName, Date startTime, Date endTime, String time,
			String noShowStatus, Model model, HttpServletRequest request) throws ServiceException {

		logger.debug("------visit TradeController.tradeList method start---------");
		
		if (time == null || "".equals(time)) {
			// 默认查询前三个月的订单
			time = "1";
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


		if (tradeQuery != null) {
			if (tradeQuery.getStatus() != null && tradeQuery.getStatus().equals(0)) {
				tradeQuery.setStatus(null);
			}else if (tradeQuery.getStatus() != null && tradeQuery.getStatus().equals(new Integer(-3))){
				tradeQuery.setRefundStatus(1);
			}
		}

		

		tradeQuery.setEnterpriseId(getId(request));
		tradeQuery.setPageSize(10);

		Map<String, Object> properties = new HashMap<>();
		if (startTime != null && endTime != null) {
			if (startTime.compareTo(endTime) == 0) {
				// 如果两个日期相等
				Calendar endTimeCalendar = Calendar.getInstance();
				endTimeCalendar.setTime(endTime);
				endTimeCalendar.add(Calendar.HOUR_OF_DAY, 23);
				endTimeCalendar.add(Calendar.MINUTE, 59);
				endTime = endTimeCalendar.getTime();
			}
			properties.put("startTime", startTime);
			properties.put("endTime", endTime);
			model.addAttribute("startTime", format.format(startTime));
			model.addAttribute("endTime", format.format(endTime));
		}

		if (productName != null && !"".equals(productName)) {
			model.addAttribute("productName", productName);
			properties.put("productName", productName);
		}

		if (time != null && "1".equals(time)) {
			// 查询条件是近三个月,也就是当前时间往前推三个月
			Date endTime1 = new Date();

			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH, -3);
			Date startTime1 = c.getTime();

			tradeQuery.setGmtCreateFrom(startTime1);
			tradeQuery.setGmtCreateTo(endTime1);
		}

		if (time != null && "2".equals(time)) {
			// 查询条件是前三个月,也就是开始时间是：项目上线时间，结束时间是当前时间往前3个月
			String strTime1 = "2016-09-04 11:32:00";
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startTime1 = null;
			try {
				startTime1 = format1.parse(strTime1);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH, -3);
			Date endTime1 = c.getTime();

			tradeQuery.setGmtCreateFrom(startTime1);
			tradeQuery.setGmtCreateTo(endTime1);
		}
		// 不显示某个状态的订单
		if (noShowStatus != null && !"".equals(noShowStatus)) {
			properties.put("noShowStatus", noShowStatus);
		}
		if (!properties.isEmpty()) {
			tradeQuery.setProperties(properties);
		}

		PageResult<TradeDto> listTrade = tradeList(tradeQuery);
		model.addAttribute("trades", listTrade);
		model.addAttribute("tradeQuery", tradeQuery);
		model.addAttribute("time", time);
		model.addAttribute("noShowStatus", noShowStatus);
		model.addAttribute("title", "订单管理");
		//是否选中
		model.addAttribute("selectType", "1");
		return "/pages/itemlist";
	}

	private PageResult<TradeDto> tradeList(TradeQuery tradeQuery) {
		tradeQuery.setSort("GMT_CREATE desc");
		Integer status = tradeQuery.getStatus();
		if (status != null && status <= 0) {
			tradeQuery.setStatus(null);
		}

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
		}else if (EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue().equals(tradeQuery.getStatus())
				|| EnumTradeStatus.TRADE_CLOSED.getValue().equals(tradeQuery.getStatus())) {
			tradeQuery.setStatusArray(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue(),
					EnumTradeStatus.TRADE_CLOSED.getValue());
			tradeQuery.setStatus(null);
		}

		PageResult<TradeDto> listTrade = tradeService.quyerTradeList(tradeQuery);
		tradeQuery.setStatus(status);
		return listTrade;
	}

	/**
	 * 发货页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "sendGoogsPage")
	public String sendGoogsPage(String tradeIds, String type,Model model,HttpServletRequest request) {
		if (tradeIds == null) {
			// 没有数据
		} else {
			List<Long> list = new ArrayList<>();

			if (tradeIds.contains(",")) {
				String[] strs = tradeIds.split(",");
				for (int i = 0; i < strs.length; i++) {
					list.add(new Long(strs[i]));
				}
			} else {
				list.add(new Long(tradeIds));
			}

			try {
				Long[] arrayId = new Long[list.size()];
				arrayId = list.toArray(arrayId);
				TradeQuery query = new TradeQuery();
				query.setIdArray(arrayId);
				query.setEnterpriseId(getId(request));
				List<Trade> trades = tradeService.queryList(query);

				if (new Integer(1).equals(trades.get(0).getDistributionMode())) {
					LogisticsCompanyQuery queryLogi = new LogisticsCompanyQuery();
					queryLogi.setStatus(1);
					queryLogi.setSort("GMT_CREATE desc");
					// 快递
					// 查询物流公司
					List<LogisticsCompany> companyList = logisticsCompanyService.queryList(queryLogi);
					model.addAttribute("companys", companyList);
				}
				model.addAttribute("trades", trades);
				// 配送方式
				model.addAttribute("distributionMode", trades.get(0).getDistributionMode());
			} catch (Exception e) {
				logger.error("sendGoogsPage错误", e);
			}
		}
		if ("2".equals(type)) {
			return "/pages/sendgoodsPageMax";
		} else {
			return "/pages/sendgoodsPage";
		}
	}

	/**
	 * 发货
	 * 
	 * @return
	 */
	@RequestMapping(value = "sendGoogs")
	@ResponseBody
	public LunjarApiResponse sendGoogs(Long[] tradeIds,String[] companys,String[] codes,HttpServletRequest request) {

		try {
			if (tradeIds == null) {
				// 没有数据
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单号为空!");
			}
			
			TradeQuery query = new TradeQuery();
			query.setIdArray(tradeIds);
			query.setEnterpriseId(getId(request));
			List<Trade> trades = tradeService.queryList(query);
			
			if(trades==null || trades.size()!=tradeIds.length){
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "数据错误!");
			}
			
			Integer distributionMode = trades.get(0).getDistributionMode();
			
			if(new Integer(1).equals(distributionMode)){
				if(companys==null || companys.length<=0){
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "物流公司为空!");
				}
				
				if(codes==null || codes.length<=0){
					return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "物流单号为空!");
				}
			}
			
			tradeService.sendGoogs(tradeIds,companys,codes, distributionMode);
		} catch (Exception e) {
			logger.error("sendGoogs错误", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "系统繁忙!");
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 订单详情页面
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "detail",method=RequestMethod.GET)
	public String tradeDetail(String tradeId, Model model, HttpServletRequest request,HttpServletResponse response)
			throws ServiceException {
		if (tradeId == null) {

		}
		TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), getId(request), 3);
		model.addAttribute("trade", dto);
		model.addAttribute("title", tradeId+"详情");
		//是否选中
		model.addAttribute("selectType", "1");
		return "/pages/itemdetail";
	}

	/**
	 * 关闭订单
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "closeTrade")
	@ResponseBody
	public LunjarApiResponse closeTrade(String tradeId,HttpServletRequest request) throws NumberFormatException, ServiceException {
		if (tradeId == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求订单号为空!");
		}

		TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), getId(request), 3);
		if (dto == null || dto.getTrade() == null) {
			// 没有查询到记录
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "查询不到订单!");
		} else {
			Integer status = dto.getTrade().getStatus();
			//只有待支付的订单才可以关闭
			if(status.equals(EnumTradeStatus.TRADE_NO_CREATE_PAY.getValue()) || status.equals(EnumTradeStatus.WAIT_BUYER_PAY.getValue())){
				Trade t = new Trade();
				t.setId(new Long(tradeId));
				t.setStatus(EnumTradeStatus.TRADE_CLOSED_BY_TAOBAO.getValue());
				tradeService.update(t);
			}else{
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单状态不正确,不能关闭!");
			}
		}
		return LunjarApiResponse.create();
	}

	/**
	 * 卖家添加备注
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "addSaleRemark")
	@ResponseBody
	public LunjarApiResponse addSaleRemark(String remarkTradeIds, String remark,HttpServletRequest request)
			throws NumberFormatException, ServiceException {

		if (remarkTradeIds == null || "".equals(remarkTradeIds)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单号为空!");
		}
		List<String> ids = new ArrayList<>();
		if (remarkTradeIds.contains(",")) {
			String arrs[] = remarkTradeIds.split(",");
			// 说明有多个
			for (int i = 0; i < arrs.length; i++) {
				ids.add(arrs[i]);
			}

		} else {
			// 只有一个
			ids.add(remarkTradeIds);
		}

		for (String tradeId : ids) {
			if (tradeId == null) {
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "订单号为空!");
			}

			TradeDto dto = tradeService.getTradeInfo(new Long(tradeId), getId(request), 3);
			if (dto == null || dto.getTrade() == null) {
				// 没有查询到记录
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "查询不到订单!");
			} else {
				Trade t = new Trade();
				t.setId(new Long(tradeId));
				t.setSaleRemarks(remark);
				tradeService.update(t);
			}
		}
		return LunjarApiResponse.create();
	}
	/**
	 * 通过名称查询物流公司
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "queryLogistics")
	@ResponseBody
	public LunjarApiResponse queryLogistics(String name){
		LogisticsCompanyQuery queryLogi = new LogisticsCompanyQuery();
		if(name!=null && !"".equals(name.trim())){
			queryLogi.setName(name);
		}
		queryLogi.setStatus(1);
		queryLogi.setSort("GMT_CREATE desc");
		// 快递
		// 查询物流公司
		List<LogisticsCompany> companyList = logisticsCompanyService.queryList(queryLogi);
		
		return LunjarApiResponse.create(companyList);
	}
	
}
