
package com.zd.school.plartform.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseTPerimisson Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 菜单功能权限表实体类. date: 2016-07-17
 * 权限ID、菜单ID、权限名称、按钮ref别名、权限接口、备注、菜单编码（查询出来）
 * 
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_MenuPermission")
@AttributeOverride(name = "menuPermissionId", column = @Column(name = "menuPermissionId", length = 20, nullable = false) )
public class SysMenuPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "菜单ID",type="varchar(20)",explain="菜单权限管理菜单ID")
	@Column(name = "menuId", columnDefinition="varchar(20)", nullable = false)
	private String menuId;

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuId() {
		return menuId;
	}

	@FieldInfo(name = "权限名称",type="nvarchar(36)",explain="菜单权限管理的权限名称")
	@Column(name = "permissionName", columnDefinition="nvarchar(36)", nullable = false)
	private String permissionName;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@FieldInfo(name = "按钮别名",type="nvarchar(36)",explain="菜单权限管理的按钮别名")
	@Column(name = "buttonName", columnDefinition="nvarchar(36)", nullable = false)
	private String buttonName;

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	@FieldInfo(name = "权限接口前缀",type="nvarchar(36)",explain="菜单权限管理的权限接口前缀")
	@Column(name = "interfacePrefix", columnDefinition="nvarchar(36)", nullable = false)
	private String interfacePrefix;

	
	@FieldInfo(name = "权限接口后缀",type="nvarchar(36)",explain="菜单权限管理的权限接口后缀")
	@Column(name = "interfacePostfix",columnDefinition="nvarchar(36)", nullable = false)
	private String interfacePostfix;


	
	public String getInterfacePrefix() {
		return interfacePrefix;
	}

	public void setInterfacePrefix(String interfacePrefix) {
		this.interfacePrefix = interfacePrefix;
	}

	public String getInterfacePostfix() {
		return interfacePostfix;
	}

	public void setInterfacePostfix(String interfacePostfix) {
		this.interfacePostfix = interfacePostfix;
	}

	@FieldInfo(name = "权限备注",type="nvarchar(100)",explain="菜单权限管理的权限备注")
	@Column(name = "permissionuRemark", columnDefinition="nvarchar(100)", nullable = false)
	private String permissionuRemark;

	
	public String getPermissionuRemark() {
		return permissionuRemark;
	}

	public void setPermissionuRemark(String permissionuRemark) {
		this.permissionuRemark = permissionuRemark;
	}

	@FieldInfo(name = "菜单名称",type="nvarchar(36)",explain="菜单权限管理的菜单名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Menu a WHERE a.menuId=menuId)")
	private String menuText;

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	
	@FieldInfo(name = "菜单编码",type="nvarchar(36)",explain="菜单权限管理的菜单编码")
	@Formula("(SELECT a.menuCode FROM T_PT_Menu a WHERE a.menuId=menuId)")
	private String menuCode;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}