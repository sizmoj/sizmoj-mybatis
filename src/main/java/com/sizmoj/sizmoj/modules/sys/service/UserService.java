package com.sizmoj.sizmoj.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.nosql.redis.JedisTemplate;
import org.springside.modules.nosql.redis.JedisTemplate.JedisActionNoResult;

import redis.clients.jedis.Jedis;

import com.sizmoj.sizmoj.common.entity.RedisObjectType;
import com.sizmoj.sizmoj.common.persistence.RedisManager;
import com.sizmoj.sizmoj.modules.sys.entity.User;
import com.sizmoj.sizmoj.modules.sys.mapper.UserMapper;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserMapper userMapper;
	private JedisTemplate jedisTemplate = RedisManager.getJedisTemplate();
	private JsonMapper jsonMapper = new JsonMapper();
	
	
	public long addUser(final User user){
		userMapper.insertUser(user);
		final long id=  user.getId();
		/*jedisTemplate.execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				jedis.setex(RedisObjectType.USER.getPrefix() + id, 
						RedisObjectType.USER.getExpiredTime(),jsonMapper.toJson(user));
			}
		});*/
		return id;
	}
	public User selectUser(long userId) {
		User user = userMapper.findUserById(1);
		return user;
	}
}
