package com.yc.q1.base.pt.build.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.build.dao.BaseOfficeAllotDao;
import com.yc.q1.base.pt.build.model.OfficeAllot;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: JwOfficeallotDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: JW_T_OFFICEALLOT实体Dao接口实现类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class BaseOfficeAllotDaoImpl extends BaseDaoImpl<OfficeAllot> implements BaseOfficeAllotDao{
    public BaseOfficeAllotDaoImpl() {
        super(OfficeAllot.class);
        // TODO Auto-generated constructor stub
    }
}