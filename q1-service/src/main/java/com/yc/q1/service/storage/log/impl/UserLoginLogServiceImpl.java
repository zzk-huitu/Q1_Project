package com.yc.q1.service.storage.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.storage.log.LogUserLogin;
import com.yc.q1.service.storage.log.UserLoginLogService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class UserLoginLogServiceImpl extends BaseServiceImpl<LogUserLogin> implements UserLoginLogService {

	@Resource(name = "UserLoginLogDao") // 将具体的dao注入进来
	public void setDao(BaseDao<LogUserLogin> dao) {
		super.setDao(dao);
	}
}
