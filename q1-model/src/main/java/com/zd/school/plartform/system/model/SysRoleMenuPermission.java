
package com.zd.school.plartform.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseTPerimisson Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 角色菜单功能权限表实体类. date: 2016-07-17
 * 
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_RoleMenuPermission")
@AttributeOverride(name = "roleMenuPermissionId", column = @Column(name = "roleMenuPermissionId", length = 20, nullable = false) )
public class SysRoleMenuPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@FieldInfo(name = "角色ID")
	@Column(name = "roleId", columnDefinition="varchar(20)", nullable = false)
	private String roleId;
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}
	
	@FieldInfo(name = "角色菜单权限ID")		//角色菜单的权限id
	@Column(name = "perId", columnDefinition="varchar(20)", nullable = false)
	private String perId;

	public void setPerId(String perId) {
		this.perId = perId;
	}

	public String getPerId() {
		return perId;
	}
	
	@FieldInfo(name = "菜单功能权限ID")	//菜单和功能的权限id
	@Column(name = "menuPerId",columnDefinition="varchar(20)", nullable = false)
	private String menuPerId;

	public void setMenuPerId(String menuPerId) {
		this.menuPerId = menuPerId;
	}

	public String getMenuPerId() {
		return menuPerId;
	}
	

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}
