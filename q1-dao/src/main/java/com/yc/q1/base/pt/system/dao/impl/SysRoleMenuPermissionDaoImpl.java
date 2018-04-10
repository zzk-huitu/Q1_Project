
package com.yc.q1.base.pt.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yc.q1.base.pt.system.dao.SysRoleMenuPermissionDao;
import com.yc.q1.base.pt.system.model.RoleMenuPermission;
import com.zd.core.dao.BaseDaoImpl;



@Repository
public class SysRoleMenuPermissionDaoImpl extends BaseDaoImpl<RoleMenuPermission> implements SysRoleMenuPermissionDao {
    public SysRoleMenuPermissionDaoImpl() {
        super(RoleMenuPermission.class);
        // TODO Auto-generated constructor stub
    }
}
