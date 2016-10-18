package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumLogSubsystemType implements DataDictionaryEnum{
	/** 后台 **/
	ADMIN(1, "运营管理支撑系统"),
	/** 接入者 **/
	PARTNER(2, "接入者管理控制台"),
	/** 普通用户 **/
	NORMAL(3, "个人中心"),
	/** 其他 **/
	OTHER(4, "其他"),;

	private Integer value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumLogSubsystemType> pool = new HashMap<Integer, EnumLogSubsystemType>();

	static {
		for (EnumLogSubsystemType each : EnumLogSubsystemType.values()) {
			EnumLogSubsystemType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumLogSubsystemType(Integer value, String text) {
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
