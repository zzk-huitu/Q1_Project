package com.zd.school.wisdomclass.ecc.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.jw.ecc.model.ClassParam;
import com.zd.school.wisdomclass.ecc.dao.EccClassparamDao;


/**
 * 
 * ClassName: EccClassparamDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 班牌参数设置表(ECC_T_CLASSPARAM)实体Dao接口实现类.
 * date: 2016-11-28
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class EccClassparamDaoImpl extends BaseDaoImpl<ClassParam> implements EccClassparamDao {
    public EccClassparamDaoImpl() {
        super(ClassParam.class);
        // TODO Auto-generated constructor stub
    }
}