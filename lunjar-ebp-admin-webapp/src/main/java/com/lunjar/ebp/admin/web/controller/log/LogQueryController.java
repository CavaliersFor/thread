package com.lunjar.ebp.admin.web.controller.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ancun.bps.core.log.LogQueryService;
import com.ancun.bps.core.log.dto.ApiLogsDto;
import com.ancun.bps.core.log.dto.LoginLogsDto;
import com.ancun.bps.core.log.dto.OperateLogsDto;
import com.ancun.bps.core.log.query.ApiLogsQuery;
import com.ancun.bps.core.log.query.LoginLogsQuery;
import com.ancun.bps.core.log.query.OperateLogsQuery;
import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.web.annotation.QueryPage;
/**
 * 日志信息查询
 * 
 * <p>
 * create at 2016年4月28日 下午9:30:55
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
@RequestMapping("log")
@Controller
@AdminAuthority("log")
public class LogQueryController {

	@Autowired
	private LogQueryService logQueryService;
	
	@RequestMapping(value="api/list")
	@AdminAuthority("log_api_list")
	public String apiLogPage(Model model,@QueryPage ApiLogsQuery apiLogsQuery){
		apiLogsQuery.setSort("reqtime");
		apiLogsQuery.setSortCode("desc");
		PageResult<ApiLogsDto> page = logQueryService.apiLogPage(apiLogsQuery);
		model.addAttribute("page", page);
		model.addAttribute("query", apiLogsQuery);
		return "log/log-api";
	}
	
	@RequestMapping(value="login/list")
	@AdminAuthority("log_login_list")
	public String loginLogPage(Model model,@QueryPage LoginLogsQuery loginLogsQuery){
		loginLogsQuery.setSort("loginTime");
		loginLogsQuery.setSortCode("desc");
		PageResult<LoginLogsDto> page = logQueryService.loginLogPage(loginLogsQuery);
		model.addAttribute("page", page);
		model.addAttribute("query", loginLogsQuery);
		return "log/log-login";
	}
	
	@RequestMapping(value="operate/list")
	@AdminAuthority("log_operate_list")
	public String operateLogPage(Model model,@QueryPage OperateLogsQuery operateLogsQuery){
		operateLogsQuery.setSort("operateTime");
		operateLogsQuery.setSortCode("desc");
		PageResult<OperateLogsDto> page = logQueryService.operateLogPage(operateLogsQuery);
		model.addAttribute("page", page);
		model.addAttribute("query", operateLogsQuery);
		return "log/log-operate";
	}
}
