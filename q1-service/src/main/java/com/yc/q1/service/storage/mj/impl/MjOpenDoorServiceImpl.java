package com.yc.q1.service.storage.mj.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.storage.mj.MjOpenDoor;
import com.yc.q1.service.storage.mj.MjOpenDoorService;

@Service
@Transactional
public class MjOpenDoorServiceImpl extends BaseServiceImpl<MjOpenDoor> implements MjOpenDoorService{

	@Resource(name = "mjOpenDoorDao") // 将具体的dao注入进来
	public void setDao(BaseDao<MjOpenDoor> dao) {
		super.setDao(dao);
	}
}