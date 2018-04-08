package com.zd.school.plartform.basedevice.dao.Impl;
import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.PriceBind;
import com.zd.school.plartform.basedevice.dao.PtPriceBindDao;

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
