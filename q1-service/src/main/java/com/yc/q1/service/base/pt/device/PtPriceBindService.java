package com.yc.q1.service.base.pt.device;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.device.PtPriceBind;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
public interface PtPriceBindService extends BaseService<PtPriceBind>{

	void doPriceBind(String[] termId, String[] termSn, String meterId,String xm);

}
