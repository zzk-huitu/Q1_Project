package com.yc.q1.base.xf.service;

import com.yc.q1.model.base.pt.device.PtRoomBagRuleBind;
import com.zd.core.service.BaseService;

/**
 * 钱包规则绑定
 * @author hucy
 *
 */
public interface RoomBagRuleBindService extends BaseService<PtRoomBagRuleBind>{

	public void doAddRuleBind(String roomRuleId, String roomIds, String deductionUserIds,
			String deductionRoomIds, String xm);

}
