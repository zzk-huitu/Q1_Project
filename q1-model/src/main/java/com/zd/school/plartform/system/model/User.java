package com.zd.school.plartform.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeDeserializer;
import com.zd.core.util.DateTimeSerializer;

/**
 * 用户信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_User")
@AttributeOverride(name = "id", column = @Column(name = "userId", length = 20, nullable = false) )
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "账号", type = "varchar(16) NOT NULL", explain = "用户的账号")
	@Column(name = "userName", length = 16, nullable = false)
	private String userName;

	@FieldInfo(name = "用户编号", type = "varchar(16) NOT NULL", explain = "用户编号（对应老师的工号和对应学生的学号）")
	@Column(name = "userNumb", length = 16, nullable = false)
	private String userNumb;

	@FieldInfo(name = "姓名", type = "nvarchar(10) NOT NULL", explain = "用户的姓名")
	@Column(name = "name", columnDefinition = "nvarchar(10)", nullable = false)
	private String name;

	@FieldInfo(name = "密码", type = "varchar(128) NOT NULL", explain = "用户的密码（默认是123456）")
	@Column(name = "userPwd", length = 128, nullable = false)
	private String userPwd;

	@FieldInfo(name = "状态", type = "bit NOT NULL DEFAULT 0", explain = "用户的账户状态（0-锁定，1-解锁）")
	@Column(name = "state", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean state;

	@FieldInfo(name = "是否系统账户", type = "bit NOT NULL DEFAULT 0", explain = "是否是系统用户( 0=非内置，1=内置 )")
	@Column(name = "isSystem", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean isSystem;

	@FieldInfo(name = "身份", type = "varchar(1) NOT NULL DEFAULT '0'", explain = "用户的当前身份（ 0=内部用户 1=老师 2=学生 3=家长 ）")
	@Column(name = "category",  columnDefinition = "varchar(1) DEFAULT '0'", nullable = false)
	private String category;

	@FieldInfo(name = "性别码", type = "varchar(2) DEFAULT '0'", explain = "用户的性别码（数据字典）")
	@Column(name = "sex",  columnDefinition = "varchar(2) DEFAULT '0'", nullable = true)
	private String sex;

	@FieldInfo(name = "学校ID", type = "varchar(20)  DEFAULT ''", explain = "用户所在的学校id")
	@Column(name = "schoolId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String schoolId;

	@FieldInfo(name = "是否隐藏", type = "bit DEFAULT 0", explain = "是否隐藏（0-不隐藏 1-隐藏）")
	@Column(name = "isHidden", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean isHidden;

	@FieldInfo(name = "出生日期", type = "datetime", explain = "用户的出生日期")
	@Column(name = "birthDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date birthDate;

	@FieldInfo(name = "身份证件号", type = "varchar(18) DEFAULT ''", explain = "用户的身份证件号")
	@Column(name = "identityNumber",  columnDefinition = "varchar(18) DEFAULT ''", nullable = true)
	private String identityNumber;

	@FieldInfo(name = "最后登录时间", type = "datetime", explain = "用户的最后登录时间")
	@Column(name = "loginTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date loginTime;

	@JsonIgnore
	@FieldInfo(name = "用户隶属角色", type = "Set<SysRole>", explain = "多对多实体关系映射，生成一个中间表T_PT_RoleUser")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_RoleUser", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> sysRoles = new HashSet<Role>();

	// 默认设置为本部门
	@FieldInfo(name = "部门权限类型", type = "varchar(1) DEFAULT '2'", explain = "用户拥有的部门权限类型（0-所有权限，1-指定部门[默认包含了本部门和岗位主管的部门]， 2-本部门）")
	@Column(name = "rightType", columnDefinition = "varchar(1) DEFAULT '2'", nullable = true)
	private String rightType;

	@FieldInfo(name = "政治面貌", type = "varchar(10) DEFAULT ''", explain = "用户的政治面貌（数据字典）")
	@Column(name = "politicalStatus",  columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String politicalStatus;

	@FieldInfo(name = "移动电话", type = "varchar(11) DEFAULT ''", explain = "用户的移动电话")
	@Column(name = "mobilePhone", columnDefinition = "varchar(11) DEFAULT ''", nullable = true)
	private String mobilePhone;

	@FieldInfo(name = "电子信箱", type = "varchar(40) DEFAULT ''", explain = "用户的电子信箱")
	@Column(name = "email", columnDefinition = "varchar(40) DEFAULT ''", nullable = true)
	private String email;

	@FieldInfo(name = "人员编制类型", type = "varchar(10) DEFAULT ''", explain = "用户的人员编制类型")
	@Column(name = "headCountType", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String headCountType;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 *
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "下次自动登录")
	@Transient
	private Boolean rememberMe;

	// @FieldInfo(name = "主部门ID",type="varchar(20)",explain="用户的主部门ID")
	@Formula("(SELECT ISNULL(a.deptId,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptjobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String deptId;

	// @FieldInfo(name = "主部门名称",type="nvarchar(36)",explain="用户的主部门名称")
	@Formula("(SELECT ISNULL(a.deptName,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String deptName;

	// @FieldInfo(name = "主岗位ID",type="varchar(20)",explain="用户的主岗位ID")
	@Formula("(SELECT ISNULL(a.jobId,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String jobId;

	// @FieldInfo(name = "主岗位名称",type="nvarchar(36)",explain="用户的主岗位名称")
	@Formula("(SELECT ISNULL(a.jobName,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String jobName;

	// @FieldInfo(name = "学校名称",type="nvarchar(36)",explain="当前用户所属的学校名称")
	@Formula("(SELECT a.nodeText from T_PT_Department a where a.deptId=schoolId)")
	private String schoolName;

	// @FieldInfo(name = "当前学年")
	@Transient
	private Integer studyYear;

	// @FieldInfo(name = "当前学期")
	@Transient
	private String semester;

	// @FieldInfo(name = "当前学年名称")
	@Transient
	private String studyYearname;

	// @FieldInfo(name = "UP卡流水号",type="nvarchar(36)",explain="用户的UP卡流水号")
	@Formula("(SELECT top 1 a.cardNo FROM T_PT_Card a where a.userId=userId order by a.createTime desc)")
	private Long upCardId;

	// @FieldInfo(name = "UP卡状态",type="nvarchar(36)",explain="用户的UP卡状态(1正常 2挂失
	// 3注销 4换卡 7冻结)")
	@Formula("(SELECT top 1 a.cardStatusId FROM T_PT_Card a where a.userId=userId order by a.createTime desc)")
	private Integer useState;

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public Boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Set<Role> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<Role> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadCountType() {
		return headCountType;
	}

	public void setHeadCountType(String headCountType) {
		this.headCountType = headCountType;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStudyYearname() {
		return studyYearname;
	}

	public void setStudyYearname(String studyYearname) {
		this.studyYearname = studyYearname;
	}

	public Long getUpCardId() {
		return upCardId;
	}

	public void setUpCardId(Long upCardId) {
		this.upCardId = upCardId;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public User() {
		super();
	}

	public User(String id) {
		super(id);
	}

}