package com.yc.q1.base.pt.wisdomclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.wisdomclass.dao.AttTermDao;
import com.yc.q1.base.pt.wisdomclass.model.AttendTerm;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: AttTermDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤机具(ATT_T_TERM)实体Dao接口实现类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class AttTermDaoImpl extends BaseDaoImpl<AttendTerm> implements AttTermDao {
    public AttTermDaoImpl() {
        super(AttendTerm.class);
        // TODO Auto-generated constructor stub
    }
}