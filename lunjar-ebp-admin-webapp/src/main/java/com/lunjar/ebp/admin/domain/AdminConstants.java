package com.lunjar.ebp.admin.domain;

import com.lunjar.products.core.CoreConstants;

/**
 * 后台公用常量
 * 
 * <p>
 * create at 2016年4月2日 下午1:18:47
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version %I%, %G%
 * @see
 */
public interface AdminConstants extends CoreConstants{
	/**
	 * 初始密码
	 */
	String INIT_PASSWORD = "123456";	

	/** 状态 已删除 */
	int STATUS_REMOVED = -1;
	
	/** 状态 正常 */
	int STATUS_NOMAIL = 1;		

	/** 字符编码 */
	String WEB_ENCODING="UTF-8";
}
