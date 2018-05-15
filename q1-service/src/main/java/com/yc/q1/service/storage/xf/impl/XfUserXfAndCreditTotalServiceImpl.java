package com.yc.q1.service.storage.xf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.xf.XfUserXfAndCreditTotal;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.xf.XfUserXfAndCreditTotalService;
@Service
@Transactional
public class XfUserXfAndCreditTotalServiceImpl extends BaseServiceImpl<XfUserXfAndCreditTotal> implements XfUserXfAndCreditTotalService{

	@Resource(name="XfUserXfAndCreditTotalDao")	//将具体的dao注入进来
	public void setDao(BaseDao<XfUserXfAndCreditTotal> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
