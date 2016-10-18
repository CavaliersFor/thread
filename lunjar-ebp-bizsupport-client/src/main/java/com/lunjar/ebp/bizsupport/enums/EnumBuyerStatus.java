package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 买家 状态1：正常 2：停止
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月25日下午4:59:41
 */
public enum EnumBuyerStatus implements DataDictionaryEnum{
	/** 正常 */
	NORMAL(1,"正常"),  
	/** 停止 */
	STOP(2,"停止"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumBuyerStatus> pool = new HashMap<Integer, EnumBuyerStatus>();

	static {
		for (EnumBuyerStatus each : EnumBuyerStatus.values()) {
			EnumBuyerStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumBuyerStatus(int value, String text) {
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

	public static EnumBuyerStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
