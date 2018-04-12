package com.yc.q1.service.base.pt.device;

import com.yc.q1.model.base.pt.device.PtPriceBind;
import com.zd.core.service.BaseService;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
public interface PriceBindService extends BaseService<PtPriceBind>{

	void doPriceBind(String[] termId, String[] termSn, String meterId,String xm);

}
