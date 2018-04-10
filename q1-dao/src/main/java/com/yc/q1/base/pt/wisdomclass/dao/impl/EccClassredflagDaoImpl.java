package com.yc.q1.base.pt.wisdomclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.wisdomclass.dao.EccClassredflagDao;
import com.yc.q1.base.pt.wisdomclass.model.ClassRedFlag;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: EccClassregflagDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级流动红旗(ECC_T_CLASSREGFLAG)实体Dao接口实现类.
 * date: 2016-12-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class EccClassredflagDaoImpl extends BaseDaoImpl<ClassRedFlag> implements EccClassredflagDao {
    public EccClassredflagDaoImpl() {
        super(ClassRedFlag.class);
        // TODO Auto-generated constructor stub
    }
}