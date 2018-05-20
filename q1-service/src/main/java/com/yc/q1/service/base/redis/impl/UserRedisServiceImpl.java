package com.yc.q1.service.base.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.yc.q1.core.constant.MenuCodeType;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.service.base.redis.UserRedisService;

@Service
public class UserRedisServiceImpl implements UserRedisService{
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	

	//取出的键为 userId+menuCode
	@Override
	public Object getMenuTreeByUser(String userId,String sysMenuCode) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		String hkey=userId;
		if(StringUtils.isNotEmpty(sysMenuCode))
			hkey+="-"+sysMenuCode;
		Object baseDicItem = hashOper.get("userMenuTree", hkey);
		return baseDicItem;
	}

	@Override
	public Object getDeskFuncByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object baseDicItem = hashOper.get("userDeskFunc", userId);
		return baseDicItem;
	}

	//存入的键为 userId+menuCode
	@Override
	public void setMenuTreeByUser(String userId,String sysMenuCode, Object values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		String hkey=userId;
		if(StringUtils.isNotEmpty(sysMenuCode))
			hkey+="-"+sysMenuCode;
		hashOper.put("userMenuTree",hkey, values);		
	}

	@Override
	public void setDeskFuncByUser(String userId, Set<String> values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userDeskFunc", userId, values);		
	}

	@Override
	public void deleteAuthByUser(Object... userIds) {
		// TODO Auto-generated method stub
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			hashOper.delete("userAuth", userIds);
		}
	}

	@Override
	public void deleteBtnByUser(Object... userIds) {
		// TODO Auto-generated method stub
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			hashOper.delete("userBtn", userIds);	
		}
	}

	@Override
	public Object getAuthByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object userAuth = hashOper.get("userAuth", userId);
		return userAuth;
	}

	@Override
	public Object getBtnByUser(String userId) {
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		Object userBtn = hashOper.get("userBtn", userId);
		return userBtn;
	}

	@Override
	public void setAuthByUser(String userId, Set<String>  values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userAuth", userId, values);	
	}

	@Override
	public void setBtnByUser(String userId, Set<String>  values) {
		// TODO Auto-generated method stub
		HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
		hashOper.put("userBtn", userId, values);	
	}
	
	//2018-5-19：hkey针对每个子系统菜单，加入了menucode后缀，所以要一次性排除
	@Override
	public void deleteMenuTreeByUser(Object... userIds) {
		// TODO Auto-generated method stub
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			
			//加入菜单code后缀
			List<String> userMenuCode=new ArrayList<>();
			for(int i=0;i<userIds.length;i++){
				String userId=(String) userIds[i];
				userMenuCode.add(userId+"-"+MenuCodeType.PT);
				userMenuCode.add(userId+"-"+MenuCodeType.KW);
				userMenuCode.add(userId+"-"+MenuCodeType.XF);
				userMenuCode.add(userId+"-"+MenuCodeType.SK);
				userMenuCode.add(userId+"-"+MenuCodeType.DK);
				userMenuCode.add(userId+"-"+MenuCodeType.MJ);
				userMenuCode.add(userId+"-"+MenuCodeType.KZ);
				userMenuCode.add(userId+"-"+MenuCodeType.BP);
				userMenuCode.add(userId+"-"+MenuCodeType.JS);
				userMenuCode.add(userId+"-"+MenuCodeType.DC);
			}
			
			//hashOper.delete("userMenuTree", userIds);
			hashOper.delete("userMenuTree", userMenuCode.toArray());
		}
	}

	@Override
	public void deleteDeskFuncByUser(Object... userIds) {
		// TODO Auto-generated method stub
		if (userIds.length > 0) {
			HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
			hashOper.delete("userDeskFunc", userIds);	
		}
	}
	
	

}
