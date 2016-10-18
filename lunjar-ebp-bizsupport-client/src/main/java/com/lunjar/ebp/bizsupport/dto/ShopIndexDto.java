package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.List;

public class ShopIndexDto implements Serializable {
	
	private static final long serialVersionUID = -8824891219592711661L;

	private Integer type ;//类型1：轮播图2：大图3：推荐产品4：热卖产品
	private String name;//名称(针对类型3来动态设置类型名称)
	private List<ShopIndexParams> list;//1：轮播图2：大图4：热卖产品
	private List<ProductShopDto> proList;//商品列表3：推荐产品 5：产品
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<ShopIndexParams> getList() {
		return list;
	}
	public void setList(List<ShopIndexParams> list) {
		this.list = list;
	}
	public List<ProductShopDto> getProList() {
		return proList;
	}
	public void setProList(List<ProductShopDto> proList) {
		this.proList = proList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
