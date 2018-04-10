package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.PushInfoDao;
import com.yc.q1.base.log.model.PushInfo;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class PushInfoDaoImpl extends BaseDaoImpl<PushInfo> implements PushInfoDao {
    public PushInfoDaoImpl() {
        super(PushInfo.class);
        // TODO Auto-generated constructor stub
    }
}
