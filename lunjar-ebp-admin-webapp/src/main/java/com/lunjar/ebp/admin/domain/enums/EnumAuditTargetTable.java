package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumAuditTargetTable implements DataDictionaryEnum {

	/** 用户表 **/
	PARTNER_TABLE(1,"partner"),
	/** 模版表 **/
	TEMPLATE_TABLE(2,"template"),
	;
	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumAuditTargetTable> pool = new HashMap<Integer, EnumAuditTargetTable>();

	static {
		for (EnumAuditTargetTable each : EnumAuditTargetTable.values()) {
			EnumAuditTargetTable defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumAuditTargetTable(Integer value, String text) {
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

	public static EnumAuditTargetTable valueOf(Integer value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

}
