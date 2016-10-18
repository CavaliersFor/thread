package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumLogOperatedObjectType implements DataDictionaryEnum {
	/**被操作对象**/
	API_TABLE("平台接口表", "平台接口表"),
	API_PARAM_TABLE("平台接口参数", "平台接口参数"),
	AUDIT_RECORD_TABLE("审核记录表", "审核记录表"),
	PARTNER_TABLE("接入者", "接入者"),
	PARTNER_PRODUCT_TABLE("接入者产品权限表", "接入者产品权限表"),
	PRODUCT_TABLE("产品表", "产品表"),
	PRODUCT_ITEM_TABLE("产品业务表", "产品业务表"),
	RPODUCT_ITEM_FIELD_TABLE("产品业务字段表", "产品业务字段表"),
	ITEM_TABLE("业务流程表", "业务流程表"),
	PRODUCT_ITEM_TITLE_TABLE("产品业务表头字段表", "产品业务表头字段表"),
	PRODUCT_KEY_TABLE("产品接入key表", "产品接入key表"),
	API_REQ_CONTROLL_TABLE("接口访问控制表", "接口访问控制表"),
	TEMPLATE("模板表", "模板表"),
	TEMPLATE_SOURCES("模板资源库", "模板资源库"),
	;

	private String value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<String, EnumLogOperatedObjectType> pool = new HashMap<String, EnumLogOperatedObjectType>();

	static {
		for (EnumLogOperatedObjectType each : EnumLogOperatedObjectType.values()) {
			EnumLogOperatedObjectType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumLogOperatedObjectType(String value, String text) {
		this.value = value;
		this.text = text;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value.toString());
		dataDictionary.setText(text);
	}

	public String getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
}
