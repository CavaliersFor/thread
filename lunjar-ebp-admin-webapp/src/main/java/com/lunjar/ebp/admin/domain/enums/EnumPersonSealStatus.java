package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 个人章状态
 */
public enum EnumPersonSealStatus implements DataDictionaryEnum {
	/** 开通 */
	NORMAL(1, "开通", "green"),
	/** 未开通 */
	DEPARTURE(0, "未开通", ""),

	
	//	/** 外派 */
	//	ASSIGNMENT(3, "外派", "blue"),
	//	/** 冻结 */
	//	FREEZE(4, "冻结", "red")
	;

	private int value;
	private String text;
	private String color;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumPersonSealStatus> pool = new HashMap<Integer, EnumPersonSealStatus>();

	static {
		for (EnumPersonSealStatus each : EnumPersonSealStatus.values()) {
			EnumPersonSealStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumPersonSealStatus(int value, String text, String color) {
		this.value = value;
		this.text = text;
		this.color = color;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value + "");
		dataDictionary.setText(text);
		dataDictionary.setColor(color);
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}

	public static EnumPersonSealStatus valueOf(int value) {
		return pool.get(value);
	}

	public String getColor() {
		return color;
	}

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
}
