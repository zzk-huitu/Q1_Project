
package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 角色的菜单的功能权限
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_RoleMenuPermission")
@AttributeOverride(name = "id", column = @Column(name = "roleMenuPermissionId", length = 20, nullable = false) )
public class PtRoleMenuPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "角色ID", type = "varchar(20) NOT NULL", explain = "角色ID")
	@Column(name = "roleId", length = 20, nullable = false)
	private String roleId;

	@FieldInfo(name = "角色菜单权限ID", type = "varchar(20) NOT NULL", explain = "角色菜单权限ID") // 角色菜单的权限id
	@Column(name = "permissionId", length = 20, nullable = false)
	private String permissionId;

	@FieldInfo(name = "菜单功能权限ID", type = "varchar(20) NOT NULL", explain = "菜单和功能的权限id") // 菜单和功能的权限id
	@Column(name = "menuPermissionId", length = 20, nullable = false)
	private String menuPermissionId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getMenuPermissionId() {
		return menuPermissionId;
	}

	public void setMenuPermissionId(String menuPermissionId) {
		this.menuPermissionId = menuPermissionId;
	}

	public PtRoleMenuPermission() {
		super();
	}

	public PtRoleMenuPermission(String id) {
		super(id);
	}
}
