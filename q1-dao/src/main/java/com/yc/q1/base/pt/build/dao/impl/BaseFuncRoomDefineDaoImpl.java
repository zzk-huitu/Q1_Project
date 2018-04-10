package com.yc.q1.base.pt.build.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.build.dao.BaseFuncRoomDefineDao;
import com.yc.q1.base.pt.build.model.FuncRoomDefine;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BuildFuncroomdefinDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: BUILD_T_FUNCROOMDEFIN实体Dao接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseFuncRoomDefineDaoImpl extends BaseDaoImpl<FuncRoomDefine> implements BaseFuncRoomDefineDao {
    public BaseFuncRoomDefineDaoImpl() {
        super(FuncRoomDefine.class);
        // TODO Auto-generated constructor stub
    }
}