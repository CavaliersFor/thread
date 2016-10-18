package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumAuditType implements DataDictionaryEnum {

	/** 个人审核  */
	PERSONAL(0,"个人审核"),
	/** 企业用户审核  */
	ENERPRISEAUDIT(1,"企业用户审核"),
	/** 模版审核_技术  **/
	TEMPLATE_TECHNICAL_AUDIT(2,"模版审核_技术"),
	/** 模版审核_法务 **/
	TEMPLATE_LEGAL_AUDIT(3,"模版审核_法务"),
	;
	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumAuditType> pool = new HashMap<Integer, EnumAuditType>();

	static {
		for (EnumAuditType each : EnumAuditType.values()) {
			EnumAuditType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumAuditType(Integer value, String text) {
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

	public static EnumAuditType valueOf(Integer value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

}
