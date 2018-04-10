package com.zd.core.security;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.yc.q1.base.log.model.OprateLog;
import com.yc.q1.base.log.service.SysOperateLogService;

//@Component(value="LogJobQuartz")
public class LogJobQuartz {
	
	@Resource
	private RedisTemplate<String, OprateLog> redisTemplate;

	@Resource
	private SysOperateLogService logService; 
	
	protected void execute() {
		try{
			ListOperations<String, OprateLog> listOper = redisTemplate.opsForList();
			List<OprateLog> lists = listOper.range("SysOperateLog",0,-1);
			redisTemplate.delete("SysOperateLog");
			
			logService.multiAddEntity(lists);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
