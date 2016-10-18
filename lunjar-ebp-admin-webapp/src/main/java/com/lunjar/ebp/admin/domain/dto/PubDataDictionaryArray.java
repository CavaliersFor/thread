package com.lunjar.ebp.admin.domain.dto;

import com.lunjar.products.core.datadict.model.PubDataDictionaryEntity;

/**
 * 基础数据数组类，用于接收前台提交的数组数据
 * <p> 
 * @author  <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version 2012-12-5 下午11:00:25
 */
public class PubDataDictionaryArray {
	private PubDataDictionaryEntity items[];

	public PubDataDictionaryEntity[] getItems() {
		return items;
	}

	public void setItems(PubDataDictionaryEntity[] items) {
		this.items = items;
	}
}