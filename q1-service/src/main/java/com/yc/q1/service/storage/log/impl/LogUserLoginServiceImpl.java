package com.yc.q1.service.storage.log.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.log.LogUserLogin;
import com.yc.q1.service.storage.log.LogUserLoginService;

@Service
@Transactional
public class LogUserLoginServiceImpl extends BaseServiceImpl<LogUserLogin> implements LogUserLoginService {

	@Resource(name = "logUserLoginDao") // 将具体的dao注入进来
	public void setDao(BaseDao<LogUserLogin> dao) {
		super.setDao(dao);
	}
}
