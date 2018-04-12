package com.yc.q1.service.base.pt.device;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.device.PtRoomBagRuleBind;

/**
 * 钱包规则绑定
 * @author hucy
 *
 */
public interface PtRoomBagRuleBindService extends BaseService<PtRoomBagRuleBind>{

	public void doAddRuleBind(String roomRuleId, String roomIds, String deductionUserIds,
			String deductionRoomIds, String xm);

}
