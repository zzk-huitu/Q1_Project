package com.yc.q1.base.pt.system.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 角色菜单的权限中间表（同时生成中间表T_PT_RolePermission：分别对应菜单id 和 角色id）
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Permission")
@AttributeOverride(name = "id", column = @Column(name = "permissionId", length = 20, nullable = false) )
public class Permission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "权限菜单类型", type = "varchar(10) NOT NULL", explain = "权限类型（MENU）")
	@Column(name = "permissionType", columnDefinition = "varchar(10)", nullable = false)
	private String permissionType;

	@FieldInfo(name = "权限菜单ID", type = "varchar(20) NOT NULL", explain = "权限菜单ID")
	@Column(name = "permissionCode", length = 20, nullable = false)
	private String permissionCode;

	// @FieldInfo(name = "权限名称",type="nvarchar(20)",explain="权限名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Menu a WHERE a.menuId=permissionCode)")
	private String permissionName;

	@FieldInfo(name = "权限路径", type = "varchar(128)  default ''", explain = "权限路径")
	@Column(name = "permissionPath", columnDefinition = "varchar(128) default ''", nullable = true)
	private String permissionPath;

	@FieldInfo(name = "有权限的角色", type = "Set<SysRole>", explain = "多对多实体关联。生成一个中间表T_PT_RolePermission")
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "T_PT_RolePermission", joinColumns = {
			@JoinColumn(name = "permissionId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
	private Set<Role> sysRoles = new HashSet<Role>();

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionPath() {
		return permissionPath;
	}

	public void setPermissionPath(String permissionPath) {
		this.permissionPath = permissionPath;
	}

	public Set<Role> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<Role> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public Permission() {
		super();
	}

	public Permission(String id) {
		super(id);
	}

}