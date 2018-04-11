package com.yc.q1.base.sk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.base.sk.model.SkTermStatus;
import com.yc.q1.base.sk.service.SkTermStatusService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

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