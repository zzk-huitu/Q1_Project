package com.yc.q1.base.pt.device.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.PtTermBagsDao;
import com.yc.q1.base.pt.device.model.TermBag;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 设备钱包
 * @author hucy
 *
 */
@Repository
public class PtTermBagsDaoImpl extends BaseDaoImpl<TermBag> implements PtTermBagsDao{
	
	public PtTermBagsDaoImpl() {
		super(TermBag.class);
	}

}
