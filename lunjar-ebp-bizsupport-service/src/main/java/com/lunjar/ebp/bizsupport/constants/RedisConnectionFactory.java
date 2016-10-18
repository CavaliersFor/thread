package com.lunjar.ebp.bizsupport.constants;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.lunjar.ebp.bizsupport.exception.RedisException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public abstract class RedisConnectionFactory {

	private static final Logger log = Logger
			.getLogger(RedisConnectionFactory.class);

	private static JedisPool pool = null;
	private static String password = null;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException(
					"[redis.properties] is not found!");
		}

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle
				.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle
				.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle
				.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle
				.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle
				.getString("redis.pool.testOnReturn")));

		pool = new JedisPool(config, bundle.getString("redis.ip"),
				Integer.valueOf(bundle.getString("redis.port")));
		
		password = bundle.getString("redis.password");
		
		log.info("--!成功读取redis配置并初始化连接池！" + bundle.getString("redis.ip") + ":"
				+ bundle.getString("redis.port"));
	}

	/**
	 * 获取 Redis 连接
	 * @param retryTimes
	 * @return
	 * @throws RedisException
	 */
	private static Jedis getConnection(int retryTimes) throws RedisException {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			// 处理线程池数据丢失情况
			if (null == jedis) {
				if (retryTimes < 2) {
					Thread.sleep(200);
					return getConnection(retryTimes++);
				} else {
					throw new RedisException("3次获取jedis连接出错，池数据丢失！");
				}
			}
			
			// 密码验证
			if (password != null && password.length() > 0) {
				jedis.auth(password);
			}
		} catch (Exception e) {
			log.error("获取jedis连接线程出错，请检查网络及redis的启动情况！" + retryTimes, e);
			if (null != jedis) {
				jedis.close();
			}
			if (retryTimes < 5) {
				return getConnection(retryTimes++);
			} else {
				throw new RedisException("5次获取jedis连接出错，网络或redis状态错误！");
			}
		}
		return jedis;
	}

	public static Jedis getConnection() throws RedisException {
		return getConnection(0);
	}
	
	public static void close(Jedis jedis){
		if(null != jedis){
			jedis.close();
		}
	}
	
	public static void destroyPool() {
		if (pool != null && pool.getNumActive() <= 0) {
			pool.destroy();
		}
	}
	
}
