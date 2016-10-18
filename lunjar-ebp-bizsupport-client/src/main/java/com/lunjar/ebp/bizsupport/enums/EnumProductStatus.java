package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 产品状态
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月10日下午3:58:03
 */
public enum EnumProductStatus implements DataDictionaryEnum{
	/** 销售中 */
	SALE(1,"销售中"), 
	/** 仓库中 */
	WAREHOSE(2,"仓库中"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductStatus> pool = new HashMap<Integer, EnumProductStatus>();

	static {
		for (EnumProductStatus each : EnumProductStatus.values()) {
			EnumProductStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductStatus(int value, String text) {
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

	public static EnumProductStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
