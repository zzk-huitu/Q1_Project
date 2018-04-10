
package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.SysOperateLogDao;
import com.yc.q1.base.log.model.OprateLog;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class SysOperateLogDaoImpl  extends BaseDaoImpl<OprateLog> implements SysOperateLogDao {
    public SysOperateLogDaoImpl() {
        super(OprateLog.class);
        // TODO Auto-generated constructor stub
    }
}