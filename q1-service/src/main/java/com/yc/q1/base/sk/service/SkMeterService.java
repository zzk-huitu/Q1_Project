package com.yc.q1.base.sk.service;

import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.model.base.sk.SkMeter;
import com.zd.core.service.BaseService;

/**
 * 水控流量计表
 * @author hucy
 *
 */
public interface SkMeterService extends BaseService<SkMeter>{
	public SkMeter doAddEntity(SkMeter entity, User currentUser);
	public SkMeter doUpdateEntity(SkMeter entity, User currentUser);
}
