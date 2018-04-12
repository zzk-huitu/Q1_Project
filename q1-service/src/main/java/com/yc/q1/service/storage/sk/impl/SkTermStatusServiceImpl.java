package com.yc.q1.service.storage.sk.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.sk.SkTermStatus;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.sk.SkTermStatusService;

@Service
@Transactional
public class SkTermStatusServiceImpl extends BaseServiceImpl<SkTermStatus> implements SkTermStatusService{

	@Resource(name="SkTermStatusDao")	//将具体的dao注入进来
	public void setDao(BaseDao<SkTermStatus> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;

//	@Override
//	public BaseDao<SkTermStatus> getDao() {
//		// TODO Auto-generated method stub
//		return  this.dao;
//	}

}