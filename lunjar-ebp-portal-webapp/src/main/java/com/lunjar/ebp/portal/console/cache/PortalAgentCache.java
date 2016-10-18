package com.lunjar.ebp.portal.console.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lunjar.ebp.portal.console.model.PortalAgent;
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
public class PortalAgentCache extends CacheSupport<PortalAgent> {
	private static final String CACHE_ID = PortalAgentCache.class.getSimpleName();

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
