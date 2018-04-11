
package com.yc.q1.base.pt.system.service;

import java.util.List;

import com.yc.q1.model.base.pt.system.PtMenuPermission;
import com.yc.q1.model.base.pt.system.PtUser;
import com.zd.core.service.BaseService;


public interface MenuPermissionService extends BaseService<PtMenuPermission> {

	List<PtMenuPermission> getRoleMenuPerlist(String roleId, String perId);

	PtMenuPermission doAddEntity(PtMenuPermission entity, PtUser currentUser);

	PtMenuPermission doUpdateEntity(PtMenuPermission entity, PtUser currentUser);

}