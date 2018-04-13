package com.yc.q1.dao.base.pt.device.impl;
import org.springframework.stereotype.Repository;

import com.yc.q1.core.dao.BaseDaoImpl;
import com.yc.q1.dao.base.pt.device.PtPriceBindDao;
import com.yc.q1.model.base.pt.device.PtPriceBind;

/**
 * 水控、电控费率绑定表
 * @author hucy
 *
 */
@Repository("PtPriceBindDao")
public class PtPriceBindDaoImpl extends BaseDaoImpl<PtPriceBind> implements PtPriceBindDao{
}