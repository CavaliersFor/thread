package com.lunjar.ebp.admin.biz.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.admin.domain.model.EnterpriseAgent;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

@Component
public class EnterpriseAgentCache extends CacheSupport<EnterpriseAgent>{
	
	private static final String CACHE_ID = EnterpriseAgentCache.class.getSimpleName();
	
	@Autowired
	@Qualifier("ehCacheManagerAdmin")
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
