package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * shopIndexUrl类
 * @author Administrator
 *
 */
public class ShopIndexPicUrlDto implements Serializable{
	private String productId;
	private String sort;
	private Boolean isRemove = false;
	
	private ShopIndexPicUrlDto(String str){
		this.productId = str.split(",")[0];
		this.sort = str.split(",")[1];
	}
	
	public static List<ShopIndexPicUrlDto> getShopIndexPicUrlDto(String[] picUrlArray){
		
		List<ShopIndexPicUrlDto> list = new ArrayList<>();
		for(String str : picUrlArray){
			if(str.contains(",")){
				ShopIndexPicUrlDto s = new ShopIndexPicUrlDto(str);
				list.add(s);
			}
		}
		return list;
	}

	public String getProductId() {
		return productId;
	}

	public String getSort() {
		return sort;
	}

	public Boolean getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(Boolean isRemove) {
		this.isRemove = isRemove;
	}
	
	
}
