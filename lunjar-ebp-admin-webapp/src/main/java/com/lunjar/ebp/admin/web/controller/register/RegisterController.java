package com.lunjar.ebp.admin.web.controller.register;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.bizsupport.dto.VerCodeDto;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.query.EnterpriseQuery;
import com.lunjar.ebp.bizsupport.service.CreateVerCodeService;
import com.lunjar.ebp.bizsupport.service.EnterpriseService;
import com.lunjar.products.core.exception.ServiceException;
import com.lunjar.products.core.utils.DigestsUtils;
import com.lunjar.products.core.web.controller.ControllerSupport;
import com.lunjar.products.core.webapi.LunjarApiResponse;
import com.lunjar.products.core.webapi.LunjarApiResponseCode;

@Controller
@RequestMapping("register")
public class RegisterController extends ControllerSupport {

	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private EnterpriseService enterpriseService;

	@Resource(name = "createVerCodeService")
	private CreateVerCodeService createVerCodeService;

	/**
	 * 到注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "registerPage" }, method = RequestMethod.GET)
	public String registerPage() {

		return "pages/userHome/register";
	}

	/**
	 * 校验账号是否存在
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "checkUserName" }, method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse checkUserName(String username) {

		if (username != null && !"".equals(username.trim())) {
			EnterpriseQuery query = new EnterpriseQuery();
			query.setAccount(username);
			List<Enterprise> list = enterpriseService.queryList(query);
			if (list != null && list.size() > 0) {
				// 账号已经存在
				return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "账号已经存在!");
			}
		} else {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入账号!");
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 注册方法
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = { "register" }, method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse register(String account, String phone, String password, String verCode, String random,
			String passSign, HttpServletRequest request) {

		if (account == null || "".equals(account.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入账号!");
		}

		if (phone == null || "".equals(phone.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入手机号!");
		}

		if (password == null || "".equals(password.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入密码!");
		}

		if (verCode == null || "".equals(verCode.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入验证码!");
		}

		if (random == null || "".equals(random.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}

		if (passSign == null || "".equals(passSign.trim())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}

		EnterpriseQuery query = new EnterpriseQuery();
		query.setAccount(account);
		List<Enterprise> list = enterpriseService.queryList(query);
		if (list != null && list.size() > 0) {
			// 账号已经存在
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "账号已经存在!");
		}

		String newPassSign = DigestsUtils.md5Hex(DigestsUtils.md5Hex((password + random).getBytes()).getBytes());

		if (!passSign.equals(newPassSign)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "非法请求!");
		}

		// 校验验证码是不是相等
		String cacheVerCode = createVerCodeService.getVerCode(phone);

		if (!verCode.equals(cacheVerCode)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "验证码不正确!");
		}

		logger.debug("key:{},获取的验证码是:{}", phone, cacheVerCode);

		Enterprise t = new Enterprise();
		t.setEnterpiseName(" ");
		t.setGmtCreate(new Date());
		t.setAccount(account.trim());
		t.setPassword(password.trim());
		t.setContactPhone(phone.trim());
		t.setDistributionMode(1);
		try {
			enterpriseService.add(t);
		} catch (ServiceException e) {
			logger.error("注册失败", e);
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "注册失败!");
		}

		return LunjarApiResponse.create();
	}

	/**
	 * 获取验证码
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "getVerCode" }, method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse getVerCode(String phone, HttpServletRequest request) {

		if (phone == null || "".equals(phone)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "手机号为空!");
		}

		if (!isMobile(phone)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入正确的手机号!");
		}
		String newPhone = phone.trim();
		EnterpriseQuery query = new EnterpriseQuery();
		query.setContactPhone(newPhone);
		List<Enterprise> list = enterpriseService.queryList(query);

		if (list != null && list.size() > 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "手机号码已注册!");
		}

		// 获取验证码
		VerCodeDto dto = createVerCodeService.createVerCode(6, newPhone);

		if (dto == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "发送验证码错误!");
		}

		if (dto.getMessage() != null && !"".equals(dto.getMessage())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), dto.getMessage());
		}

		logger.debug("key:{},生成的验证码是:{}", phone, dto.getVerCode());

		return LunjarApiResponse.create(dto.getVerCode());
	}

	/**
	 * 重置密码获取手机验证码
	 * 
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "getVerCodeForRestPass" }, method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse getVerCodeForRestPass(String phone, HttpServletRequest request) {
		if (phone == null || "".equals(phone)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "手机号为空!");
		}

		if (!isMobile(phone)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请输入正确的手机号!");
		}
		String newPhone = phone.trim();
		EnterpriseQuery query = new EnterpriseQuery();
		query.setContactPhone(newPhone);
		List<Enterprise> list = enterpriseService.queryList(query);

		if (list == null || list.size() <= 0) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "手机号码未注册!");
		}

		// 获取验证码
		VerCodeDto dto = createVerCodeService.createVerCode(6, newPhone);

		if (dto == null) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "发送验证码错误!");
		}

		if (dto.getMessage() != null && !"".equals(dto.getMessage())) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), dto.getMessage());
		}

		logger.debug("key:{},生成的验证码是:{}", phone, dto.getVerCode());

		return LunjarApiResponse.create(dto.getVerCode());
	}

	/**
	 * 找回密码页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "forgetPassword")
	public String forgetPassword() {

		return "pages/userHome/getBackPassword";
	}

	/**
	 * 找回密码页面
	 * 
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "resetPassPage")
	public String resetPassPage(String phone, String code, Model model) throws ServiceException {

		if (phone == null || "".equals(phone)) {
			throw new ServiceException("手机号码为空");
		}

		if (code == null || "".equals(code)) {
			throw new ServiceException("验证码为空");
		}

		// 校验验证码是不是相等
		String cacheVerCode = createVerCodeService.getVerCode(phone);

		if (cacheVerCode == null) {
			// 手机号有误，或者验证码过期
			throw new ServiceException("请重新获取验证码");
		}

		if (!code.equals(cacheVerCode)) {
			// 验证码不正确
			throw new ServiceException("验证码错误");
		}

		model.addAttribute("phone", phone);
		model.addAttribute("code", code);
		return "pages/userHome/resetPassword";
	}

	/**
	 * 找回密码提交方法
	 * 
	 * @return
	 */
	@RequestMapping(value = { "findPass" }, method = RequestMethod.POST)
	@ResponseBody
	public LunjarApiResponse findPass(String phone, String code, String pass1, String pass2, String random,
			String passSign) {

		if (phone == null || "".equals(phone)) {
			logger.error("method:{},message:{}", "findPass", "手机号码为空");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误!");
		}
		if (code == null || "".equals(code)) {
			logger.error("method:{},message:{}", "findPass", "验证码为空");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		if (pass1 == null || "".equals(pass1)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		if (pass2 == null || "".equals(pass2)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		if (random == null || "".equals(random)) {
			logger.error("method:{},message:{}", "findPass", "随机码为空");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		if (passSign == null || "".equals(passSign)) {
			logger.error("method:{},message:{}", "findPass", "签名为空");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		if (!pass1.equals(pass2)) {
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "新密码和确认密码不相同!");
		}
		String newPhone = phone.trim();
		// 校验验证码是不是相等
		String cacheVerCode = createVerCodeService.getVerCode(newPhone);
		if(cacheVerCode==null || "".equals(cacheVerCode)){
			logger.error("method:{},message:{}", "findPass", "验证码为空");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		
		if (!code.equals(cacheVerCode)) {
			logger.error("method:{},message:{}", "findPass", "验证码不正确");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		
		EnterpriseQuery query = new EnterpriseQuery();
		query.setContactPhone(newPhone);
		List<Enterprise> list = enterpriseService.queryList(query);
		
		if(list==null || list.size()!=1){
			logger.error("method:{},message:{}", "findPass", "手机号对应多条记录，数据错误");
			return LunjarApiResponse.create(LunjarApiResponseCode._1000001.getCode(), "请求数据错误,请重试!");
		}
		
		//更新密码
		Enterprise e = list.get(0);
		Enterprise updateE = new Enterprise();
		updateE.setId(e.getId());
		updateE.setPassword(pass1);
		enterpriseService.update(updateE);
		
		return LunjarApiResponse.create();
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
}
