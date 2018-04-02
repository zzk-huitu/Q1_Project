package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 用户部门岗位
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_UseDeptJob")
@AttributeOverride(name = "id", column = @Column(name = "useDeptJobId", length = 20, nullable = false) )
public class BaseUserdeptjob extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "用户ID", type = "varchar(20) NOT NULL", explain = "用户ID")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "部门岗位ID", type = "varchar(20) NOT NULL", explain = "用户的部门岗位ID")
	@Column(name = "deptjobId", length = 20, nullable = false)
	private String deptjobId;

	@FieldInfo(name = "部门ID", type = "varchar(20) NOT NULL", explain = "用户的部门ID")
	@Column(name = "deptId", length = 20, nullable = false)
	private String deptId;

	@FieldInfo(name = "岗位ID", type = "varchar(20) NOT NULL", explain = "用户的岗位ID")
	@Column(name = "jobId", length = 20, nullable = false)
	private String jobId;

	@FieldInfo(name = "是否主部门", type = "bit NOT NULL", explain = "是否主部门(0-不是 1-是)")
	@Column(name = "isMainDept", nullable = false)
	private Boolean isMainDept;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "部门名称",type="nvarchar",explain="用户的部门名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Department a WHERE a.deptId=deptId )")
	private String deptName;

	// @FieldInfo(name = "部门类型",type="varchar(2)",explain="用户的部门类型")
	@Formula("(SELECT a.deptType FROM T_PT_Department a WHERE a.deptId=deptId )")
	private String deptType;

	// @FieldInfo(name = "岗位名称",type="nvarchar(32)",explain="用户的岗位名称")
	@Formula("(SELECT a.jobName FROM T_PT_Job a WHERE a.jobId=jobId )")
	private String jobName;

	// @FieldInfo(name = "部门全称",type="nvarchar(500)",explain="用户的部门全称")
	@Formula("(SELECT a.allDeptName FROM dbo.T_PT_Department a WHERE a.deptId=deptId )")
	private String allDeptName;

	// @FieldInfo(name = "部门全称id",type="varchar(MAX)",explain="用户的部门全称id")
	@Formula("(SELECT a.treeIds FROM dbo.T_PT_Department a WHERE a.deptId=deptId )")
	private String treeIds;

	// @FieldInfo(name = "姓名",type="varchar(36)",explain="用户的姓名")
	@Formula("(SELECT a.name FROM dbo.T_PT_User a WHERE a.userId=userId )")
	private String name;

	// @FieldInfo(name = "用户编号",type="varchar(18)",explain="用户编号")
	@Formula("(SELECT a.userNumb FROM dbo.T_PT_User a WHERE a.userId=userId )")
	private String userNumb;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptjobId() {
		return deptjobId;
	}

	public void setDeptjobId(String deptjobId) {
		this.deptjobId = deptjobId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Boolean getIsMainDept() {
		return isMainDept;
	}

	public void setIsMainDept(Boolean isMainDept) {
		this.isMainDept = isMainDept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAllDeptName() {
		return allDeptName;
	}

	public void setAllDeptName(String allDeptName) {
		this.allDeptName = allDeptName;
	}

	public String getTreeIds() {
		return treeIds;
	}

	public void setTreeIds(String treeIds) {
		this.treeIds = treeIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public BaseUserdeptjob() {
		super();
	}

	public BaseUserdeptjob(String id) {
		super(id);
	}

}