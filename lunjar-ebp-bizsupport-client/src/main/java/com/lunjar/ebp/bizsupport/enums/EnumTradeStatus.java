package com.lunjar.ebp.bizsupport.enums;

import java.util.HashMap;
import java.util.Map;

import com.lunjar.products.core.enums.DataDictionaryEnum;
import com.lunjar.products.core.model.DataDictionary;

/**
 *交易状态. *1: TRADE_NO_CREATE_PAY(没有创建支付宝交易) * 2:WAIT_BUYER_PAY(等待买家付款) * 
 *3:SELLER_CONSIGNED_PART(卖家部分发货) * 4:WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) * 
 *5:WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) * 6:TRADE_BUYER_SIGNED(买家已签收,货到付款专用) *
 * 7:TRADE_FINISHED(交易成功) * 8:TRADE_CLOSED(付款以后用户退款成功，交易自动关闭) * 
 * 9:TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易) * 10:PAY_PENDING(国际信用卡支付付款确认中) *
 *  11:WAIT_PRE_AUTH_CONFIRM(0元购合约中)
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年8月16日下午3:52:38
 */
public enum EnumTradeStatus implements DataDictionaryEnum{
	/** 没有创建支付宝交易 */
	TRADE_NO_CREATE_PAY(1,"没有创建支付宝交易"), 
	/**等待买家付款 */
	WAIT_BUYER_PAY(2,"等待买家付款"),
	/** 卖家部分发货 */
	SELLER_CONSIGNED_PART(3,"卖家部分发货"),
	/** 等待卖家发货,即:买家已付款 */
	WAIT_SELLER_SEND_GOODS(4,"等待卖家发货,即:买家已付款"), 
	/**等待买家确认收货,即:卖家已发货 */
	WAIT_BUYER_CONFIRM_GOODS(5,"等待买家确认收货,即:卖家已发货"),
	/** 买家已签收,货到付款专用 */
	TRADE_BUYER_SIGNED(6,"买家已签收,货到付款专用"),
	/** 交易成功 */
	TRADE_FINISHED(7,"交易成功"),
	/** 付款以后用户退款成功，交易自动关闭 */
	TRADE_CLOSED(8,"付款以后用户退款成功，交易自动关闭"), 
	/**付款以前，卖家或买家主动关闭交易 */
	TRADE_CLOSED_BY_TAOBAO(9,"付款以前，卖家或买家主动关闭交易"),
	/** 国际信用卡支付付款确认中 */
	PAY_PENDING(10,"国际信用卡支付付款确认中"),
	/** 0元购合约中 */
	WAIT_PRE_AUTH_CONFIRM(11,"0元购合约中"),
	;

	private Integer value;
	private String text;

	private DataDictionary dataDictionary;

	private static Map<Integer, EnumTradeStatus> pool = new HashMap<Integer, EnumTradeStatus>();

	static {
		for (EnumTradeStatus each : EnumTradeStatus.values()) {
			EnumTradeStatus defined = pool.get(each.getValue());
			if (null != defined) {
				throw new java.lang.IllegalArgumentException(defined.toString() + " defined as same code with "
						+ each.toString());
			}
			pool.put(each.getValue(), each);
		}
	}

	EnumTradeStatus(int value, String text) {
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

	public static EnumTradeStatus valueOf(int value) {
		return pool.get(value);
	}

	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}
	
}
