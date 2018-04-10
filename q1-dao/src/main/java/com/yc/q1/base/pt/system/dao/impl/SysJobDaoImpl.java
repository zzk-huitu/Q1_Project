package com.yc.q1.base.pt.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.system.dao.SysJobDao;
import com.yc.q1.base.pt.system.model.Job;
import com.zd.core.dao.BaseDaoImpl;

/**
 * 
 * ClassName: BizTJobDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 岗位信息实体Dao接口实现类.
 * date: 2016-05-16
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class SysJobDaoImpl extends BaseDaoImpl<Job> implements SysJobDao {
    public SysJobDaoImpl() {
        super(Job.class);
        // TODO Auto-generated constructor stub
    }
}