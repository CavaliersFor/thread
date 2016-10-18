package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 模版状态
 * 
 * <p>
 * create at 2016年4月29日 上午10:19:01
 * 
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumTemplateStatus implements DataDictionaryEnum {

	/** 未提交 **/
	NOT_SUBMITTED(1, "未提交"),
	/** 审核中 **/
	AUDIT(2, "审核中"),
	/** 审核通过" **/
	AUDIT_PASS(3, "审核通过"),
	/** 审核不通过" **/
	AUDIT_DOES_NOT_PASS(4, "审核不通过"),
	/** 过期（被新版覆盖）" **/
	EXPIRED_COVERED_BY_THE_NEW_VERSION(5, "过期（被新版覆盖）"),;

	private Integer value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumTemplateStatus> pool = new HashMap<Integer, EnumTemplateStatus>();

	static {
		for (EnumTemplateStatus each : EnumTemplateStatus.values()) {
			EnumTemplateStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumTemplateStatus(int value, String text) {
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

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	public static EnumTemplateStatus valueOf(int value) {
		return pool.get(value);
	}

}
