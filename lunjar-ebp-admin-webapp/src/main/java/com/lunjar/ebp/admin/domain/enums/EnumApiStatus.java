package com.lunjar.ebp.admin.domain.enums;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

public enum EnumApiStatus implements DataDictionaryEnum{
	/**发布*/
	PUBLISH(1,"发布"),
	/**未发布**/
	UNPUBLISH(2,"未发布");
	
	
	private Integer value;
	private String text;
	private DataDictionary dataDictionary;
	
	EnumApiStatus(Integer value,String text){
		this.text=text;
		this.value=value;
		dataDictionary=new DataDictionary();
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