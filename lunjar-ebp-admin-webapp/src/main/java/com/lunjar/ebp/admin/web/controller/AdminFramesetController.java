package com.lunjar.ebp.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.biz.exception.PasswordWrongException;
import com.lunjar.ebp.admin.biz.service.AdminFunctionResourceService;
import com.lunjar.ebp.admin.biz.service.AdminUserService;
import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.ebp.admin.domain.model.FunctionResource;
import com.lunjar.ebp.admin.web.session.AdminAgentSession;
import com.lunjar.products.core.web.utils.HttpUtils;
import com.lunjar.products.core.webapi.LunjarApiResponse;

@Controller
public class AdminFramesetController {
	@Autowired
	private AdminFunctionResourceService functionResourceService;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminAgentSession adminAgentSession;

	@RequestMapping(value = "frameset", method = RequestMethod.GET)
	public String frameset(Model model, HttpServletRequest request) {
		AdminAgent agent = adminAgentSession.get(request);

		List<FunctionResource> menus = functionResourceService.getUserResource(agent);
		String p_url = HttpUtils.getAppURL(request);
		model.addAttribute("menus", menus);
		model.addAttribute("p_url", p_url);
		return "frameset/admin-frameset";
	}

	@RequestMapping(value = "logout")
	@ResponseBody
	public void logout(HttpServletRequest request) {
		adminAgentSession.remove(request);
	}

	/**
	 * 登录后修改密码
	 * 
	 * @throws PasswordWrongException
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public void updatePwdSubmit(Model model, @RequestParam("pwd") String pwd, @RequestParam("newPwd") String newPwd,HttpServletRequest request)
			throws PasswordWrongException {
		AdminAgent agent = adminAgentSession.get(request);
		adminUserService.updateMyPassword(pwd, newPwd,agent);
	}

	/**
	 * 登录前修改密码
	 * 
	 * @param model
	 * @param pwd
	 * @param newPwd
	 * @throws PasswordWrongException
	 *             <p>
	 *             author: <a href="mailto:chenqiuyun@ancun.com">chenqiuyun</a>
	 *             <br>
	 *             create at: 2015年9月30日 下午2:37:29
	 */
	@RequestMapping(value = "updatePwds", method = RequestMethod.POST)
	@ResponseBody
	public void updatePwd(Model model, @RequestParam("pwd") String pwd, @RequestParam("newPwd") String newPwd,
			@RequestParam("userAccount") String userAccount) throws PasswordWrongException {
		adminUserService.updateMyPasswords(pwd, newPwd, userAccount);
	}

	/**
	 * 修改资料
	 * 
	 * @param model
	 * @return 用户信息
	 * @throws PasswordWrongException
	 *             <p>
	 *             author: xuyuanyang<br>
	 *             create at 2014年9月19日 下午2:43:55
	 */
	@RequestMapping(value = "updatedata")
	@ResponseBody
	public LunjarApiResponse updateData(HttpServletRequest request) {
		LunjarApiResponse msg = new LunjarApiResponse();
		AdminAgent adminAgent = adminAgentSession.get(request);
		AdminUser user = adminUserService.getById(adminAgent.getId());
		msg.setData(user);
		return msg;
	}

	/**
	 * 修改资料提交
	 * 
	 * @param model
	 * @param pwd
	 * @param newPwd
	 * @throws PasswordWrongException
	 *             <p>
	 *             author: xuyuanyang<br>
	 *             create at 2014年9月19日 下午2:44:06
	 */
	@RequestMapping(value = "updatedatasumit", method = RequestMethod.POST)
	@ResponseBody
	public void updateDataSubmit(Model model, @Valid AdminUser user,HttpServletRequest request) {
		AdminAgent agent = adminAgentSession.get(request);
		
		adminUserService.updateData(user,agent);
	}

	/**
	 * 欢迎页
	 */
	@RequestMapping(value = "welcome")
	public String welcome() {
		return "frameset/admin-welcome";
	}
}
