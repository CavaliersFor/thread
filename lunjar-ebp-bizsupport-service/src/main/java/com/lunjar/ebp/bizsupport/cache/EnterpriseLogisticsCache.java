package com.lunjar.ebp.bizsupport.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.bizsupport.model.EnterpriseLogistics;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

@Component
public class EnterpriseLogisticsCache extends CacheSupport<EnterpriseLogistics> {
	private static final String CACHE_ID = EnterpriseLogisticsCache.class.getSimpleName();

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
