package com.lunjar.ebp.admin.web.interceptor;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.ebp.admin.domain.dto.GetMenu;
import com.lunjar.ebp.admin.domain.dto.MenuDto;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.products.core.utils.Excludor;
import com.lunjar.products.core.utils.PathUtils;
import com.lunjar.products.core.web.authority.OperatorAuthInterceptor;

/**
 * 
 * 后台权限控制拦截器
 * <p>
 * create at 2014年4月18日 下午5:23:15
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version %I%, %G%
 * @see
 */
public class AdminAuthInterceptor implements HandlerInterceptor {
	private static final Log log = LogFactory.getLog(OperatorAuthInterceptor.class);

	/** 拦截的方法缓存,无需每次通过反射读取 */
	private Map<Method, Set<String>> caches = new ConcurrentHashMap<Method, Set<String>>();
	/** 没有配置权限控制注解的Methed缓存 */
	private Set<Method> noAuthCaches = Collections.synchronizedSet(new HashSet<Method>());
	private Excludor excludor;
	@Autowired
	private UrlPathHelper urlPathHelper;

	public void init() {
		if (excludor == null) {
			excludor = new Excludor();
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = urlPathHelper.getPathWithinServletMapping(request);
		if (PathUtils.matches(requestPath, excludor.getExcludes())) {
			return true;
		}
		if(requestPath!=null && !"".equals(requestPath) && requestPath.contains("/register")){
			return true;
		}
		if(requestPath!=null && !"".equals(requestPath) && requestPath.contains(".")){
			return true;
		}
		if (HandlerMethod.class.isInstance(handler)) {
			//HandlerMethod handlerMethod = (HandlerMethod) handler;
			//Method method = handlerMethod.getMethod();

			if (log.isDebugEnabled()) {
				log.debug("validate login user in servlet [" + requestPath + "]");
			}
			EnterpriseAgent loginUser = (EnterpriseAgent) request.getAttribute(EnterpriseAgent.REQUEST_ATTR_NAME);
			if (loginUser == null) {
				if (log.isDebugEnabled()) {
					log.debug("No login in servlet [" + requestPath + "]");
				}
				response.sendRedirect("/");
			}
			/*if (!pass(loginUser, method)) {
				if (log.isDebugEnabled()) {
					log.debug("user:{id:" + loginUser.getId() + ",account:" + loginUser.getAccount()
							+ "} no permission in servlet [" + requestPath + "]");
				}
				throw new PermissionDeniedException();
			}*/
			return true;
		}
		return false;
	}

	private boolean pass(AdminAgent adminAgent, Method handlerMethod) {
		if (adminAgent == null) {
			return false;
		}
		if (noAuthCaches.contains(handlerMethod)) {// 该method在不需要权限控制的methed缓存中，则无需判断
			return true;
		}
		if (adminAgent.isSystemAdmin()) {// 超级管理员，则无需判断
			return true;
		}
		Set<String> functions = null;
		functions = this.caches.get(handlerMethod);
		if (functions == null) {
			functions = getDefindFunctions(handlerMethod);
			// 没有配置@AdminAuthority
			if (functions.size() == 0) {
				// 该方法或类没有配置AdminAuthority,将handlerMethod加入noControlCaches缓存
				noAuthCaches.add(handlerMethod);
				return true;
			}
			// 配置了@AdminAuthority，将配置的的权限加入缓存
			this.caches.put(handlerMethod, functions);
		}
		return adminAgent.haveOneOfPermissions(functions);
		// return true;
	}

	/**
	 * 获取方法或类上定义的允许的功能Id
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2013-2-28 上午10:47:57 <br>
	 * 
	 * @param handlerMethod
	 * @return
	 */
	private Set<String> getDefindFunctions(Method handlerMethod) {
		AdminAuthority auth1 = null, auth2 = null;
		Set<String> defindFunctions = new HashSet<String>();

		// 取method上的@AdminAuthority
		auth1 = AnnotationUtils.getAnnotation(handlerMethod, AdminAuthority.class);
		addFunToSet(auth1, defindFunctions);

		// 取method上没有配置@AdminAuthority
		if (defindFunctions.isEmpty()) {
			// 取class上的@AdminAuthority
			auth2 = AnnotationUtils.getAnnotation(handlerMethod.getDeclaringClass(), AdminAuthority.class);
			addFunToSet(auth2, defindFunctions);
		}

		return defindFunctions;
	}

	/**
	 * <p>
	 * author: <a href="mailto:shenwei@ancun.com">ShenWei</a><br>
	 * version: 2013-2-28 上午11:05:24 <br>
	 * 
	 * @param auth
	 * @param defindFunctions
	 */
	private void addFunToSet(AdminAuthority auth, Set<String> defindFunctions) {
		if (auth != null && auth.value() != null && auth.value().length > 0) {
			for (String s : auth.value()) {
				defindFunctions.add(s);
			}
		}
	}
	/**
	 * 也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestPath = urlPathHelper.getPathWithinServletMapping(request);
		if(requestPath!=null && !"".equals(requestPath) && !requestPath.contains(".")){
			
			List<MenuDto> menus = GetMenu.getMenuS();
			
			for(MenuDto m:menus){
				/*if(requestPath.contains(m.getUrl())){
					m.setIsChecked("1");
					request.setAttribute("childMenus", m.getChildMenus());
				}*/
				for(MenuDto child :m.getChildMenus()){
					if(requestPath.contains(child.getCompareUrl()) || child.getCompareUrl().contains(requestPath)){
						m.setIsChecked("1");
						child.setIsChecked("1");
						request.setAttribute("childMenus", m.getChildMenus());
					}
				}
			}
			
			/*for(MenuDto m:menus){
				if(requestPath.contains(m.getUrl())){
					request.setAttribute("childMenus", m.getChildMenus());
					break;
				}
			}*/
			request.setAttribute("menus", menus);
		}
	}
	/**
	 * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void setExcludor(Excludor excludor) {
		this.excludor = excludor;
	}
}
