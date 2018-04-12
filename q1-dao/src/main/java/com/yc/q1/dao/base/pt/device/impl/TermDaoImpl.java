package com.yc.q1.dao.base.pt.device.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.dao.base.pt.device.TermDao;
import com.yc.q1.model.base.pt.device.PtTerm;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 设备表
 * @author hucy
 *
 */
@Repository
public class TermDaoImpl extends BaseDaoImpl<PtTerm> implements TermDao{
}
