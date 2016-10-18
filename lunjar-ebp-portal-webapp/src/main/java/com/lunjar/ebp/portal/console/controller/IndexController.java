package com.lunjar.ebp.portal.console.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;
import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.ebp.bizsupport.query.CategoryQuery;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.ebp.portal.console.exception.NullAgentException;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商铺首页页面
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午4:31:53
 */
@Controller
public class IndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ShopIndexService shopIndexService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private PortalAgentSession portalAgentSession;

	/**
	 * 商城首页
	 * @throws NullAgentException 
	 */
	@RequestMapping(value = { "index/{shopId}" }, method = RequestMethod.GET)
	public String index(@PathVariable("shopId") String shopId, Model model, HttpServletResponse res,
			HttpServletRequest req) throws ServiceException, NullAgentException {
		// 商铺信息
		logger.debug("进入商铺首页，商铺id为=========" + shopId);
		PortalAgent agent = portalAgentSession.get(req);;
//		agent = portalAgentSession.get(req);
//		if (agent == null) {
//			throw new NullAgentException();
//		}
		List<ShopIndexDto> indexList = shopIndexService.getShopIndex(agent.getShopId());
		model.addAttribute("shopIndexDto", indexList);
		// 底部菜单查询
		CategoryQuery query = new CategoryQuery();
		query.setShopId(agent.getShopId());
		// 查询使用中的
		query.setStatus(1);
		query.setSort("sort_num asc");
		List<Category> listCategory = categoryService.queryList(query);
		model.addAttribute("categorys", listCategory);
		// 标题
		model.addAttribute("title", agent.getShopName());
		// 店铺名称
		model.addAttribute("shopName", agent.getShopName());
		// 店铺地址
		model.addAttribute("shopUrl", agent.getUrl());

		model.addAttribute("shopId", shopId);
		return "pages/home";
	}

	@RequestMapping(value = {"/pagenotfind"})
	public String pageNotFind(HttpServletRequest request) {
		return "pages/commons/404";
//		String shopId = (String) request.getAttribute(Constants.SHORT_ID);
//		Shop shop = shopService.load(Long.parseLong(shopId));
//		return "redirect:" + shop.getUrl();
	}

//	@RequestMapping(value = {"/invalidSession"})
//	public void InvalidSession() {
//		logger.info("invalidSession。。。。");
//	}
}