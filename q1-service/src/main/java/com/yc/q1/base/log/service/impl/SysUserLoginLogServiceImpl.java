package com.yc.q1.base.log.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.log.dao.UserLoginLogDao;
import com.yc.q1.base.log.model.UserLoginLog;
import com.yc.q1.base.log.service.SysUserLoginLogService;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class SysUserLoginLogServiceImpl extends BaseServiceImpl<UserLoginLog> implements SysUserLoginLogService {

    @Resource
    public void setSysUserLoginLogDao(UserLoginLogDao dao) {
        this.dao = dao;
    }
    
}
