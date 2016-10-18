package com.lunjar.ebp.admin.domain.dto;

import java.io.Serializable;
import java.util.List;

public class ProductPName implements Serializable {
	//[{name: '颜色', value: 'p1'}, {name: '尺码', value: 'p2'}]
	private String name;
	private String value;
	
	List<ProductPValue> list;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ProductPValue> getList() {
		return list;
	}

	public void setList(List<ProductPValue> list) {
		this.list = list;
	}
}
