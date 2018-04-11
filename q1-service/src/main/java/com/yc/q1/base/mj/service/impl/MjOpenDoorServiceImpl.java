package com.yc.q1.base.mj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.mj.service.MjOpenDoorService;
import com.yc.q1.model.storage.mj.MjOpenDoor;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class MjOpenDoorServiceImpl extends BaseServiceImpl<MjOpenDoor> implements MjOpenDoorService{

	@Resource(name = "MjOpenDoorDao") // 将具体的dao注入进来
	public void setDao(BaseDao<MjOpenDoor> dao) {
		super.setDao(dao);
	}
}