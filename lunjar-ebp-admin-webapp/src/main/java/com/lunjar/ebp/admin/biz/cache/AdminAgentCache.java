package com.lunjar.ebp.admin.biz.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.admin.domain.model.AdminAgent;
import com.lunjar.products.core.cache.CacheSupport;
import com.lunjar.products.core.cache.EHCacheUtil;

import net.sf.ehcache.CacheManager;

/**
 * 
 * 
 * <p>
 * create at 2014年4月15日 上午12:33:42
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version %I%, %G%
 * @see
 */
@Component
public class AdminAgentCache extends CacheSupport<AdminAgent> {
	private static final String CACHE_ID = AdminAgentCache.class.getSimpleName();

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
