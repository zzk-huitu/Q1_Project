package com.yc.q1.service.base.sk;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkMeter;

/**
 * 水控流量计表
 * @author hucy
 *
 */
public interface SkMeterService extends BaseService<SkMeter>{
	public SkMeter doAddEntity(SkMeter entity, PtUser currentUser);
	public SkMeter doUpdateEntity(SkMeter entity, PtUser currentUser);
}
