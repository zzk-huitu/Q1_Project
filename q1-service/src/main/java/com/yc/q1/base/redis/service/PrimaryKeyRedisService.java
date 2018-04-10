package com.yc.q1.base.redis.service;

public interface PrimaryKeyRedisService {
	public String getId(String code);
	
	public Long getIncrementValue();
	
	public void resetIncrementValue();
}
