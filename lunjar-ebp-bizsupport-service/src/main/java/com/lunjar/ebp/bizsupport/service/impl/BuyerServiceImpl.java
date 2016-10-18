package com.lunjar.ebp.bizsupport.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.BuyerInfoCache;
import com.lunjar.ebp.bizsupport.mappers.BuyerAddressMapper;
import com.lunjar.ebp.bizsupport.mappers.BuyerMapper;
import com.lunjar.ebp.bizsupport.model.Buyer;
import com.lunjar.ebp.bizsupport.model.BuyerAddress;
import com.lunjar.ebp.bizsupport.query.BuyerAddressQuery;
import com.lunjar.ebp.bizsupport.query.BuyerQuery;
import com.lunjar.ebp.bizsupport.service.BuyerAddressService;
import com.lunjar.ebp.bizsupport.service.BuyerService;
import com.lunjar.products.core.exception.ServiceException;

/***
 * 买家详细地址服务实现类
 * 
 * @author Administrator
 *
 */
@Service(value = "buyerService")
public class BuyerServiceImpl implements BuyerService {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BuyerServiceImpl.class);

	@Autowired
	private BuyerMapper buyerMapper;
	@Autowired
	private BuyerInfoCache buyerInfoCache;

	@Override
	public Buyer load(Long id) {
		return buyerMapper.load(id);
	}

	@Override
	public Long add(Buyer t) throws ServiceException {
		buyerMapper.insert(t);
		return t.getId();
	}

	@Override
	public int update(Buyer t) {
		return buyerMapper.update(t);
	}

	@Override
	public void delete(Long id) {
		buyerMapper.delete(id);
	}

	@Override
	public List<Buyer> queryList(BuyerQuery q) {
		return buyerMapper.queryList(q);
	}

	@Override
	public int queryCount(BuyerQuery q) {
		return buyerMapper.queryCount(q);
	}
	
	@Override
	public Buyer getBuyerInfo(String key, String openId, String appKey) throws ServiceException {
		return buyerInfoCache.load(key, openId, appKey);
	}
	
	@Override
	public void removeBuyerInfo(String key) {
		buyerInfoCache.remove(key);
	}

	@Override
	public Buyer getBuyerByOpenIdAndPartnerId(String openId, Long partnerId) {
		BuyerQuery query = new BuyerQuery();
		query.setBuyerId(openId);
		query.setPartnerId(partnerId);
		List<Buyer> list = buyerMapper.queryList(query);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}else {
			return null;
		}
	}
}
