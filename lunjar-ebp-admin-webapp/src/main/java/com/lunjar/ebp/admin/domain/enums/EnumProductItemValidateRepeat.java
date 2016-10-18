package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 管理员状态
 */
public enum EnumProductItemValidateRepeat implements DataDictionaryEnum {
	/** 正常 */
	NORMAL(0, "不需要", "green"),
	/** 冻结 */
	DEPARTURE(1, "需要", "#ff6600"),
//	/**锁定*/
//	LOCK(3,"关闭","red"),
	
	//	/** 外派 */
	//	ASSIGNMENT(3, "外派", "blue"),
	//	/** 冻结 */
	//	FREEZE(4, "冻结", "red")
	;

	private int value;
	private String text;
	private String color;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductItemValidateRepeat> pool = new HashMap<Integer, EnumProductItemValidateRepeat>();

	static {
		for (EnumProductItemValidateRepeat each : EnumProductItemValidateRepeat.values()) {
			EnumProductItemValidateRepeat defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductItemValidateRepeat(int value, String text, String color) {
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

	public static EnumProductItemValidateRepeat valueOf(int value) {
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
