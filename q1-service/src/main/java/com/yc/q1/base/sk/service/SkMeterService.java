package com.yc.q1.base.sk.service;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.sk.model.SkMeter;
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
