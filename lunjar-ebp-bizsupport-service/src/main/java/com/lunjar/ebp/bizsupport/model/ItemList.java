package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemList extends ArrayList<Item> implements Serializable {

	private static final long serialVersionUID = -3306521475456949554L;

	public ItemList() {

	}

	public ItemList(List<Item> list) {
		Collections.addAll(this, list.toArray(new Item[] {}));
	}
}
