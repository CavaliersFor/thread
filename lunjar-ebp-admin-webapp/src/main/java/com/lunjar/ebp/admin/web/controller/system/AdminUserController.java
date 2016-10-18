package com.lunjar.ebp.admin.web.controller.system;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.ebp.admin.biz.exception.UserExistsException;
import com.lunjar.ebp.admin.biz.service.AdminFunctionResourceService;
import com.lunjar.ebp.admin.biz.service.AdminUserService;
import com.lunjar.ebp.admin.domain.model.AdminUser;
import com.lunjar.ebp.admin.domain.model.FunctionResource;
import com.lunjar.ebp.admin.domain.query.AdminUserQuery;
import com.lunjar.products.core.exception.DataNotFindException;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.WebCoreConstants;
import com.lunjar.products.core.web.annotation.CsrfValidate;
import com.lunjar.products.core.web.annotation.QueryPage;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;

@Controller
@RequestMapping("adminuser")
@AdminAuthority("sys_userManage")
public class AdminUserController extends ControllerSupport {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminFunctionResourceService adminFunctionResourceService;

	/** 列表 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String list(Model model, @QueryPage(defaultPageSize = 10) AdminUserQuery query,
			HttpServletResponse response) {
		controllerUtils.saveQueryToCookie(query, response);
		return getViewAndPageData(model, query);
	}

	/** 列表返回时从cookie中取出AdminUserQuery */
	@RequestMapping(value = WebCoreConstants.LIST_BACK_ACTION_NAME, method = RequestMethod.GET)
	public String listb(Model model, HttpServletRequest request) {
		AdminUserQuery query = controllerUtils.getQueryFromCookie(AdminUserQuery.class, request);
		return getViewAndPageData(model, query);
	}

	/** 查询获取列表数据并渲染 */
	private String getViewAndPageData(Model model, AdminUserQuery query) {
		PageResult<AdminUser> page = adminUserService.getListPage(query);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "system/adminuser/adminuser-list";
	}

	/** 添加 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, @ModelAttribute("user") AdminUser user) {
		model.addAttribute("screen_title", "添加管理人员");
		return "system/adminuser/adminuser-form";
	}

	/** 添加提交 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@CsrfValidate
	public LunjarApiResponse addSubmit(@ModelAttribute("user") @Valid AdminUser user) throws UserExistsException {
		adminUserService.add(user);
		return LunjarApiResponse.create(user.getId());
	}

	/**
	 * 账号校验
	 */
	@RequestMapping("/account-check")
	@ResponseBody
	public String checkAccount(@ModelAttribute() AdminUser user) {
		// return "true";
		user.setStatus(-1);
		return String.valueOf(adminUserService.checkAccount(user));
	}

	/**
	 * 工号校验
	 * 
	 * @param user
	 * @return
	 * 		<p>
	 *         author: xuyuanyang<br>
	 *         create at 2014年9月22日 上午10:56:56
	 */
	@RequestMapping("/workno-check")
	@ResponseBody
	public String checkWorkNo(@ModelAttribute() AdminUser user) {
		// return "true";
		user.setStatus(-1);
		return String.valueOf(adminUserService.checkWorkNo(user));
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	@AdminAuthority("sys_userManage_remove")
	public void delete(AdminUser user) {
		adminUserService.remove(user.getId(), user.getUserName());
	}

	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public void resetPwd(AdminUser user) {
		adminUserService.resetPwd(user.getId(), user.getUserName());
	}

	/**
	 * 冻结/解冻
	 * 
	 * @throws DataNotFindException
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public void updateStatus(AdminUser user) throws DataNotFindException {
		adminUserService.updateStatus(user.getId(), user.getStatus());
	}

	/**
	 * 详细
	 */
	@RequestMapping(value = "/d{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("id") Long id) {
		AdminUser user = adminUserService.getById(id);
		if (user != null) {
			model.addAttribute("user", user);
		}
		return "system/adminuser/adminuser-detail";
	}

	/**
	 * 修改表单
	 */
	@RequestMapping(value = "/e{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("id") Long id) {
		AdminUser user = adminUserService.getById(id);
		if (user != null) {
			model.addAttribute("screen_title", "修改操作员:" + user.getUserName());
			model.addAttribute("user", user);
		}
		return "system/adminuser/adminuser-form";
	}

	/**
	 * 修改提交
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse editSubmit(@ModelAttribute() AdminUser user) {
		adminUserService.update(user);
		return LunjarApiResponse.create(user.getId());

	}

	/** 管理人员分配权限页面 */
	@RequestMapping("f{userId}")
	@AdminAuthority("sys_userManage_roles")
	public String functions(@PathVariable("userId") Integer userId, Model model) {
		List<FunctionResource> functions = adminFunctionResourceService.getAllResource();
		Set<String> userFunctions = adminUserService.getUserFunctions(userId);

		model.addAttribute("userFunctions", userFunctions);
		model.addAttribute("functions", functions);
		model.addAttribute("userId", userId);

		return "/system/adminuser/adminuser-function";
	}

	/**
	 * 保存管理人员的权限
	 */
	@RequestMapping("/saveuserfunctions")
	@ResponseBody
	@AdminAuthority("sys_userManage_roles")
	public void saveRoleItems(@RequestParam("userId") Integer userId, HttpServletRequest request) {
		String[] functionIds = request.getParameterValues("items");
		adminUserService.saveUserFunctions(userId, functionIds);
	}

//	/**
//	 * 分配角色表单
//	 */
//	@AdminAuthority("sys_userManage_roles")
//	@RequestMapping(value = "/p{userId}", method = RequestMethod.GET)
//	public String permission(Model model, @PathVariable("userId") Long userId,
//			@RequestParam("userName") String userName) {
//		// Set<String> userFunctions = adminUserManager.getFunctions(userId);
//		// model.addAttribute("allFunctions", allResourcesHtml);
//		List<AdminRole> rolesNotOfUser = adminUserService.getNotOfUser(userId);
//		List<AdminRole> rolesOfUser = adminUserService.getOfUser(userId);
//
//		model.addAttribute("userId", userId);
//		model.addAttribute("rolesNotOfUser", rolesNotOfUser);
//		model.addAttribute("rolesOfUser", rolesOfUser);
//		// model.addAttribute("userFunctions", userFunctions);
//		return "system/adminuser/adminuser-permission";
//	}
//
//	/**
//	 * 保存角色分配
//	 */
//	@AdminAuthority("sys_userManage_roles")
//	@RequestMapping(value = "savePermission", method = RequestMethod.POST)
//	@ResponseBody
//	public void savePermission(@RequestParam("userId") Long userId,
//			@RequestParam(value = "rolesOfUser[]", required = false) Integer[] rolesOfUser,
//			HttpServletRequest request) {
//		// String[] rolesOfUser = request.getParameterValues("rolesOfUser");
//		adminUserService.saveUserRoles(userId, rolesOfUser);
//	}

	// /**
	// * 获取一个角色的权限
	// *
	// */
	// @RequestMapping("/getRoleItems")
	// @ResponseBody
	// public String[] getRoleItems(@RequestParam("roleId") Integer roleId) {
	// String[] fids = adminRoleService.getRoleFunctionIds(roleId);
	// return fids;
	// }
	//
	// /**
	// * 保存一个角色的权限
	// */
	// @RequestMapping("/saveRoleItems")
	// @ResponseBody
	// public void saveRoleItems(@RequestParam("roleId") Integer roleId,
	// HttpServletRequest request) {
	// String[] functionIds = request.getParameterValues("items");
	// adminRoleService.saveRoleFunctions(roleId, functionIds);
	// }
}
