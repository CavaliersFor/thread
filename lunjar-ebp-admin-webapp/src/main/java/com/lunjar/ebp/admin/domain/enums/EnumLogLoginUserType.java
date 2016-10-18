package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 登录日志-用户类型
 * 
 * <p>
 * create at 2016年4月29日 上午10:29:14
 * 
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumLogLoginUserType implements DataDictionaryEnum {
	/** 后台 **/
	ADMIN(1, "admin"),
	/** 接入者 **/
	PARTNER(2, "partner"),
	/** 普通用户 **/
	NORMAL(3, "normal"),
	/** 其他 **/
	OTHER(4, "other"),;

	private Integer value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumLogLoginUserType> pool = new HashMap<Integer, EnumLogLoginUserType>();

	static {
		for (EnumLogLoginUserType each : EnumLogLoginUserType.values()) {
			EnumLogLoginUserType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumLogLoginUserType(Integer value, String text) {
		this.value = value;
		this.text = text;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value.toString());
		dataDictionary.setText(text);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
}
