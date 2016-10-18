package com.lunjar.ebp.portal.console.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lunjar.ebp.portal.console.exception.NullAgentException;
import com.lunjar.ebp.portal.console.model.PortalAgent;
import com.lunjar.ebp.portal.console.session.PortalAgentSession;
import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.exception.ServiceException;

public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

//	@Autowired
//	private PartnerAgentSession partnerAgentSession;
	@Autowired
	protected SysConfig sysConfig;

//	static Integer keyType = EnumKeyType.PRODUCE.getValue();//保全数据类型：1正式数据2测试数据
	static PortalAgent partnerAgent;//接入者登录信息  
//	static Partner partner;//接入者信息
	
	final static int EXCEPTION_500=500;//系统异常错误500
	final static String EXCEPTION_500_MSG="很抱歉，系统出错了，请您稍后访问！";
	final static String LOG_MSG="[{}]:::[{}]";//URL:::错误提示
	final static String VM_WARN="warn";//警告模版
	final static String VM_ERROR="error";//错误模版
	final static String URL_LOGIN="/login";//登录页面
	final static String URL_WARN="/warn/";//登录页面
	final static String URL_ERROR="/error/";//登录页面
	
	
	void redirect(String url,HttpServletResponse res){
		try {
			res.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化接入者登录信息
	 */
//	void initAgent(HttpServletRequest req,HttpServletResponse res){
//		partnerAgent=partnerAgentSession.get(req);
//		if(null==partnerAgent){
//			//未登陆或登陆超时
//			try {
//				res.sendRedirect(URL_LOGIN);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.warn(LOG_MSG,req.getRequestURL().toString(),e.getMessage());
//			}
//		}
//	}
	/**
	 * 初始化接入者登录信息+接入者信息
	 * @throws  
	 */
	void initPartner(HttpServletRequest req,HttpServletResponse res){
//		initAgent(req,res);
//		partner = partnerService.findByUid(partnerAgent.getUid());
//		if(null==partner){
//			//未登陆或登陆超时
//			try {
//				res.sendRedirect(URL_LOGIN);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.warn(LOG_MSG,req.getRequestURL().toString(),e.getMessage());
//			}
//		}
	}

	 /**
	  * 是否是开发环境
	  * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/26 17:10
	  * @version %I%,%G%
	  * @see
	  */
	protected boolean isDevelop(){
		return sysConfig.getProfile().equals(SysConfig.Profile.DEVELOP);
	}

//	protected PortalAgent getPartnerAgent(HttpServletRequest request){
//		PortalAgent partnerAgent = partnerAgentSession.get(request);
//		return partnerAgent;
//	}

	 /**
	  * 转换图片的url
	  * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/27 20:22
	  * @version %I%,%G%
	  * @see
	  */
	protected void convertPictureUrl(String uri,String key,Model model){
		if(StringUtils.isNotBlank(uri)){
			String uploadUrlPrefix = sysConfig.get(SysConfig.KEY_STORE_SERVER_URL);
			String url=uploadUrlPrefix+uri;
			model.addAttribute(key,url);
		}
	}

	/**
	 * 构建发送的email中的回调地址
	 * @param email
	 * @param randomCode
     * @return
     */
	protected String getEmailActiveUrl(String serverUrl,Long userId,String email,String randomCode){
		String url = String.format("%s?email=%s&userId=%d&token=%s", serverUrl, email,userId, randomCode);
//		url=UrlEncodeUtil.encode(url);
		return url;
	}

	protected String getEmailRetrieveUrl(){
		return sysConfig.get("email.retrieve.url");
	}

//	protected boolean isPersonal(Integer type){
//		if(type==EnumUcUserType.DEFAULT.getCode()){
//			logger.warn("数据错误,该用户是普通用户，不能登录管理控制台");
//			throw new RuntimeException("用户数据错误");
//		}
//		return EnumUcUserType.PERSONAL.getCode().equals(type);
//	}
	
	/** 基于@ExceptionHandler异常处理 */  
    @ExceptionHandler  
    public String exp(HttpServletRequest request, Exception ex) {  
          
        request.setAttribute("ex", ex);  
          
        // 根据不同错误转向不同页面  
        if(ex instanceof ServiceException) {  
        	return "pages/commons/500";
        }
        else if(ex instanceof Exception) {  
//        	return "redirect:/pagenotfind";
        	return "pages/commons/500";
        } 
        else {  
            return "error";  
        }  
    }  
}