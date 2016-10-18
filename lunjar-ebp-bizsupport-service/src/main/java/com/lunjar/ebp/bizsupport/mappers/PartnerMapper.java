package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Partner;
import com.lunjar.ebp.bizsupport.query.PartnerQuery;

public interface PartnerMapper {
	/***/
	Partner load(Long id);

	/***/
	void insert(Partner partner);

	/***/
	void update(Partner partner);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Partner> queryList(PartnerQuery partnerQuery);

	/***/
	int queryCount(PartnerQuery partnerQuery);
}