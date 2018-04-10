package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.TeaTeacherbaseDao;
import com.yc.q1.base.pt.basic.model.TeacherBaseInfo;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: TeaTeacherbaseDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 教职工基本数据实体Dao接口实现类.
 * date: 2016-07-19
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class TeaTeacherbaseDaoImpl extends BaseDaoImpl<TeacherBaseInfo> implements TeaTeacherbaseDao {
    public TeaTeacherbaseDaoImpl() {
        super(TeacherBaseInfo.class);
        // TODO Auto-generated constructor stub
    }
}