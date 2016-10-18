package com.lunjar.ebp.bizsupport.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.dto.CollectTimeDto;
import com.lunjar.ebp.bizsupport.dto.ProductDto;
import com.lunjar.ebp.bizsupport.mappers.CollectPlaceMapper;
import com.lunjar.ebp.bizsupport.mappers.EnterpriseMapper;
import com.lunjar.ebp.bizsupport.mappers.TradeMapper;
import com.lunjar.ebp.bizsupport.model.CollectPlace;
import com.lunjar.ebp.bizsupport.model.Enterprise;
import com.lunjar.ebp.bizsupport.model.Trade;
import com.lunjar.ebp.bizsupport.query.CollectPlaceQuery;
import com.lunjar.ebp.bizsupport.service.CollectPlaceService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.model.PageResult;
import com.lunjar.products.core.utils.DateUtils;

@Service(value = "collectPlaceService")
public class CollectPlaceServiceImpl implements CollectPlaceService {

	private static final String FORMAT = "yyyyMMddHHmmss";
	
	@Autowired
	private CollectPlaceMapper collectPlaceMapper;
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Override
	public CollectPlace load(Long id) {
		return collectPlaceMapper.load(id);
	}

	@Override
	public Long add(CollectPlace collectPlace) {
		Assert.notNull(collectPlace, "object collectPlace is null");
		collectPlaceMapper.insert(collectPlace);
		return collectPlace.getId();
	}

	@Override
	public int update(CollectPlace collectPlace) {
		Assert.notNull(collectPlace, "object discount is null");
		Assert.notNull(collectPlace.getId(), "required discount id is null");
		return collectPlaceMapper.update(collectPlace);
	}

	@Override
	public void delete(Long id) {
		collectPlaceMapper.delete(id);
	}

	@Override
	public List<CollectPlace> queryList(CollectPlaceQuery collectPlaceQuery) {
		return collectPlaceMapper.queryList(collectPlaceQuery);
	}

	@Override
	public int queryCount(CollectPlaceQuery collectPlaceQuery) {
		return collectPlaceMapper.queryCount(collectPlaceQuery);
	}

	@Override
	public int deleteByIdAndEnterpriseId(CollectPlace collectPlace) {
		Assert.notNull(collectPlace, "object collectPlace is null");
		Assert.notNull(collectPlace.getId(), "object collectPlace id is null");
		Assert.notNull(collectPlace.getEnterpriseId(), "object discount enterpriseId is null");
		return collectPlaceMapper.deleteByIdAndEnterpriseId(collectPlace);
	}

	@Override
	public CollectTimeDto getCollectTime(Long collectPlaceId) {
		CollectTimeDto collectTimeDto = null;
		CollectPlace collectPlace = collectPlaceMapper.load(collectPlaceId);
		if (collectPlace != null) {
			Enterprise enterprise = enterpriseMapper.load(collectPlace.getEnterpriseId());
			if (enterprise != null) {
				collectTimeDto = new CollectTimeDto();
				boolean b = compare2StringDate(DateUtils.format(new Date(), FORMAT), enterprise.getDeliveryDeadline());
				int d = collectPlace.getCollectTime()/24;
				Date date = DateUtils.parse(DateUtils.format(new Date()));
				if (b) {
					d = d + 1;
				}
				collectTimeDto.setStartTime(DateUtils.addDay(date, d));
				collectTimeDto.setEndTime(DateUtils.addDay(date, d + collectPlace.getMaxDepositDays()));
			}
		}
		return collectTimeDto;
	}
	@Override
	public List<CollectTimeDto> getCollectTimeList(List<CollectTimeDto> list) {
		// TODO Auto-generated method stub
		if (CollectionUtils.isNotEmpty(list)) {
			CollectPlace collectPlace = null;
			Trade trade = null;
			Enterprise enterprise = null;
			for(CollectTimeDto c: list) {
				trade = tradeMapper.load(c.getTradeId());
				collectPlace = collectPlaceMapper.load(trade.getBuyerAddressId());
				enterprise = enterpriseMapper.load(trade.getEnterpriseId());
				boolean b = compare2StringDate(DateUtils.format(trade.getBuyerPayTime(), FORMAT), enterprise.getDeliveryDeadline());
				int d = collectPlace.getCollectTime()/24;
				Date date = DateUtils.parse(DateUtils.format(new Date()));
				if (b) {
					d = d + 1;
				}
				c.setStartTime(DateUtils.addDay(date, d));
				c.setEndTime(DateUtils.addDay(date, d + collectPlace.getMaxDepositDays()));
			}
		}
		return list;
	}

	/**
	 * 
	 *@author <a href="mailto:xbd0723@163.com">LiXuan</a>
	 *2016年8月31日下午5:54:58
	 * @param format2 目前时间
	 * @param deliveryDeadline 自提点送货统计时间
	 * @return true 表示 超过时间 false 表示没超过
	 */
	private static boolean compare2StringDate(String format2, String deliveryDeadline) {
		String d1 = format2.substring(8);
		String d2 = GenerateUtil.autoGenericCodeEnd(deliveryDeadline.replace(":", ""), d1.length());
		if (Long.parseLong(d1)-Long.parseLong(d2) > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PageResult<CollectPlace> queryListPage(CollectPlaceQuery query) {
		List<CollectPlace> list = this.queryList(query);
		int count = this.queryCount(query);
		return PageResult.create(query, list, count);
	}
}
