package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.UserLoginLogDao;
import com.yc.q1.base.log.model.UserLoginLog;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class UserLoginLogDaoImpl  extends BaseDaoImpl<UserLoginLog> implements UserLoginLogDao {
}