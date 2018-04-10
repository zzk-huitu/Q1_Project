package com.yc.q1.base.xf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.xf.model.RoomBagRule;
import com.yc.q1.base.xf.service.RoomBagRuleService;
import com.zd.core.dao.BaseDao;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class RoomBagRuleServiceImpl extends BaseServiceImpl<RoomBagRule> implements RoomBagRuleService{
	
	@Resource(name="RoomBagRuleDao")	//将具体的dao注入进来
	public void setDao(BaseDao<RoomBagRule> dao) {
		super.setDao(dao);
	}
}
