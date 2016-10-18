package com.lunjar.ebp.bizsupport.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryList extends ArrayList<Category> implements Serializable {

	private static final long serialVersionUID = 2301458436141986928L;

	public CategoryList() {

	}

	public CategoryList(List<Category> categoryList) {
		Collections.addAll(this, categoryList.toArray(new Category[] {}));
	}
}
