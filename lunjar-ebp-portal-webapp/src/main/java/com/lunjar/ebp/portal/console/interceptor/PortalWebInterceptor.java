package com.lunjar.ebp.portal.console.interceptor;

import com.lunjar.ebp.bizsupport.enums.EnumBuyerStatus;
import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.ebp.portal.console.cache.ShopsCache;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.model.ShopAccessKeyAndSecretKey;
import com.lunjar.ebp.portal.console.model.ShopAccessKeyAndSecretKeyList;
import com.lunjar.ebp.portal.console.service.InterceptorExcludor;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.ebp.portal.console.utils.Base64;
import com.lunjar.ebp.portal.console.utils.FilterExcludor;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.Excludor;
import com.lunjar.products.core.utils.PathUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

public class PortalWebInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(PortalWebInterceptor.class);
	private static final String SHOP_ACCESS_KEY_AND_SECRET_KEY_CACHE_KEY = "shop_access_key_and_secret_key_cache_key";

	@Autowired
	private ShopService shopService;

	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private PortalAgentSession portalAgentSession;

	@Autowired
	private ShopsCache shopsMapCache;
	
	private Excludor excludor = new FilterExcludor() ;
	

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		PortalAgent agent = null;
		agent =portalAgentSession.get(request);
		String shortUrl = request.getRequestURI();
		if (agent == null && !PathUtils.matches(shortUrl, excludor.getExcludes())) {
			String realUrl = request.getRequestURL().toString();
			logger.debug("跳转访问地址为==========="+ realUrl);
			String visitUrl = request.getRequestURL().toString();
			logger.debug("访问相对路径为==============" + shortUrl);
			String shopId = com.lunjar.ebp.portal.console.utils.StringUtils
					.getShopId(shortUrl.substring(shortUrl.indexOf("/") + 1));
			logger.debug("shopId =============" + shopId);
			if (org.apache.commons.lang3.StringUtils.isBlank(shopId)) {
				logger.debug("shopId 为空");
				return false;
			} else if (!org.apache.commons.lang3.StringUtils.isNumeric(shopId)) {
				logger.debug("shopId 不是数字");
				return false;
			} else {
				String appKey = request.getParameter("appkey");
				if (org.apache.commons.lang3.StringUtils.isBlank(appKey)) {
					Shop shop = this.shopService.load(Long.valueOf(Long.parseLong(shopId)));
					if (shop != null) {
						//获取所有商铺的key与secretKey并加入缓存
						initShopAccessKeyAndSecretKey();
						String redirectUrl = visitUrl + "&accessKey=" + shop.getAccessKey();
						String getOpenIdUrl = shop.getWxUrl();
						String url = getOpenIdUrl + URLEncoder.encode(redirectUrl);
						logger.debug("跳转的url地址 ==============" + url);
						response.sendRedirect(url);
						logger.debug("jump to wx...");
					} else {
						logger.debug("商铺不存在!");
					}
					return false;
				} else {
					String sign = request.getParameter("sign");
					String parameters = request.getParameter("parameters");
					logger.debug("parameters ==========" + parameters);
					Map map = convertBase64StringtoMap(parameters);
					if (!map.isEmpty()) {
						String openId = (String) map.get("openId");
						logger.debug("get openid ============" + openId);
						if ((org.apache.commons.lang3.StringUtils.isNotBlank(appKey))
								&& (org.apache.commons.lang3.StringUtils.isNotBlank(openId))) {
							logger.debug("get openid && appKey  ============" + openId + "&&" + appKey);
							return savePortalAgent(shopId, openId, request, response);
						}
					}
				}
			}
		}
		return true;
	}

	//初始化所有shop的key
	private void initShopAccessKeyAndSecretKey() {
		List<ShopAccessKeyAndSecretKey> list = shopsMapCache.get(SHOP_ACCESS_KEY_AND_SECRET_KEY_CACHE_KEY );
		if (CollectionUtils.isEmpty(list)) {
			List<Shop> shopList = shopService.queryList(null);
			if (CollectionUtils.isNotEmpty(shopList)) {
				list = new ArrayList<>();
				ShopAccessKeyAndSecretKey accessKeyAndSecretKey = null;
				for(Shop s: shopList) {
					accessKeyAndSecretKey = new ShopAccessKeyAndSecretKey();
					accessKeyAndSecretKey.setAccessKey(s.getAccessKey());
					accessKeyAndSecretKey.setSecretKey(s.getSecretKey());
					list.add(accessKeyAndSecretKey);
				}
				if (CollectionUtils.isNotEmpty(list)) {
					shopsMapCache.put(SHOP_ACCESS_KEY_AND_SECRET_KEY_CACHE_KEY, new ShopAccessKeyAndSecretKeyList(list));
				}
			}
		}
	}

	private boolean savePortalAgent(String shopId, String openId, HttpServletRequest request, HttpServletResponse response)
			throws ServiceException {
		boolean flag = false;
		Shop shop = this.shopService.load(Long.valueOf(Long.parseLong(shopId)));
		if (shop != null) {
			logger.debug("=============shop is not null================");
			Buyer buyer = this.buyerService.getBuyerByOpenIdAndPartnerId(openId, shop.getPartnerId());
			if (buyer == null) {
				buyer = new Buyer();
				createBuyer(shop, buyer, openId);
			}
			CreateSession(shop, buyer, request, response);
			flag = true;
		}
		return flag;
	}

	private void CreateSession(Shop shop, Buyer buyer, HttpServletRequest request, HttpServletResponse response) {
		PortalAgent agent = new PortalAgent();
		agent.setAccessKey(shop.getAccessKey());
		agent.setBuyerId(buyer.getId());
		agent.setHeadPicUrl(shop.getUrl());
		if (org.apache.commons.lang3.StringUtils.isNotBlank(buyer.getNickname())) {
			agent.setNickname(buyer.getNickname());
		}
		agent.setOpenId(buyer.getBuyerId());
		agent.setPartnerId(buyer.getPartnerId());
		if (org.apache.commons.lang3.StringUtils.isNotBlank(buyer.getPhone())) {
			agent.setPhone(buyer.getPhone());
		}
		agent.setSaleId(shop.getSaleId());
		agent.setSecretKey(shop.getSecretKey());
		agent.setShopId(shop.getId());
		agent.setShopName(shop.getShopName());
		agent.setUrl(shop.getUrl());
		this.portalAgentSession.save(agent, request, response);
	}

	private void createBuyer(Shop shop, Buyer buyer, String openId) throws ServiceException {
		buyer.setGmtCreate(new Date());
		buyer.setGmtModify(new Date());
		buyer.setBuyerId(openId);
		buyer.setPartnerId(shop.getPartnerId());
		buyer.setStatus(EnumBuyerStatus.NORMAL.getValue());
		this.buyerService.add(buyer);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> convertBase64StringtoMap(String str) {
		if (str == null) {
			return null;
		}
		String keyvalues = null;
		try {
			keyvalues = new String(Base64.decode(str), "UTF-8");
		} catch (IOException e) {
			return null;
		}
		if ((keyvalues == null) || (keyvalues.length() == 0))
			return null;
		String[] keyvalueArray = keyvalues.split("&");
		Map map = new HashMap();
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("=");
			if ((s == null) || (s.length != 2))
				return null;
			map.put(s[0], s[1]);
		}
		return map;
	}

	public static String createSign(String appKey, String appSecret, String parameters) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(appSecret);
			sb.append("appKey");
			sb.append(appKey);
			sb.append("parameters");
			sb.append(parameters);
			sb.append(appSecret);
			System.out.println("sb is:::" + sb.toString());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(sb.toString().getBytes("UTF-8"));
			System.out.println("bytes is::" + bytes);
			return byte2hex(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs;
		}
		return hs.toUpperCase();
	}

	public static String convertMaptoBase64(Map<String, String> map) {
		String str = null;
		try {
			if ((null != map) && (!map.isEmpty())) {
				StringBuffer keyvalues = new StringBuffer();
				for (String key : map.keySet()) {
					keyvalues.append(key);
					keyvalues.append("=");
					keyvalues.append((String) map.get(key));
					keyvalues.append("&");
				}
				str = Base64.encode(new String(keyvalues.toString().getBytes(), "UTF-8").getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}