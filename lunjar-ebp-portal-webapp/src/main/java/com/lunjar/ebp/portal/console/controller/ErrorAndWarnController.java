package com.lunjar.ebp.portal.console.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorAndWarnController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(ErrorAndWarnController.class);
	
	@RequestMapping(value = { "error/{msg}"}, method = RequestMethod.GET)
	public String error(Model model,@PathVariable(value="msg") String msg){
		model.addAttribute(VM_ERROR, msg);
		return VM_ERROR;
	}
	
	@RequestMapping(value = {"warn/{msg}"}, method = RequestMethod.GET)
	public String warn(Model model,@PathVariable("msg") String msg){
		model.addAttribute(VM_WARN, msg);
		return VM_WARN;
	}
}
