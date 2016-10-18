package com.lunjar.ebp.portal.console.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.portal.console.model.ShopAccessKeyAndSecretKeyList;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

/**
 * 
 * @author <a href="mailto:xbd0723@163.com">LiXuan</a>
 * 2016年10月8日上午9:28:11
 */
@Component
public class ShopsCache extends CacheSupport<ShopAccessKeyAndSecretKeyList> {
	private static final String CACHE_ID = ShopsCache.class.getSimpleName();

	@Autowired
	@Qualifier("ehCacheManagerPartner")
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
