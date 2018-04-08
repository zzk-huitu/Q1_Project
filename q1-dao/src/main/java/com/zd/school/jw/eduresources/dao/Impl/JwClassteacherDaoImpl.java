package com.zd.school.jw.eduresources.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.jw.eduresources.dao.JwClassteacherDao ;
import com.zd.school.jw.eduresources.model.ClassTeacher ;


/**
 * 
 * ClassName: JwClassteacherDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班主任信息实体Dao接口实现类.
 * date: 2016-08-22
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class JwClassteacherDaoImpl extends BaseDaoImpl<ClassTeacher> implements JwClassteacherDao {
    public JwClassteacherDaoImpl() {
        super(ClassTeacher.class);
        // TODO Auto-generated constructor stub
    }
}