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
 * 
 * ClassName: BaseDeptjob Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 部门岗位信息(BASE_T_DEPTJOB)实体类. date: 2017-03-27
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_DeptJob")
@AttributeOverride(name = "deptJobId", column = @Column(name = "deptJobId", length = 20, nullable = false))
public class BaseDeptjob extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "部门ID",type="varchar(20)",explain="部门ID")
	@Column(name = "deptId", length = 20, nullable = false)
	private String deptId;

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	@FieldInfo(name = "岗位ID",type="varchar(20)",explain="岗位ID")
	@Column(name = "jobId", length = 20, nullable = false)
	private String jobId;

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobId() {
		return jobId;
	}

	@FieldInfo(name = "岗位类型 2-普通岗位 -1副负责岗位  0-主负责岗位",type="Integer",explain="岗位类型 ")
	@Column(name = "jobType",nullable = false)
	private Integer jobType;

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public Integer getJobType() {
		return jobType;
	}

	@FieldInfo(name = "上级部门ID",type="varchar(20)",explain="上级部门ID")
	@Column(name = "parentdeptId",columnDefinition="varchar(20) defalut ''" , nullable = true)
	private String parentdeptId;

	public String getParentdeptId() {
		return parentdeptId;
	}

	public void setParentdeptId(String parentdeptId) {
		this.parentdeptId = parentdeptId;
	}

	@FieldInfo(name = "上级岗位ID",type="varchar(20)",explain="上级岗位ID")
	@Column(name = "parentjobId", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String parentjobId;

	public String getParentjobId() {
		return parentjobId;
	}

	public void setParentjobId(String parentjobId) {
		this.parentjobId = parentjobId;
	}

	@FieldInfo(name = "部门名称",type="nvarchar(36)",explain="部门名称")
	@Column(name = "deptName", columnDefinition="nvarchar(36) defalut ''", nullable = true)
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@FieldInfo(name = "岗位名称",type="nvarchar(36)",explain="岗位名称")
	@Column(name = "jobName",  columnDefinition="nvarchar(36) defalut ''", nullable = true)
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@FieldInfo(name = "上级部门名称",type="nvarchar(36)",explain="上级部门名称")
	@Column(name = "parentdeptName",  columnDefinition="nvarchar(36) defalut ''", nullable = true)
	private String parentdeptName;

	public String getParentdeptName() {
		return parentdeptName;
	}

	public void setParentdeptName(String parentdeptName) {
		this.parentdeptName = parentdeptName;
	}

	@FieldInfo(name = "上级岗位名称",type="nvarchar(36)",explain="上级岗位名称")
	@Column(name = "parentjobName",  columnDefinition="nvarchar(36) defalut ''", nullable = true)
	private String parentjobName;

	public String getParentjobName() {
		return parentjobName;
	}

	public void setParentjobName(String parentjobName) {
		this.parentjobName = parentjobName;
	}

	@FieldInfo(name = "部门全称",type="nvarchar(500)",explain="部门全称")
	@Column(name = "allDeptName",  columnDefinition="nvarchar(500) defalut ''", nullable = true)
	private String allDeptName;

	public String getAllDeptName() {
		return allDeptName;
	}

	public void setAllDeptName(String allDeptName) {
		this.allDeptName = allDeptName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "部门岗位名称",type="nvarchar(36)",explain="部门岗位名称")
	@Formula("(SELECT a.deptName+a.jobName FROM T_PT_DeptJob a WHERE a.deptJobId=deptJobId)")
	private String deptjobName;

	public String getDeptjobName() {
		return deptjobName;
	}

	public void setDeptjobName(String deptjobName) {
		this.deptjobName = deptjobName;
	}

	@FieldInfo(name = "部门岗位全称",type="nvarchar(36)",explain="部门岗位全称")
	@Formula("(SELECT a.allDeptName+a.jobName FROM T_PT_DeptJob a WHERE a.deptJobId=deptJobId)")
	private String alldeptjobName;

	public String getAlldeptjobName() {
		return alldeptjobName;
	}

	public void setAlldeptjobName(String alldeptjobName) {
		this.alldeptjobName = alldeptjobName;
	}

	@FieldInfo(name = "岗位级别",type="nvarchar(36)",explain="岗位级别")
	@Formula("(SELECT a.orderIndex FROMT_PT_Job a WHERE a.jobId=jobId )")
	private Integer jobLevel;

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}
	
	
	public BaseDeptjob() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseDeptjob(String uuid) {
		super(uuid);
		// TODO Auto-generated constructor stub
	}
}