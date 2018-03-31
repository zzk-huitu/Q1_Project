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
 * ClassName: SysDatapermission Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 用户数据权限表(SYS_T_DATAPERMISSION)实体类. date: 2016-09-01
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_DataPermission")
@AttributeOverride(name = "dataPermissionId", column = @Column(name = "dataPermissionId", length = 20, nullable = false))
public class SysDatapermission extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "用户ID",type="varchar(20)",explain="用户ID")
    @Column(name = "userId", columnDefinition="varchar(20)", nullable = false)
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @FieldInfo(name = "实体名称",type="nvarchar(32))",explain="实体名称")
    @Column(name = "entityName", columnDefinition="nvarchar(32)", nullable = false)
    private String entityName;

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    @FieldInfo(name = "权限控制字段",type="nvarchar(32))",explain="权限控制字段")
    @Column(name = "permissionField",columnDefinition="nvarchar(32)", nullable = false)
    private String permissionField;

    public void setPermissionField(String permissionField) {
        this.permissionField = permissionField;
    }

    public String getPermissionField() {
        return permissionField;
    }

    @FieldInfo(name = "父节点字段:针对树形数据配置",type="nvarchar(36))",explain="父节点字段")
    @Column(name = "parentField", columnDefinition="nvarchar(36) defalut ''", nullable = true)
    private String parentField;

    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    public String getParentField() {
        return parentField;
    }

    @FieldInfo(name = "权限类型:0-所有数据,1-本单元,2-本单元及下级单元,3-指定数据",type="varchar(2))",explain="权限类型")
    @Column(name = "permissionType", length = 2, nullable = false)
	private String permissionType;

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getPermissionType() {
		return permissionType;
	}

    @FieldInfo(name = "数据展现方式:0-列表,1-树形,默认列表",type="varchar(2))",explain="数据展现方式")
    @Column(name = "displayMode", length = 2, nullable = false)
    private String displayMode;

    public String getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(String displayMode) {
        this.displayMode = displayMode;
    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
}