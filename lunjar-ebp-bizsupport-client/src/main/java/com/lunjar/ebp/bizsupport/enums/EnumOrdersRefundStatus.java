package com.lunjar.ebp.bizsupport.enums;
/**
 * 退款状态。退款状态。可选值 WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)
 * WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货)
 * WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货) SELLER_REFUSE_BUYER(卖家拒绝退款)
 * CLOSED(退款关闭) SUCCESS(退款成功)
 * 
 * @author Administrator
 *
 */
public enum EnumOrdersRefundStatus {
	/**
	 * 买家已经申请退款，等待卖家同意
	 */
	WAIT_SELLER_AGREE(1,"买家已经申请退款，等待卖家同意"),
	
	/**
	 * 卖家已经同意退款，等待买家退货
	 */
	WAIT_BUYER_RETURN_GOODS(2,"卖家已经同意退款，等待买家退货"),
	/**
	 * 买家已经退货，等待卖家确认收货
	 */
	WAIT_SELLER_CONFIRM_GOODS(3,"买家已经退货，等待卖家确认收货"),
	/**
	 * 卖家拒绝退款
	 */
	SELLER_REFUSE_BUYER(4,"卖家拒绝退款"),
	/**
	 * 退款关闭
	 */
	CLOSED(5,"退款关闭"),
	/**
	 * 退款成功
	 */
	SUCCESS(6,"退款成功");
	
	
	EnumOrdersRefundStatus(Integer value,String text){
		this.value = value;
		this.text = text;
	}
	
	
	private Integer value;
	private String text;
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
