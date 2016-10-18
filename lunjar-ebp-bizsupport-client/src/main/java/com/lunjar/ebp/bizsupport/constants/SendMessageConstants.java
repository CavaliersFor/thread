package com.lunjar.ebp.bizsupport.constants;

/**
 * 发送短信常量类
 * @author Administrator
 *
 */
public interface SendMessageConstants {
		
	/**
	 * 发送提货码
	 * 模板【趣易购】您好，您的订单#tradeno#将于#time#到达自提点#address#，提货码#code#，请于约定时间段提取。
	 * @author Administrator
	 *
	 */
	 interface SendPickUpGoods{
		 /**
		  * 模板id
		  */
		 public static final Long TPID = 1549602L;
		 /**
		  * 对应模板的变量tradeno
		  */
		 public static final String TRADENO ="tradeno";
		 /**
		  * 对应模板的变量time
		  */
		 public static final String TIME ="time";
		 
		 /**
		  * 对应模板的变量address
		  */
		 public static final String ADDRESS ="address";
		 
		 /**
		  * 对应模板的变量code
		  */
		 public static final String CODE ="code";
	 }
	 
	 /**
		 * 发送提货码
		 * 模板【趣易购】您好，您的订单#tradeno#将于#time#到达自提点#address#，提货码#code#，请于约定时间段提取。
		 * @author Administrator
		 *
		 */
		 interface RegisterCode{
			 /**
			  * 模板id
			  */
			 public static final Long TPID = 1599098L;
			 /**
			  * 对应模板的变量tradeno
			  */
			 public static final String NUMBER ="number";
		 }
	 
}
