
package com.yc.q1.service.base.pt.system;

import java.util.List;

import com.yc.q1.model.base.pt.system.PtPermission;
import com.yc.q1.model.base.pt.system.PtRoleMenuPermission;
import com.zd.core.service.BaseService;


public interface PtRoleMenuPermissionService extends BaseService<PtRoleMenuPermission> {

	boolean doSetRoleMenuPermission(String roleId, String perId, String roleMenuPers);

	void removeRoleMenuPermission(String roleId, List<PtPermission> cancelPerimission);

}