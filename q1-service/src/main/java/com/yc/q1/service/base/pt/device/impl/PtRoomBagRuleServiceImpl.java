package com.yc.q1.service.base.pt.device.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.model.base.pt.device.PtRoomBagRule;
import com.yc.q1.service.base.pt.device.PtRoomBagRuleService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

@Service
@Transactional
public class PtRoomBagRuleServiceImpl extends BaseServiceImpl<PtRoomBagRule> implements PtRoomBagRuleService{
	
	@Resource(name="ptRoomBagRuleDao")	//将具体的dao注入进来
	public void setDao(BaseDao<PtRoomBagRule> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
}
