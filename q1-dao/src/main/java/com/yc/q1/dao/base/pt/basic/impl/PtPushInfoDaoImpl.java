package com.yc.q1.dao.base.pt.basic.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.basic.PtPushInfoDao;
import com.yc.q1.model.base.pt.basic.PtPushInfo;

@Repository("PtPushInfoDao")
public class PtPushInfoDaoImpl extends BaseDaoImpl<PtPushInfo> implements PtPushInfoDao {
}
