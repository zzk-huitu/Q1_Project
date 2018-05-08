package com.yc.q1.service.base.xf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.xf.XfXcTermSet;
import com.yc.q1.service.base.xf.XfXcTermSetService;
@Service
@Transactional
public class XfXcTermSetServiceImpl extends BaseServiceImpl<XfXcTermSet> implements XfXcTermSetService {
	@Resource(name = "XfXcTermSetDao") // 将具体的dao注入进来
	public void setDao(BaseDao<XfXcTermSet> dao) {
		super.setDao(dao);
	}
}
