package com.yc.q1.base.pt.device.dao.impl;



import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.device.dao.BaseFrontServerDao;
import com.yc.q1.base.pt.device.model.FrontServer;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 综合前置服务器管理
 * @author hucy
 *
 */
@Repository
public class BaseFrontServerDaoImpl extends BaseDaoImpl<FrontServer> implements BaseFrontServerDao {
    public BaseFrontServerDaoImpl() {
        super(FrontServer.class);
        // TODO Auto-generated constructor stub
    }
}