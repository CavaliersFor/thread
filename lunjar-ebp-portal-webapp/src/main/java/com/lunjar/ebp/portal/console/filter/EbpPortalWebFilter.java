package com.lunjar.ebp.portal.console.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lunjar.products.core.web.filter.AbstractOperatorContextFilter;

public class EbpPortalWebFilter extends AbstractOperatorContextFilter implements Filter {
	private final Logger log = LoggerFactory.getLogger(EbpPortalWebFilter.class);
//	private PortalAgentSession portalAgentSession;
//	private Excludor excludor = new FilterExcludor() ;

	@Override
	public void process(HttpServletRequest request) {
//		PortalAgent agent = portalAgentSession.get(request);
//		String visitUrl = request.getRequestURL().toString();
//		String shortUrl = request.getRequestURI();
//		if (agent == null) {
//			request.setAttribute(Constants.VISIT_URL, visitUrl);
//			request.setAttribute(Constants.SHORT_URL, shortUrl);
//			try {
//				request.getRequestDispatcher("/invalidSession").forward(request, null);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		request.getRequestDispatcher("/invalidSession");
	}

	/**
	 * @param partnerAgentSession
	 *            the bpsAgentSession to set
	 */
/*	public void setPortalAgentSession(PortalAgentSession portalAgentSession) {
		this.portalAgentSession = portalAgentSession;
	}*/

//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		HttpServletResponse resp = (HttpServletResponse) response;
////		String requestPath = urlPathHelper.getPathWithinServletMapping(request);
//		PortalAgent agent = portalAgentSession.get(request);
//		String visitUrl = request.getRequestURL().toString();
//		log.debug("访问路径为=======" + visitUrl + "============");
//		String shortUrl = request.getRequestURI();
//		String shopId = com.lunjar.ebp.portal.console.utils.StringUtils.getShopId(shortUrl.substring(shortUrl.indexOf("/") + 1));
//		request.setAttribute(Constants.SHORT_ID, shopId);
//		if (!PathUtils.matches(shortUrl, excludor.getExcludes())) {
//			if (agent == null || !agent.getShopId().toString().equals(shopId)) {
//				request.setAttribute(Constants.VISIT_URL, visitUrl);
//				request.setAttribute(Constants.SHORT_URL, shortUrl);
////				resp.sendRedirect("/invalidSession");
//				request.getRequestDispatcher("/invalidSession").forward(request, resp);
////				process(request);
//			} 
//		}
//		chain.doFilter(request, response);
//	}
}
