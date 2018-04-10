package com.yc.q1.base.pt.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.system.dao.SysPerimissonDao;
import com.yc.q1.base.pt.system.model.Permission;
import com.zd.core.dao.BaseDaoImpl;


/**
 * 
 * ClassName: BaseTPerimissonDaoImpl
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 权限表实体Dao接口实现类.
 * date: 2016-07-17
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Repository
public class SysPerimissonDaoImpl extends BaseDaoImpl<Permission> implements SysPerimissonDao {
    public SysPerimissonDaoImpl() {
        super(Permission.class);
        // TODO Auto-generated constructor stub
    }
}