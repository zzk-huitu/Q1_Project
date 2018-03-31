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
 * ClassName: BaseUserdeptjob Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 用户部门岗位(BASE_T_USERDEPTJOB)实体类. date: 2017-03-27
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_UseDeptJob")
@AttributeOverride(name = "useDeptJobId", column = @Column(name = "useDeptJobId", length = 20, nullable = false))
public class BaseUserdeptjob extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "用户ID",type="varchar(20)",explain="用户ID")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@FieldInfo(name = "部门岗位ID",type="varchar(20)",explain="用户的部门岗位ID")
	@Column(name = "deptjobId", length = 20, nullable = false)
	private String deptjobId;

	public void setDeptjobId(String deptjobId) {
		this.deptjobId = deptjobId;
	}

	public String getDeptjobId() {
		return deptjobId;
	}

	@FieldInfo(name = "部门ID",type="varchar(20)",explain="用户的部门ID")
	@Column(name = "deptId", length = 20, nullable = false)
	private String deptId;

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	@FieldInfo(name = "岗位ID",type="varchar(20)",explain="用户的岗位ID")
	@Column(name = "jobId", length = 20, nullable = false)
	private String jobId;

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobId() {
		return jobId;
	}

	@FieldInfo(name = "是否主部门 0-不是 1-是",type="boolean",explain="是否主部门")
	@Column(name = "mainDept",  nullable = false)
	private boolean mainDept;

	public void setMainDept(boolean mainDept) {
		this.mainDept = mainDept;
	}

	public boolean getMainDept() {
		return mainDept;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "部门名称",type="nvarchar",explain="用户的部门名称")
	@Formula("(SELECT a.nodeText FROM T_PT_Department a WHERE a.deptId=deptId )")
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@FieldInfo(name = "部门类型",type="varchar(2)",explain="用户的部门类型")
	@Formula("(SELECT a.deptType FROM T_PT_Department a WHERE a.deptId=deptId )")
	private String deptType;

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	

	@FieldInfo(name = "岗位名称",type="nvarchar(32)",explain="用户的岗位名称")
	@Formula("(SELECT a.jobName FROM T_PT_Job a WHERE a.jobId=jobId )")
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@FieldInfo(name = "岗位级别",type="varchar(2)",explain="用户的岗位级别")
	@Formula("(SELECT a.orderIndex FROM T_PT_Job a WHERE a.jobId=jobId )")
	private Integer jobLevel;

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	@FieldInfo(name = "部门全称",type="nvarchar(500)",explain="用户的部门全称")
	@Formula("(SELECT a.allDeptName FROM dbo.T_PT_Department a WHERE a.deptId=deptId )")
	private String allDeptName;

	public String getAllDeptName() {
		return allDeptName;
	}

	public void setAllDeptName(String allDeptName) {
		this.allDeptName = allDeptName;
	}
	
	@FieldInfo(name = "部门全称id",type="varchar(MAX)",explain="用户的部门全称id")
	@Formula("(SELECT a.treeIds FROM dbo.T_PT_Department a WHERE a.deptId=deptId )")
	private String treeIds;

	public String getTreeIds() {
		return treeIds;
	}

	public void setTreeIds(String treeIds) {
		this.treeIds = treeIds;
	}
	
	@FieldInfo(name = "姓名",type="varchar(36)",explain="用户的姓名")
	@Formula("(SELECT a.name FROM dbo.T_PT_User a WHERE a.userId=userId )")
	private String name;

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	
	@FieldInfo(name = "用户编号",type="varchar(18)",explain="用户编号")
	@Formula("(SELECT a.userNumb FROM dbo.T_PT_User a WHERE a.userId=userId )")
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}
	
	
	
	public BaseUserdeptjob() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseUserdeptjob(String uuid) {
		super(uuid);
		// TODO Auto-generated constructor stub
	}
}