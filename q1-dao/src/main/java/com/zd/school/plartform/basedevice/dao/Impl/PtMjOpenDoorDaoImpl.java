package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.MjOpenDoor;
import com.zd.school.plartform.basedevice.dao.PtMjOpenDoorDao;


@Repository
public class PtMjOpenDoorDaoImpl extends BaseDaoImpl<MjOpenDoor> implements PtMjOpenDoorDao {
    public PtMjOpenDoorDaoImpl() {
        super(MjOpenDoor.class);
        // TODO Auto-generated constructor stub
    }
}