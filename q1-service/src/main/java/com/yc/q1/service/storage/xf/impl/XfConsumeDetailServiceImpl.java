package com.yc.q1.service.storage.xf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.xf.XfConsumeDetail;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.xf.XfConsumeDetailService;
@Service
@Transactional
public class XfConsumeDetailServiceImpl extends BaseServiceImpl<XfConsumeDetail> implements XfConsumeDetailService {
	@Resource(name="XfConsumeDetailDao")	//将具体的dao注入进来
	public void setDao(BaseDao<XfConsumeDetail> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
