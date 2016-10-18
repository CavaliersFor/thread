package com.lunjar.ebp.admin.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.lunjar.ebp.admin.web.session.AdminAgentSession;
import com.lunjar.products.core.config.SysConfig;
import com.lunjar.products.core.utils.PathUtils;

public class AdminControllerSupport  {
	private static final Logger logger = LoggerFactory.getLogger(AdminControllerSupport.class);
	public static final String WEBRESOURCES_URL = "webresources.url";

	@Autowired
	protected AdminAgentSession session;
	@Autowired
	protected SysConfig sysConfig;
//	@Autowired
//	protected OperateLogService operateLogService;
//	
//	protected void writeUpdateLog(HttpServletRequest request, Object old, Object newObj){
//		operateLogService.writeUpdateLog(session.get(request), old, newObj);
//	}

	/**
	 * 转换图片的url,为数据库中图片地址加上url路径,如/partner/1.jpg -> http://localhost/partner/1.jpg
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/27 20:22
	 * @version %I%,%G%
	 * @see
	 */
	protected void convertPictureUrl(String uri,String key,Model model){
		String uploadUrlPrefix = sysConfig.get(SysConfig.KEY_STORE_SERVER_URL);
		String url=uploadUrlPrefix+uri;
		model.addAttribute(key,url);
	}

	protected void convertPersonalHeadsPhotoUrl(String photoUrl, String key, Model model){
		if(photoUrl ==null){
			String webresourcesUrl = sysConfig.get(WEBRESOURCES_URL);
			photoUrl = PathUtils.concat(webresourcesUrl,"/images/personal-front.png");
			model.addAttribute(key,photoUrl);
		}else {
			convertPictureUrl(photoUrl,key,model);
		}
	}

	protected void convertPersonalTailsPhotoUrl(String photoUrl, String key, Model model){
		if(photoUrl ==null){
			String webresourcesUrl = sysConfig.get(WEBRESOURCES_URL);
			photoUrl = PathUtils.concat(webresourcesUrl,"/images/personal-back.png");
			model.addAttribute(key,photoUrl);
		}else {
			convertPictureUrl(photoUrl,key,model);
		}
	}

	protected void convertEnterprisePhotoUrl1(String photoUrl, String key, Model model){
		if(StringUtils.isBlank(photoUrl)){
			String webresourcesUrl = sysConfig.get(WEBRESOURCES_URL);
			photoUrl = PathUtils.concat(webresourcesUrl,"/images/enterprise1.png");
			model.addAttribute(key,photoUrl);
		}else {
			convertPictureUrl(photoUrl,key,model);
		}
	}

	protected void convertEnterprisePhotoUrl2(String photoUrl, String key, Model model){
		if(StringUtils.isBlank(photoUrl)){
			String webresourcesUrl = sysConfig.get(WEBRESOURCES_URL);
			photoUrl = PathUtils.concat(webresourcesUrl,"/images/enterprise2.png");
			model.addAttribute(key,photoUrl);
		}else {
			convertPictureUrl(photoUrl,key,model);
		}
	}

	/**
	 * 缩略图的url转换成大图的url,为数据库中图片地址加上url路径,如/partner/1-scale.jpg -> http://localhost/partner/1-scale.jpg
	 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/27 20:22
	 * @version %I%,%G%
	 * @see
	 */
	protected void convertOriginPictureUrl(String scalePicUri, String key, Model model){
		if(scalePicUri==null){
			return;
		}

		try{
			int index= scalePicUri.lastIndexOf("-scale");
			if(index==-1){
				logger.warn("文件图片路径不正确,【{}】",scalePicUri);
				return;
			}

			String originPicUri= scalePicUri.substring(0,index);
			index= scalePicUri.lastIndexOf(".");
			String extension=scalePicUri.substring(index);
			originPicUri=originPicUri+extension;
			logger.info("scalePicUri【{}】,originPicUri【{}】",scalePicUri,originPicUri);

			String uploadUrlPrefix = sysConfig.get(SysConfig.KEY_STORE_SERVER_URL);
			String url=uploadUrlPrefix+ originPicUri;
			model.addAttribute(key,url);
		}catch(Exception e){
			logger.warn("缩率图路径【{}】出错",scalePicUri,e);
		}

	}

}
