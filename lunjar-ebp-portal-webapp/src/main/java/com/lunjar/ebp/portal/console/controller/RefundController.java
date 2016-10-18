package com.lunjar.ebp.portal.console.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lunjar.ebp.bizsupport.model.Refund;
import com.lunjar.ebp.bizsupport.query.RefundQuery;
import com.lunjar.ebp.bizsupport.service.RefundService;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.model.PageResult;

/**
 * 退款控制器
 * @author Administrator
 *
 */
@RequestMapping(value="refund")
@Controller
public class RefundController extends BaseController{
	
	@Autowired
	private RefundService refundService;
	@Autowired
    private PortalAgentSession portalAgentSession;
	
	/**
	 * 获取买方id
	 * 
	 * @return
	 */
	// TODO 获取买方id
//	public Long getBuyerId() {
//		return new Long(12313);
//	}
	/**
	 * 退款列表
	 * @return
	 */
	@RequestMapping(value="list/{shopId}")
	public String list(RefundQuery refundQuery,Model model,@PathVariable("shopId") String shopId, HttpServletRequest request){
		//查询退款列表
		PortalAgent agent = portalAgentSession.get(request);
		model.addAttribute("page", refundList(refundQuery, agent.getBuyerId()));
		model.addAttribute("shopId", shopId);
		return "";
	}
	/**
	 * 查询列表的方法
	 * @param refundQuery
	 * @return
	 */
	private PageResult<Refund> refundList(RefundQuery refundQuery, Long buyerId){
		refundQuery.setBuyerId(buyerId);
		List<Refund> listRefund = refundService.queryList(refundQuery);
		int count = refundService.queryCount(refundQuery);
		return PageResult.create(refundQuery, listRefund, count);
	}
}
