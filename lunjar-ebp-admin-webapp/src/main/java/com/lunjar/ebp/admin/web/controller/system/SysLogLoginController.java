package com.lunjar.ebp.admin.web.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lunjar.ebp.admin.biz.service.AdminLoginLogService;
import com.lunjar.ebp.admin.domain.model.AdminLoginLog;
import com.lunjar.ebp.admin.domain.query.AdminLoginLogQuery;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.annotation.QueryPage;

@Controller
@RequestMapping("sysloglogin")
public class SysLogLoginController {

	//@Autowired
	private AdminLoginLogService sysLogLoginService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String shedList(Model model, @QueryPage AdminLoginLogQuery query) {
		PageResult<AdminLoginLog> page = sysLogLoginService.getListPage(query);
		model.addAttribute("page", page);
		return "system/sysloglogin";
	}

}
