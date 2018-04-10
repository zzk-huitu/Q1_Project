package com.yc.q1.base.dk.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.dk.model.DkTermStatus;
import com.yc.q1.base.dk.service.DkTermStatusService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class DkTermStatusServiceImpl extends BaseServiceImpl<DkTermStatus> implements DkTermStatusService {

	@Resource(name = "DkTermStatusDao") // 将具体的dao注入进来
	public void setDao(BaseDao<DkTermStatus> dao) {
		super.setDao(dao);
	}

}