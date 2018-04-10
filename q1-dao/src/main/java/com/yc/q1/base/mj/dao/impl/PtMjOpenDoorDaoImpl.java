package com.yc.q1.base.mj.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.mj.dao.PtMjOpenDoorDao;
import com.yc.q1.base.mj.model.MjOpenDoor;
import com.zd.core.dao.BaseDaoImpl;


@Repository
public class PtMjOpenDoorDaoImpl extends BaseDaoImpl<MjOpenDoor> implements PtMjOpenDoorDao {
    public PtMjOpenDoorDaoImpl() {
        super(MjOpenDoor.class);
        // TODO Auto-generated constructor stub
    }
}