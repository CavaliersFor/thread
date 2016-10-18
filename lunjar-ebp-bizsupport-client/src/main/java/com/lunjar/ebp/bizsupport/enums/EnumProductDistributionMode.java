package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 配送方式 1：快递2：自取
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月10日下午3:58:30
 */
public enum EnumProductDistributionMode implements DataDictionaryEnum{
	/** 快递 */
	EXPRESS(1,"快递"), 
	/** 自取 */
	COLLECT(2,"自取"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductDistributionMode> pool = new HashMap<Integer, EnumProductDistributionMode>();

	static {
		for (EnumProductDistributionMode each : EnumProductDistributionMode.values()) {
			EnumProductDistributionMode defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductDistributionMode(int value, String text) {
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

	public static EnumProductDistributionMode valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
