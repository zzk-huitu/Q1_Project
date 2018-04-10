
package com.yc.q1.base.pt.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 系统菜单的功能权限表实体类
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_MenuPermission")
@AttributeOverride(name = "id", column = @Column(name = "menuPermissionId", length = 20, nullable = false) )
public class MenuPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "菜单ID", type = "varchar(20) NOT NULL", explain = "菜单权限管理菜单ID")
	@Column(name = "menuId", length = 20, nullable = false)
	private String menuId;

	@FieldInfo(name = "权限名称", type = "nvarchar(16) NOT NULL", explain = "菜单权限管理的权限名称")
	@Column(name = "permissionName", columnDefinition = "nvarchar(16)", nullable = false)
	private String permissionName;

	@FieldInfo(name = "按钮别名", type = "varchar(32)  NOT NULL", explain = "菜单权限管理的按钮别名")
	@Column(name = "buttonName", columnDefinition = "varchar(32)", nullable = false)
	private String buttonName;

	@FieldInfo(name = "权限接口前缀", type = "varchar(36) NOT NULL", explain = "菜单权限管理的权限接口前缀")
	@Column(name = "authPrefix", columnDefinition = "varchar(36)", nullable = false)
	private String authPrefix;

	@FieldInfo(name = "权限接口后缀", type = "varchar(36)  NOT NULL", explain = "菜单权限管理的权限接口后缀")
	@Column(name = "authPostfix", columnDefinition = "varchar(36)", nullable = false)
	private String authPostfix;

	@FieldInfo(name = "权限备注", type = "nvarchar(128) default ''", explain = "菜单权限管理的权限备注")
	@Column(name = "permissionNotes", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String permissionNotes;

	// @FieldInfo(name = "菜单名称",type="nvarchar(36)",explain="菜单权限管理的菜单名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Menu a WHERE a.menuId=menuId)")
	private String menuText;

	// @FieldInfo(name = "菜单编码",type="nvarchar(36)",explain="菜单权限管理的菜单编码")
	@Formula("(SELECT a.menuCode FROM T_PT_Menu a WHERE a.menuId=menuId)")
	private String menuCode;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getAuthPrefix() {
		return authPrefix;
	}

	public void setAuthPrefix(String authPrefix) {
		this.authPrefix = authPrefix;
	}

	public String getAuthPostfix() {
		return authPostfix;
	}

	public void setAuthPostfix(String authPostfix) {
		this.authPostfix = authPostfix;
	}

	public String getPermissionNotes() {
		return permissionNotes;
	}

	public void setPermissionNotes(String permissionNotes) {
		this.permissionNotes = permissionNotes;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public MenuPermission() {
		super();
	}

	public MenuPermission(String id) {
		super(id);
	}

}