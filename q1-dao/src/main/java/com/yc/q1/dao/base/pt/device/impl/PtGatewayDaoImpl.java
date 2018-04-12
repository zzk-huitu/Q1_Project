package com.yc.q1.dao.base.pt.device.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.device.PtGatewayDao;
import com.yc.q1.model.base.pt.device.PtGateway;

/**
 * 网关表
 * @author hucy
 *
 */
@Repository("PtGatewayDao")
public class PtGatewayDaoImpl extends BaseDaoImpl<PtGateway> implements PtGatewayDao{
}
