package com.zd.school.plartform.system.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.plartform.system.dao.SysUserLoginLogDao;
import com.zd.school.plartform.system.model.UserLoginLog;

@Repository
public class SysUserLoginLogDaoImpl  extends BaseDaoImpl<UserLoginLog> implements SysUserLoginLogDao {
    public SysUserLoginLogDaoImpl() {
        super(UserLoginLog.class);
        // TODO Auto-generated constructor stub
    }
}