package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;

public interface ProductShopMapper {
	/***/
	ProductShop load(Long id);

	/***/
	void insert(ProductShop productShop);

	/***/
	void update(ProductShop productShop);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<ProductShop> queryList(ProductShopQuery productShopQuery);

	/***/
	int queryCount(ProductShopQuery productShopQuery);
	
	
	void updateByCondition(ProductShop productShop);

	void deleteProductShop(ProductShop productShop);
}