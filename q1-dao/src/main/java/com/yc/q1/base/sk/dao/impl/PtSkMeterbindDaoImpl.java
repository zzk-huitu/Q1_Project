package com.yc.q1.base.sk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.sk.dao.PtSkMeterbindDao;
import com.yc.q1.base.sk.model.SkMeterBind;
import com.zd.core.dao.BaseDaoImpl;

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
