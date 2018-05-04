package com.yc.q1.service.base.xf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.xf.XfRateTermSet;
import com.yc.q1.service.base.xf.XfRateTermSetService;
@Service
@Transactional
public class XfRateTermSetServiceImpl extends BaseServiceImpl<XfRateTermSet> implements XfRateTermSetService {
	@Resource(name = "XfRateTermSetDao") // 将具体的dao注入进来
	public void setDao(BaseDao<XfRateTermSet> dao) {
		super.setDao(dao);
	}
}
