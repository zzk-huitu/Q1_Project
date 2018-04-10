package com.zd.school.plartform.system.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.school.plartform.system.dao.SysUserLoginLogDao;
import com.zd.school.plartform.system.model.UserLoginLog;
import com.zd.school.plartform.system.service.SysUserLoginLogService;

@Service
@Transactional
public class SysUserLoginLogServiceImpl extends BaseServiceImpl<UserLoginLog> implements SysUserLoginLogService {

    @Resource
    public void setSysUserLoginLogDao(UserLoginLogDao dao) {
        this.dao = dao;
    }
    
}
