package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 商铺状态1:正常 2:停止3:删除
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午11:06:59
 */
public enum EnumShopStatus implements DataDictionaryEnum{
	/** 正常 */
	NORMAL(1,"正常"), 
	/** 停止 */
	CEASE(2,"停止"),
	/** 删除 */
	DELETE(3,"删除"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumShopStatus> pool = new HashMap<Integer, EnumShopStatus>();

	static {
		for (EnumShopStatus each : EnumShopStatus.values()) {
			EnumShopStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumShopStatus(int value, String text) {
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

	public static EnumShopStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
