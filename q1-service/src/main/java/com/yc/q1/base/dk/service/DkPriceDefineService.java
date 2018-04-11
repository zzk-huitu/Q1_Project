package com.yc.q1.base.dk.service;

import com.yc.q1.model.base.dk.DkPriceDefine;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


/**
 * 电控费率定义
 * @author hucy
 *
 */
public interface DkPriceDefineService extends BaseService<DkPriceDefine> {
	public DkPriceDefine doAddEntity(DkPriceDefine entity, User currentUser);
	public DkPriceDefine doUpdateEntity(DkPriceDefine entity, User currentUser);
}