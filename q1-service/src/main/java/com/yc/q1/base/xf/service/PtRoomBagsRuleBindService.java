package com.yc.q1.base.xf.service;

import com.yc.q1.base.xf.model.RoomBagRuleBind;
import com.zd.core.service.BaseService;

/**
 * 钱包规则绑定
 * @author hucy
 *
 */
public interface PtRoomBagsRuleBindService extends BaseService<RoomBagRuleBind>{

	public void doAddRuleBind(String roomRuleId, String roomIds, String deductionUserIds,
			String deductionRoomIds, String xm);

}
