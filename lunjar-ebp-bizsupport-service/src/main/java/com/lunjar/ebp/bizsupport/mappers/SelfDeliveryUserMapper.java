package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.SelfDeliveryUser;
import com.lunjar.ebp.bizsupport.query.SelfDeliveryUserQuery;

public interface SelfDeliveryUserMapper {
	/***/
	SelfDeliveryUser load(Long id);

	/***/
	void insert(SelfDeliveryUser selfDeliveryUser);

	/***/
	void update(SelfDeliveryUser selfDeliveryUser);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<SelfDeliveryUser> queryList(SelfDeliveryUserQuery selfDeliveryUserQuery);

	/***/
	int queryCount(SelfDeliveryUserQuery selfDeliveryUserQuery);
	
	/***/
	SelfDeliveryUser loadByUserName(String userName);
}