package com.yc.q1.base.sk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.sk.dao.BasePtSkMeterDao;
import com.yc.q1.base.sk.model.SkMeter;
import com.zd.core.dao.BaseDaoImpl;

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
