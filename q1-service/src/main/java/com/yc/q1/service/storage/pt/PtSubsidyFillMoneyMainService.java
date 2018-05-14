package com.yc.q1.service.storage.pt;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.storage.pt.PtSubsidyFillMoneyMain;

public interface PtSubsidyFillMoneyMainService extends BaseService<PtSubsidyFillMoneyMain> {
	public PtSubsidyFillMoneyMain doAddEntity(PtSubsidyFillMoneyMain entity,PtUser currentUser,String ids);
	public PtSubsidyFillMoneyMain doUpdateEntity(PtSubsidyFillMoneyMain entity, PtUser currentUser,String ids);
}
