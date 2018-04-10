package com.yc.q1.base.pt.build.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.BaseStudentDormDao;
import com.yc.q1.base.pt.build.model.StudentDorm;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: DormStudentdormDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: (学生分配宿舍)实体Dao接口实现类.
 * date: 2016-08-26
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseStudentDormDaoImpl extends BaseDaoImpl<StudentDorm> implements BaseStudentDormDao {
    public BaseStudentDormDaoImpl() {
        super(StudentDorm.class);
        // TODO Auto-generated constructor stub
    }
}