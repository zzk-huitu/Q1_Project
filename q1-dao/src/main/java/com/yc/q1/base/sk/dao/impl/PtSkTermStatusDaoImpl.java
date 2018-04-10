package com.yc.q1.base.sk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.sk.dao.PtSkTermStatusDao;
import com.yc.q1.base.sk.model.SkTermStatus;
import com.zd.core.dao.BaseDaoImpl;


@Repository
public class PtSkTermStatusDaoImpl extends BaseDaoImpl<SkTermStatus> implements PtSkTermStatusDao {
    public PtSkTermStatusDaoImpl() {
        super(SkTermStatus.class);
        // TODO Auto-generated constructor stub
    }
}