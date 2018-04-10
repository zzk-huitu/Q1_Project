package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.StuParentsDao;
import com.yc.q1.base.pt.basic.model.StudentParents;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: StuParentsDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学生家长信息实体Dao接口实现类.
 * date: 2016-08-05
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class StuParentsDaoImpl extends BaseDaoImpl<StudentParents> implements StuParentsDao {
    public StuParentsDaoImpl() {
        super(StudentParents.class);
        // TODO Auto-generated constructor stub
    }
}