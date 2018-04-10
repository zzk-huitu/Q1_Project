package com.zd.school.plartform.basedevice.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.service.BaseServiceImpl;
import com.zd.school.control.device.model.DkTermStatus;
import com.zd.school.plartform.basedevice.dao.PtEcTermStatusDao;
import com.zd.school.plartform.basedevice.service.PtEcTermStatusService;

@Service
@Transactional
public class PtEcTermStatusServiceImpl extends BaseServiceImpl<DkTermStatus> implements PtEcTermStatusService{

    @Resource
    public void setPtEcTermStatusDao(DkTermStatusDao dao) {
        this.dao = dao;
    }

}