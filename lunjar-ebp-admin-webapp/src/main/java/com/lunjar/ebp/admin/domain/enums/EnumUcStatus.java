package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 用户状态信息枚举类
 * 
 * <p>
 * create at 2016年6月6日 下午4:04:47
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumUcStatus implements DataDictionaryEnum {
	
	DEFAULT(0,"默认","black"),
	DELETE(-1,"删除","#CCCCCC"),
	NO_ACTIVE(1,"未激活","blue"),
	NORMAL(2,"正常","green"),
	LOCKED(3,"锁定","red"),
	FREEZE(4,"冻结","purple"),
	LOGOFF(5,"注销","darkred"),
	;
	
	private Integer value;
	private String text;
	private String color;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumUcStatus> pool = new HashMap<Integer, EnumUcStatus>();

	static {
		for (EnumUcStatus each : EnumUcStatus.values()) {
			EnumUcStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same value with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}
	
	EnumUcStatus(Integer value,String text,String color){
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
