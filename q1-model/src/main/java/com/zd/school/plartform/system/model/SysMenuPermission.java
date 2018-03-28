
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
@AttributeOverride(name = "menuPermissionuId", column = @Column(name = "menuPermissionuId", length = 36, nullable = false) )
public class SysMenuPermission extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "菜单ID")
	@Column(name = "menuId", length = 36, nullable = false)
	private String menuId;

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuId() {
		return menuId;
	}

	@FieldInfo(name = "权限名称")
	@Column(name = "perName", length = 36, nullable = false)
	private String perName;

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
	}

	@FieldInfo(name = "按钮别名")
	@Column(name = "perBtnName", length = 36, nullable = false)
	private String perBtnName;

	public String getPerBtnName() {
		return perBtnName;
	}

	public void setPerBtnName(String perBtnName) {
		this.perBtnName = perBtnName;
	}
	
	@FieldInfo(name = "权限接口前缀")
	@Column(name = "interfacePrefix", length = 36, nullable = false)
	private String interfacePrefix;

	
	@FieldInfo(name = "权限接口后缀")
	@Column(name = "interfacePostfix", length = 36, nullable = false)
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

	@FieldInfo(name = "权限备注")
	@Column(name = "perRemark", length = 100, nullable = false)
	private String perRemark;

	public String getPerRemark() {
		return perRemark;
	}

	public void setPerRemark(String perRemark) {
		this.perRemark = perRemark;
	}
	
	@FieldInfo(name = "菜单名称")
	@Formula("(SELECT a.NODE_TEXT FROM SYS_T_MENU a WHERE a.MENU_ID=MENU_ID)")
	private String menuText;

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	
	@FieldInfo(name = "菜单编码")
	@Formula("(SELECT a.MENU_CODE FROM SYS_T_MENU a WHERE a.MENU_ID=MENU_ID)")
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