package com.lunjar.ebp.bizsupport.service;

import java.util.List;

import com.lunjar.ebp.bizsupport.dto.CollectTimeDto;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;
import com.lunjar.products.core.model.PageResult;

/**
 * 商品自取地点服务类
 * @author Administrator
 *
 */
public interface CollectPlaceService extends CommonService<CollectPlace, CollectPlaceQuery>{
	/**
	 * 通过id和EnterpriseId(商家id)进行删除
	 * @param discount
	 * @return
	 */
	int deleteByIdAndEnterpriseId(CollectPlace collectPlace);
	
	/**
	 * 根据自提点id、订单id列表获取自提点自提时间范围列表
	 */
	List<CollectTimeDto> getCollectTimeList(List<CollectTimeDto> list);

	/**
	 * 根据自提点id获取提货的起始与结束时间
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年9月1日下午12:21:04
	 * @param collectPlaceId
	 * @return
	 */
	CollectTimeDto getCollectTime(Long collectPlaceId);
	
	/**
	 * 查询自提点有分页
	 * @param query
	 * @return
	 */
	public PageResult<CollectPlace> queryListPage(CollectPlaceQuery query);
}
