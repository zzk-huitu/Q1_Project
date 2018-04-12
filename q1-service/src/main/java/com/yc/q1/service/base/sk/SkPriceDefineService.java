package com.yc.q1.service.base.sk;

import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.model.base.sk.SkPriceDefine;
import com.zd.core.service.BaseService;


/**
 * 水控费率定义
 * @author hucy
 *
 */
public interface SkPriceDefineService extends BaseService<SkPriceDefine> {

	public SkPriceDefine doAddEntity(SkPriceDefine entity, PtUser currentUser);
	public SkPriceDefine doUpdateEntity(SkPriceDefine entity, PtUser currentUser);
	
}