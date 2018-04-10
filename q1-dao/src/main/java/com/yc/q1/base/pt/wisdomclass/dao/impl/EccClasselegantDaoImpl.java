package com.yc.q1.base.pt.wisdomclass.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.wisdomclass.dao.EccClasselegantDao;
import com.yc.q1.base.pt.wisdomclass.model.ClassMien;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: EccClassstarDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班级评星信息(ECC_T_CLASSSTAR)实体Dao接口实现类.
 * date: 2016-12-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class EccClasselegantDaoImpl extends BaseDaoImpl<ClassMien> implements EccClasselegantDao {
    public EccClasselegantDaoImpl() {
        super(ClassMien.class);
        // TODO Auto-generated constructor stub
    }
}