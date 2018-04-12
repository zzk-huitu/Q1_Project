package com.yc.q1.dao.base.pt.device.impl;
import org.springframework.stereotype.Repository;

import com.yc.q1.dao.base.pt.device.PriceBindDao;
import com.yc.q1.model.base.pt.device.PtPriceBind;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
@Repository
public class PriceBindDaoImpl extends BaseDaoImpl<PtPriceBind> implements PriceBindDao{
}
