package com.yc.q1.base.pt.device.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.BasePtTermDao;
import com.yc.q1.base.pt.device.model.Term;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 设备表
 * @author hucy
 *
 */
@Repository
public class BasePtTermDaoImpl extends BaseDaoImpl<Term> implements BasePtTermDao{
	
	public BasePtTermDaoImpl() {
		super(Term.class);
	}

}
