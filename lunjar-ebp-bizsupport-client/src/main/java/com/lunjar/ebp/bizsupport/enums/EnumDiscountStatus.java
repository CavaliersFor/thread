package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 游湖信息 状态1：使用中 2：停止使用
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月15日上午10:00:11
 */
public enum EnumDiscountStatus implements DataDictionaryEnum{
	/** 使用中 */
	IN_USE(1,"使用中"),  
	/** 停止使用 */
	STOP(2,"停止使用"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumDiscountStatus> pool = new HashMap<Integer, EnumDiscountStatus>();

	static {
		for (EnumDiscountStatus each : EnumDiscountStatus.values()) {
			EnumDiscountStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumDiscountStatus(int value, String text) {
		this.value = value;
		this.text = text;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value + "");
		dataDictionary.setText(text);
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}

	public static EnumDiscountStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
