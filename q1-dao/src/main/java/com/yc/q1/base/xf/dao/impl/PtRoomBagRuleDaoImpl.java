package com.yc.q1.base.xf.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.xf.dao.PtRoomBagRuleDao;
import com.yc.q1.base.xf.model.RoomBagRule;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 房间钱包规则
 * @author hucy
 *
 */
@Repository
public class PtRoomBagRuleDaoImpl extends BaseDaoImpl<RoomBagRule> implements PtRoomBagRuleDao{
	
	public PtRoomBagRuleDaoImpl() {
		super(RoomBagRule.class);
	}

}
