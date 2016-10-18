package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 地区类型，1：国家，2： 省份/直辖市，3：市，4：县/区
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月17日下午7:32:29
 */
public enum EnumRegionType implements DataDictionaryEnum{
	/** 国家 */
	NATION(1,"国家"), 
	/**省份/直辖市 */
	PROVINCE(2,"省份/直辖市"),
	/** 市 */
	CITY(3,"市"),
	/** 县/区*/
	REGION(4,"县/区"), 
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumRegionType> pool = new HashMap<Integer, EnumRegionType>();

	static {
		for (EnumRegionType each : EnumRegionType.values()) {
			EnumRegionType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumRegionType(int value, String text) {
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

	public static EnumRegionType valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
