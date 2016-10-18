package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 商铺首页状态1：正常 2：停止
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午11:06:59
 */
public enum EnumShopIndexStatus implements DataDictionaryEnum{
	/** 正常 */
	NORMAL(1,"正常"), 
	/** 停止 */
	CEASE(2,"停止"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumShopIndexStatus> pool = new HashMap<Integer, EnumShopIndexStatus>();

	static {
		for (EnumShopIndexStatus each : EnumShopIndexStatus.values()) {
			EnumShopIndexStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumShopIndexStatus(int value, String text) {
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

	public static EnumShopIndexStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
