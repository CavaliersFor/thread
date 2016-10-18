package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 实名审核状态 1 未审核，2审核通过，3审核不通过，4-审核中，0 初始化
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月11日上午10:34:08
 */
public enum EnumSellerCheckStatus implements DataDictionaryEnum{
	/** 初始化 */
	INITIALIZE(0,"初始化"),  
	/** 未审核 */
	NOTAUDIT(1,"未审核"),  
	/** 审核通过 */
	AUDITED(2,"审核通过"),
	/** 审核不通过 */
	AUDIT_NOT_PASS(3,"审核不通过"), 
	/** 审核通过 */
	AUDITING(4,"审核中"),
	
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumSellerCheckStatus> pool = new HashMap<Integer, EnumSellerCheckStatus>();

	static {
		for (EnumSellerCheckStatus each : EnumSellerCheckStatus.values()) {
			EnumSellerCheckStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumSellerCheckStatus(int value, String text) {
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

	public static EnumSellerCheckStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
