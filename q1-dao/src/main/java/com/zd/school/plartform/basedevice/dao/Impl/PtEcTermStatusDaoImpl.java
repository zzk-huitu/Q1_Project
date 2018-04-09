package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.DkTermStatus;
import com.zd.school.plartform.basedevice.dao.PtEcTermStatusDao;


@Repository
public class PtEcTermStatusDaoImpl extends BaseDaoImpl<DkTermStatus> implements PtEcTermStatusDao {
    public PtEcTermStatusDaoImpl() {
        super(DkTermStatus.class);
        // TODO Auto-generated constructor stub
    }
}