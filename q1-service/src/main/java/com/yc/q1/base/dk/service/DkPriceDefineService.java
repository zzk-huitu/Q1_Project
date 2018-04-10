package com.yc.q1.base.dk.service;

import com.yc.q1.base.dk.model.DkPriceDefine;
import com.yc.q1.base.pt.system.model.User;
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