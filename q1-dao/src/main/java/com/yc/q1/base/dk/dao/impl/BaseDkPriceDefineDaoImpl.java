package com.yc.q1.base.dk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.dk.dao.BaseDkPriceDefineDao;
import com.yc.q1.base.dk.model.DkPriceDefine;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 电控费率定义
 * @author hucy
 *
 */
@Repository
public class BaseDkPriceDefineDaoImpl extends BaseDaoImpl<DkPriceDefine> implements BaseDkPriceDefineDao {
    public BaseDkPriceDefineDaoImpl() {
        super(DkPriceDefine.class);
        // TODO Auto-generated constructor stub
    }
}