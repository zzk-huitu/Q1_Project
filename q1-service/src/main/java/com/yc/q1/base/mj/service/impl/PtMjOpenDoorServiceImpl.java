package com.yc.q1.base.mj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.mj.dao.MjOpenDoorDao;
import com.yc.q1.base.mj.model.MjOpenDoor;
import com.yc.q1.base.mj.service.PtMjOpenDoorService;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class PtMjOpenDoorServiceImpl extends BaseServiceImpl<MjOpenDoor> implements PtMjOpenDoorService{

    @Resource
    public void setPtMjOpenDoorDao(MjOpenDoorDao dao) {
        this.dao = dao;
    }

}