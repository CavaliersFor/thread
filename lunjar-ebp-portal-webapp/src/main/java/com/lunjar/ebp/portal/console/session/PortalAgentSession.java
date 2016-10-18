package com.lunjar.ebp.portal.console.session;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.lunjar.ebp.portal.console.cache.PortalAgentCache;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.web.session.AbstractOperatorAgentSession;
import com.lunjar.products.core.web.utils.CookieUtil;
import com.lunjar.products.core.web.utils.WebContextUtils;

/**
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月29日下午7:55:04
 */
@Component
public class PortalAgentSession extends AbstractOperatorAgentSession<PortalAgent> {

	private final static Logger log = LoggerFactory.getLogger(PortalAgentSession.class);

	private final static int TIME_OUT = 14400;

	private final static String COOKIE_KEY = "token";
	
	@Autowired
	private PortalAgentCache portalAgentCache;
	@Autowired
	private UrlPathHelper urlPathHelper;
	@Autowired
	private WebContextUtils webContextUtils;
	@Autowired
	private SysConfig sysConfig;

	public void save(PortalAgent portalAgent, HttpServletRequest request, HttpServletResponse response) {
		String sessionCookieName = request.getSession().getId();//DigestsUtils.md5Hex(UUID.randomUUID().toString().getBytes());

		if (log.isDebugEnabled()) {
			log.debug("Servlet [" + urlPathHelper.getPathWithinServletMapping(request) + "], put sessionCookieName:"
					+ sessionCookieName + " " + portalAgent);
		}

		portalAgentCache.put(sessionCookieName, portalAgent);

		// SessionId 存放到cookie里边
//		CookieUtil.save(COOKIE_KEY, sessionCookieName, TIME_OUT, response, "UTF-8");
	}

	public PortalAgent get(HttpServletRequest request) {
		boolean isDevelop=sysConfig.getProfile().equals(SysConfig.Profile.DEVELOP);
		if(isDevelop){
			PortalAgent portal = new PortalAgent();
			portal.setBuyerId(22L);
			portal.setShopId(1L);
			portal.setSaleId(1L);
			return portal;
		}


		String sessionCookieName = request.getSession().getId();//CookieUtil.read(COOKIE_KEY, request, "UTF-8");
		log.debug("sessionCookieName is:::" + sessionCookieName);
		if (StringUtils.isBlank(sessionCookieName)) {
			return null;
		}

		if (log.isDebugEnabled()) {
			log.debug("Servlet [" + webContextUtils.getAppServer(request)
					+ urlPathHelper.getPathWithinServletMapping(request) + "], sessionCookieName: [" + sessionCookieName
					+ "]");
		}

		return portalAgentCache.get(sessionCookieName);
	}

	public void remove(HttpServletRequest request,HttpServletResponse response) {
		String sessionCookieName = CookieUtil.read(COOKIE_KEY, request, "UTF-8");

		if (log.isDebugEnabled()) {
			log.debug("remove session the sessionCookieName: [" + sessionCookieName + "]");
		}

		portalAgentCache.remove(sessionCookieName);
		CookieUtil.remove(COOKIE_KEY, request, response);
	}
}
