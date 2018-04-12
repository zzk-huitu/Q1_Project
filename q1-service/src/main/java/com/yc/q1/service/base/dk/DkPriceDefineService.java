package com.yc.q1.service.base.dk;

import com.yc.q1.model.base.dk.DkPriceDefine;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.service.BaseService;


/**
 * 电控费率定义
 * @author hucy
 *
 */
public interface DkPriceDefineService extends BaseService<DkPriceDefine> {
	public DkPriceDefine doAddEntity(DkPriceDefine entity, PtUser currentUser);
	public DkPriceDefine doUpdateEntity(DkPriceDefine entity, PtUser currentUser);
}