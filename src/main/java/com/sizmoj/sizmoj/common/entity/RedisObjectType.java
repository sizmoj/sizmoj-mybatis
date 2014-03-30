package com.sizmoj.sizmoj.common.entity;

public enum RedisObjectType {
	USER("sys_user:",60*60*1),
	POST("sys_post:",60*60*4);
	private String prefix;
	private int expiredTime;
	
	RedisObjectType(String prefix, int expireTime) {
		this.prefix = prefix;
		this.expiredTime = expireTime;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public int getExpiredTime() {
		return expiredTime;
	}
}
