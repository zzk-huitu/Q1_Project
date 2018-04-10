package com.yc.q1.base.dk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.dk.dao.PtEcTermStatusDao;
import com.yc.q1.base.dk.model.DkTermStatus;
import com.zd.core.dao.BaseDaoImpl;


@Repository
public class PtEcTermStatusDaoImpl extends BaseDaoImpl<DkTermStatus> implements PtEcTermStatusDao {
    public PtEcTermStatusDaoImpl() {
        super(DkTermStatus.class);
        // TODO Auto-generated constructor stub
    }
}