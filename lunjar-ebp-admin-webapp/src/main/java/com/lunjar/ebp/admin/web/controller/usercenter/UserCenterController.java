package com.lunjar.ebp.admin.web.controller.usercenter;
//package com.lunjar.demo.web.controller.usercenter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ancun.bps.usercenter.model.UcUser;
//import com.ancun.bps.usercenter.query.UcUserQuery;
//import com.ancun.products.core.model.PageResult;
//import com.ancun.products.core.web.annotation.QueryPage;
//import com.ancun.products.core.webapi.AncunApiResponse;
//import com.lunjar.demo.biz.authority.AdminAuthority;
//import com.lunjar.demo.domain.enums.EnumUcStatus;
//
///**
// * 用户中心控制类
// * 
// * <p>
// * create at 2016年6月5日 下午5:31:03
// * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
// * @version %I%, %G%
// * @see
// */
//@Controller
//@RequestMapping(value = "usercenter")
//@AdminAuthority("usercenter")
//public class UserCenterController {
////	@Autowired
////	private UcUserService ucUserService;
////	
////	@RequestMapping(value = { "list" })
////	@AdminAuthority("usercenter_list")
////	public String index(Model model, @QueryPage(defaultPageSize=10) UcUserQuery ucUserQuery){
////		PageResult<UcUser> page=ucUserService.ucUserPage(ucUserQuery);
////		model.addAttribute("page", page);
////		model.addAttribute("query", ucUserQuery);
////		return "usercenter/usercenter-list";
////	}
//	
//	@RequestMapping(value = { "update/status" }, method=RequestMethod.POST)
//	@AdminAuthority("usercenter_update_status")
//	@ResponseBody
//	public AncunApiResponse updateStatus(@RequestParam("id") Long id, @RequestParam("status") int status){
//		UcUser ucUser=new UcUser();
//		ucUser.setId(id);
//		ucUser.setStatus(status);
//		if(status==EnumUcStatus.NORMAL.getValue()){
//			ucUser.setLoginErrorCount(0);
//		}
////		ucUserService.updateUcUserById(ucUser);
//		return AncunApiResponse.create();
//	}
//}
