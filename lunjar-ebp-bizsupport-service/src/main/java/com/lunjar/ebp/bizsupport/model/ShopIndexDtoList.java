package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;

public class ShopIndexDtoList extends ArrayList<ShopIndexDto> implements Serializable {

	private static final long serialVersionUID = 4863522524956918699L;

	public ShopIndexDtoList() {

	}

	public ShopIndexDtoList(List<ShopIndexDto> ShopIndexDtoList) {
		Collections.addAll(this, ShopIndexDtoList.toArray(new ShopIndexDto[] {}));
	}
}
