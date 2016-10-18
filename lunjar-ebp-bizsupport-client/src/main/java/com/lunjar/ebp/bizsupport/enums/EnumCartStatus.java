package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 购物车状态 1：未结算 2：已结算
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月15日上午10:00:11
 */
public enum EnumCartStatus implements DataDictionaryEnum{
	/** 未结算 */
	NOTSETTLED(1,"未结算"),  
	/** 停止 */
	SETTLED(2,"已结算"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumCartStatus> pool = new HashMap<Integer, EnumCartStatus>();

	static {
		for (EnumCartStatus each : EnumCartStatus.values()) {
			EnumCartStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumCartStatus(int value, String text) {
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

	public static EnumCartStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
