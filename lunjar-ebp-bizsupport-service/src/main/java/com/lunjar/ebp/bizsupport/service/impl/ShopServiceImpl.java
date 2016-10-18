package com.lunjar.ebp.bizsupport.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.lunjar.ebp.bizsupport.cache.ShopInfoCache;
import com.lunjar.ebp.bizsupport.dto.ShopIndexParams;
import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.mappers.ProductShopMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopIndexMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopMapper;
import com.lunjar.ebp.bizsupport.model.ProductShop;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.ebp.bizsupport.model.ShopIndex;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.query.ShopIndexQuery;
import com.lunjar.ebp.bizsupport.query.ShopQuery;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.service.ShopService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;

/**
 * 商铺服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日上午11:11:42
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {
	private static final Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Value("${view.url}")
	private String url;
	@Value("${store.server.url}")
	private String picUrl;

	@Autowired
	ShopMapper shopMapper;
	@Autowired
	ShopInfoCache shopInfoCache;
	@Autowired
	ShopIndexService shopIndexService;
	@Autowired
	ProductShopMapper productShopMapper;

	@Transactional
	@Override
	public Long add(Shop shop) {
		Assert.notNull(shop, "object shop is null");
		shop.setAccessKey(GenerateUtil.gennerateAccessKey());
		shop.setSecretKey(GenerateUtil.gennerateSecretKey());
		shop.setUrl(url + "/index?accessKey=" + shop.getAccessKey());
		shopMapper.insert(shop);
		return shop.getId();

	}

	@Transactional
	@Override
	public void update(Shop shop) {
		Assert.notNull(shop, "object shop is null");
		Assert.notNull(shop.getId(), "required shop id is null");
		shopMapper.update(shop);
	}

	@Override
	public List<Shop> queryList(ShopQuery query) {
		return shopMapper.queryList(query);
	}

	@Override
	public int queryCount(ShopQuery query) {
		return shopMapper.queryCount(query);
	}

	@Override
	public Shop load(Long id) {
		Assert.notNull(id, "object id is null");
		return shopMapper.load(id);
	}

	@Override
	public ShopInfo getShopInfo(String accessKey) {
		// TODO Auto-generated method stub
		return shopInfoCache.load(accessKey);
	}

	@Override
	public ShopInfo updateShopInfo(String accessKey) {
		// TODO Auto-generated method stub
		return shopInfoCache.update(accessKey);
	}

	@Override
	public void removeShopInfo(String accessKey) {
		// TODO Auto-generated method stub
		shopInfoCache.remove(accessKey);
	}

	@Override
	public Shop getShopByAccessKey(String accessKey) {
		return shopMapper.getShopByAccessKey(accessKey);
	}
}
