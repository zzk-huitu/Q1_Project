package com.zd.school.plartform.system.model;

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

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseTPerimisson Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description:角色菜单权限表实体类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Permission")
@AttributeOverride(name = "permissionId", column = @Column(name = "permissionId", length = 20, nullable = false))
public class SysPermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "权限类型",type="nvarchar(8)",explain="权限类型")
    @Column(name = "permissionType",columnDefinition = "nvarchar(8)", nullable = false)
    private String permissionType;

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionType() {
        return permissionType;
    }

    @FieldInfo(name = "权限码",type="nvarchar(36)",explain="权限码")
    @Column(name = "permissionCode", columnDefinition = "nvarchar(36)", nullable = false)
    private String permissionCode;

  
    public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
 
	@FieldInfo(name = "权限名称",type="nvarchar(36)",explain="权限名称")
    @Formula("(SELECT a.nodeText FROM T_PT_Menu a WHERE a.menuId=permissionCode)")
    private String permissionName;
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

    @FieldInfo(name = "权限路径",type="nvarchar(128)",explain="权限路径")
    @Column(name = "permissionUrl", columnDefinition = "nvarchar(128)", nullable = true)
    private String permissionUrl;

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    @FieldInfo(name = "有权限的角色",type="Set<SysRole>",explain="多对多实体关联。生成一个中间表T_PT_RolePermission")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "T_PT_RolePermission", joinColumns = { @JoinColumn(name = "permissionId") }, inverseJoinColumns = {
            @JoinColumn(name = "roleId") })
    private Set<SysRole> sysRoles = new HashSet<SysRole>();

    public Set<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(Set<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

	

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
}