package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 地区类型
 * 
 * <p>
 * create at 2016年4月29日 上午10:19:27
 * 
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumRegionType implements DataDictionaryEnum {
	/** 国家 **/
	COUNTRY(1, "国家"),
	/** 省份 **/
	PROVINCE(2, "省份"),
	/** 城市 **/
	CITY(3, "城市"),
	/** 地区 **/
	REGION(4, "地区"),;

	private Integer value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumRegionType> pool = new HashMap<Integer, EnumRegionType>();

	static {
		for (EnumRegionType each : EnumRegionType.values()) {
			EnumRegionType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumRegionType(Integer value, String text) {
		this.value = value;
		this.text = text;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value.toString());
		dataDictionary.setText(text);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
}
