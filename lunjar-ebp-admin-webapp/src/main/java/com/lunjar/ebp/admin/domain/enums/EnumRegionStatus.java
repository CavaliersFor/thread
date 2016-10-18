package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 地区状态
 * 
 * <p>
 * create at 2016年4月29日 上午10:20:20
 * 
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumRegionStatus implements DataDictionaryEnum {
	/** 正常 */
	NORMAL(1, "正常", "green"),
	/** 失效 */
	DELETED(-1, "失效", "red"),;

	private Integer value;
	private String text;
	private String color;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumRegionStatus> pool = new HashMap<Integer, EnumRegionStatus>();

	static {
		for (EnumRegionStatus each : EnumRegionStatus.values()) {
			EnumRegionStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumRegionStatus(int value, String text, String color) {
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

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	public String getColor() {
		return color;
	}

	public static EnumRegionStatus valueOf(int value) {
		return pool.get(value);
	}
}
