package com.yc.q1.base.pt.device.dao.impl;
import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.PriceBindDao;
import com.yc.q1.base.pt.device.model.PriceBind;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
@Repository
public class PriceBindDaoImpl extends BaseDaoImpl<PriceBind> implements PriceBindDao{
}
