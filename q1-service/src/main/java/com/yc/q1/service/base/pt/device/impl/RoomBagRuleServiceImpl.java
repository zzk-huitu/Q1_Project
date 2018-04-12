package com.yc.q1.service.base.pt.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.model.base.pt.device.PtRoomBagRule;
import com.yc.q1.service.base.pt.device.RoomBagRuleService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class RoomBagRuleServiceImpl extends BaseServiceImpl<PtRoomBagRule> implements RoomBagRuleService{
	
	@Resource(name="RoomBagRuleDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtRoomBagRule> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
