package com.yc.q1.base.pt.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yc.q1.model.base.pt.system.Menu;
import com.yc.q1.model.base.pt.system.User;
import com.yc.q1.pojo.base.pt.MenuTree;
import com.zd.core.service.BaseService;

/**
 * 
 * ClassName: BaseTMenuService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 系统菜单表实体Service接口类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface MenuService extends BaseService<Menu> {

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
    public List<MenuTree> getTreeList(String whereSql, String orderSql);

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
    public List<MenuTree> getPermTree(String roodId, String author, String authorType, Boolean isSee,
            Boolean expanded);

    /**
     * 
     * getRoleMenuTreeList:获取指定角色的权限的菜单树.
     *
     * @author luoyibo
     * @param roleId
     * @return List<SysMenuTree>
     * @throws @since
     *             JDK 1.8
     */
    public List<MenuTree> getRoleMenuTreeList(String roleId);

    /**
     * 
     * cancelRoleRightMenu:取消指定角色的菜单权限).
     *
     * @author luoyibo
     * @param roleId
     * @param cancelMenuId
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doCancelRoleRightMenu(String roleId, String cancelMenuId);

    /**
     * 
     * addRoleRightMenu给指定的角色增加权限菜单 .
     *
     * @author luoyibo
     * @param roleId
     * @param addMenuid
     * @return Boolean
     * @throws @since
     *             JDK 1.8
     */
    public Boolean doAddRoleRightMenu(String roleId, String addMenuid);

    /**
     * 
     * getUserPermissionToRole:获取指定用户能对指定角色授权的菜单.
     *
     * @author luoyibo
     * @param roleId:要授权菜单的角色
     * @param userId:当前授权人
     * @return List<SysMenuTree>
     * @throws @since
     *             JDK 1.8
     */
    public List<MenuTree> getUserPermissionToRole(String roleId, String userId);

    public Menu addMenu(Menu menu, User currentUser) throws IllegalAccessException, InvocationTargetException;

	public Menu doUpdateMenu(Menu entity, String xm);
    
    //public
}