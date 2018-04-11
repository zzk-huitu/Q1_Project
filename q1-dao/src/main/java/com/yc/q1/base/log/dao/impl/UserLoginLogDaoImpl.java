package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.UserLoginLogDao;
import com.yc.q1.model.storage.log.LogUserLoginLog;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class UserLoginLogDaoImpl  extends BaseDaoImpl<LogUserLoginLog> implements UserLoginLogDao {
}