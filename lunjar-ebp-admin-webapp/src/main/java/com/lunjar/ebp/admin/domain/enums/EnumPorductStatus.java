package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 管理员状态
 */
public enum EnumPorductStatus implements DataDictionaryEnum {
	/** 正常 */
	NORMAL(1, "正常", "green"),
	/** 冻结 */
	DEPARTURE(2, "停止", "#ff6600"),
	/**锁定*/
	LOCK(3,"关闭","red"),
	
	//	/** 外派 */
	//	ASSIGNMENT(3, "外派", "blue"),
	//	/** 冻结 */
	//	FREEZE(4, "冻结", "red")
	;

	private int value;
	private String text;
	private String color;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumPorductStatus> pool = new HashMap<Integer, EnumPorductStatus>();

	static {
		for (EnumPorductStatus each : EnumPorductStatus.values()) {
			EnumPorductStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumPorductStatus(int value, String text, String color) {
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

	public static EnumPorductStatus valueOf(int value) {
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
