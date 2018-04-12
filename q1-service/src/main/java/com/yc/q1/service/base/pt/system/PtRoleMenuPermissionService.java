
package com.yc.q1.service.base.pt.system;

import java.util.List;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtPermission;
import com.yc.q1.model.base.pt.system.PtRoleMenuPermission;


public interface PtRoleMenuPermissionService extends BaseService<PtRoleMenuPermission> {

	boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers);

	void removeRoleMenuPermission(String roleId, List<PtPermission> cancelPerimission);

}