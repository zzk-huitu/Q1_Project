package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 用户额外的部门权限（除了用户目前分配的部门岗位，以及部门岗位主管的部门之外的权限）
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "T_PT_UserDeptRight")
@AttributeOverride(name = "id", column = @Column(name = "deptRightId", length = 20, nullable = false) )
public class PtUserDeptRight extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "部门ID", type = "varchar(20) NOT NULL", explain = "部门ID")
	@Column(name = "deptId", columnDefinition = "varchar(20)", nullable = false)
	private String deptId;

	@FieldInfo(name = "用户ID", type = "varchar(20) NOT NULL", explain = "用户ID")
	@Column(name = "userId", columnDefinition = "varchar(20)", nullable = false)
	private String userId;

	// @FieldInfo(name = "主部门名称",type="varchar",explain="主部门名称")
	@Formula("(SELECT ISNULL(a.nodeText,'') FROM T_PT_Department a WHERE a.deptId = deptId)")
	private String deptName;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public PtUserDeptRight() {
		super();
	}

	public PtUserDeptRight(String id) {
		super(id);
	}

}