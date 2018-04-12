package com.yc.q1.dao.base.pt.device.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.device.PtTermDao;
import com.yc.q1.model.base.pt.device.PtTerm;

/**
 * 设备表
 * @author hucy
 *
 */
@Repository("PtTermDao")
public class PtTermDaoImpl extends BaseDaoImpl<PtTerm> implements PtTermDao{
}
