package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.SkMeterBind;
import com.zd.school.plartform.basedevice.dao.PtSkMeterbindDao;

/**
 * 水控流量记表绑定
 
 *
 */
@Repository
public class PtSkMeterbindDaoImpl extends BaseDaoImpl<SkMeterBind> implements PtSkMeterbindDao{
	
	public PtSkMeterbindDaoImpl() {
		super(SkMeterBind.class);
	}

}
