package com.yc.q1.base.mj.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.mj.dao.MjUserrightDao;
import com.yc.q1.base.mj.model.MjUserRight;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: MjUserrightDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 门禁权限表(MJ_UserRight)实体Dao接口实现类.
 * date: 2016-09-08
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class MjUserrightDaoImpl extends BaseDaoImpl<MjUserRight> implements MjUserrightDao {
    public MjUserrightDaoImpl() {
        super(MjUserRight.class);
        // TODO Auto-generated constructor stub
    }
}