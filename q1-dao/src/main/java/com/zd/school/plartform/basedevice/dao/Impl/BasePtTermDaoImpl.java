package com.zd.school.plartform.basedevice.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.control.device.model.Term;
import com.zd.school.plartform.basedevice.dao.BasePtTermDao;

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
