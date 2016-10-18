package com.lunjar.ebp.bizsupport.dto;

import java.io.Serializable;
import java.util.List;

import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.ProductPropImgs;
import com.lunjar.ebp.bizsupport.model.ProductSku;

public class ProductDto implements Serializable{
	
	private static final long serialVersionUID = -1060387878778169465L;
	
	private Product product;//产品
//	private List<ProductPropImgs> list;//图片属性list
	private List<ShopIndexParams> list;//描述图片
	
	private List<ProductSku> skus;
	
	public ProductDto() {
		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ShopIndexParams> getList() {
		return list;
	}

	public void setList(List<ShopIndexParams> list) {
		this.list = list;
	}

	public List<ProductSku> getSkus() {
		return skus;
	}

	public void setSkus(List<ProductSku> skus) {
		this.skus = skus;
	}

//	public List<ProductPropImgs> getList() {
//		return list;
//	}
//
//	public void setList(List<ProductPropImgs> list) {
//		this.list = list;
//	}
}
