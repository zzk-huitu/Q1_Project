package com.yc.q1.dao.storage.log.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.dao.storage.log.UserLoginLogDao;
import com.yc.q1.model.storage.log.LogUserLogin;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class UserLoginLogDaoImpl  extends BaseDaoImpl<LogUserLogin> implements UserLoginLogDao {
}