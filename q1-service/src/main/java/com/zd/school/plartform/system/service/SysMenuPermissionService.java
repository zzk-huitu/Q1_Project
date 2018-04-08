
package com.zd.school.plartform.system.service;

import java.util.List;

import com.zd.core.service.BaseService;
import com.zd.school.plartform.system.model.MenuPermission;
import com.zd.school.plartform.system.model.User;


public interface SysMenuPermissionService extends BaseService<MenuPermission> {

	List<MenuPermission> getRoleMenuPerlist(String roleId, String perId);

	MenuPermission doAddEntity(MenuPermission entity, User currentUser);

	MenuPermission doUpdateEntity(MenuPermission entity, User currentUser);

}