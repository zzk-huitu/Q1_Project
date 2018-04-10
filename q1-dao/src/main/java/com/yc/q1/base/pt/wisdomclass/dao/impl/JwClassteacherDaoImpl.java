package com.yc.q1.base.pt.wisdomclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.wisdomclass.dao.JwClassteacherDao;
import com.yc.q1.base.pt.wisdomclass.model.ClassTeacher;
import com.zd.core.dao.BaseDaoImpl;


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