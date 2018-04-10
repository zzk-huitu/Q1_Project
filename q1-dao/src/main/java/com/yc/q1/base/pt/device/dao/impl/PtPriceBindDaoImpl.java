package com.yc.q1.base.pt.device.dao.impl;
import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.PtPriceBindDao;
import com.yc.q1.base.pt.device.model.PriceBind;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
@Repository
public class PtPriceBindDaoImpl extends BaseDaoImpl<PriceBind> implements PtPriceBindDao{
	
	public PtPriceBindDaoImpl() {
		super(PriceBind.class);
	}

}
