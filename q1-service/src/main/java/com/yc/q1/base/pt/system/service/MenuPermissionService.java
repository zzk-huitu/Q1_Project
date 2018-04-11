
package com.yc.q1.base.pt.system.service;

import java.util.List;

import com.yc.q1.model.base.pt.system.MenuPermission;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.service.BaseService;


public interface MenuPermissionService extends BaseService<MenuPermission> {

	List<MenuPermission> getRoleMenuPerlist(String roleId, String perId);

	MenuPermission doAddEntity(MenuPermission entity, User currentUser);

	MenuPermission doUpdateEntity(MenuPermission entity, User currentUser);

}