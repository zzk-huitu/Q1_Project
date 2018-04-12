package com.yc.q1.dao.storage.log.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.storage.log.LogUserLoginDao;
import com.yc.q1.model.storage.log.LogUserLogin;

@Repository("LogUserLoginDao")
public class LogUserLoginDaoImpl  extends BaseDaoImpl<LogUserLogin> implements LogUserLoginDao {
}