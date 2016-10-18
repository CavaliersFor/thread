package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.Discount;

public class EnterpriseProModel implements Serializable{
	private String enterpriseName;//商户名称
	private Long enterpriseId;//商户编号
	private List<CartDto> listCartDto;//购车
	private Integer num;//该商户下面商品数量
	private BigDecimal total;//该商户下面总价
	private BigDecimal postFee;//邮费
	private Integer distributionMode;//配送方式1:快递2:自提
	private List<CollectPlaceDto> collectPlaces; //自提点
	private DiscountDto discountDto;
	private String discounts; //该商户的优惠信息JSON形式
	private String headPicUrl;//商家图片
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public List<CartDto> getListCartDto() {
		return listCartDto;
	}
	public void setListCartDto(List<CartDto> listCartDto) {
		this.listCartDto = listCartDto;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getPostFee() {
		return postFee;
	}
	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}
	public List<CollectPlaceDto> getCollectPlaces() {
		return collectPlaces;
	}
	public void setCollectPlaces(List<CollectPlaceDto> collectPlaces) {
		this.collectPlaces = collectPlaces;
	}
	public DiscountDto getDiscountDto() {
		return discountDto;
	}
	public void setDiscountDto(DiscountDto discountDto) {
		this.discountDto = discountDto;
	}
	public Integer getDistributionMode() {
		return distributionMode;
	}
	public void setDistributionMode(Integer distributionMode) {
		this.distributionMode = distributionMode;
	}
	public String getDiscounts() {
		return discounts;
	}
	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
}
