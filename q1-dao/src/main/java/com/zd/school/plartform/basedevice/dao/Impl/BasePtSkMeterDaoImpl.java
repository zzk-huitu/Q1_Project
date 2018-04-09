package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.SkMeter;
import com.zd.school.plartform.basedevice.dao.BasePtSkMeterDao;

/**
 * 水控流量计表
 * @author hucy
 *
 */
@Repository
public class BasePtSkMeterDaoImpl extends BaseDaoImpl<SkMeter> implements BasePtSkMeterDao{
	
	public BasePtSkMeterDaoImpl() {
		super(SkMeter.class);
	}


}
