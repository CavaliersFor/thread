package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumAuditStatus implements DataDictionaryEnum {

	/** 未审核  */
	NOT_AUDIT(1,"未审核"),
	/** 审核通过  */
	AUDIT_PASS(2,"审核通过"),
	/** 审核不通过 */
	AUDIT_NOT_PASS(3,"审核不通过"),
	/** 审核中 */
	AUDIT_PARSING(4,"审核中"),
	;
	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumAuditStatus> pool = new HashMap<Integer, EnumAuditStatus>();

	static {
		for (EnumAuditStatus each : EnumAuditStatus.values()) {
			EnumAuditStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumAuditStatus(Integer value, String text) {
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

	public static EnumAuditStatus valueOf(Integer value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

}
