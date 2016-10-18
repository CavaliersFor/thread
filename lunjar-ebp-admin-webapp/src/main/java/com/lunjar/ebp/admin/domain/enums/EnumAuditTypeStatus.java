package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 秘钥类型 1 正常 ，2 停止 
 * 
 * <p>
 * create at 2016年3月16日 下午4:21:32
 * @author <a href="mailto:qiande@ancun.com">QianDe</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumAuditTypeStatus implements DataDictionaryEnum{
	/** type  */
	PERSONAL(0,"个人审核"),
	/** type  */
	ENERPRISEAUDIT(1,"企业用户审核"),
	/** type */
	TEMPLATEAUDIT(2,"业务模板审核及流程模板审核"),
	
	
	/** 用户表 */
	PARTNER(10,"partner"),
	/** 模板表 */
	TEMPLATE(11,"template")
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumAuditTypeStatus> pool = new HashMap<Integer, EnumAuditTypeStatus>();

	static {
		for (EnumAuditTypeStatus each : EnumAuditTypeStatus.values()) {
			EnumAuditTypeStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumAuditTypeStatus(Integer value, String text) {
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

	public static EnumAuditTypeStatus valueOf(Integer value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
