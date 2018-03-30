package com.zd.school.plartform.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.TreeNodeEntity;

/**
 * 
 * ClassName: BaseTMenu Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 系统菜单表实体类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Menu")
@AttributeOverride(name = "menuId", column = @Column(name = "menuId", length = 20, nullable = false))
public class SysMenu extends TreeNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "菜单编码",type="nvarchar(32)",explain="菜单编码")
    @Column(name = "menuCode", columnDefinition="nvarchar(32)", nullable = false)
    private String menuCode;

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    @FieldInfo(name = "菜单小图标",type="nvarchar(256)",explain="菜单的小图标")
    @Column(name = "smallIcon", columnDefinition="nvarchar(256) defalut ''", nullable = true)
    private String smallIcon;

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    @FieldInfo(name = "菜单大图标",type="nvarchar(256)",explain="菜单的大图标")
    @Column(name = "bigIcon", columnDefinition="nvarchar(256) defalut ''", nullable = true)
    private String bigIcon;

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    @FieldInfo(name = "菜单地址",type="nvarchar(128)",explain="菜单的url")
    @Column(name = "menuTarget", columnDefinition="varchar(128) defalut ''",nullable=true)
    private String menuTarget;

    public String getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    @FieldInfo(name = "菜单类型",type="nvarchar(10)",explain="菜单类型")
    @Column(name = "menuType",columnDefinition="nvarchar(10)", nullable=false)
    private String menuType;

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @FieldInfo(name = "是否叶菜单",type="boolean",explain="是否叶菜单")
    @Column(name = "menuLeaf",columnDefinition="defalut 0",nullable=true)
    private boolean menuLeaf;

    public boolean getMenuLeaf() {
        return menuLeaf;
    }

    public void setMenuLeaf(boolean menuLeaf) {
        this.menuLeaf = menuLeaf;
    }

    @FieldInfo(name = "是否系统菜单",type="boolean",explain="是否系统菜单")
    @Column(name = "issystem", nullable = false)
    private boolean issystem;

    public void setIssystem(boolean issystem) {
        this.issystem = issystem;
    }

    public boolean getIssystem() {
        return issystem;
    }

    @FieldInfo(name = "是否隐藏,0-不隐藏 1-隐藏",type="boolean",explain="是否隐藏菜单")
    @Column(name = "isHidden",columnDefinition="defalut 0" , nullable = true)
    private boolean isHidden;

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
    @FieldInfo(name = "上级菜单名称",type="nvarchar",explain="上级菜单名称")
    @Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_Menu a WHERE a.menuId=parentNode)")
    private String parentMenuName;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }
    
  
    @FieldInfo(name = "角色菜单权限ID",type="boolean",explain="角色菜单权限ID")
    @Formula("(SELECT top 1 a.permissionId FROM T_PT_Permission a WHERE a.permissionCode=menuId)")
    private String perId;

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

}