package com.yc.q1.service.base.pt.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.device.PtRoomBag;
import com.yc.q1.service.base.pt.device.PtRoomBagService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

/**
 * 宿舍钱包
 * @author hucy
 *
 */
@Service
@Transactional
public class PtRoomBagServiceImpl extends BaseServiceImpl<PtRoomBag> implements PtRoomBagService{
	
	@Resource(name="PtRoomBagDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtRoomBag> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
