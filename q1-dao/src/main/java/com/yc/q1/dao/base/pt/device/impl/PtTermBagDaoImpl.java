package com.yc.q1.dao.base.pt.device.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.device.PtTermBagDao;
import com.yc.q1.model.base.pt.device.PtTermBag;

/**
 * 设备钱包
 * @author hucy
 *
 */
@Repository("PtTermBagDao")
public class PtTermBagDaoImpl extends BaseDaoImpl<PtTermBag> implements PtTermBagDao{
}
