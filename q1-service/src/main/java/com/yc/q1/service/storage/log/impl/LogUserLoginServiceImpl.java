package com.yc.q1.service.storage.log.impl;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.log.LogUserLogin;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.log.LogUserLoginService;

@Service
@Transactional
public class LogUserLoginServiceImpl extends BaseServiceImpl<LogUserLogin> implements LogUserLoginService {

	@Resource(name = "LogUserLoginDao") // 将具体的dao注入进来
	public void setDao(BaseDao<LogUserLogin> dao) {
		super.setDao(dao);
	}

	@Resource
	private PrimaryKeyRedisService keyRedisService;
	
	@Override
	public void doAddLoginLog(PtUser user, Session session) {
		// TODO Auto-generated method stub	
		LogUserLogin loginLog = new LogUserLogin(keyRedisService.getId(LogUserLogin.ModuleType));
		loginLog.setUserId(user.getId());
		loginLog.setSessionId((String) session.getId());
		loginLog.setUserName(user.getUserName());
		loginLog.setIpHost(session.getHost());
		loginLog.setLoginDate(session.getLastAccessTime());
		loginLog.setLastAccessDate(session.getLastAccessTime());
		this.persist(loginLog);		
	}
}
