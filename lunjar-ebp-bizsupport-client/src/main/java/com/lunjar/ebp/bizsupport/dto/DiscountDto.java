package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.lunjar.ebp.bizsupport.model.Cart;

public class DiscountDto implements Serializable {

	private static final long serialVersionUID = -8604236151347716402L;

	private Long enterpriseId;//商家id
	private BigDecimal realPrice;//支付价格
	private BigDecimal expressPrice;//快递费用
	private BigDecimal discountPrice;//优惠价格
	private Integer distributionMode;//配送模式
	private Long addressId;//买家的收货地址id或者是自提地点的id
	private String city;//快递收货城市
	private String discountDesc;//优惠描述
	
	List<Cart> list;//商品信息list
	public DiscountDto() {}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public BigDecimal getExpressPrice() {
		return expressPrice;
	}
	public void setExpressPrice(BigDecimal expressPrice) {
		this.expressPrice = expressPrice;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getDistributionMode() {
		return distributionMode;
	}
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public List<Cart> getList() {
		return list;
	}

	public void setList(List<Cart> list) {
		this.list = list;
	}

	public String getDiscountDesc() {
		return discountDesc;
	}

	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
}
