package com.lunjar.ebp.admin.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.biz.service.AdminUserService;
import com.lunjar.ebp.admin.domain.AdminConstants;
import com.lunjar.ebp.admin.domain.AdminServiceResultCode;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.ebp.admin.web.session.EnterpriseAgentSession;
import com.lunjar.ebp.bizsupport.dto.EnterpriseDto;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.products.core.cache.RedisUtil;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.jms.ActiveMQHelper;
import com.lunjar.products.core.web.annotation.CsrfValidate;
import com.lunjar.products.core.web.exception.WebException;
import com.lunjar.products.core.web.utils.CookieUtil;
import com.lunjar.products.core.web.utils.HttpUtils;
import com.lunjar.products.core.web.utils.ValidateCodeUtil;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

/**
 * 后台用户登录
 * 
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at
 *         2016/6/20 10:15
 * @version %I%,%G%
 */
@Controller
public class AdminLoginController {
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
	private final static int TIME_OUT = 14400;
	private static final String OBJECT_KEY = AdminLoginController.class.getName();
	private final static String COOKIE_KEY = OBJECT_KEY + "ancun_bps_admin_token";

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ValidateCodeUtil validateCodeUtil;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private ActiveMQHelper activeMQHelper;

	@Autowired
	private EnterpriseService enterpriseService;

	@Resource(name = "enterpriseAgentSession")
	private EnterpriseAgentSession enterpriseAgentSession;

	/**
	 * 进入登录页面，通过缓存和cookie保存验证码key
	 *
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at
	 *         2016/4/13 11:47
	 * @version %I%,%G%
	 * @see
	 */
	@RequestMapping(value = { "", "login" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		String captchaKey = request.getSession().getId();
		CookieUtil.save(COOKIE_KEY, captchaKey, TIME_OUT, response, AdminConstants.WEB_ENCODING);
		redisUtil.remove(OBJECT_KEY, captchaKey);
		Integer logerrNum = (Integer) request.getSession().getAttribute("logerrNum");

		model.addAttribute("logerrNum", logerrNum);

		return "frameset/admin-login";
	}

	/**
	 * 检查是否有恶意的刷屏，如果同一IP不带验证码请求超过3次，则抛出异常
	 *
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at
	 *         2016/4/13 12:06
	 * @version %I%,%G%
	 * @see
	 */
	private void checkCaptcha(EnterpriseDto loginDto, HttpServletRequest request) throws WebException {

		Integer loginCount = (Integer) request.getSession().getAttribute("logerrNum");
		;

		String validateCode = loginDto.getValidateCode();
		if (StringUtils.isBlank(validateCode) && loginCount != null && loginCount > 3) {
			logger.warn("登录不带验证码次数超过3次,IP【{}】 ,", HttpUtils.getRemoteAddr(request));
			throw new WebException(AdminServiceResultCode._2060505.getCode(),
					(AdminServiceResultCode._2060505.getMsg()));
		}
	}

	/**
	 * 用户登录
	 *
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at
	 *         2016/4/6 16:25
	 * @version %I%,%G%
	 * @see
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	@CsrfValidate
	public LunjarApiResponse loginSubmit(EnterpriseDto dto, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Integer logerrNum = (Integer) request.getSession().getAttribute("logerrNum");
		try {
			// 过滤不带验证码的请求
			checkCaptcha(dto, request);

			// session.setAttribute("strCode", strCode);
			String strCode = (String) session.getAttribute("strCode");
			if (dto != null && dto.getValidateCode() != null) {
				// 检查验证码是否正确
				if (!dto.getValidateCode().equals(strCode)) {
					throw new WebException(LunjarApiResponseCode._1000122);
				}
			}
			// 验证用户密码是否正确
			Enterprise loginResult = enterpriseService.login(dto);
			 request.getSession().setAttribute("logerrNum",0);
			/**
			 * 如果登录成功，保存session信息
			 */
			EnterpriseAgent enterpriseAgent = new EnterpriseAgent();
			enterpriseAgent.setAccount(loginResult.getAccount());
			enterpriseAgent.setEnterpiseName(loginResult.getEnterpiseName());
			enterpriseAgent.setId(loginResult.getId());
			enterpriseAgent.setAbsoluteHeadPicUrl(loginResult.getAbsoluteHeadPicUrl());
			// 配送方式
			enterpriseAgent.setDistributionMode(loginResult.getDistributionMode());
			enterpriseAgentSession.save(enterpriseAgent, request, response);
			return LunjarApiResponse.create();

		} catch (WebException e2) {
			logger.error("loginSubmit异常", e2);
			return LunjarApiResponse.create(e2.getCode(), e2.getMessage());
		} catch (ServiceException e1) {
			logger.error("loginSubmit异常", e1);
			if (logerrNum == null) {
				logerrNum = 0;
			}
			logerrNum = logerrNum + 1;
			request.getSession().setAttribute("logerrNum", logerrNum);
			return LunjarApiResponse.create(e1.getCode(), e1.getMessage());
		} catch (Exception e) {
			logger.error("loginSubmit异常", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000002.getCode(), "系统繁忙");
		}
	}

	/**
	 * 第一次登录修改密码
	 *
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at
	 *         2016/4/6 13:14
	 * @version %I%,%G%
	 * @see
	 */
	@RequestMapping(value = "updatePwdByFirstLogin", method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse updatePwd(@RequestParam("newPwd") String newPwd,
			@RequestParam("userAccount") String userAccount) throws ServiceException {
		adminUserService.updateMyPasswords(AdminConstants.INIT_PASSWORD, newPwd, userAccount);

		// 修改登录次数为1,以便下次修改的还是初始密码造成再弹出初次修改密码的框
		AdminUser user = adminUserService.getAdminUserByAccount(userAccount);
		if (user == null) {
			throw new ServiceException(AdminServiceResultCode._2060507);
		}
		user.setLoginCount(1);
		adminUserService.update(user);

		return LunjarApiResponse.create();
	}

	/**
	 * session 过期跳转页面
	 *
	 * @author lixuan
	 */
	@RequestMapping(value = "outSession")
	public String outSession() {
		return "commons/return-login";
	}

	@RequestMapping(value = "amqTest")
	@ResponseBody
	public LunjarApiResponse amqTest() {
		// TODO Auto-generated method stub
		String jsonMessage = "这是一条测试消息。。。。。";
		Map<String, String> map = new HashMap<>();
		map.put("JMSPname1", "pname1111");
		map.put("JMSPname2", "pname2222");
		try {
			int i = 0;
			while (i < 1) {
				i++;
				activeMQHelper.sendQueueMessage(jsonMessage, "ancun-bsp-test", map);
				System.out.println(i);
			}
			// activeMQHelperSpring.sendQueueMessage(jsonMessage);
			// activeMQHelperSpring.sendQueueMessage(jsonMessage,"test1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LunjarApiResponse.create();
	}
}
