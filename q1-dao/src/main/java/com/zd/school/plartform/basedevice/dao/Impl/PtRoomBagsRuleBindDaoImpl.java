package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.RoomBagRuleBind;
import com.zd.school.plartform.basedevice.dao.PtRoomBagsRuleBindDao;

/**
 * 钱包规则绑定
 * @author hucy
 *
 */
@Repository
public class PtRoomBagsRuleBindDaoImpl extends BaseDaoImpl<RoomBagRuleBind> implements PtRoomBagsRuleBindDao{
	
	public PtRoomBagsRuleBindDaoImpl() {
		super(RoomBagRuleBind.class);
	}

}
