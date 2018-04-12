package com.yc.q1.service.base.sk;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.sk.SkMeterBind;

/**
 * 水控流量记表绑定
 *
 *
 */
public interface SkMeterBindService extends BaseService<SkMeterBind>{
	void doMeterBind(String[] termId, String[] termSn, String meterId,String xm);
}
