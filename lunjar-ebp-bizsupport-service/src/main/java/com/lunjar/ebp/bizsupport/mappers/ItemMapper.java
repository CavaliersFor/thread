package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Item;
import com.lunjar.ebp.bizsupport.query.ItemQuery;

public interface ItemMapper {
	/***/
	Item load(Long id);

	/***/
	void insert(Item item);

	/***/
	void update(Item item);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Item> queryList(ItemQuery itemQuery);

	/***/
	int queryCount(ItemQuery itemQuery);
}