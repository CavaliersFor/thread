package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.List;

public class TradeInfoDto implements Serializable {

	private static final long serialVersionUID = 9028404156222761843L;

	private Long enterpriseId;// 商家id

	private Long addressId;// 买家收货地址id或者自提地点id

	private String buyerRemarks;// 买家留言

	private List<Long> cartIdList;// 购物车id
	
	private String buyerName;//买家姓名(自取必填)
	
	private String buyerPhone;//买家手机号码(自取必填)
	
	private Long saleId;//卖家id
	
	private Integer isInvoice;//是否需要开发票
	
	private Integer invoiceType;//发票类型 1电子发票 2纸质发票
	
	private String invoiceName;//发票抬头
	
	private List<CombinDto> listCombinDto;//组合商品信息
	
	private Long buyerId;//买家id
	
	private Long shopId;//商铺id
	
	private Long combinationId;//组合商品id
	
	private String collectTime;//格式yyyy-MM-dd
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getBuyerRemarks() {
		return buyerRemarks;
	}

	public void setBuyerRemarks(String buyerRemarks) {
		this.buyerRemarks = buyerRemarks;
	}

	public List<Long> getCartIdList() {
		return cartIdList;
	}

	public void setCartIdList(List<Long> cartIdList) {
		this.cartIdList = cartIdList;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public List<CombinDto> getListCombinDto() {
		return listCombinDto;
	}

	public void setListCombinDto(List<CombinDto> listCombinDto) {
		this.listCombinDto = listCombinDto;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(Long combinationId) {
		this.combinationId = combinationId;
	}

	public String getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}
}
