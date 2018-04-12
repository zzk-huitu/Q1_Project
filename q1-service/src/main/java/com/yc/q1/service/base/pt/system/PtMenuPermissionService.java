
package com.yc.q1.service.base.pt.system;

import java.util.List;

import com.yc.q1.core.service.BaseService;
import com.yc.q1.model.base.pt.system.PtMenuPermission;
import com.yc.q1.model.base.pt.system.PtUser;


public interface PtMenuPermissionService extends BaseService<PtMenuPermission> {

	List<PtMenuPermission> getRoleMenuPerlist(String roleId, String perId);

	PtMenuPermission doAddEntity(PtMenuPermission entity, PtUser currentUser);

	PtMenuPermission doUpdateEntity(PtMenuPermission entity, PtUser currentUser);

}