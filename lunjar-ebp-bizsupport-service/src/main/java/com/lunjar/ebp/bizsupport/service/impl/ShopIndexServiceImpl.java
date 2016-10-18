package com.lunjar.ebp.bizsupport.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.alibaba.rocketmq.common.protocol.header.GetProducerConnectionListRequestHeader;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.lunjar.ebp.bizsupport.cache.ShopIndexDtoListCache;
import com.lunjar.ebp.bizsupport.dto.ProductShopDto;
import com.lunjar.ebp.bizsupport.dto.ShopIndexDto;
import com.lunjar.ebp.bizsupport.dto.ShopIndexParams;
import com.lunjar.ebp.bizsupport.enums.BizSupportCode;
import com.lunjar.ebp.bizsupport.enums.EnumShopIndexStatus;
import com.lunjar.ebp.bizsupport.mappers.ShopIndexMapper;
import com.lunjar.ebp.bizsupport.model.ShopIndex;
import com.lunjar.ebp.bizsupport.model.ShopIndexDtoList;
import com.lunjar.ebp.bizsupport.query.ProductShopQuery;
import com.lunjar.ebp.bizsupport.query.ShopIndexQuery;
import com.lunjar.ebp.bizsupport.service.CategoryService;
import com.lunjar.ebp.bizsupport.service.ProductShopService;
import com.lunjar.ebp.bizsupport.service.ShopIndexService;
import com.lunjar.ebp.bizsupport.utils.GenerateUtil;
import com.lunjar.products.core.exception.ServiceException;

/**
 * 商铺首页服务实现类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月23日下午3:18:57
 */
@Service("shopIndexService")
public class ShopIndexServiceImpl implements ShopIndexService {
	private static final Logger log = LoggerFactory.getLogger(ShopIndexServiceImpl.class);
	private static final String SHOP_INDEX_CACHE_KEY = "shop_index_cache_key_";

	@Value("${view.url}")
	private String url;
	@Value("${store.server.url}")
	private String storeUrl;

	@Autowired
	ShopIndexMapper shopIndexMapper;
	@Autowired
	ProductShopService productShopService;
	@Autowired
	CategoryService CategoryService;
	@Autowired
	ShopIndexDtoListCache shopIndexDtoListCache;

	@Transactional
	@Override
	public Long add(ShopIndex shopIndex) {
		Assert.notNull(shopIndex, "object shopIndex is null");
		shopIndexMapper.insert(shopIndex);
		return shopIndex.getId();

	}

	@Transactional
	@Override
	public void update(ShopIndex shopIndex) {
		Assert.notNull(shopIndex, "object shop is null");
		Assert.notNull(shopIndex.getId(), "required shop id is null");
		shopIndexMapper.update(shopIndex);
	}

	@Override
	public List<ShopIndex> queryList(ShopIndexQuery query) {
		return shopIndexMapper.queryList(query);
	}

	@Override
	public int queryCount(ShopIndexQuery query) {
		return shopIndexMapper.queryCount(query);
	}

	@Override
	public ShopIndex load(Long id) {
		Assert.notNull(id, "object id is null");
		return shopIndexMapper.load(id);
	}

	@Override
	public List<ShopIndexDto> getShopIndex(Long shopId) throws ServiceException {
		List<ShopIndexDto>dtoList = null;
		ShopIndexDto dto = null;
		Assert.notNull(shopId, BizSupportCode._2001016.getMsg());
		dtoList = shopIndexDtoListCache.get(SHOP_INDEX_CACHE_KEY  + shopId);
		if (CollectionUtils.isEmpty(dtoList)) {
			ShopIndexQuery query = new ShopIndexQuery();
			query.setShopId(shopId);
			query.setStatus(EnumShopIndexStatus.NORMAL.getValue());
			query.setSort("  sort_num");
			List<ShopIndex> list = shopIndexMapper.queryList(query);
			List<ShopIndexParams> params = null;// 幻灯片
			List<ProductShopDto> proList = null;// 产品列表
			Long[] ids = null;
			if (CollectionUtils.isNotEmpty(list)) {
				dtoList = new ArrayList<>();
				for (ShopIndex si : list) {
					dto = new ShopIndexDto();
					dto.setType(si.getType());
					if (si.getType() == 5 || si.getType() ==3	) {
						ids = GenerateUtil.getProductIds(si.getPicUrls());
						proList = getProductList(ids, shopId);
						dto.setProList(proList);
						if (si.getType() == 3) {
							dto.setName(si.getName());
						}
					} else {
						params = GenerateUtil.createShopIndexList(si.getPicUrls(), storeUrl);
						dto.setList(params);
					}
					dtoList.add(dto);
				}
			}
			if (CollectionUtils.isNotEmpty(dtoList)) {
				shopIndexDtoListCache.put(SHOP_INDEX_CACHE_KEY  + shopId, new ShopIndexDtoList(dtoList));
			}
		}
		return dtoList;
	}

	private List<ProductShopDto> getProductList(Long[] ids, Long shopId) throws ServiceException {
		ProductShopQuery shopQuery = new ProductShopQuery();
		shopQuery.setpIdArray(ids);
		shopQuery.setShopId(shopId);
		shopQuery.setStatus(1);
		return productShopService.queryList(shopQuery);
	}

	@Override
	public void delete(Long id) {
		shopIndexMapper.delete(id);
	}

}
