package com.lunjar.ebp.bizsupport.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.dto.ShopInfo;
import com.lunjar.ebp.bizsupport.mappers.SellerMapper;
import com.lunjar.ebp.bizsupport.mappers.ShopMapper;
import com.lunjar.ebp.bizsupport.model.Product;
import com.lunjar.ebp.bizsupport.model.Seller;
import com.lunjar.ebp.bizsupport.model.Shop;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

/**
 * 商铺缓存类
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a> 2016年8月11日下午2:20:57
 */
@Component
public class ProductCache extends CacheSupport<Product> {
	public static final String CACHE_ID = ShopInfoCache.class.getSimpleName();

	@Autowired
	@Qualifier("ehCacheManagerBizsupport")
	private CacheManager ehCacheManager;

	@Override
	public String getCacheId() {
		return CACHE_ID;
	}

	@PostConstruct
	public void init() {
		ehCacheUtil = new EHCacheUtil(ehCacheManager, CACHE_ID);
	}
}
