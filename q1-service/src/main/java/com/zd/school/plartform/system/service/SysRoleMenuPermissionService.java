
package com.zd.school.plartform.system.service;

import java.util.List;

import com.zd.core.service.BaseService;
import com.zd.school.plartform.system.model.Permission;
import com.zd.school.plartform.system.model.RoleMenuPermission;


public interface SysRoleMenuPermissionService extends BaseService<RoleMenuPermission> {

	boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers);

	void removeRoleMenuPermission(String roleId, List<Permission> cancelPerimission);

}