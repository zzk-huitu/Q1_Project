package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.JwFuncroomcourseDao;
import com.yc.q1.base.pt.basic.model.FuncRoomCourse;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: JwFuncroomcourseDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 排课课程表(JW_T_FUNCROOMCOURSE)实体Dao接口实现类.
 * date: 2017-03-06
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class JwFuncroomcourseDaoImpl extends BaseDaoImpl<FuncRoomCourse> implements JwFuncroomcourseDao {
    public JwFuncroomcourseDaoImpl() {
        super(FuncRoomCourse.class);
        // TODO Auto-generated constructor stub
    }
}