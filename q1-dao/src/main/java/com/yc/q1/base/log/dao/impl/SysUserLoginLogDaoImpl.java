package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.SysUserLoginLogDao;
import com.yc.q1.base.log.model.UserLoginLog;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class SysUserLoginLogDaoImpl  extends BaseDaoImpl<UserLoginLog> implements SysUserLoginLogDao {
    public SysUserLoginLogDaoImpl() {
        super(UserLoginLog.class);
        // TODO Auto-generated constructor stub
    }
}