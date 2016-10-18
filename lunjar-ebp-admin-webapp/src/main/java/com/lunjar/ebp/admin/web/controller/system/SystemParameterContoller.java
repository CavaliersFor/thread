package com.lunjar.ebp.admin.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunjar.ebp.admin.biz.authority.AdminAuthority;
import com.lunjar.ebp.admin.biz.service.SysParameterManager;
import com.lunjar.products.core.config.model.SysParameter;
import com.lunjar.products.core.config.model.SysParameterQuery;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 
 * 系统参数管理控制类
 * <p>
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2012-12-6 上午9:34:02
 */
@Controller
@RequestMapping("sysparams")
@AdminAuthority("sys_parameter")
public class SystemParameterContoller {
	@Autowired
	private SysParameterManager sysParameterManager;

	/** 入口 */
	@RequestMapping("/index")
	public String index(Model model, SysParameterQuery query) {
		List<SysParameter> data = sysParameterManager.queryForList(query);
		model.addAttribute("data", data);
		return "system/systemparams";
	}

	/** 更新 
	 * @throws ServiceException */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(SysParameter pubParameter) throws ServiceException {
		sysParameterManager.update(pubParameter);
	}
}
