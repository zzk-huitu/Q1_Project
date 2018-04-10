package com.yc.q1.base.sk.service;

import com.yc.q1.base.pt.system.model.User;
import com.yc.q1.base.sk.model.SkPriceDefine;
import com.zd.core.service.BaseService;


/**
 * 水控费率定义
 * @author hucy
 *
 */
public interface SkPriceDefineService extends BaseService<SkPriceDefine> {

	public SkPriceDefine doAddEntity(SkPriceDefine entity, User currentUser);
	public SkPriceDefine doUpdateEntity(SkPriceDefine entity, User currentUser);
	
}