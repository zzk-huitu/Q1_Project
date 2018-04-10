package com.yc.q1.base.pt.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.system.dao.SysDeptjobDao;
import com.yc.q1.base.pt.system.model.DeptJob;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BaseDeptjobDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 部门岗位信息(BASE_T_DEPTJOB)实体Dao接口实现类.
 * date: 2017-03-27
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class SysDeptjobDaoImpl extends BaseDaoImpl<DeptJob> implements SysDeptjobDao {
    public SysDeptjobDaoImpl() {
        super(DeptJob.class);
        // TODO Auto-generated constructor stub
    }
}