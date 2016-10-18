package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;

public interface CollectPlaceMapper {
	/***/
	CollectPlace load(Long id);

	/***/
	void insert(CollectPlace collectPlace);

	/***/
	int update(CollectPlace collectPlace);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<CollectPlace> queryList(CollectPlaceQuery collectPlaceQuery);

	/***/
	int queryCount(CollectPlaceQuery collectPlaceQuery);

	int deleteByIdAndEnterpriseId(CollectPlace collectPlace);
}