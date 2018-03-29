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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseTRole Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 角色管理实体类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Role")
@AttributeOverride(name = "roleId", column = @Column(name = "roleId", length = 20, nullable = false))
public class SysRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "角色编码")
    @Column(name = "roleCode", columnDefinition = "nvarchar(12)", nullable = false)
    private String roleCode;

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    @FieldInfo(name = "角色名称")
    @Column(name = "roleName", columnDefinition = "nvarchar(32)", nullable = false)
    private String roleName;

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @FieldInfo(name = "是否系统角色  1-系统内置 0-非系统内置")
    @Column(name = "issystem",nullable = false)
    private boolean issystem;

    public void setIssystem(boolean issystem) {
        this.issystem = issystem;
    }

    public boolean getIssystem() {
        return issystem;
    }

    @FieldInfo(name = "备注")
    @Column(name = "remark",columnDefinition="nvarchar(128) defalut ''", nullable = true)
    private String remark;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    @FieldInfo(name = "有权限的菜单")
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "T_PT_RolePermission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
            @JoinColumn(name = "permissionId") })
    private Set<SysPermission> sysPermissions = new HashSet<SysPermission>();

    public Set<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(Set<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    @FieldInfo(name = "是否隐藏,0-不隐藏 1-隐藏")
    @Column(name = "isHidden",columnDefinition="defalut 0", nullable = true)
    private boolean isHidden;

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    
    @FieldInfo(name = "备注")
    @Column(name = "schoolId", columnDefinition="nvarchar(128) defalut ''", nullable = true)
    private String schoolId;
    
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public SysRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysRole(String uuid) {
		super(uuid);
		// TODO Auto-generated constructor stub
	}
    
    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
}