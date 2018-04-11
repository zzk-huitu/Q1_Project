package com.yc.q1.base.pt.device.service;

import com.yc.q1.model.base.pt.device.PriceBind;
import com.zd.core.service.BaseService;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
public interface PriceBindService extends BaseService<PriceBind>{

	void doPriceBind(String[] termId, String[] termSn, String meterId,String xm);

}
