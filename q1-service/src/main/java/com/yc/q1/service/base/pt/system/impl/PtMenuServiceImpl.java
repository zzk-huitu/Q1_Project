package com.yc.q1.service.base.pt.system.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.constant.AdminType;
import com.yc.q1.core.constant.AuthorType;
import com.yc.q1.core.constant.MenuType;
import com.yc.q1.core.constant.PermType;
import com.yc.q1.core.constant.TreeVeriable;
import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.BeanUtils;
import com.yc.q1.core.util.ModelUtil;
import com.yc.q1.model.base.pt.system.PtMenu;
import com.yc.q1.model.base.pt.system.PtMenuPermission;
import com.yc.q1.model.base.pt.system.PtPermission;
import com.yc.q1.model.base.pt.system.PtRole;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.pojo.base.pt.MenuTree;
import com.yc.q1.service.base.pt.system.PtMenuPermissionService;
import com.yc.q1.service.base.pt.system.PtMenuService;
import com.yc.q1.service.base.pt.system.PtPermissionService;
import com.yc.q1.service.base.pt.system.PtRoleMenuPermissionService;
import com.yc.q1.service.base.pt.system.PtRoleService;
import com.yc.q1.service.base.pt.system.PtUserService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: BaseTMenuServiceImpl Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 系统菜单表实体Service接口实现类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtMenuServiceImpl extends BaseServiceImpl<PtMenu> implements PtMenuService {

	@Resource(name = "PtMenuDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtMenu> dao) {
		super.setDao(dao);
	}
	@Resource
    private PrimaryKeyRedisService keyRedisService;
	@Resource
	private PtRoleService sysRoleService;

	@Resource
	private PtUserService sysUserService;

	@Resource
	private PtPermissionService perimissonSevice;

	@Resource
	private PtMenuPermissionService menuPermissionService; // service层接口

	@Resource
	private PtRoleMenuPermissionService roleMenuPermissionService;

	/**
	 * 
	 * listTree:获取系统菜单的树形列表
	 *
	 * @author luoyibo
	 * @param whereSql
	 *            :查询条件
	 * @param orderSql
	 *            :排序条件
	 * @param parentSql:
	 * @param querySql
	 * @return List<SysMenuTree>
	 * @throws @since
	 *             JDK 1.8
	 */

	@Override
	public List<MenuTree> getTreeList(String whereSql, String orderSql) {

		StringBuffer hql = new StringBuffer("from PtMenu where 1=1");
		hql.append(whereSql);
		hql.append(orderSql);

		// 总记录数
		StringBuffer countHql = new StringBuffer("select count(*) from PtMenu where 1=1");
		countHql.append(whereSql);

		List<PtMenu> typeList = super.queryByHql(hql.toString());
		List<MenuTree> result = new ArrayList<MenuTree>();
		// 构建Tree数据
		recursion(new MenuTree(TreeVeriable.ROOT, new ArrayList<MenuTree>()), result, typeList);

		return result;
	}

	private void recursion(MenuTree parentNode, List<MenuTree> result, List<PtMenu> list) {
		List<PtMenu> childs = new ArrayList<PtMenu>();
		for (PtMenu SysMenu : list) {
			if (SysMenu.getParentNode().equals(parentNode.getId())) {
				childs.add(SysMenu);
			}
		}
		for (PtMenu SysMenu : childs) {
			MenuTree child = new MenuTree(SysMenu.getId(), SysMenu.getNodeText(), "", SysMenu.getLeaf(),
					SysMenu.getNodeLevel(), SysMenu.getTreeIds(), SysMenu.getParentNode(), SysMenu.getOrderIndex(),
					new ArrayList<MenuTree>(), SysMenu.getMenuCode(), SysMenu.getSmallIcon(), SysMenu.getBigIcon(),
					SysMenu.getMenuTarget(), SysMenu.getMenuType(), SysMenu.getNodeCode(),
					SysMenu.getIsSystem(), SysMenu.getIsHidden());
			if (SysMenu.getParentNode().equals(TreeVeriable.ROOT)) {
				result.add(child);
			} else {
				List<MenuTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			recursion(child, result, list);
		}

	}

	/**
	 * 获取指定对象的授权树
	 * 
	 * @param roodId
	 *            授权树的根节点
	 * @param author
	 *            授权对象ID
	 * @param authorType
	 *            授权类型
	 * @param isSee
	 *            是否可见
	 * @param expanded
	 *            是否展开
	 * @return
	 */
	@Override
	public List<MenuTree> getPermTree(String roodId, String author, String authorType, Boolean isSee,
			Boolean expanded) {
		Boolean isAdmin = false;
		String hql = "from PtMenu ";
		
		// 对于超级管理员的用户与角色，默认有所有菜单的权限
		if (authorType.equals(AuthorType.ROLE)) {
			PtRole thisRole = sysRoleService.get(author);
			if (thisRole.getRoleCode().equals(AdminType.ADMIN_ROLE_NAME))
				isAdmin = true;
		} else {
			PtUser thisUser = sysUserService.get(author);
			if (thisUser.getUserName().equals(AdminType.ADMIN_USER_NAME)){
				isAdmin = true;
			}else{				
				Iterator<PtRole> iterator=thisUser.getSysRoles().iterator();
				while(iterator.hasNext()){
					if(iterator.next().getId().equals(AdminType.ADMIN_ROLE_ID)){	//判断角色id
						isAdmin = true;
						break;
					}
				}			
			}
		}
		// 除了超级管理员用户，其它的只能看未隐藏的菜单
		if (isAdmin == false)
			hql += " where isHidden='0'";
		hql += " order by parentNode,orderIndex asc ";
		List<PtMenu> lists = super.queryByHql(hql.toString());
		
		// 得到当前对象的权限
		Map<String, PtPermission> maps = buildPermMap(author, authorType);
		if (maps == null) {
			return null;
		}
		// 非超级管理员账户
		if (!isAdmin) {
			List<PtMenu> removeLists = new ArrayList<PtMenu>();
			for (PtMenu node : lists) {
				if (isSee) {
					if (maps.get(node.getId()) == null && !node.getId().equalsIgnoreCase(TreeVeriable.ROOT)) {
						removeLists.add(node);
					}
				} else {
				}
			}
			if (isSee) {
				for (PtMenu node : removeLists) {
					lists.remove(node);
				}
			}
		}
		List<MenuTree> result = new ArrayList<MenuTree>();
		// 构建Tree数据
		recursion(new MenuTree(TreeVeriable.ROOT, new ArrayList<MenuTree>()), result, lists);

		return result;

	}

	// 构建权限map
	private Map<String, PtPermission> buildPermMap(String author, String authorType) {
		Map<String, PtPermission> maps = new HashMap<String, PtPermission>();
		if (AuthorType.ROLE.equalsIgnoreCase(authorType)) {
			PtRole sysRole = sysRoleService.get(author);
			if (sysRole != null && sysRole.getIsDelete() == 0) {
				Set<PtPermission> perms = sysRole.getSysPermissions();
				for (PtPermission perm : perms) {
					maps.put(perm.getPermissionCode(), perm);
				}
			}
		} else {
			PtUser user = sysUserService.get(author);
			if (user != null) {
				// 得到角色
				Set<PtRole> roles = user.getSysRoles();
				for (PtRole role : roles) {
					// 得到指定角色的权限
					if (role != null && role.getIsDelete() == 0) {
						Set<PtPermission> perms = role.getSysPermissions();
						for (PtPermission perm : perms) {
							maps.put(perm.getPermissionCode(), perm);
						}
					}
				}
			}
		}
		return maps;
	}

	private void roleMenuRecursion(MenuTree parentNode, List<MenuTree> result, List<PtMenu> list,
			List<PtMenuPermission> menuPers) {
		List<PtMenu> childs = new ArrayList<PtMenu>();
		for (PtMenu SysMenu : list) {
			if (SysMenu.getParentNode().equals(parentNode.getId())) {
				childs.add(SysMenu);
			}
		}
		for (PtMenu SysMenu : childs) {
			MenuTree child = new MenuTree(SysMenu.getId(), SysMenu.getNodeText(), "", SysMenu.getLeaf(),
					SysMenu.getNodeLevel(), SysMenu.getTreeIds(), SysMenu.getParentNode(), SysMenu.getOrderIndex(),
					new ArrayList<MenuTree>(), SysMenu.getMenuCode(), SysMenu.getSmallIcon(), SysMenu.getBigIcon(),
					SysMenu.getMenuTarget(), SysMenu.getMenuType(), SysMenu.getNodeCode(),
					SysMenu.getIsSystem(), SysMenu.getIsHidden(), SysMenu.getPermissionId());

			String menuPerName = "";
			for (int j = menuPers.size() - 1; j >= 0; j--) {
				if (menuPers.get(j).getMenuId().equals(SysMenu.getId())) {
					menuPerName += menuPers.get(j).getPermissionName() + "/";
					menuPers.remove(j);
				}
			}
			if (menuPerName.length() > 0) {
				menuPerName = menuPerName.substring(0, menuPerName.length() - 1);
			}
			child.setRoleMenuPerName(menuPerName);

			if (SysMenu.getParentNode().equals(TreeVeriable.ROOT)) {
				result.add(child);
			} else {
				List<MenuTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			roleMenuRecursion(child, result, list, menuPers);
		}

	}

	@Override
	public List<MenuTree> getRoleMenuTreeList(String roleId) {
		// 当前角色有权限的菜单
		Set<PtPermission> rolePerimisson = sysRoleService.get(roleId).getSysPermissions();
		// String hql = "from SysMenu e where e.isHidden='0' and e.uuid in
		// (select o.perCode from SysPermission o where o in (:roleRight)) ";
		String hql = "from PtMenu e where e.id in (select o.permissionCode from PtPermission o where o in (:roleRight)) ";
		// 非超级管理员要排除掉隐藏的菜单
		if (!roleId.equals(AdminType.ADMIN_ROLE_ID))
			hql += " and e.isHidden='0'";	//只显示正常的
		List<PtMenu> lists = new ArrayList<PtMenu>();
		List<MenuTree> result = new ArrayList<MenuTree>();
		if (rolePerimisson.size() > 0) {
			lists = this.queryByHql(hql.toString(), 0, 999, "roleRight", rolePerimisson.toArray());// 执行查询方法

			// 查询此菜单的功能权限
			List<PtMenuPermission> menuPers = menuPermissionService.getRoleMenuPerlist(roleId, null);
			// 然后在递归组装树中把菜单功能权限设置进来。
			roleMenuRecursion(new MenuTree(TreeVeriable.ROOT, new ArrayList<MenuTree>()), result, lists,
					menuPers);
		}
		return result;
	}

	/**
	 * 
	 * cancelRoleRightMenu:取消指定角色的菜单权限). new：删除菜单所有功能权限。
	 * 
	 * @author luoyibo
	 * @param roleId
	 * @param cancelMenuId
	 * @return Boolean
	 * @throws @since
	 *             JDK 1.8
	 */
	@Override
	public Boolean doCancelRoleRightMenu(String roleId, String cancelMenuId) {
		Boolean doResult = false;

		String menuIds = "'" + cancelMenuId.replace(",", "','") + "'";
		String hql = " from PtPermission a where a.permissionCode in (" + menuIds + ") and a.permissionType='" + PermType.TYPE_MENU
				+ "'";
		List<PtPermission> cancelPerimission = perimissonSevice.queryByHql(hql);

		PtRole cancelRole = sysRoleService.get(roleId);
		Set<PtPermission> rolePermission = cancelRole.getSysPermissions();
		rolePermission.removeAll(cancelPerimission);

		cancelRole.setSysPermissions(rolePermission);
		sysRoleService.merge(cancelRole);

		// 删除此角色的菜单功能权限
		roleMenuPermissionService.removeRoleMenuPermission(roleId, cancelPerimission);

		// 删除此角色的redis菜单数据
		sysUserService.deleteUserMenuTreeRedis(new String[] { roleId });

		doResult = true;

		return doResult;
	}

	/**
	 * 
	 * addRoleRightMenu给指定的角色增加权限菜单 . new：给菜单默认加入所有功能权限。
	 * 
	 * @author luoyibo
	 * @param roleId
	 * @param addMenuid
	 * @return Boolean
	 * @throws @since
	 *             JDK 1.8
	 */
	@Override
	public Boolean doAddRoleRightMenu(String roleId, String addMenuid) {
		Boolean doResult = false;

		String[] addMenuIds = addMenuid.split(",");

		// 要增加权限菜单的角色及已有权限菜单信息
		PtRole addRoleEntity = sysRoleService.get(roleId);
		Set<PtPermission> rolePermission = addRoleEntity.getSysPermissions();

		// 要添加的菜单的权限清单
		List<PtMenu> addMenuEntity = this.queryByProerties("id", addMenuIds);
		List<String> perimissonIds = new ArrayList<>();

		String[] propName = { "permissionType", "permissionCode" };
		String[] propValue = new String[] {};
		Set<PtPermission> addPerimisson = new HashSet<PtPermission>();
		for (PtMenu sysMenu : addMenuEntity) {
			String perCode = sysMenu.getId();
			propValue = new String[] { PermType.TYPE_MENU, perCode };
			PtPermission isPeriminsson = perimissonSevice.getByProerties(propName, propValue);
			// 当前菜单在权限资源表中
			if (ModelUtil.isNotNull(isPeriminsson)) {
				addPerimisson.add(isPeriminsson);
			} else {
				// 当前菜单不在权限资源中，需要先增加权限资源
				isPeriminsson = new PtPermission();
				isPeriminsson.setPermissionCode(perCode);
				isPeriminsson.setPermissionType(PermType.TYPE_MENU);
				isPeriminsson.setId(keyRedisService.getId(PtPermission.ModuleType));
				isPeriminsson = perimissonSevice.merge(isPeriminsson);
				addPerimisson.add(isPeriminsson);
			}
			perimissonIds.add(isPeriminsson.getId());
		}
		// rolePermission.removeAll(rolePermission);
		rolePermission.addAll(addPerimisson);

		addRoleEntity.setSysPermissions(rolePermission);
		sysRoleService.merge(addRoleEntity);

		// 初始化角色菜单功能权限。
		for (int i = 0; i < addMenuEntity.size(); i++) {
			PtMenu sysMenu = addMenuEntity.get(i);
			String perCode = sysMenu.getId();
			String perId = perimissonIds.get(i);

			// 查询此菜单拥有的功能权限
			String hql = "select a.id from PtMenuPermission a where a.isDelete=0 and a.menuId=?";
			List<String> menuPerList = this.queryEntityByHql(hql, perCode);
			String menuPerStr = "";
			for (int j = 0; j < menuPerList.size(); j++) {
				menuPerStr += menuPerList.get(j) + ",";
			}
			if (menuPerStr.length() > 0)
				menuPerStr = menuPerStr.substring(0, menuPerStr.length() - 1);

			roleMenuPermissionService.doSetRoleMenuPermission(roleId, perId, menuPerStr);
		}

		// 如果有新的菜单加入，就清除redis菜单数据
		if (addMenuEntity.size() > 0) {
			sysUserService.deleteUserMenuTreeRedis(new String[] { roleId });
		}

		doResult = true;

		return doResult;
	}

	/**
	 * 
	 * getPermissionMenu:获取指定对象有权限的菜单.
	 *
	 * @author luoyibo
	 * @param roodId
	 * @param author
	 * @param authorType
	 * @return List<SysMenu>
	 * @throws @since
	 *             JDK 1.8
	 */
	private List<PtMenu> getPermissionMenu(String author, String authorType) {
		String hql = "from PtMenu where isHidden='0'";

		// 查询出有效的菜单
		List<PtMenu> lists = super.queryByHql(hql.toString());

		// 对于超级管理员的用户与角色，默认有所有菜单的权限
		if (authorType.equals(AuthorType.ROLE)) {
			PtRole thisRole = sysRoleService.get(author);
			if (thisRole.getRoleCode().equals(AdminType.ADMIN_ROLE_NAME))	//判断角色名
				return lists;
		} else {
			PtUser thisUser = sysUserService.get(author);
			if (thisUser.getUserName().equals(AdminType.ADMIN_USER_NAME)){	//判断用户名
				return lists;
			}else{				
				Iterator<PtRole> iterator=thisUser.getSysRoles().iterator();
				while(iterator.hasNext()){
					if(iterator.next().getId().equals(AdminType.ADMIN_ROLE_ID)){	//判断角色id
						return lists;
					}
				}			
			}
		}

		// 对于非超级管理员，得到当前对象的权限
		Map<String, PtPermission> maps = buildPermMap(author, authorType);
		if (maps == null) {
			return null;
		}

		// 根据当前用户的权限，从系统菜单中清除无权限的菜单
		List<PtMenu> removeLists = new ArrayList<PtMenu>();
		for (PtMenu node : lists) {
			if (maps.get(node.getId()) == null && !node.getId().equalsIgnoreCase(TreeVeriable.ROOT)) {
				// 如果当前菜单不在权限菜单中，则放入清除的菜单中
				removeLists.add(node);
			}
		}
		// 从所有的菜单中删除无权限的菜单
		for (PtMenu node : removeLists) {
			lists.remove(node);
		}

		return lists;
	}

	/**
	 * 
	 * getUserPermissionToRole 获取指定用户能对指定角色授权的菜单.
	 *
	 * @author luoyibo
	 * @param roleId
	 *            要授权菜单的角色
	 * @param userId
	 *            当前授权人
	 * @return List<SysMenuTree>
	 * @throws @since
	 *             JDK 1.8
	 */

	@Override
	public List<MenuTree> getUserPermissionToRole(String roleId, String userId) {
		// 当前角色已有的授权菜
		Map<String, PtPermission> maps = this.buildPermMap(roleId, AuthorType.ROLE);

		// 当前用户有权限的菜单
		List<PtMenu> userPermissionMenu = this.getPermissionMenu(userId, AuthorType.USER);
		List<PtMenu> removeLists = new ArrayList<PtMenu>();
		for (PtMenu node : userPermissionMenu) {
			if (maps.get(node.getId()) != null && !node.getMenuType().equals(MenuType.TYPE_MENU)) {
				// 如果当前菜单在角色的菜单权限中，则过滤掉
				removeLists.add(node);
			}
		}
		// 从当前用户有权限的菜单中除掉已对角色授权的菜单
		for (PtMenu node : removeLists) {
			userPermissionMenu.remove(node);
		}

		List<MenuTree> result = new ArrayList<MenuTree>();
		// 生成树形数据
		createChildTree(new  MenuTree(TreeVeriable.ROOT, new ArrayList<MenuTree>()), result,
				userPermissionMenu);

		return result;
	}

	private void createChildTree(MenuTree parentNode, List<MenuTree> result, List<PtMenu> list) {
		List<PtMenu> childs = new ArrayList<PtMenu>();
		for (PtMenu SysMenu : list) {
			if (SysMenu.getParentNode().equals(parentNode.getId())) {
				childs.add(SysMenu);
			}
		}

		for (PtMenu sysMenu : childs) {
			MenuTree child = new MenuTree(sysMenu.getId(), sysMenu.getNodeText(), "", sysMenu.getLeaf(),
					sysMenu.getNodeLevel(), sysMenu.getTreeIds(), sysMenu.getParentNode(),sysMenu.getOrderIndex(), 
					new ArrayList<MenuTree>(),sysMenu.getMenuCode(), sysMenu.getSmallIcon(), sysMenu.getBigIcon(),
					sysMenu.getMenuTarget(),sysMenu.getMenuType(), sysMenu.getNodeCode(), sysMenu.getIsSystem(),sysMenu.getIsHidden());

			if (sysMenu.getParentNode().equals(TreeVeriable.ROOT)) {
				result.add(child);
			} else {
				List<MenuTree> trees = parentNode.getChildren();
				trees.add(child);
				parentNode.setChildren(trees);
			}
			createChildTree(child, result, list);
		}
	}

	@Override
	public PtMenu addMenu(PtMenu menu, PtUser currentUser) throws IllegalAccessException, InvocationTargetException {
		String parentNode = menu.getParentNode();
		String parentName = menu.getParentMenuName();
		String menuType = menu.getMenuType();
		menu.setId(keyRedisService.getId(PtMenu.ModuleType));
		/*zzk：此字段不需要了*/
//		String menuLeaf = "LEAF";
//		if (menuType.equals(MenuType.TYPE_MENU))
//			menuLeaf = "GENERAL";
	
		PtMenu saveEntity = new PtMenu();
		List<String> excludedProp = new ArrayList<>();
		excludedProp.add("id");
		BeanUtils.copyProperties(saveEntity, menu, excludedProp);		
		saveEntity.setCreateUser(currentUser.getId()); // 创建人
		saveEntity.setLeaf(true);
		saveEntity.setIsSystem(true);
		saveEntity.setIsHidden(true);
//		saveEntity.setMenuLeaf(menuLeaf);
		if (!parentNode.equals(TreeVeriable.ROOT)) {
			PtMenu parEntity = this.get(parentNode);
			parEntity.setLeaf(false);
			this.merge(parEntity);
			saveEntity.BuildNode(parEntity);
		} else
			saveEntity.BuildNode(null);

		menu = this.merge(saveEntity);
		menu.setParentMenuName(parentName);
		menu.setParentNode(parentNode);

		return menu;
	}

	@Override
	public PtMenu doUpdateMenu(PtMenu entity, String xm) {
		// TODO Auto-generated method stub
		String parentNode = entity.getParentNode();
		String uuid = entity.getId();

		// 先拿到已持久化的实体
		PtMenu perEntity = this.get(uuid);
		boolean isLeaf = perEntity.getLeaf();
		// 将entity中不为空的字段动态加入到perEntity中去。
		try {
			BeanUtils.copyPropertiesExceptNull(perEntity, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		perEntity.setUpdateTime(new Date()); // 设置修改时间
		perEntity.setUpdateUser(xm); // 设置修改人的中文名
		perEntity.setLeaf(isLeaf);
		entity = this.merge(perEntity);// 执行修改方法
		
		//entity.setParentName(parentName);
		//entity.setParentNode(parentNode);
		
		// 更新父节点的是否叶节点的标记
		PtMenu parentMenu = this.get(parentNode);
		if (parentMenu != null) {
			parentMenu.setUpdateTime(new Date()); // 设置修改时间
			parentMenu.setUpdateUser(xm); // 设置修改人的中文名
			parentMenu.setLeaf(false);
			this.merge(parentMenu);// 执行修改方法
		}

		// 删除有权限的角色的用户的redis数据
		if (entity.getPermissionId() != null) {
			PtPermission sysPermission = perimissonSevice.get(entity.getPermissionId());
			if (sysPermission != null)
				sysUserService.deleteUserMenuTreeRedis(sysPermission);
		}

		return entity;
	}
}