package com.lunjar.ebp.bizsupport.constants;

import com.lunjar.products.core.CoreConstants;

/**
 * 登陆及状态常量类
 * @author Administrator
 *
 */
public interface SelfDeliveryConstants extends CoreConstants{
	/**
	 * 初始密码
	 */
	String INIT_PASSWORD = "123456";	

	/** 状态 停用*/
	int STATUS_REMOVED = 2;
	
	/** 状态 正常 */
	int STATUS_NOMAIL = 1;		

	/** 字符编码 */
	String WEB_ENCODING="UTF-8";
}
