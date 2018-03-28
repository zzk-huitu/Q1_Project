package com.zd.school.plartform.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeDeserializer;
import com.zd.core.util.DateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@AttributeOverride(name = "userId", column = @Column(name = "userId", length = 36, nullable = false) )
@Inheritance(strategy = InheritanceType.JOINED)
public class SysUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * @FieldInfo(name = "部门ID")
	 * 
	 * @Column(name = "DEPT_ID", length = 36, nullable = true) private String
	 * deptId;
	 * 
	 * public void setDeptId(String deptId) { this.deptId = deptId; }
	 * 
	 * public String getDeptId() { return deptId; }
	 */

	@FieldInfo(name = "账号")
	@Column(name = "userName", length = 16, nullable = false)
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	@FieldInfo(name = "密码")
	@Column(name = "userPwd", length = 128, nullable = false)
	private String userPwd;

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPwd() {
		return userPwd;
	}

	@FieldInfo(name = "状态")
	@Column(name = "state", length = 4, nullable = true)
	private String state;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@FieldInfo(name = "是否系统 1=非内置 0=内置")
	@Column(name = "issystem", length = 10, nullable = true)
	private Integer issystem;

	public void setIssystem(Integer issystem) {
		this.issystem = issystem;
	}

	public Integer getIssystem() {
		return issystem;
	}

	@FieldInfo(name = "身份 0=内部用户 1=老师 2=学生 3=家长 ")
	@Column(name = "category", length = 10, nullable = true)
	private String category;

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	@FieldInfo(name = "最后登录时间")
	@Column(name = "loginTime", length = 23, nullable = true)
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
	@FieldInfo(name = "用户隶属角色")
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

	/*
	 * @JsonIgnore
	 * 
	 * @FieldInfo(name = "用户所属部门")
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "SYS_T_USERDEPT", joinColumns = { @JoinColumn(name =
	 * "USER_ID") }, inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "DEPT_ID") })
	 * 
	 * @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	 * private Set<BaseOrg> userDepts = new HashSet<BaseOrg>();
	 * 
	 * public Set<BaseOrg> getUserDepts() { return userDepts; }
	 * 
	 * public void setUserDepts(Set<BaseOrg> userDepts) { this.userDepts =
	 * userDepts; }
	 * 
	 * @JsonIgnore
	 * 
	 * @FieldInfo(name = "用户所在岗位")
	 * 
	 * @ManyToMany(fetch = FetchType.LAZY)
	 * 
	 * @JoinTable(name = "SYS_T_USERJOB", joinColumns = { @JoinColumn(name =
	 * "USER_ID") }, inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "JOB_ID") })
	 * 
	 * @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	 * private Set<BaseJob> userJobs = new HashSet<BaseJob>();
	 * 
	 * public Set<BaseJob> getUserJobs() { return userJobs; }
	 * 
	 * public void setUserJobs(Set<BaseJob> userJobs) { this.userJobs =
	 * userJobs; }
	 */

	@FieldInfo(name = "学校ID")
	@Column(name = "schoolId", length = 36, nullable = true)
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@FieldInfo(name = "是否隐藏,0-不隐藏 1-隐藏")
	@Column(name = "isHidden", length = 10, nullable = true)
	private String isHidden;

	public String getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	// 默认设置为本部门
	@FieldInfo(name = "部门权限类型 0-所有权限 1-指定部门（默认包含了本部门和岗位主管的部门）  2-本部门")
	@Column(name = "rightType", nullable = true)
	private Integer rightType;

	public Integer getRightType() {
		return rightType;
	}

	public void setRightType(Integer rightType) {
		this.rightType = rightType;
	}

	/**
	 * CATEGORY=1 对应老师的工号(gh) CATEGORY=2 对应学生的学号(xh)
	 */
	@FieldInfo(name = "用户编号")
	@Column(name = "userNumb", length = 16, nullable = true)
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	@FieldInfo(name = "姓名")
	@Column(name = "name", length = 36, nullable = false)
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@FieldInfo(name = "性别码")
	@Column(name = "genderCode", length = 10, nullable = true)
	private String genderCode;

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderCode() {
		return genderCode;
	}

	@FieldInfo(name = "出生日期")
	@Column(name = "birthData", length = 23, nullable = true)
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

	@FieldInfo(name = "身份证件号")
	@Column(name = "personalIdentityDocument", length = 20, nullable = true)
	private String personalIdentityDocument;

	@FieldInfo(name = "政治面貌")
	@Column(name = "politicalStatus", length = 10, nullable = true)
	private String politicalStatus;

	@FieldInfo(name = "移动电话")
	@Column(name = "mobilePhone", length = 64, nullable = true)
	private String mobilePhone;

	@FieldInfo(name = "电子信箱")
	@Column(name = "email", length = 40, nullable = true)
	private String email;

	@FieldInfo(name = "人员编制类型")
	@Column(name = "headCountType", length = 40, nullable = true)
	private String headCountType;
	

	/*
	 * @FieldInfo(name = "主岗位ID")
	 * 
	 * @Column(name = "JOB_ID", length = 36, nullable = true) private String
	 * jobId;
	 * 
	 * public String getJobId() { return jobId; }
	 * 
	 * public void setJobId(String jobId) { this.jobId = jobId; }
	 * 
	 * @FieldInfo(name = "主岗位编码")
	 * 
	 * @Column(name = "JOB_CODE", length = 16, nullable = true) private String
	 * jobCode;
	 * 
	 * public String getJobCode() { return jobCode; }
	 * 
	 * public void setJobCode(String jobCode) { this.jobCode = jobCode; }
	 * 
	 * @FieldInfo(name = "主岗位名称")
	 * 
	 * @Column(name = "JOB_NAME", length = 36, nullable = true) private String
	 * jobName;
	 * 
	 * public String getJobName() { return jobName; }
	 * 
	 * public void setJobName(String jobName) { this.jobName = jobName; }
	 */

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

	@FieldInfo(name = "主部门ID")
	@Formula("(SELECT ISNULL(a.DEPT_ID,'') FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=(SELECT b.DEPTJOB_ID FROM BASE_T_USERDEPTJOB b WHERE b.MASTER_DEPT=1 AND  b.USER_ID=USER_ID AND b.ISDELETE=0))")
	private String deptId;

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	@FieldInfo(name = "主部门名称")
	@Formula("(SELECT ISNULL(a.DEPT_NAME,'') FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=(SELECT b.DEPTJOB_ID FROM BASE_T_USERDEPTJOB b WHERE b.MASTER_DEPT=1 AND  b.USER_ID=USER_ID AND b.ISDELETE=0))")
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@FieldInfo(name = "主岗位ID")
	@Formula("(SELECT ISNULL(a.JOB_ID,'') FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=(SELECT b.DEPTJOB_ID FROM BASE_T_USERDEPTJOB b WHERE b.MASTER_DEPT=1 AND  b.USER_ID=USER_ID AND b.ISDELETE=0))")
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@FieldInfo(name = "主岗位名称")
	@Formula("(SELECT ISNULL(a.JOB_NAME,'') FROM BASE_T_DEPTJOB a WHERE a.DEPTJOB_ID=(SELECT b.DEPTJOB_ID FROM BASE_T_USERDEPTJOB b WHERE b.MASTER_DEPT=1 AND  b.USER_ID=USER_ID AND b.ISDELETE=0))")
	private String jobName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@FieldInfo(name = "学校名称")
	@Formula("(SELECT a.NODE_TEXT from BASE_T_ORG a where a.DEPT_ID=SCHOOL_ID)")
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

	@FieldInfo(name = "UP卡流水号")
	@Formula("(SELECT top 1 a.CARDNO FROM PT_CARD a where a.USER_ID=USER_ID order by a.CREATE_TIME desc)")
	private Long upCardId;

	public Long getUpCardId() {
		return upCardId;
	}

	public void setUpCardId(Long upCardId) {
		this.upCardId = upCardId;
	}

	@FieldInfo(name = "UP卡状态")
	@Formula("(SELECT top 1 a.CARDSTATUSID FROM PT_CARD a where a.USER_ID=USER_ID order by a.CREATE_TIME desc)")
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
	/*
	 * @FieldInfo(name = "所有岗位ID")
	 * 
	 * @Transient private String allJobId;
	 * 
	 * public String getAllJobId() { return allJobId; }
	 * 
	 * public void setAllJobId(String allJobId) { this.allJobId = allJobId; }
	 * 
	 * @FieldInfo(name = "所有岗位名称") // @Transient
	 * 
	 * @Formula("(SELECT dbo.fn_GetUserJobNames(user_id))") private String
	 * allJobName;
	 * 
	 * public String getAllJobName() { return allJobName; }
	 * 
	 * public void setAllJobName(String allJobName) { this.allJobName =
	 * allJobName; }
	 * 
	 * @FieldInfo(name = "所有部门名称")
	 * 
	 * @Formula("(SELECT dbo.fn_GetUserDeptNames(user_id))") private String
	 * allDeptName;
	 * 
	 * public String getAllDeptName() { return allDeptName; }
	 * 
	 * public void setAllDeptName(String allDeptName) { this.allDeptName =
	 * allDeptName; }
	 */
}