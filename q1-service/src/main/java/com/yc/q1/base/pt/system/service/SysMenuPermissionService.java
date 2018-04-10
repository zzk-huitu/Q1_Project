
package com.yc.q1.base.pt.system.service;

import java.util.List;

import com.yc.q1.base.pt.system.model.MenuPermission;
import com.yc.q1.base.pt.system.model.User;
import com.zd.core.service.BaseService;


public interface SysMenuPermissionService extends BaseService<MenuPermission> {

	List<MenuPermission> getRoleMenuPerlist(String roleId, String perId);

	MenuPermission doAddEntity(MenuPermission entity, User currentUser);

	MenuPermission doUpdateEntity(MenuPermission entity, User currentUser);

}