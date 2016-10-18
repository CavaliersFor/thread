package com.lunjar.ebp.portal.console.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式校验工具类
 * @author <a href=mailto:zhangxufeng@ancun.com>ZhangXufeng</a> create at 2016/4/19 9:34
 * @version %I%,%G%
 * @see
 */
public class RegexUtils {

	/**
	 * 判断是否是IP地址
	 *
	 * @param str
	 * @return
	 */
	public static boolean isIPAdress(String str) {
		Pattern pattern = Pattern
				.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
		return pattern.matcher(str).matches();
	}

	/**
	 * URL检查
	 *
	 * @param pInput     要检查的字符串
	 * @return boolean   返回检查结果
	 */
	public static boolean isUrl (String pInput) {
		if(pInput == null){
			return false;
		}
		String regEx ="^(http|https|ftp|file|telnet)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(pInput);
		return matcher.matches();
	}
	/**
	 *域名检查
	 *
	 * @param pInput     要检查的字符串
	 * @return boolean   返回检查结果
	 */
	public static boolean isDns (String pInput) {
		if(pInput == null){
			return false;
		}
		String regEx ="^(http|https|ftp|file|telnet)\\://|([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(pInput);
		return matcher.matches();
	}
	/**
	 * 判断是否为手机号码
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断是否为电话号码
	 * @param telephone
	 * @return
	 */
	public static boolean isTelephone(String telephone){
		Pattern p = Pattern.compile("\\d{5,8}");	//改成5位开始
		Matcher m = p.matcher(telephone);
		return m.matches();
	}

	/**
	 * 过滤网站名称的特殊字符，-前面的名称
	 *
	 * @param name
	 * @return
	 */
	public static String filterWebName(String name) {
		String temp=name.replaceAll("\"", "").replaceAll("\'", "");
		return temp.split("-|--|_|—| |―")[0];
	}

}
