package com.lunjar.ebp.bizsupport.mappers;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lunjar.ebp.bizsupport.model.Express;
import com.lunjar.ebp.bizsupport.query.ExpressQuery;

public interface ExpressMapper {
	/***/
	Express load(Long id);

	/***/
	void insert(Express express);

	/***/
	int update(Express express);

	/***/
	void updateStatus(@Param("id") Long id, @Param("status") Serializable status);

	/***/
	void delete(Long id);

	/***/
	List<Express> queryList(ExpressQuery expressQuery);

	/***/
	int queryCount(ExpressQuery expressQuery);

	/**
	 * 通过id和商户id删除
	 * 
	 * @param express
	 * @return
	 */
	int deleteByIdAndEnterpriseId(Express express);

	/**
	 * 根据商家id获取快递对象
	 * 
	 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月19日上午11:02:43
	 * @param enterpriseId
	 * @return
	 */
	Express getByEntId(Long enterpriseId);
}