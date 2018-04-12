package com.yc.q1.service.base.redis;

public interface PrimaryKeyRedisService {
	public String getId(String code);
	
	public Long getIncrementValue();
	
	public void resetIncrementValue();
}
