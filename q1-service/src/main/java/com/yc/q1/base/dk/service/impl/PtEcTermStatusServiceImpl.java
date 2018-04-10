package com.yc.q1.base.dk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.dk.dao.DkTermStatusDao;
import com.yc.q1.base.dk.model.DkTermStatus;
import com.yc.q1.base.dk.service.PtEcTermStatusService;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class PtEcTermStatusServiceImpl extends BaseServiceImpl<DkTermStatus> implements PtEcTermStatusService{

    @Resource
    public void setPtEcTermStatusDao(DkTermStatusDao dao) {
        this.dao = dao;
    }

}