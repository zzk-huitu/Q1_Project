package com.zd.school.plartform.basedevice.dao.Impl;



import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.build.define.model.FrontServer;
import com.zd.school.plartform.basedevice.dao.BaseFrontServerDao;


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