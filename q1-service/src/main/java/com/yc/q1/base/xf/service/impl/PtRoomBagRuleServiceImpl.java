package com.yc.q1.base.xf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.base.xf.dao.RoomBagRuleDao;
import com.yc.q1.base.xf.model.RoomBagRule;
import com.yc.q1.base.xf.service.PtRoomBagRuleService;
import com.zd.core.service.BaseServiceImpl;

@Service
@Transactional
public class PtRoomBagRuleServiceImpl extends BaseServiceImpl<RoomBagRule> implements PtRoomBagRuleService{
	
	@Resource
    public void setPtRoomBagRuleDao(RoomBagRuleDao dao) {
        this.dao = dao;
    }
}
