package com.yc.q1.base.redis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yc.q1.base.redis.service.DeptRedisService;
import com.yc.q1.pojo.base.pt.CommTree;
import com.yc.q1.pojo.base.pt.DepartmentTree;

@Service
public class DeptRedisServiceImpl implements DeptRedisService{
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void deleteDeptTreeAll() {
		// TODO Auto-generated method stub
		redisTemplate.delete("userRightDeptTree");
		redisTemplate.delete("userRightDeptClassTree");	
		redisTemplate.delete("userRightDeptDisciplineTree");
	}

	
	@Override
	public void deleteDeptTreeByUsers(Object... userIds) {
		// TODO Auto-generated method stub
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			hashOper.delete("userRightDeptTree", userIds);
			hashOper.delete("userRightDeptClassTree", userIds);		
			hashOper.delete("userRightDeptDisciplineTree", userIds);	
		}
	}


	@Override
	public Object getRightDeptTreeByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object userRightDeptTree = hashOper.get("userRightDeptTree", userId);	
		return userRightDeptTree;
	}


	@Override
	public void setRightDeptTreeByUser(String userId, List<DepartmentTree> values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userRightDeptTree", userId, values);
	}


	@Override
	public Object getRightDeptClassTreeByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object userRightDeptClassTree = hashOper.get("userRightDeptClassTree", userId);	
		return userRightDeptClassTree;
	}


	@Override
	public void setRightDeptClassTreeByUser(String userId, List<CommTree> values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userRightDeptClassTree", userId, values);
	}


	@Override
	public Object getRightDeptDisciplineTreeByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object userRightDeptDisciplineTree = hashOper.get("userRightDeptDisciplineTree", userId);	
		return userRightDeptDisciplineTree;
	}


	@Override
	public void setRightDeptDisciplineTreeByUser(String userId, List<CommTree> values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userRightDeptDisciplineTree", userId, values);
	}


	

}
