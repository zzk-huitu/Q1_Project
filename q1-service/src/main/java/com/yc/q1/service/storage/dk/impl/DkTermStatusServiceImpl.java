package com.yc.q1.service.storage.dk.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.dk.DkTermStatus;
import com.yc.q1.service.storage.dk.DkTermStatusService;

@Service
@Transactional
public class DkTermStatusServiceImpl extends BaseServiceImpl<DkTermStatus> implements DkTermStatusService {

	@Resource(name = "DkTermStatusDao") // 将具体的dao注入进来
	public void setDao(BaseDao<DkTermStatus> dao) {
		super.setDao(dao);
	}

}