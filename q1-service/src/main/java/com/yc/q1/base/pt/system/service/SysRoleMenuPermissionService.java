
package com.yc.q1.base.pt.system.service;

import java.util.List;

import com.yc.q1.base.pt.system.model.Permission;
import com.yc.q1.base.pt.system.model.RoleMenuPermission;
import com.zd.core.service.BaseService;


public interface SysRoleMenuPermissionService extends BaseService<RoleMenuPermission> {

	boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers);

	void removeRoleMenuPermission(String roleId, List<Permission> cancelPerimission);

}