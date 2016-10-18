package com.lunjar.ebp.bizsupport.constants;

/**
 * 常量
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月25日下午4:37:59
 */
public class Constants {

	public static String BUYER_TOKEN_ID = "buyer_token_id";

	/** WeiXin 支付结果通知存到 Redis 库 **/
	public static final int PAY_RESULT_INDEX = 3;
	
	/** 支付结果队列名 **/
	public static final String PAY_RESULT_QUEUE = "pay_result_queue";
	
	/** 截取字符串长度*/
	public static final int INTERCEPT_STRING_LENGTH=2;
}
