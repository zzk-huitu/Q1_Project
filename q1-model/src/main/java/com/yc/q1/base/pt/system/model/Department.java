package com.yc.q1.base.pt.system.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.TreeNodeEntity;

/**
 * 部门
 * 
 * 注：courseId：旧版使用备用字段1作为学科的Id
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Department")
@AttributeOverride(name = "id", column = @Column(name = "deptId", length = 20, nullable = false) )
public class Department extends TreeNodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "是否系统内置", type = "bit NOT NULL", explain = "部门是否系统内置（ 0-非系统内置 1-系统内置 ）")
	@Column(name = "isSystem", nullable = false)
	private Boolean isSystem;

	@FieldInfo(name = "传真", type = "varchar(20) DEFAULT '", explain = "部门的传真号码")
	@Column(name = "fax", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String fax;

	@FieldInfo(name = "内线电话", type = "varchar(20) DEFAULT ''", explain = "部门的内线电话")
	@Column(name = "inPhone", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String inPhone;

	@FieldInfo(name = "外线电话", type = "varchar(20)  DEFAULT ''", explain = "部门的外线电话")
	@Column(name = "outPhone", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String outPhone;

	/* 2017-10-20新加入 */
	@FieldInfo(name = "学段编码", type = "varchar(10) DEFAULT ''", explain = "年级的学段编码")
	@Column(name = "sectionCode", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String sectionCode;

	/* 2017-10-20新加入 */
	@FieldInfo(name = "年级", type = "varchar(10) DEFAULT ''", explain = "年级")
	@Column(name = "grade", columnDefinition = "nvarchar(10) DEFAULT ''", nullable = true)
	private String grade;


	@FieldInfo(name = "学科id", type = "varchar(20) DEFAULT ''", explain = "学科id")
	@Column(name = "courseId", columnDefinition = "nvarchar(20) DEFAULT ''", nullable = true)
	private String courseId;

	@FieldInfo(name = "备注", type = "nvarchar(255)", explain = "备注说明")
	@Column(name = "remark", columnDefinition = "nvarchar(255) DEFAULT ''", nullable = true)
	private String remark;

	@FieldInfo(name = "部门类型", type = "varchar(2)", explain = "部门类型( 01-学校 02-校区 03-部门  04-年级  05-班级　06-学科)")
	@Column(name = "deptType", length = 2, nullable = true)
	private String deptType;

	@FieldInfo(name = "上级主管部门", type = "varchar(20) DEFAULT ''", explain = "上级主管部门")
	@Column(name = "superDept", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String superDept;

	@FieldInfo(name = "上级主管部门名称", type = "nvarchar(16) DEFAULT ''", explain = "上级主管部门名称")
	@Column(name = "superDeptName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String superDeptName;

	@FieldInfo(name = "上级主管岗位", type = "varchar(20)  DEFAULT ''", explain = "上级主管岗位")
	@Column(name = "superJob", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String superJob;

	@FieldInfo(name = "上级主管岗位名称", type = "nvarchar(16) DEFAULT ''", explain = "上级主管岗位名称")
	@Column(name = "superJobName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String superJobName;

	@FieldInfo(name = "部门全称", type = "nvarchar(500) DEFAULT ''", explain = "部门全称")
	@Column(name = "allDeptName", columnDefinition = "nvarchar(500) DEFAULT ''", nullable = true)
	private String allDeptName;

	// @FieldInfo(name = "上级部门名称",type="nvarchar(36)",explain="上级部门名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_Department a WHERE a.deptId=parentNode)")
	private String parentName;

	// @FieldInfo(name = "上级部门类型",type="nvarchar(36)",explain="上级部门类型")
	@Formula("(SELECT isnull(a.deptType,'01') FROM T_PT_Department a WHERE a.deptId=parentNode)")
	private String parentType;

	// @FieldInfo(name = "主负责岗位名称",type="nvarchar(36)",explain="主负责岗位名称")
	@Formula("(SELECT a.jobName FROM T_PT_DeptJob a WHERE a.jobType=0 and a.deptId=deptId)")
	private String mainLeaderName;

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getInPhone() {
		return inPhone;
	}

	public void setInPhone(String inPhone) {
		this.inPhone = inPhone;
	}

	public String getOutPhone() {
		return outPhone;
	}

	public void setOutPhone(String outPhone) {
		this.outPhone = outPhone;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getSuperDept() {
		return superDept;
	}

	public void setSuperDept(String superDept) {
		this.superDept = superDept;
	}

	public String getSuperDeptName() {
		return superDeptName;
	}

	public void setSuperDeptName(String superDeptName) {
		this.superDeptName = superDeptName;
	}

	public String getSuperJob() {
		return superJob;
	}

	public void setSuperJob(String superJob) {
		this.superJob = superJob;
	}

	public String getSuperJobName() {
		return superJobName;
	}

	public void setSuperJobName(String superJobName) {
		this.superJobName = superJobName;
	}

	public String getAllDeptName() {
		return allDeptName;
	}

	public void setAllDeptName(String allDeptName) {
		this.allDeptName = allDeptName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public String getMainLeaderName() {
		return mainLeaderName;
	}

	public void setMainLeaderName(String mainLeaderName) {
		this.mainLeaderName = mainLeaderName;
	}

	public Department() {
		super();
	}

	public Department(String id) {
		super(id);
	}
}