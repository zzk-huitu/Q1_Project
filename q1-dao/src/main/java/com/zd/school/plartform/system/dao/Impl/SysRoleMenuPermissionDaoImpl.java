
package com.zd.school.plartform.system.dao.Impl;

import org.springframework.stereotype.Repository;

import com.zd.core.dao.BaseDaoImpl;
import com.zd.school.plartform.system.dao.SysRoleMenuPermissionDao;
import com.zd.school.plartform.system.model.RoleMenuPermission;



@Repository
public class SysRoleMenuPermissionDaoImpl extends BaseDaoImpl<RoleMenuPermission> implements SysRoleMenuPermissionDao {
    public SysRoleMenuPermissionDaoImpl() {
        super(RoleMenuPermission.class);
        // TODO Auto-generated constructor stub
    }
}
