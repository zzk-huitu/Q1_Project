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
 * ClassName: BaseTUser Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 用户管理实体类. date: 2016-07-17
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_User")
@AttributeOverride(name = "userId", column = @Column(name = "userId", length = 20, nullable = false) )
@Inheritance(strategy = InheritanceType.JOINED)
public class SysUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "账号",type="nvarchar(16)",explain="用户的账号")
	@Column(name = "userName", columnDefinition="nvarchar(16)", nullable = false)
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	@FieldInfo(name = "密码",type="nvarchar(128)",explain="用户的密码（默认是123456）")
	@Column(name = "userPwd", columnDefinition="nvarchar(128)", nullable = false)
	private String userPwd;

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd() {
		return userPwd;
	}

	@FieldInfo(name = "状态",type="nvarchar(4)",explain="用户的账户状态")
	@Column(name = "state", columnDefinition="nvarchar(4) defalut ''", nullable = true)
	private String state;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@FieldInfo(name = "是否系统 1=非内置 0=内置",type="boolean",explain="是否是系统用户")
	@Column(name = "issystem", columnDefinition="defalut 0", nullable = true)
	private boolean issystem;

	public void setIssystem(boolean issystem) {
		this.issystem = issystem;
	}

	public boolean getIssystem() {
		return issystem;
	}

	@FieldInfo(name = "身份 0=内部用户 1=老师 2=学生 3=家长 ",type="varchar(2)",explain="用户的当前身份")
	@Column(name = "category", columnDefinition="varchar(2) defalut ''", nullable = true)
	private String category;

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	@FieldInfo(name = "最后登录时间",type="datetime",explain="用户的最后登录时间")
	@Column(name = "loginTime", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date loginTime;

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	@JsonIgnore
	@FieldInfo(name = "用户隶属角色",type="Set<SysRole>",explain="多对多实体关系映射，生成一个中间表T_PT_RoleUser")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_RoleUser", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<SysRole> sysRoles = new HashSet<SysRole>();

	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
    @FieldInfo(name = "学校ID",type="varchar(20)",explain="用户所在的学校id")
	@Column(name = "schoolId", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@FieldInfo(name = "是否隐藏,0-不隐藏 1-隐藏",type="boolean",explain="是否隐藏")
	@Column(name = "isHidden",columnDefinition="defalut 0" , nullable = true)
	private boolean isHidden;

	public boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	// 默认设置为本部门
	@FieldInfo(name = "部门权限类型 0-所有权限 1-指定部门（默认包含了本部门和岗位主管的部门）  2-本部门",type="byte",explain="用户拥有的部门权限类型")
	@Column(name = "rightType",columnDefinition="defalut 0", nullable = true)
	private byte rightType;

	public byte getRightType() {
		return rightType;
	}

	public void setRightType(byte rightType) {
		this.rightType = rightType;
	}

	/**
	 * CATEGORY=1 对应老师的工号(gh) CATEGORY=2 对应老师的工号(xh)
	 */
	@FieldInfo(name = "用户编号",type="nvarchar(18)",explain="用户编号（对应老师的工号和对应学生的学号）")
	@Column(name = "userNumb", columnDefinition="nvarchar(18) defalut ''", nullable = true)
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	@FieldInfo(name = "姓名",type="nvarchar(36)",explain="用户的姓名")
	@Column(name = "name",  columnDefinition="nvarchar(36)", nullable = false)
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@FieldInfo(name = "性别码",type="nvarchar(10)",explain="用户的性别码")
	@Column(name = "genderCode",  columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String genderCode;

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderCode() {
		return genderCode;
	}

	@FieldInfo(name = "出生日期",type="datetime",explain="用户的出生日期")
	@Column(name = "birthData",  columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private Date birthData;

	public void setBirthData(Date birthData) {
		this.birthData = birthData;
	}

	public Date getBirthData() {
		return birthData;
	}

	@FieldInfo(name = "身份证件号",type="varchar(20）",explain="用户的身份证件号")
	@Column(name = "personalIdentityDocument", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String personalIdentityDocument;

	@FieldInfo(name = "政治面貌",type="nvarchar(10)",explain="用户的政治面貌")
	@Column(name = "politicalStatus", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String politicalStatus;

	@FieldInfo(name = "移动电话",type="varchar(64)",explain="用户的移动电话")
	@Column(name = "mobilePhone",  columnDefinition="varchar(64) defalut ''", nullable = true)
	private String mobilePhone;

	@FieldInfo(name = "电子信箱",type="varchar(40)",explain="用户的电子信箱")
	@Column(name = "email",  columnDefinition="varchar(40) defalut ''", nullable = true)
	private String email;

	@FieldInfo(name = "人员编制类型",type="nvarchar(40)",explain="用户的人员编制类型")
	@Column(name = "headCountType",  columnDefinition="nvarchar(40) defalut ''", nullable = true)
	private String headCountType;
	


	public String getPersonalIdentityDocument() {
		return personalIdentityDocument;
	}

	public void setPersonalIdentityDocument(String personalIdentityDocument) {
		this.personalIdentityDocument = personalIdentityDocument;
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

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 *
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "下次自动登录")
	@Transient
	private boolean rememberMe;

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@FieldInfo(name = "主部门ID",type="varchar(20)",explain="用户的主部门ID")
	@Formula("(SELECT ISNULL(a.deptId,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptjobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String deptId;

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	@FieldInfo(name = "主部门名称",type="nvarchar(36)",explain="用户的主部门名称")
	@Formula("(SELECT ISNULL(a.deptName,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@FieldInfo(name = "主岗位ID",type="varchar(20)",explain="用户的主岗位ID")
	@Formula("(SELECT ISNULL(a.jobId,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@FieldInfo(name = "主岗位名称",type="nvarchar(36)",explain="用户的主岗位名称")
	@Formula("(SELECT ISNULL(a.jobName,'') FROM T_PT_DeptJob a WHERE a.deptJobId="
			+ "(SELECT b.deptJobId FROM T_PT_UseDeptJob b WHERE b.mainDept=1 AND  b.userId=userId AND b.isDelete=0))")
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@FieldInfo(name = "学校名称",type="nvarchar(36)",explain="当前用户所属的学校名称")
	@Formula("(SELECT a.nodeText from T_PT_Department a where a.deptId=schoolId)")
	private String schoolName;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@FieldInfo(name = "当前学年")
	@Transient
	private Integer studyYear;

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	@FieldInfo(name = "当前学期")
	@Transient
	private String semester;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	@FieldInfo(name = "当前学年名称")
	@Transient
	private String studyYearname;

	public String getStudyYearname() {
		return studyYearname;
	}

	public void setStudyYearname(String studyYearname) {
		this.studyYearname = studyYearname;
	}

	@FieldInfo(name = "UP卡流水号",type="nvarchar(36)",explain="用户的UP卡流水号")
	@Formula("(SELECT top 1 a.cardNo FROM T_PT_Card a where a.userId=userId order by a.createTime desc)")
	private Long upCardId;

	public Long getUpCardId() {
		return upCardId;
	}

	public void setUpCardId(Long upCardId) {
		this.upCardId = upCardId;
	}

	@FieldInfo(name = "UP卡状态",type="nvarchar(36)",explain="用户的UP卡状态(1正常 2挂失 3注销 4换卡 7冻结)")
	@Formula("(SELECT top 1 a.cardStatusId FROM T_PT_Card a where a.userId=userId order by a.createTime desc)")
	private Integer useState;

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public SysUser() {
		super();
	}

	public SysUser(String uuid) {
		super(uuid);
	}
	
}