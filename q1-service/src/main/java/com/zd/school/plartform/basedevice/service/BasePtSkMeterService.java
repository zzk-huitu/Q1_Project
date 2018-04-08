package com.zd.school.plartform.basedevice.service;

import com.zd.core.service.BaseService;
import com.zd.school.control.device.model.SkMeter;
import com.zd.school.plartform.system.model.User;

/**
 * 水控流量计表
 * @author hucy
 *
 */
public interface BasePtSkMeterService extends BaseService<SkMeter>{
	public SkMeter doAddEntity(SkMeter entity, User currentUser);
	public SkMeter doUpdateEntity(SkMeter entity, User currentUser);
}
