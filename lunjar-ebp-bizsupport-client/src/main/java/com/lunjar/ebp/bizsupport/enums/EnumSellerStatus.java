package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 卖家状态 1 未激活 2 正常 3 锁定 4 冻结 5 注销
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午10:24:00
 */
public enum EnumSellerStatus implements DataDictionaryEnum{
	/** 未激活 */
	NOTACTIVE(1,"未激活"), 
	/** 正常 */
	NORMAL(2,"正常"),
	/** 锁定 */
	LOCK(3,"锁定"), 
	/** 冻结 */
	FREEZE(4,"冻结"),
	/**  注销 */
	CANCAL(5," 注销"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumSellerStatus> pool = new HashMap<Integer, EnumSellerStatus>();

	static {
		for (EnumSellerStatus each : EnumSellerStatus.values()) {
			EnumSellerStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumSellerStatus(int value, String text) {
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

	public static EnumSellerStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
