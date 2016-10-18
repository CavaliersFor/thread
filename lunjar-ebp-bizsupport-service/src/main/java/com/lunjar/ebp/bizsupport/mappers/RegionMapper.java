package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Region;
import com.lunjar.ebp.bizsupport.query.RegionQuery;

public interface RegionMapper {
	/***/
	Region load(String id);

	/***/
	void insert(Region region);

	/***/
	void update(Region region);

	/***/
	void updateStatus(@Param("id") String id, @Param("status") Serializable status);

	/***/
	void delete(String id);

	/***/
	List<Region> queryList(RegionQuery regionQuery);

	/***/
	int queryCount(RegionQuery regionQuery);

	String getParentCode(String code);
}