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
 * 部门岗位
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_DeptJob")
@AttributeOverride(name = "id", column = @Column(name = "deptJobId", length = 20, nullable = false) )
public class DeptJob extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "部门ID", type = "varchar(20) NOT NULL", explain = "部门ID")
	@Column(name = "deptId", length = 20, nullable = false)
	private String deptId;

	@FieldInfo(name = "岗位ID", type = "varchar(20) NOT NULL", explain = "岗位ID")
	@Column(name = "jobId", length = 20, nullable = false)
	private String jobId;

	@FieldInfo(name = "岗位类型", type = "varchar(1) NOT NULL", explain = "岗位类型 ( 2-普通岗位 -1副负责岗位  0-主负责岗位)")
	@Column(name = "jobType", length = 1, nullable = false)
	private String jobType;

	@FieldInfo(name = "上级部门ID", type = "varchar(20)  DEFAULT ''", explain = "上级部门ID")
	@Column(name = "parentDeptId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String parentDeptId;

	@FieldInfo(name = "上级岗位ID", type = "varchar(20) DEFAULT ''", explain = "上级岗位ID")
	@Column(name = "parentJobId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String parentJobId;

	@FieldInfo(name = "部门名称", type = "nvarchar(16) DEFAULT ''", explain = "部门名称")
	@Column(name = "deptName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String deptName;

	@FieldInfo(name = "岗位名称", type = "nvarchar(16)  DEFAULT ''", explain = "岗位名称")
	@Column(name = "jobName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String jobName;

	@FieldInfo(name = "上级部门名称", type = "nvarchar(16)  DEFAULT ''", explain = "上级部门名称")
	@Column(name = "parentDeptName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String parentDeptName;

	@FieldInfo(name = "上级岗位名称", type = "nvarchar(16) DEFAULT ''", explain = "上级岗位名称")
	@Column(name = "parentJobName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String parentJobName;

	@FieldInfo(name = "部门全称", type = "nvarchar(500) DEFAULT ''", explain = "部门全称")
	@Column(name = "allDeptName", columnDefinition = "nvarchar(500) DEFAULT ''", nullable = true)
	private String allDeptName;

	
	@FieldInfo(name = "部门岗位名称")
	//@Formula("(SELECT a.DEPT_NAME+a.JOB_NAME FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=DEPTJOB_ID)")
	private String deptJobName=deptName+jobName;

	@FieldInfo(name = "部门岗位全称")
	//@Formula("(SELECT a.ALL_DEPTNAME+a.JOB_NAME FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=DEPTJOB_ID)")
	private String allDeptJobName=allDeptName+jobName;
	
	
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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getParentDeptId() {
		return parentDeptId;
	}

	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}

	public String getParentJobId() {
		return parentJobId;
	}

	public void setParentJobId(String parentJobId) {
		this.parentJobId = parentJobId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public String getParentJobName() {
		return parentJobName;
	}

	public void setParentJobName(String parentJobName) {
		this.parentJobName = parentJobName;
	}

	public String getAllDeptName() {
		return allDeptName;
	}

	public void setAllDeptName(String allDeptName) {
		this.allDeptName = allDeptName;
	}

	public String getDeptJobName() {
		return deptJobName;
	}

	public void setDeptJobName(String deptJobName) {
		this.deptJobName = deptJobName;
	}

	public String getAllDeptJobName() {
		return allDeptJobName;
	}

	public void setAllDeptJobName(String allDeptJobName) {
		this.allDeptJobName = allDeptJobName;
	}

	public DeptJob() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeptJob(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
}