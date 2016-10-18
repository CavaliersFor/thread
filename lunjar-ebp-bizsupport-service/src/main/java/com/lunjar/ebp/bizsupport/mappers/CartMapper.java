package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Cart;
import com.lunjar.ebp.bizsupport.query.CartQuery;

public interface CartMapper {
	/***/
	Cart load(Long id);

	/***/
	void insert(Cart cart);

	/***/
	void update(Cart cart);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Cart> queryList(CartQuery cartQuery);

	/***/
	int queryCount(CartQuery cartQuery);

	void deleteByQuery(CartQuery cartQuery);
}