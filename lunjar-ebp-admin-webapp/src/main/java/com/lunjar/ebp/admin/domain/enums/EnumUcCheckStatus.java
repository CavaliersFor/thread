package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;
/**
 * 用户认证状态信息枚举类
 * 
 * <p>
 * create at 2016年6月6日 下午4:06:11
 * @author <a href="mailto:xiangzhitong@ancun.org">xiangzhitong</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumUcCheckStatus implements DataDictionaryEnum {
	
	DEFAULT(0,"默认","black"),
	DELETE(-1,"删除","#CCCCCC"),
	NOCHCKED(1,"未审核","purple"),
	CHCKED(2,"审核通过","green"),
	UNCHCKED(3,"审核未通过","red"),
	CHECKING(4,"审核中","blue"),
	;

	private Integer value;
	private String text;
	private String color;
	private DataDictionary dataDictionary;

	private static Map<Integer, EnumUcCheckStatus> pool = new HashMap<Integer, EnumUcCheckStatus>();

	static {
		for (EnumUcCheckStatus each : EnumUcCheckStatus.values()) {
			EnumUcCheckStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same value with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}
	
	EnumUcCheckStatus(Integer value,String text,String color){
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
