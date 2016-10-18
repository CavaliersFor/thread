package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.List;

import com.lunjar.ebp.bizsupport.model.Orders;
import com.lunjar.ebp.bizsupport.model.Trade;

public class TradeDto implements Serializable {

	private static final long serialVersionUID = 5175153147854622644L;

	private Trade trade ;//商家id
	
	private List<Orders> list;//
	
	private String showRefundBtn;//1表示不显示退款按钮 2表示显示退款按钮
	
	private boolean itCanPaid;//是否可以支付
	
	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public List<Orders> getList() {
		return list;
	}

	public void setList(List<Orders> list) {
		this.list = list;
	}

	public String getShowRefundBtn() {
		return showRefundBtn;
	}

	public void setShowRefundBtn(String showRefundBtn) {
		this.showRefundBtn = showRefundBtn;
	}

	public boolean isItCanPaid() {
		return itCanPaid;
	}

	public void setItCanPaid(boolean itCanPaid) {
		this.itCanPaid = itCanPaid;
	}
}
