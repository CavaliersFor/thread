package com.lunjar.ebp.admin.domain.enums;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumApiType implements DataDictionaryEnum{
	/**保全*/
	SAFE(1,"保全"),
	/**门户*/
	DOOR(2,"门户接口"),
	/**数据增值*/
	DATA(3,"取证接口"),
	/**其他*/
	OTHER(8,"其他");
	
	private Integer value;
	private String text;
	private DataDictionary dataDictionary;

	EnumApiType(Integer value, String text) {
		this.value = value;
		this.text = text;
		dataDictionary = new DataDictionary();
		dataDictionary.setValue(value.toString());
		dataDictionary.setText(text);
	}

	public Integer getValue() {
		return value;
	}

	

	public String getText() {
		return text;
	}

	@Override
	public DataDictionary getDataDictionary() {
		return dataDictionary;
	}
	
}
