package com.yc.q1.base.xf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.redis.service.PrimaryKeyRedisService;
import com.yc.q1.base.xf.service.RoomBagService;
import com.yc.q1.model.base.pt.device.PtRoomBag;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 宿舍钱包
 * @author hucy
 *
 */
@Service
@Transactional
public class RoomBagServiceImpl extends BaseServiceImpl<PtRoomBag> implements RoomBagService{
	
	@Resource(name="RoomBagDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtRoomBag> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
