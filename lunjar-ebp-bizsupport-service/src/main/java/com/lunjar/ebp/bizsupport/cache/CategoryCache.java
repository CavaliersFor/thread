package com.lunjar.ebp.bizsupport.cache;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.lunjar.ebp.bizsupport.model.Category;
import com.lunjar.products.core.cache.RedisUtil;

public class CategoryCache {

	private static final int DEFAULT_REDIS_TIME_TO_LIVE_SECONDS = 7200;

	private static final String CATEGORY_CACHE_ID = Category.class.getSimpleName();

	private int redisTimeToLiveSeconds = DEFAULT_REDIS_TIME_TO_LIVE_SECONDS;

	private RedisUtil redisUtil;

	public void put(Serializable key, Object o) {
		Date expiry = null;
		if (getRedisTimeToLiveSeconds() > 0) {
			expiry = DateUtils.addSeconds(new Date(), getRedisTimeToLiveSeconds());
		}

		Assert.notNull(key, "param [key] can't empty");

		redisUtil.put(CATEGORY_CACHE_ID, key, o, expiry);
	}

	public Category get(Serializable key) {
		Assert.notNull(key, "param [key] can't empty");
		Category category = redisUtil.get(CATEGORY_CACHE_ID, key, Category.class);
		return category;
	}

	public void remove(Serializable key) {
		Assert.notNull(key, "param [key] can't empty");
		redisUtil.remove(key, CATEGORY_CACHE_ID);
	}

	/**
	 * 子类可覆盖该方法，设置过期时间
	 */
	protected int getRedisTimeToLiveSeconds() {
		return redisTimeToLiveSeconds;
	}

	@Autowired
	public void setRedisUtil(RedisUtil redisUtil) {
		this.redisUtil = redisUtil;
	}

}
