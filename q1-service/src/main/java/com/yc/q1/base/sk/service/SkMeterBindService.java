package com.yc.q1.base.sk.service;

import com.yc.q1.base.sk.model.SkMeterBind;
import com.zd.core.service.BaseService;

/**
 * 水控流量记表绑定
 *
 *
 */
public interface SkMeterBindService extends BaseService<SkMeterBind>{
	void doMeterBind(String[] termId, String[] termSn, String meterId,String xm);
}
