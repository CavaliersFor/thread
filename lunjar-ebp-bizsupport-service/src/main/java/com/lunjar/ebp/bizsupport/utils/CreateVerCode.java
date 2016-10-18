package com.lunjar.ebp.bizsupport.utils;

import java.util.Random;

/**
 * 获取手机验证码
 * @author Administrator
 *
 */
public class CreateVerCode {
	public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
	private static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
	
}
