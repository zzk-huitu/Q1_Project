
package com.yc.q1.base.pt.system.service;

import java.util.List;

import com.yc.q1.model.base.pt.system.Permission;
import com.yc.q1.model.base.pt.system.RoleMenuPermission;
import com.zd.core.service.BaseService;


public interface RoleMenuPermissionService extends BaseService<RoleMenuPermission> {

	boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers);

	void removeRoleMenuPermission(String roleId, List<Permission> cancelPerimission);

}