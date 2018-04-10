package com.yc.q1.base.pt.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.system.dao.SysOrgDao;
import com.yc.q1.base.pt.system.model.Department;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BaseOrgDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: BASE_T_ORG实体Dao接口实现类.
 * date: 2016-07-26
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class SysOrgDaoImpl extends BaseDaoImpl<Department> implements SysOrgDao {
    public SysOrgDaoImpl() {
        super(Department.class);
        // TODO Auto-generated constructor stub
    }
}