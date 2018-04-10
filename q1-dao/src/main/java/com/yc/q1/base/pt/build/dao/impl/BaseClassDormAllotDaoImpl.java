package com.yc.q1.base.pt.build.dao.impl;


import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.build.dao.BaseClassDormAllotDao;
import com.yc.q1.base.pt.build.model.ClassDormAllot;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: JwClassdormDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级宿舍(JW_T_CLASSDORM)实体Dao接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseClassDormAllotDaoImpl extends BaseDaoImpl<ClassDormAllot> implements BaseClassDormAllotDao {
    public BaseClassDormAllotDaoImpl() {
        super(ClassDormAllot.class);
        // TODO Auto-generated constructor stub
    }
}