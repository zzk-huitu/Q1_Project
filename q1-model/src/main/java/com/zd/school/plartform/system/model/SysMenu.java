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
 * 系统菜单表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Menu")
@AttributeOverride(name = "id", column = @Column(name = "menuId", length = 20, nullable = false) )
public class SysMenu extends TreeNodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "菜单编码", type = "varchar(16) NOT NULL", explain = "菜单编码")
	@Column(name = "menuCode", columnDefinition = "varchar(16)", nullable = false)
	private String menuCode;

	@FieldInfo(name = "菜单类型", type = "varchar(10) NOT NULL", explain = "菜单类型")
	@Column(name = "menuType", columnDefinition = "varchar(10)", nullable = false)
	private String menuType;

	@FieldInfo(name = "是否系统菜单", type = "bit NOT NULL defalut 0", explain = "是否系统菜单（0-非 1-是）")
	@Column(name = "isSystem", nullable = false)
	private Boolean issystem;

	@FieldInfo(name = "菜单小图标", type = "varchar(256)  defalut ''", explain = "菜单的小图标")
	@Column(name = "smallIcon", columnDefinition = "varchar(256) defalut ''", nullable = true)
	private String smallIcon;

	@FieldInfo(name = "菜单大图标", type = "varchar(256) defalut ''", explain = "菜单的大图标")
	@Column(name = "bigIcon", columnDefinition = "varchar(256) defalut ''", nullable = true)
	private String bigIcon;

	@FieldInfo(name = "菜单目标值", type = "varchar(128)", explain = "菜单的前端模块值（例：smartcontrol.userauthority.mainlayout,core.smartcontrol.userauthority.controller.MainController）")
	@Column(name = "menuTarget", columnDefinition = "varchar(128) defalut ''", nullable = true)
	private String menuTarget;

	@FieldInfo(name = "是否叶子菜单", type = "bit defalut 0", explain = "是否叶菜单（0-非 1-是）")
	@Column(name = "isMenuLeaf", columnDefinition = "defalut 0", nullable = true)
	private Boolean isMenuLeaf;

	@FieldInfo(name = "是否隐藏", type = "bit defalut 0", explain = "是否隐藏菜单（0-不隐藏 1-隐藏）")
	@Column(name = "isHidden", columnDefinition = "defalut 0", nullable = true)
	private Boolean isHidden;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "上级菜单名称",type="nvarchar",explain="上级菜单名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_Menu a WHERE a.menuId=parentNode)")
	private String parentMenuName;

	// @FieldInfo(name = "角色菜单权限ID",type="boolean",explain="角色的菜单权限ID")
	@Formula("(SELECT top 1 a.permissionId FROM T_PT_Permission a WHERE a.permissionCode=menuId)")
	private String permissionId;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Boolean getIssystem() {
		return issystem;
	}

	public void setIssystem(Boolean issystem) {
		this.issystem = issystem;
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

	public Boolean getIsMenuLeaf() {
		return isMenuLeaf;
	}

	public void setIsMenuLeaf(Boolean isMenuLeaf) {
		this.isMenuLeaf = isMenuLeaf;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public SysMenu() {
		super();
	}

	public SysMenu(String id) {
		super(id);
	}

}