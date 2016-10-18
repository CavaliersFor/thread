package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 卖家的用户类型 1个人 2企业 默认为1
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午10:24:00
 */
public enum EnumSellerType implements DataDictionaryEnum{
	/** 个人 */
	PERSONAL(1,"个人"), 
	/** 企业 */
	ENTERPRISE(2,"企业"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumSellerType> pool = new HashMap<Integer, EnumSellerType>();

	static {
		for (EnumSellerType each : EnumSellerType.values()) {
			EnumSellerType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumSellerType(int value, String text) {
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

	public static EnumSellerType valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
