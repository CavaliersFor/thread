package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;
/**
 * 操作日志-操作人类型
 * 
 * <p>
 * create at 2016年4月29日 上午10:15:19
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumLogOperatorType implements DataDictionaryEnum {

	/**插入操作**/
	ADMIN("admin", "超级管理员"),
	;

	private String value;
	private String text;
	private DataDictionary dataDictionary;

	private static Map<String, EnumLogOperatorType> pool = new HashMap<String, EnumLogOperatorType>();

	static {
		for (EnumLogOperatorType each : EnumLogOperatorType.values()) {
			EnumLogOperatorType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(
						defined.toString() + " defined as same code with " + each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumLogOperatorType(String value, String text) {
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
