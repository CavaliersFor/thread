package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 产品商铺关系状态 1：正常2：删除
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月13日下午5:26:01
 */
public enum EnumProductShopStatus implements DataDictionaryEnum{
	/** 销售中 */
	SALE(1,"销售中"), 
	/** 删除 */
	DELETE(2,"删除"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductShopStatus> pool = new HashMap<Integer, EnumProductShopStatus>();

	static {
		for (EnumProductShopStatus each : EnumProductShopStatus.values()) {
			EnumProductShopStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductShopStatus(int value, String text) {
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

	public static EnumProductShopStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
