package com.lunjar.ebp.admin.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import com.lunjar.ebp.admin.biz.cache.EnterpriseAgentCache;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.products.core.web.session.AbstractOperatorAgentSession;
import com.lunjar.products.core.web.utils.WebContextUtils;

public class EnterpriseAgentSession extends AbstractOperatorAgentSession<EnterpriseAgent> {
	private final static Logger logger = LoggerFactory.getLogger(EnterpriseAgentSession.class);

	@Autowired
	private EnterpriseAgentCache enterpriseAgentCache;

	@Autowired
	private UrlPathHelper urlPathHelper;

	@Autowired
	private WebContextUtils webContextUtils;

	public void save(EnterpriseAgent enterpriseAgent, HttpServletRequest request, HttpServletResponse response) {

		String sessionCookieName = request.getSession().getId();

		logger.debug("Servlet [" + urlPathHelper.getPathWithinServletMapping(request) + "], put sessionCookieName:"
				+ sessionCookieName + " " + enterpriseAgent);

		enterpriseAgentCache.put(sessionCookieName, enterpriseAgent);
	}

	@Override
	public EnterpriseAgent get(HttpServletRequest request) {
		String sessionCookieName = request.getSession().getId();
		
		logger.debug("sessionCookieName is:::" + sessionCookieName);
		if (StringUtils.isBlank(sessionCookieName)) {
			return null;
		}

		logger.debug(
				"Servlet [" + webContextUtils.getAppServer(request) + urlPathHelper.getPathWithinServletMapping(request)
						+ "], sessionCookieName: [" + sessionCookieName + "]");

		return enterpriseAgentCache.get(sessionCookieName);
	}

	public void remove(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionCookieName = request.getSession().getId();
		if (StringUtils.isBlank(sessionCookieName)) {

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("remove session the sessionCookieName: [" + sessionCookieName + "]");
			}

			enterpriseAgentCache.remove(sessionCookieName);
		}
	}
}
