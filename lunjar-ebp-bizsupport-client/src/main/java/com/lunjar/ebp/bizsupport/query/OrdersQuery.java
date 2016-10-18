package com.lunjar.ebp.bizsupport.query;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lunjar.products.core.query.AbstractQueryParam;

/**
 * 查询对象
 */
public class OrdersQuery extends AbstractQueryParam implements Serializable {
	private static final long serialVersionUID = 14704144725031L;

	private Long[] idArray;// 主键
	private Long tradeId;// 订单表id
	private Integer num;// 订单数量
	private Long productId;// 产品id
	private String productName;// 产品名称
	private BigDecimal productRealPriceFrom;// 产品实际销售价格
	private BigDecimal productRealPriceTo;// 产品实际销售价格
	private String productPicPath;// 产品图片
	private BigDecimal totalPriceFrom;// 总价格
	private BigDecimal totalPriceTo;// 总价格
	private BigDecimal totalRealPriceFrom;// 实付
	private BigDecimal totalRealPriceTo;// 实付
	private BigDecimal totalDiscountPriceFrom;// 总的优惠价格
	private BigDecimal totalDiscountPriceTo;// 总的优惠价格
	private Date gmtCreateFrom;// 创建时间
	private Date gmtCreateTo;// 创建时间
	private Date gmtModifyFrom;// 修改时间
	private Date gmtModifyTo;// 修改时间
	private Integer status;// 交易状态 可选值: 1:* TRADE_NO_CREATE_PAY(没有创建支付宝交易) *
							// 2:WAIT_BUYER_PAY(等待买家付款) 3
							// SELLER_CONSIGNED_PART(卖家部分发货) *
							// WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
							// WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
							// TRADE_BUYER_SIGNED(买家已签收,货到付款专用) *
							// TRADE_FINISHED(交易成功) * TRA
	private Integer[] statusArray;// 交易状态 可选值: 1:*
									// TRADE_NO_CREATE_PAY(没有创建支付宝交易) *
									// 2:WAIT_BUYER_PAY(等待买家付款) 3
									// SELLER_CONSIGNED_PART(卖家部分发货) *
									// WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
									// WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货)
									// * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) *
									// TRADE_FINISHED(交易成功) * TRA
	private Integer refundStatus;// 退款状态。退款状态。可选值
									// WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)
									// WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货)
									// WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货)
									// SELLER_REFUSE_BUYER(卖家拒绝退款) CLOSED(退款关闭)
									// SUCCESS(退款成功)
	private Long skuId;// SKU id
	private String properties;// sku的销售属性组合字符串(p1:v1;p2:v2)
	private String propertiesname;// sku组合字符串的值

	/*** 主键 */
	public Long[] getIdArray() {
		return idArray;
	}

	/*** 主键 */
	public void setIdArray(Long... idArray) {
		this.idArray = idArray;
	}

	/*** 订单表id */
	public Long getTradeId() {
		return tradeId;
	}

	/*** 订单表id */
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	/*** 订单数量 */
	public Integer getNum() {
		return num;
	}

	/*** 订单数量 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/*** 产品id */
	public Long getProductId() {
		return productId;
	}

	/*** 产品id */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/*** 产品名称 */
	public String getProductName() {
		return productName;
	}

	/*** 产品名称 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/*** 产品实际销售价格 */
	public BigDecimal getProductRealPriceFrom() {
		return productRealPriceFrom;
	}

	/*** 产品实际销售价格 */
	public void setProductRealPriceFrom(BigDecimal productRealPriceFrom) {
		this.productRealPriceFrom = productRealPriceFrom;
	}

	/*** 产品实际销售价格 */
	public BigDecimal getProductRealPriceTo() {
		return productRealPriceTo;
	}

	/*** 产品实际销售价格 */
	public void setProductRealPriceTo(BigDecimal productRealPriceTo) {
		this.productRealPriceTo = productRealPriceTo;
	}

	/*** 产品图片 */
	public String getProductPicPath() {
		return productPicPath;
	}

