package com.yc.q1.dao.base.pt.device.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.dao.base.pt.device.GatewayDao;
import com.yc.q1.model.base.pt.device.PtGateway;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 网关表
 * @author hucy
 *
 */
@Repository
public class GatewayDaoImpl extends BaseDaoImpl<PtGateway> implements GatewayDao{
}
