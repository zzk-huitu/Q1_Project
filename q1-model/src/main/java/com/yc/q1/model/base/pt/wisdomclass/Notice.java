package com.yc.q1.model.base.pt.wisdomclass;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.model.base.pt.basic.InfoTerminal;
import com.yc.q1.model.base.pt.system.Department;
import com.yc.q1.model.base.pt.system.Role;
import com.yc.q1.model.base.pt.system.User;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 通知公告
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Notice")
@AttributeOverride(name = "id", column = @Column(name = "noticeId", length = 20, nullable = false) )
public class Notice extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "公告标题", type = "nvarchar(64) NOT NULL", explain = "通知公告公告标题")
	@Column(name = "noticeTitle", columnDefinition = "nvarchar(64)", nullable = false)
	private String noticeTitle;

	@FieldInfo(name = "公告类型", type = "varchar(4) NOT NULL", explain = "通知公告公告类型(数据字典NOTICETYPE)")
	@Column(name = "noticeType", length = 4, nullable = false)
	private String noticeType;

	@FieldInfo(name = "紧急程度", type = "varchar(4) NOT NULL", explain = "通知公告紧急程度(数据字典EMERGENCY)")
	@Column(name = "emergency", length = 4, nullable = false)
	private String emergency;

	@FieldInfo(name = "公告正文", type = "nvarchar(MAX) NOT NULL", explain = "通知公告公告正文")
	@Column(name = "noticeContent", nullable = false, columnDefinition = "nvarchar(MAX)")
	private String noticeContent;

	@FieldInfo(name = "是否微信通知", type = "bit NOT NULL", explain = "公告是否微信通知(0-不通知，1-通知)")
	@Column(name = "sendWx", nullable = false)
	private Boolean sendWx;

	@FieldInfo(name = "是否需要审核", type = "varchar(1) NOT NULL", explain = "是否需要审核(0-不需要，1-需要,2-审核通过,3-审核不通过)")
	@Column(name = "isCheck", length = 1, nullable = false)
	private String isCheck;

	@FieldInfo(name = "生效日期", type = "datetime NOT NULL", explain = "公告生效日期")
	@Column(name = "beginDate", nullable = false, columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginDate;

	@FieldInfo(name = "中止日期", type = "datetime", explain = "公告中止日期")
	@Column(name = "endDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endDate;

	@JsonIgnore
	@FieldInfo(name = "公告通知人员", type = "Set<SysUser>", explain = "多对多实体关系映射，生成T_PT_NoticeUser中间表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_NoticeUser", joinColumns = { @JoinColumn(name = "noticeId") }, inverseJoinColumns = {
			@JoinColumn(name = "userId") })
	// @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<User> noticeUsers = new HashSet<User>();

	@JsonIgnore
	@FieldInfo(name = "公告通知角色", type = "Set<SysRole>", explain = "多对多实体关系映射，生成T_PT_NoticeRole中间表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_NoticeRole", joinColumns = { @JoinColumn(name = "noticeId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	// @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> noticeRoles = new HashSet<Role>();

	@JsonIgnore
	@FieldInfo(name = "公告通知部门", type = "Set<BaseOrg>", explain = "多对多实体关系映射，生成T_PT_NoticeDept中间表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_NoticeDept", joinColumns = { @JoinColumn(name = "noticeId") }, inverseJoinColumns = {
			@JoinColumn(name = "deptId") })
	// @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Department> noticeDepts = new HashSet<Department>();

	@JsonIgnore
	@FieldInfo(name = "公告发布终端", type = "Set<OaInfoterm>", explain = "多对多实体关系映射，生成T_PT_NoticeTerminal中间表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_NoticeTerminal", joinColumns = { @JoinColumn(name = "noticeId") }, inverseJoinColumns = {
			@JoinColumn(name = "terminalId") })
	// @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<InfoTerminal> noticeTerms = new HashSet<InfoTerminal>();

	@JsonIgnore
	@FieldInfo(name = "公告通知学生", type = "Set<SysUser>", explain = "多对多实体关系映射，生成T_PT_NoticeStudent中间表")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "T_PT_NoticeStudent", joinColumns = { @JoinColumn(name = "noticeId") }, inverseJoinColumns = {
			@JoinColumn(name = "userId") })
	// @Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<User> noticeStus = new HashSet<User>();

	@JsonIgnore
	@FieldInfo(name = "公告通知审核人", type = "Set<OaNoticeauditor>", explain = "一对多实体关系映射")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "oaNotice")
	//@JoinColumn(name="noticeAutitors")
	private Set<NoticeAuditor> noticeAutitors = new HashSet<NoticeAuditor>();

	/* 特意冗余字段 */
	@FieldInfo(name = "通知部门方式（1-所有部门、2-指定部门、3-不通知）", type = "varchar(1) default '3'", explain = "公告通知部门方式")
	@Column(name = "deptRadio", length = 1, columnDefinition = "varchar(1) default '3'", nullable = true)
	private String deptRadio;

	@FieldInfo(name = "通知学生方式（1-所有学生、2-指定学生、3-不通知）", type = "varchar(1) default '3'", explain = "公告通知学生方式")
	@Column(name = "studentRadio", length = 1, columnDefinition = "varchar(1) default '3'", nullable = true)
	private String studentRadio;

	@FieldInfo(name = "通知终端方式（1-所有终端、2-指定终端、3-不通知）", type = "varchar(1) default '3'", explain = "公告通知终端方式")
	@Column(name = "terminalRadio", length = 1, columnDefinition = "varchar(1) default '3'", nullable = true)
	private String terminalRadio;

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Boolean getSendWx() {
		return sendWx;
	}

	public void setSendWx(Boolean sendWx) {
		this.sendWx = sendWx;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<User> getNoticeUsers() {
		return noticeUsers;
	}

	public void setNoticeUsers(Set<User> noticeUsers) {
		this.noticeUsers = noticeUsers;
	}

	public Set<Role> getNoticeRoles() {
		return noticeRoles;
	}

	public void setNoticeRoles(Set<Role> noticeRoles) {
		this.noticeRoles = noticeRoles;
	}

	public Set<Department> getNoticeDepts() {
		return noticeDepts;
	}

	public void setNoticeDepts(Set<Department> noticeDepts) {
		this.noticeDepts = noticeDepts;
	}

	public Set<InfoTerminal> getNoticeTerms() {
		return noticeTerms;
	}

	public void setNoticeTerms(Set<InfoTerminal> noticeTerms) {
		this.noticeTerms = noticeTerms;
	}

	public Set<User> getNoticeStus() {
		return noticeStus;
	}

	public void setNoticeStus(Set<User> noticeStus) {
		this.noticeStus = noticeStus;
	}

	public Set<NoticeAuditor> getNoticeAutitors() {
		return noticeAutitors;
	}

	public void setNoticeAutitors(Set<NoticeAuditor> noticeAutitors) {
		this.noticeAutitors = noticeAutitors;
	}

	public String getDeptRadio() {
		return deptRadio;
	}

	public void setDeptRadio(String deptRadio) {
		this.deptRadio = deptRadio;
	}

	public String getStudentRadio() {
		return studentRadio;
	}

	public void setStudentRadio(String studentRadio) {
		this.studentRadio = studentRadio;
	}

	public String getTerminalRadio() {
		return terminalRadio;
	}

	public void setTerminalRadio(String terminalRadio) {
		this.terminalRadio = terminalRadio;
	}

	public Notice() {
		super();
	}

	public Notice(String id) {
		super(id);
	}

}