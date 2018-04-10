package com.yc.q1.base.pt.build.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.build.dao.BaseDormDefineDao;
import com.yc.q1.base.pt.build.model.DormDefine;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BuildOfficeDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 宿舍定义接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseDormDefineDaoImpl extends BaseDaoImpl<DormDefine> implements BaseDormDefineDao {
    public BaseDormDefineDaoImpl() {
        super(DormDefine.class);
        // TODO Auto-generated constructor stub
    }
}