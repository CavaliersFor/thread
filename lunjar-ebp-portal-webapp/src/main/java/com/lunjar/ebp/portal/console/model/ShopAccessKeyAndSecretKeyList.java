package com.lunjar.ebp.portal.console.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShopAccessKeyAndSecretKeyList extends ArrayList<ShopAccessKeyAndSecretKey> implements Serializable {

	private static final long serialVersionUID = -648313252546760998L;

	public ShopAccessKeyAndSecretKeyList() {

	}

	public ShopAccessKeyAndSecretKeyList(List<ShopAccessKeyAndSecretKey> list) {
		Collections.addAll(this, list.toArray(new ShopAccessKeyAndSecretKey[] {}));
	}
}
