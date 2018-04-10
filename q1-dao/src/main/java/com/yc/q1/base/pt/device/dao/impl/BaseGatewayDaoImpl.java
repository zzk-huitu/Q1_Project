package com.yc.q1.base.pt.device.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.BaseGatewayDao;
import com.yc.q1.base.pt.device.model.Gateway;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 网关表
 * @author hucy
 *
 */
@Repository
public class BaseGatewayDaoImpl extends BaseDaoImpl<Gateway> implements BaseGatewayDao{
	
	public BaseGatewayDaoImpl() {
		super(Gateway.class);
	}

}
