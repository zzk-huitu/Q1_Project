package com.yc.q1.base.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.log.service.UserLoginLogService;
import com.yc.q1.model.storage.log.UserLoginLog;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class UserLoginLogServiceImpl extends BaseServiceImpl<UserLoginLog> implements UserLoginLogService {

	@Resource(name = "UserLoginLogDao") // 将具体的dao注入进来
	public void setDao(BaseDao<UserLoginLog> dao) {
		super.setDao(dao);
	}
}
