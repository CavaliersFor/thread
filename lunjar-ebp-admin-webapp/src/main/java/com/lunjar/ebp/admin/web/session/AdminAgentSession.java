package com.lunjar.ebp.admin.web.session;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.lunjar.ebp.admin.biz.cache.AdminAgentCache;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.web.session.AbstractOperatorAgentSession;
import com.lunjar.products.core.web.utils.CookieUtil;
import com.lunjar.products.core.web.utils.WebContextUtils;

/**
 * 
 * 
 * <p>
 * create at 2014骞�4鏈�15鏃� 涓婂崍12:33:42
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version %I%, %G%
 * @see
 */
@Component
public class AdminAgentSession extends AbstractOperatorAgentSession<AdminAgent> {

	private final static Logger log = LoggerFactory.getLogger(AdminAgentSession.class);

	private final static int TIME_OUT = 14400;

	private final static String COOKIE_KEY = "ancun_bps_admin_token";

	@Autowired
	private AdminAgentCache adminAgentCache;
	@Autowired
	private UrlPathHelper urlPathHelper;
	@Autowired
	private WebContextUtils webContextUtils;

	public void save(AdminAgent adminAgent, HttpServletRequest request, HttpServletResponse response) {
		String sessionCookieName = DigestsUtils.md5Hex(UUID.randomUUID().toString().getBytes());

		if (log.isDebugEnabled()) {
			log.debug("Servlet [" + urlPathHelper.getPathWithinServletMapping(request) + "], save sessionCookieName:"
					+ sessionCookieName + " " + adminAgent);
		}

		adminAgentCache.put(sessionCookieName, adminAgent);

		// SessionId 瀛樻斁鍒癱ookie閲岃竟
		CookieUtil.save(COOKIE_KEY, sessionCookieName, TIME_OUT, response, "UTF-8");
	}

	public AdminAgent get(HttpServletRequest request) {
		String sessionCookieName = CookieUtil.read(COOKIE_KEY, request, "UTF-8");

		if (StringUtils.isBlank(sessionCookieName)) {
			return null;
		}

		// String sessionId = request.getSession().getId();

		if (log.isDebugEnabled()) {
			log.debug("Servlet [" + webContextUtils.getAppServer(request)
					+ urlPathHelper.getPathWithinServletMapping(request) + "], sessionCookieName: [" + sessionCookieName
					+ "]");
		}

		return adminAgentCache.get(sessionCookieName);
	}

	public void remove(HttpServletRequest request) {
		String sessionId = request.getSession().getId();

		if (log.isDebugEnabled()) {
			log.debug("remove session the sessionId: [" + sessionId + "]");
		}

		adminAgentCache.remove(sessionId);
	}
}