	/*** 产品图片 */
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}

	/*** 总价格 */
	public BigDecimal getTotalPriceFrom() {
		return totalPriceFrom;
	}

	/*** 总价格 */
	public void setTotalPriceFrom(BigDecimal totalPriceFrom) {
		this.totalPriceFrom = totalPriceFrom;
	}

	/*** 总价格 */
	public BigDecimal getTotalPriceTo() {
		return totalPriceTo;
	}

	/*** 总价格 */
	public void setTotalPriceTo(BigDecimal totalPriceTo) {
		this.totalPriceTo = totalPriceTo;
	}

	/*** 实付 */
	public BigDecimal getTotalRealPriceFrom() {
		return totalRealPriceFrom;
	}

	/*** 实付 */
	public void setTotalRealPriceFrom(BigDecimal totalRealPriceFrom) {
		this.totalRealPriceFrom = totalRealPriceFrom;
	}

	/*** 实付 */
	public BigDecimal getTotalRealPriceTo() {
		return totalRealPriceTo;
	}

	/*** 实付 */
	public void setTotalRealPriceTo(BigDecimal totalRealPriceTo) {
		this.totalRealPriceTo = totalRealPriceTo;
	}

	/*** 总的优惠价格 */
	public BigDecimal getTotalDiscountPriceFrom() {
		return totalDiscountPriceFrom;
	}

	/*** 总的优惠价格 */
	public void setTotalDiscountPriceFrom(BigDecimal totalDiscountPriceFrom) {
		this.totalDiscountPriceFrom = totalDiscountPriceFrom;
	}

	/*** 总的优惠价格 */
	public BigDecimal getTotalDiscountPriceTo() {
		return totalDiscountPriceTo;
	}

	/*** 总的优惠价格 */
	public void setTotalDiscountPriceTo(BigDecimal totalDiscountPriceTo) {
		this.totalDiscountPriceTo = totalDiscountPriceTo;
	}

	/*** 创建时间 */
	public Date getGmtCreateFrom() {
		return gmtCreateFrom;
	}

	/*** 创建时间 */
	public void setGmtCreateFrom(Date gmtCreateFrom) {
		this.gmtCreateFrom = gmtCreateFrom;
	}

	/*** 创建时间 */
	public Date getGmtCreateTo() {
		return gmtCreateTo;
	}

	/*** 创建时间 */
	public void setGmtCreateTo(Date gmtCreateTo) {
		this.gmtCreateTo = gmtCreateTo;
	}

	/*** 修改时间 */
	public Date getGmtModifyFrom() {
		return gmtModifyFrom;
	}

	/*** 修改时间 */
	public void setGmtModifyFrom(Date gmtModifyFrom) {
		this.gmtModifyFrom = gmtModifyFrom;
	}

	/*** 修改时间 */
	public Date getGmtModifyTo() {
		return gmtModifyTo;
	}

	/*** 修改时间 */
	public void setGmtModifyTo(Date gmtModifyTo) {
		this.gmtModifyTo = gmtModifyTo;
	}

	/***
	 * 交易状态 可选值: 1:* TRADE_NO_CREATE_PAY(没有创建支付宝交易) * 2:WAIT_BUYER_PAY(等待买家付款) 3
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRA
	 */
	public Integer getStatus() {
		return status;
	}

	/***
	 * 交易状态 可选值: 1:* TRADE_NO_CREATE_PAY(没有创建支付宝交易) * 2:WAIT_BUYER_PAY(等待买家付款) 3
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRA
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/***
	 * 交易状态 可选值: 1:* TRADE_NO_CREATE_PAY(没有创建支付宝交易) * 2:WAIT_BUYER_PAY(等待买家付款) 3
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRA
	 */
	public Integer[] getStatusArray() {
		return statusArray;
	}

	/***
	 * 交易状态 可选值: 1:* TRADE_NO_CREATE_PAY(没有创建支付宝交易) * 2:WAIT_BUYER_PAY(等待买家付款) 3
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) *
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) *
	 * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * TRADE_FINISHED(交易成功) * TRA
	 */
	public void setStatusArray(Integer... statusArray) {
		this.statusArray = statusArray;
	}

	/***
	 * 退款状态。退款状态。可选值 WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)
	 * WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货)
	 * WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货) SELLER_REFUSE_BUYER(卖家拒绝退款)
	 * CLOSED(退款关闭) SUCCESS(退款成功)
	 */
	public Integer getRefundStatus() {
		return refundStatus;
	}

	/***
	 * 退款状态。退款状态。可选值 WAIT_SELLER_AGREE(买家已经申请退款，等待卖家同意)
	 * WAIT_BUYER_RETURN_GOODS(卖家已经同意退款，等待买家退货)
	 * WAIT_SELLER_CONFIRM_GOODS(买家已经退货，等待卖家确认收货) SELLER_REFUSE_BUYER(卖家拒绝退款)
	 * CLOSED(退款关闭) SUCCESS(退款成功)
	 */
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	/*** SKU id */
	public Long getSkuId() {
		return skuId;
	}

	/*** SKU id */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/*** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public String getProperties() {
		return properties;
	}

	/*** sku的销售属性组合字符串(p1:v1;p2:v2) */
	public void setProperties(String properties) {
		this.properties = properties;
	}

	/*** sku组合字符串的值 */
	public String getPropertiesname() {
		return propertiesname;
	}

	/*** sku组合字符串的值 */
	public void setPropertiesname(String propertiesname) {
		this.propertiesname = propertiesname;
	}
}
