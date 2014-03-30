package com.sizmoj.sizmoj.common.persistence;

import org.springframework.stereotype.Component;
import org.springside.modules.nosql.redis.JedisTemplate;
import org.springside.modules.nosql.redis.JedisUtils;

import redis.clients.jedis.JedisPool;

@Component
public class RedisManager {
    private static JedisPool pool;
    private static final int THREAD_COUNT = 10;
    private static JedisTemplate jedisTemplate;
	public static JedisTemplate getJedisTemplate() {
		pool = JedisPoolFactory.createJedisPool(JedisUtils.DEFAULT_HOST,
				JedisUtils.DEFAULT_PORT, JedisUtils.DEFAULT_TIMEOUT, THREAD_COUNT);
		jedisTemplate = new JedisTemplate(pool);
		return jedisTemplate;
	}
}
