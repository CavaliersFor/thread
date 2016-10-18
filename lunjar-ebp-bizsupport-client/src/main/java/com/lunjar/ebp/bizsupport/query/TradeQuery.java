package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class TradeQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14719047385311L;

	private Long[] idArray;// 主键id
	private String tradeNo;// 订单编号
	private Date gmtCreateFrom;// 订单生成时间
	private Date gmtCreateTo;// 订单生成时间
	private Date gmtModifyFrom;// 订单修改时间
	private Date gmtModifyTo;// 订单修改时间
	private Date consignTimeFrom;// 卖家发货时间
	private Date consignTimeTo;// 卖家发货时间
	private Date buyerPayTimeFrom;// 买家支付时间
	private Date buyerPayTimeTo;// 买家支付时间
	private Date endTimeFrom;// 订单结束时间
	private Date endTimeTo;// 订单结束时间
	private Long enterpriseId;// 商家id
	private String enterpriseName;// 商家名称
	private Long saleId;// 卖家id
	private String saleName;// 卖家名称
	private Long shopId;// 商铺id
	private String shopName;// 商铺名称
	private Long partnerId;// 合作者平台id
	private String partnerName;// 合作者平台名称
	private BigDecimal expressPriceFrom;// 快递费用
	private BigDecimal expressPriceTo;// 快递费用
	private Long buyerId;// 买家id
	private String buyerName;// 收货人姓名
	private String buyerAddress;// 收货人详细地址
	private String buyerPhone;// 收货人联系电话
	private Integer status;// 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) *
							// WAIT_BUYER_PAY(等待买家付款) *
							// SELLER_CONSIGNED_PART(卖家部分发货) *
							// WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
							// WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
							// TRADE_BUYER_SIGNED(买家已签收,货到付款专用) *
							// TRADE_FINISHED(交易成功) * TRADE_CL
	private Integer[] statusArray;// 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) *
									// WAIT_BUYER_PAY(等待买家付款) *
									// SELLER_CONSIGNED_PART(卖家部分发货) *
									// WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
									// WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货)
									// * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) *
									// TRADE_FINISHED(交易成功) * TRADE_CL
	private String province;// 买家所在省
	private String city;// 买家所在市
	private String region;// 区县
	private String tradeDesc;// 订单描述：满多少包邮， a+b商品一共卖50元等
	private Integer paymentType;// 支付类型1:微信2：支付宝3：网银
	private String paymentNo;// 支付编号(支付平台返回)
	private Integer invoiceType;// 发票类型1：电子发票2：纸质发票
	private String invoiceName;// 发票抬头
	private Integer isInvoice;// 是否需要开发票
	private BigDecimal discountPriceFrom;// 订单优惠价格
	private BigDecimal discountPriceTo;// 订单优惠价格
	private BigDecimal priceFrom;// 订单总的价格
	private BigDecimal priceTo;// 订单总的价格
	private BigDecimal realPriceFrom;// 订单实际支付金额
	private BigDecimal realPriceTo;// 订单实际支付金额
	private Integer distributionMode;// 配送方式1:快递2：自取
	private String saleRemarks;// 卖家备注
	private String buyerRemarks;// 买家备注
	private Long buyerAddressId;// 买家收货地址id
	private String expressCompany;// 快递公司
	private String expressNo;// 快递编号
	private String expressAbb;// 快递公司简称
	private String pickUpTime;// 买家提货时间
	private String pickUpNo;// 提货码
	// 新加的数据库中并没有
	private Integer refundStatus; // 退款状态
	private Map<String,Object> properties;//查询属性
	/*** 主键id */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键id */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 订单编号 */
	public String getTradeNo() {
		return tradeNo;
	}

	/*** 订单编号 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/*** 订单生成时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 订单生成时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 订单生成时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 订单生成时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 订单修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 订单修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 订单修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 订单修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/*** 卖家发货时间 */
	public Date getConsignTimeFrom() {
		return consignTimeFrom;
	}

	/*** 卖家发货时间 */
	public void setConsignTimeFrom(Date consignTimeFrom) {
		this.consignTimeFrom = consignTimeFrom;
	}

	/*** 卖家发货时间 */
	public Date getConsignTimeTo() {
		return consignTimeTo;
	}

	/*** 卖家发货时间 */
	public void setConsignTimeTo(Date consignTimeTo) {
		this.consignTimeTo = consignTimeTo;
	}

	/*** 买家支付时间 */
	public Date getBuyerPayTimeFrom() {
		return buyerPayTimeFrom;
	}

	/*** 买家支付时间 */
	public void setBuyerPayTimeFrom(Date buyerPayTimeFrom) {
		this.buyerPayTimeFrom = buyerPayTimeFrom;
	}

	/*** 买家支付时间 */
	public Date getBuyerPayTimeTo() {
		return buyerPayTimeTo;
	}

	/*** 买家支付时间 */
	public void setBuyerPayTimeTo(Date buyerPayTimeTo) {
		this.buyerPayTimeTo = buyerPayTimeTo;
	}

	/*** 订单结束时间 */
	public Date getEndTimeFrom() {
		return endTimeFrom;
	}

	/*** 订单结束时间 */
	public void setEndTimeFrom(Date endTimeFrom) {
		this.endTimeFrom = endTimeFrom;
	}

	/*** 订单结束时间 */
	public Date getEndTimeTo() {
		return endTimeTo;
	}

	/*** 订单结束时间 */
	public void setEndTimeTo(Date endTimeTo) {
		this.endTimeTo = endTimeTo;
	}

	/*** 商家id */
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	/*** 商家id */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/*** 商家名称 */
	public String getEnterpriseName() {
		return enterpriseName;
	}

	/*** 商家名称 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/*** 卖家id */
	public Long getSaleId() {
		return saleId;
	}

	/*** 卖家id */
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	/*** 卖家名称 */
	public String getSaleName() {
		return saleName;
	}

	/*** 卖家名称 */
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	/*** 商铺id */
	public Long getShopId() {
		return shopId;
	}

	/*** 商铺id */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/*** 商铺名称 */
	public String getShopName() {
		return shopName;
	}

	/*** 商铺名称 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/*** 合作者平台id */
	public Long getPartnerId() {
		return partnerId;
	}

	/*** 合作者平台id */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/*** 合作者平台名称 */
	public String getPartnerName() {
		return partnerName;
	}

	/*** 合作者平台名称 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	/*** 快递费用 */
	public BigDecimal getExpressPriceFrom() {
		return expressPriceFrom;
	}

	/*** 快递费用 */
	public void setExpressPriceFrom(BigDecimal expressPriceFrom) {
		this.expressPriceFrom = expressPriceFrom;
	}

	/*** 快递费用 */
	public BigDecimal getExpressPriceTo() {
		return expressPriceTo;
	}

	/*** 快递费用 */
	public void setExpressPriceTo(BigDecimal expressPriceTo) {
		this.expressPriceTo = expressPriceTo;
	}

	/*** 买家id */
	public Long getBuyerId() {
		return buyerId;
	}

	/*** 买家id */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	/*** 收货人姓名 */
	public String getBuyerName() {
		return buyerName;
	}

	/*** 收货人姓名 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/*** 收货人详细地址 */
	public String getBuyerAddress() {
		return buyerAddress;
	}

	/*** 收货人详细地址 */
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	/*** 收货人联系电话 */
	public String getBuyerPhone() {
		return buyerPhone;
	}

	/*** 收货人联系电话 */
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	/***
	 * 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) *
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRADE_CL
	 */
	public Integer getStatus() {
		return status;
	}

	/***
	 * 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) *
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRADE_CL
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/***
	 * 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) *
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRADE_CL
	 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/***
	 * 交易状态: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) *
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRADE_CL
	 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/*** 买家所在省 */
	public String getProvince() {
		return province;
	}

	/*** 买家所在省 */
	public void setProvince(String province) {
		this.province = province;
	}

	/*** 买家所在市 */
	public String getCity() {
		return city;
	}

	/*** 买家所在市 */
	public void setCity(String city) {
		this.city = city;
	}

	/*** 区县 */
	public String getRegion() {
		return region;
	}

	/*** 区县 */
	public void setRegion(String region) {
		this.region = region;
	}

	/*** 订单描述：满多少包邮， a+b商品一共卖50元等 */
	public String getTradeDesc() {
		return tradeDesc;
	}

	/*** 订单描述：满多少包邮， a+b商品一共卖50元等 */
	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	/*** 支付类型1:微信2：支付宝3：网银 */
	public Integer getPaymentType() {
		return paymentType;
	}

	/*** 支付类型1:微信2：支付宝3：网银 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/*** 支付编号(支付平台返回) */
	public String getPaymentNo() {
		return paymentNo;
	}

	/*** 支付编号(支付平台返回) */
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/*** 发票类型1：电子发票2：纸质发票 */
	public Integer getInvoiceType() {
		return invoiceType;
	}

	/*** 发票类型1：电子发票2：纸质发票 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	/*** 发票抬头 */
	public String getInvoiceName() {
		return invoiceName;
	}

	/*** 发票抬头 */
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	/*** 是否需要开发票 */
	public Integer getIsInvoice() {
		return isInvoice;
	}

	/*** 是否需要开发票 */
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	/*** 订单优惠价格 */
	public BigDecimal getDiscountPriceFrom() {
		return discountPriceFrom;
	}

	/*** 订单优惠价格 */
	public void setDiscountPriceFrom(BigDecimal discountPriceFrom) {
		this.discountPriceFrom = discountPriceFrom;
	}

	/*** 订单优惠价格 */
	public BigDecimal getDiscountPriceTo() {
		return discountPriceTo;
	}

	/*** 订单优惠价格 */
	public void setDiscountPriceTo(BigDecimal discountPriceTo) {
		this.discountPriceTo = discountPriceTo;
	}

	/*** 订单总的价格 */
	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	/*** 订单总的价格 */
	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	/*** 订单总的价格 */
	public BigDecimal getPriceTo() {
		return priceTo;
	}

	/*** 订单总的价格 */
	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	/*** 订单实际支付金额 */
	public BigDecimal getRealPriceFrom() {
		return realPriceFrom;
	}

	/*** 订单实际支付金额 */
	public void setRealPriceFrom(BigDecimal realPriceFrom) {
		this.realPriceFrom = realPriceFrom;
	}

	/*** 订单实际支付金额 */
	public BigDecimal getRealPriceTo() {
		return realPriceTo;
	}

	/*** 订单实际支付金额 */
	public void setRealPriceTo(BigDecimal realPriceTo) {
		this.realPriceTo = realPriceTo;
	}

	/*** 配送方式1:快递2：自取 */
	public Integer getDistributionMode() {
		return distributionMode;
	}

	/*** 配送方式1:快递2：自取 */
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}

	/*** 卖家备注 */
	public String getSaleRemarks() {
		return saleRemarks;
	}

	/*** 卖家备注 */
	public void setSaleRemarks(String saleRemarks) {
		this.saleRemarks = saleRemarks;
	}

	/*** 买家备注 */
	public String getBuyerRemarks() {
		return buyerRemarks;
	}

	/*** 买家备注 */
	public void setBuyerRemarks(String buyerRemarks) {
		this.buyerRemarks = buyerRemarks;
	}

	/*** 买家收货地址id */
	public Long getBuyerAddressId() {
		return buyerAddressId;
	}

	/*** 买家收货地址id */
	public void setBuyerAddressId(Long buyerAddressId) {
		this.buyerAddressId = buyerAddressId;
	}

	/*** 快递公司 */
	public String getExpressCompany() {
		return expressCompany;
	}

	/*** 快递公司 */
	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	/*** 快递编号 */
	public String getExpressNo() {
		return expressNo;
	}

	/*** 快递编号 */
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	/*** 快递公司简称 */
	public String getExpressAbb() {
		return expressAbb;
	}

	/*** 快递公司简称 */
	public void setExpressAbb(String expressAbb) {
		this.expressAbb = expressAbb;
	}

	/*** 买家提货时间 */
	public String getPickUpTime() {
		return pickUpTime;
	}

	/*** 买家提货时间 */
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	/*** 提货码 */
	public String getPickUpNo() {
		return pickUpNo;
	}

	/*** 提货码 */
	public void setPickUpNo(String pickUpNo) {
		this.pickUpNo = pickUpNo;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	public TradeQuery(){}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}
