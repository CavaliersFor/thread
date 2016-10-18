package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 用户类型定义
 * 
 * <p>
 * create at 2016年6月6日 下午4:04:38
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumUcUserType implements DataDictionaryEnum {
	
	DEFAULT(0,"默认","black"),
	PERSONAL(1,"个人","green"),
	ENTERPRISE(2,"企业","blue"),
	;

	private Integer value;
	private String text;
	private String color;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumUcUserType> pool = new HashMap<Integer, EnumUcUserType>();

	static {
		for (EnumUcUserType each : EnumUcUserType.values()) {
			EnumUcUserType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same value with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}
	
	EnumUcUserType(Integer value,String text,String color){
		this.value = value;
		this.text = text;
		this.setColor(color);
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value + "");
		dataDictionary.setText(text);
		dataDictionary.setColor(color);
	}

	public Integer getValue() {
		return value;
	}

	public void setvalue(Integer value) {
		this.value = value;
	}

	public String gettext() {
		return text;
	}

	public void settext(String text) {
		this.text = text;
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
