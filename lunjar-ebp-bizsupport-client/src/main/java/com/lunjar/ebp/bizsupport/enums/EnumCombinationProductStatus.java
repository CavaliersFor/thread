package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 组合商品状态1:出售中2：停止
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月17日下午3:07:44
 */
public enum EnumCombinationProductStatus implements DataDictionaryEnum{
	/** 出售中 */
	ON_SALE(1,"出售中"),  
	/** 停止 */
	STOP(2,"停止"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumCombinationProductStatus> pool = new HashMap<Integer, EnumCombinationProductStatus>();

	static {
		for (EnumCombinationProductStatus each : EnumCombinationProductStatus.values()) {
			EnumCombinationProductStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumCombinationProductStatus(int value, String text) {
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

	public static EnumCombinationProductStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
