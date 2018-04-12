package com.yc.q1.service.base.sk;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkPriceDefine;


/**
 * 水控费率定义
 * @author hucy
 *
 */
public interface SkPriceDefineService extends BaseService<SkPriceDefine> {

	public SkPriceDefine doAddEntity(SkPriceDefine entity, PtUser currentUser);
	public SkPriceDefine doUpdateEntity(SkPriceDefine entity, PtUser currentUser);
	
}