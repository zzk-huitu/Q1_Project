/**
 * Project Name:school-model
 * File Name:SysMenuTree.java
 * Package Name:com.zd.school.base.model.sys
 * Date:2016年6月2日上午11:58:26
 * Copyright (c) 2016 ZDKJ All Rights Reserved.
 *
*/

package com.yc.q1.pojo.base.pt;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.extjs.ExtTreeNode;

/**
 * POJO类
 * @author ZZK
 *
 */
public class MenuTree extends ExtTreeNode<MenuTree> {
    @FieldInfo(name = "菜单编码")
    private String menuCode;

    @FieldInfo(name = "小图标")
    private String smallIcon;

    @FieldInfo(name = "大图标")
    private String bigIcon;

    @FieldInfo(name = "菜单地址")
    private String menuTarget;

    @FieldInfo(name = "菜单类型")
    private String menuType;

//    @FieldInfo(name = "是否叶菜单")  原值为：GENERAL、LEAF 使用意义不大
//    private String menuLeaf;

    @FieldInfo(name = "节点编码")
    private String nodeCode;
    
    //新加入
    @FieldInfo(name = "角色菜单权限Id")
    private String perId;
   //新加入
    @FieldInfo(name = "角色菜单功能权限名称")
    private String roleMenuPerName;
    
    @FieldInfo(name = "是否系统菜单")
    private Boolean isSystem;

    @FieldInfo(name = "是否隐藏,0-不隐藏 1-隐藏")
    private Boolean isHidden;

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }


    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
    

    public String getPerId() {
		return perId;
	}

	public void setPerId(String perId) {
		this.perId = perId;
	}

	public String getRoleMenuPerName() {
		return roleMenuPerName;
	}

	public void setRoleMenuPerName(String roleMenuPerName) {
		this.roleMenuPerName = roleMenuPerName;
	}

	public MenuTree(String id, List<MenuTree> children) {

        super(id, children);
        // TODO Auto-generated constructor stub

    }

    public MenuTree(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid,String parent,Integer orderIndex,
            List<MenuTree> children, String menuCode, String smallIcon, String bigIcon, String menuTarget,
            String menuType,  String nodeCode, Boolean issystem,Boolean isHidden) {

        super(id, text, iconCls, leaf, level, treeid,parent,orderIndex, children);
        this.menuCode = menuCode;
        this.smallIcon = smallIcon;
        this.bigIcon = bigIcon;
        this.menuTarget = menuTarget;
        this.menuType = menuType;
        this.nodeCode = nodeCode;
        this.isSystem = issystem;
        this.isHidden = isHidden;    
        // TODO Auto-generated constructor stub

    }
    public MenuTree(String id, String text, String iconCls, Boolean leaf, Integer level, String treeid,String parent,Integer orderIndex,
            List<MenuTree> children, String menuCode, String smallIcon, String bigIcon, String menuTarget,
            String menuType, String nodeCode, Boolean issystem,Boolean isHidden,String perId) {

        super(id, text, iconCls, leaf, level, treeid,parent,orderIndex, children);
        this.menuCode = menuCode;
        this.smallIcon = smallIcon;
        this.bigIcon = bigIcon;
        this.menuTarget = menuTarget;
        this.menuType = menuType;
        this.nodeCode = nodeCode;
        this.isSystem = issystem;
        this.isHidden = isHidden;    
        this.perId = perId;  
        // TODO Auto-generated constructor stub

    }


	
        
}
