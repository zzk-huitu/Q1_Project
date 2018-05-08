package com.yc.q1.service.base.xf.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.xf.XfXeTermSet;
import com.yc.q1.service.base.xf.XfXeTermSetService;
@Service
@Transactional
public class XfXeTermSetServiceImpl extends BaseServiceImpl<XfXeTermSet> implements XfXeTermSetService{
	@Resource(name = "XfXeTermSetDao") // 将具体的dao注入进来
	public void setDao(BaseDao<XfXeTermSet> dao) {
		super.setDao(dao);
	}
}
