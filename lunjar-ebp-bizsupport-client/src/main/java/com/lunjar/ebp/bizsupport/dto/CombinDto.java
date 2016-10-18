package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 转换JSON使用
 * @author Administrator
 *
 */
public class CombinDto implements Serializable{
	
	private static final long serialVersionUID = -7636776367504696528L;
	
	private Long productId;//商品id
	private Long skuId;//skuId
	private Integer num;//数目
	private BigDecimal realPrice;//销售价格
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
}
