package com.lunjar.ebp.admin.domain.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 * 附件存储介质(1-阿里云;2-百度云;9-其他)
 * 
 * <p>
 * create at 2016年5月14日 下午18:21:32
 * @author <a href="mailto:xuanyi@ancun.com">LiXuan</a>
 * @version %I%, %G%
 * @see
 */
public enum EnumStorePosition implements DataDictionaryEnum{
	/** 阿里云 */
	OSS(1,"阿里云"),
	/** 百度云 */
	BOS	(2,"百度云"),
	/** 其他*/
	OTHERS(9,"其他")
	;

	private int value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumStorePosition> pool = new HashMap<Integer, EnumStorePosition>();

	static {
		for (EnumStorePosition each : EnumStorePosition.values()) {
			EnumStorePosition defined = pool.get(each.getValue());
			if (null != defined) {
				throw new IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumStorePosition(int value, String text) {
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

	public static EnumStorePosition valueOf(int value) {
		return pool.get(value);
	}

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
