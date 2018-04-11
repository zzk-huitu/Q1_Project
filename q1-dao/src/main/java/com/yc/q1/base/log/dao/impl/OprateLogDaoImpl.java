
package com.yc.q1.base.log.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.log.dao.OprateLogDao;
import com.yc.q1.model.storage.log.OprateLog;
import com.zd.core.dao.BaseDaoImpl;

@Repository
public class OprateLogDaoImpl  extends BaseDaoImpl<OprateLog> implements OprateLogDao {
}