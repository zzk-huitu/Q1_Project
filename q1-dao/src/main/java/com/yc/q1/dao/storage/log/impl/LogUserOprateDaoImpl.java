
package com.yc.q1.dao.storage.log.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.storage.log.LogUserOprateDao;
import com.yc.q1.model.storage.log.LogUserOprate;

@Repository("LogUserOprateDao")
public class LogUserOprateDaoImpl  extends BaseDaoImpl<LogUserOprate> implements LogUserOprateDao {
}