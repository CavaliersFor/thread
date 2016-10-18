package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 商品图片状态1:显示2:屏蔽3:删除
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午11:06:59
 */
public enum EnumProductPropImgsStatus implements DataDictionaryEnum{
	/** 显示 */
	SHOW(1,"显示"), 
	/** 屏蔽 */
	HIDE(2,"屏蔽"),
	/** 删除 */
	DELETE(3,"删除"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumProductPropImgsStatus> pool = new HashMap<Integer, EnumProductPropImgsStatus>();

	static {
		for (EnumProductPropImgsStatus each : EnumProductPropImgsStatus.values()) {
			EnumProductPropImgsStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumProductPropImgsStatus(int value, String text) {
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

	public static EnumProductPropImgsStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
