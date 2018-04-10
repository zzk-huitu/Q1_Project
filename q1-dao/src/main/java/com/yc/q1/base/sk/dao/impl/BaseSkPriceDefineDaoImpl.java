package com.yc.q1.base.sk.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.sk.dao.BaseSkPriceDefineDao;
import com.yc.q1.base.sk.model.SkPriceDefine;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 水控费率定义
 * @author hucy
 *
 */
@Repository
public class BaseSkPriceDefineDaoImpl extends BaseDaoImpl<SkPriceDefine> implements BaseSkPriceDefineDao {
    public BaseSkPriceDefineDaoImpl() {
        super(SkPriceDefine.class);
        // TODO Auto-generated constructor stub
    }
}