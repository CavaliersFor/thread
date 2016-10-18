package com.lunjar.ebp.admin.web.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.AdminAgentSession;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.products.core.web.filter.AbstractOperatorContextFilter;
import com.lunjar.products.core.web.utils.HttpUtils;

public class AdminLoginUserFilter extends AbstractOperatorContextFilter implements Filter {
	private final Logger log = LoggerFactory.getLogger(AdminLoginUserFilter.class);

	private EnterpriseAgentSession enterpriseAgentSession;

	@Override
	public void process(HttpServletRequest request) {
		EnterpriseAgent loginAdmin = enterpriseAgentSession.get(request);

		if (loginAdmin != null) {
			loginAdmin.setIp(HttpUtils.getRemoteAddr(request));
			request.setAttribute(EnterpriseAgent.REQUEST_ATTR_NAME, loginAdmin);
		}
	}

	public EnterpriseAgentSession getEnterpriseAgentSession() {
		return enterpriseAgentSession;
	}

	public void setEnterpriseAgentSession(EnterpriseAgentSession enterpriseAgentSession) {
		this.enterpriseAgentSession = enterpriseAgentSession;
	}

}
