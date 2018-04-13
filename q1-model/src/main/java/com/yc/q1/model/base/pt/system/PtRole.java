package com.yc.q1.model.base.pt.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 角色信息
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Role")
@AttributeOverride(name = "id", column = @Column(name = "roleId", length = 20, nullable = false))
public class PtRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
    
    @FieldInfo(name = "角色编码",type="varchar(16) NOT NULL",explain="角色的编码")
    @Column(name = "roleCode", length=16, nullable = false)
    private String roleCode;
    

    @FieldInfo(name = "角色名称",type="nvarchar(16) NOT NULL",explain="角色的名称")
    @Column(name = "roleName", columnDefinition = "nvarchar(16)", nullable = false)
    private String roleName;


    @FieldInfo(name = "是否系统角色",type="bit NOT NULL",explain="是否系统角色(0-非系统内置  1-系统内置 ) ")
    @Column(name = "isSystem",nullable = false)
    private Boolean isSystem;



    @FieldInfo(name = "是否隐藏",type="bit DEFAULT 0",explain="当前角色是否隐藏(0-不隐藏 1-隐藏)")
    @Column(name = "isHidden",columnDefinition="bit DEFAULT 0", nullable = true)
    private Boolean isHidden;

    

    @FieldInfo(name = "角色说明 ",type="nvarchar(128) DEFAULT ''",explain="角色说明 ")
    @Column(name = "remark",columnDefinition="nvarchar(128) DEFAULT ''", nullable = true)
    private String remark;
    

    
    @FieldInfo(name = "有权限的菜单",type="Set<SysPermission>",explain="多对多的实体对象关联，生成一个中间表T_PT_RolePermission")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "T_PT_RolePermission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
            @JoinColumn(name = "permissionId") })
    private Set<PtPermission> sysPermissions = new HashSet<PtPermission>();

    
  
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<PtPermission> getSysPermissions() {
		return sysPermissions;
	}

	public void setSysPermissions(Set<PtPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

	public PtRole() {
		super();
	}

	public PtRole(String id) {
		super(id);		
	}
}