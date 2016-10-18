package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 是否包邮1：包邮2：不包邮
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月10日下午3:58:30
 */
public enum EnumPostProduct implements DataDictionaryEnum{
	/** 是热销产品 */
	POST(1,"包邮"), 
	/** 不是热销产品 */
	NOT_POST(2,"不包邮"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumPostProduct> pool = new HashMap<Integer, EnumPostProduct>();

	static {
		for (EnumPostProduct each : EnumPostProduct.values()) {
			EnumPostProduct defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumPostProduct(int value, String text) {
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

	public static EnumPostProduct valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
