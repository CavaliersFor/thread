package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 *  退款状态1：退款申请中2：拒绝退款 3：退款完成
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年9月5日上午11:50:12
 */
public enum EnumRefundType implements DataDictionaryEnum{
	/** 退款申请中 */
	REFUND_APPLYING(1,"退款申请中"), 
	/**拒绝退款  */
	REFUND_REFUSE(2,"拒绝退款 "),
	/** 退款完成 */
	REFUND_SUCCESS(3,"退款完成"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumRefundType> pool = new HashMap<Integer, EnumRefundType>();

	static {
		for (EnumRefundType each : EnumRefundType.values()) {
			EnumRefundType defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumRefundType(int value, String text) {
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

	public static EnumRefundType valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
