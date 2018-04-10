package com.yc.q1.base.sk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.sk.dao.SkTermStatusDao;
import com.yc.q1.base.sk.model.SkTermStatus;
import com.yc.q1.base.sk.service.PtSkTermStatusService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class PtSkTermStatusServiceImpl extends BaseServiceImpl<SkTermStatus> implements PtSkTermStatusService{

    @Resource
    public void setPtSkTermStatusDao(SkTermStatusDao dao) {
        this.dao = dao;
    }

	@Override
	public BaseDao<SkTermStatus> getDao() {
		// TODO Auto-generated method stub
		return  this.dao;
	}

}