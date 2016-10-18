package com.lunjar.ebp.admin.biz.utils;

public class ValidateContentType {
	/**
	 * 图片的类型
	 * 
	 * @return
	 */
	public static String getImageType() {
		StringBuilder sb = new StringBuilder();
		sb.append("image/png");
		sb.append("image/jpeg");
		sb.append("image/bmp");
		return sb.toString();
	}
	
	/**
	 * 校验图片类型
	 * 
	 * @param currentType
	 * @return
	 */
	public static boolean validateImage(String currentType) {
		return getImageType().contains(currentType);
	}
}
