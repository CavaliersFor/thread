package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.query.ShopQuery;

public interface ShopMapper {
	/***/
	Shop load(Long id);

	/***/
	void insert(Shop shop);

	/***/
	void update(Shop shop);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Shop> queryList(ShopQuery shopQuery);

	/***/
	int queryCount(ShopQuery shopQuery);

	Shop getShopByAccessKey(String accessKey);
}