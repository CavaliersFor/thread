package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 商品图片类型1：轮播图2：详细图片3：其他
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午11:06:59
 */
public enum EnumProductPropImgsType implements DataDictionaryEnum{
	/** 轮播图 */
	CAROUSEL(1,"轮播图"),
	/** 详细图片 */
	DETAIL(2,"详细图片"),
	/** 删除 */
	OTHER(3,"其他"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductPropImgsType> pool = new HashMap<Integer, EnumProductPropImgsType>();

	static {
		for (EnumProductPropImgsType each : EnumProductPropImgsType.values()) {
			EnumProductPropImgsType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductPropImgsType(int value, String text) {
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

	public static EnumProductPropImgsType valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
