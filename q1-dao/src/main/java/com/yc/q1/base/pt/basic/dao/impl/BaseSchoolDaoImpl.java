package com.yc.q1.base.pt.basic.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.basic.dao.BaseSchoolDao;
import com.yc.q1.base.pt.basic.model.School;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BaseSchoolDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学校信息实体Dao接口实现类.
 * date: 2016-08-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseSchoolDaoImpl extends BaseDaoImpl<School> implements BaseSchoolDao {
    public BaseSchoolDaoImpl() {
        super(School.class);
        // TODO Auto-generated constructor stub
    }
}