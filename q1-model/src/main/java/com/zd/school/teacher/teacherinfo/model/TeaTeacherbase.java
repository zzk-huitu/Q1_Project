package com.zd.school.teacher.teacherinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.util.DateTimeSerializer;
import com.zd.school.plartform.system.model.SysUser;

/**
 * 
 * ClassName: TeaTeacherbase Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 教职工基本数据实体类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_TeacherBaseInfo")
// @AttributeOverride(name = "teacherBaseInfoId", column = @Column(name =
// "teacherBaseInfoId", length = 20, nullable = false))
public class TeaTeacherbase extends SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@FieldInfo(name = "英文姓名")
	@Column(name = "englishName", columnDefinition="nvarchar(60) defalut ''", nullable = true)
	private String englishName;

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@FieldInfo(name = "姓名拼音")
	@Column(name = "spellName", columnDefinition="nvarchar(60) defalut ''", nullable = true)
	private String spellName;

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	@FieldInfo(name = "曾用名")
	@Column(name = "userdName", columnDefinition="nvarchar(32) defalut ''", nullable = true)
	private String userdName;

	public String getUserdName() {
		return userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	@FieldInfo(name = "出生地码")
	@Column(name = "bornplace", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String bornplace;

	public String getBornplace() {
		return bornplace;
	}

	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}

	@FieldInfo(name = "国籍/地区码")
	@Column(name = "nationality", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String nationality;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@FieldInfo(name = "籍贯")
	@Column(name = "nativePlace", columnDefinition="nvarchar(20) defalut ''", nullable = true)
	private String nativePlace;

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@FieldInfo(name = "户口所在地")
	@Column(name = "hkadr", columnDefinition="nvarchar(180) defalut ''", nullable = true)
	private String hkadr;

	public String getHkadr() {
		return hkadr;
	}

	public void setHkadr(String hkadr) {
		this.hkadr = hkadr;
	}

	@FieldInfo(name = "户口性质码")
	@Column(name = "AccountPropertyCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String AccountPropertyCode;

	public String getAccountPropertyCode() {
		return AccountPropertyCode;
	}

	public void setAccountPropertyCode(String accountPropertyCode) {
		AccountPropertyCode = accountPropertyCode;
	}

	@FieldInfo(name = "民族码")
	@Column(name = "folkCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String folkCode;

	public String getFolkCode() {
		return folkCode;
	}

	public void setFolkCode(String folkCode) {
		this.folkCode = folkCode;
	}

	@FieldInfo(name = "身份证件类型码")
	@Column(name = "identityTypeCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String identityTypeCode;

	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public void setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
	}

	@FieldInfo(name = "身份证件号")
	@Column(name = "personalIdentityDocument", columnDefinition="nvarchar(20) defalut ''", nullable = true)
	private String personalIdentityDocument;

	public String getPersonalIdentityDocument() {
		return personalIdentityDocument;
	}

	public void setPersonalIdentityDocument(String personalIdentityDocument) {
		this.personalIdentityDocument = personalIdentityDocument;
	}

	@FieldInfo(name = "婚姻状况码")
	@Column(name = "marriageCode",columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String marriageCode;

	public void setMarriageCode(String marriageCode) {
		this.marriageCode = marriageCode;
	}

	public String getMarriageCode() {
		return marriageCode;
	}

	@FieldInfo(name = "港澳台侨外码")
	@Column(name = "foreignCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String foreignCode;

	public String getForeignCode() {
		return foreignCode;
	}

	public void setForeignCode(String foreignCode) {
		this.foreignCode = foreignCode;
	}

	@FieldInfo(name = "政治面貌码GB/T 4762")
	@Column(name = "politicsCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String politicsCode;

	public void setPoliticsCode(String politicsCode) {
		this.politicsCode = politicsCode;
	}

	public String getPoliticsCode() {
		return politicsCode;
	}

	@FieldInfo(name = "健康状况码")
	@Column(name = "healthCode",columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String healthCode;

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	@FieldInfo(name = "信仰宗教码")
	@Column(name = "religiousCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String religiousCode;

	public String getReligiousCode() {
		return religiousCode;
	}

	public void setReligiousCode(String religiousCode) {
		this.religiousCode = religiousCode;
	}

	@FieldInfo(name = "血型码")
	@Column(name = "bloodType", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String bloodType;

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getbloodType() {
		return bloodType;
	}

	@FieldInfo(name = "照片")
	@Column(name = "photo",columnDefinition="nvarchar(200) defalut ''", nullable = true)
	private String photo;

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	@FieldInfo(name = "身份证件有效期")
	@Column(name = "identityTimeLimit", columnDefinition="nvarchar(17) defalut ''", nullable = true)
	private String identityTimeLimit;

	@FieldInfo(name = "家庭住址")
	@Column(name = "homeAddress", columnDefinition="nvarchar(180) defalut ''", nullable = true)
	private String homeAddress;

	@FieldInfo(name = "现住址")
	@Column(name = "liveLocation", columnDefinition="nvarchar(180) defalut ''", nullable = true)
	private String liveLocation;

	@FieldInfo(name = "学历码")
	@Column(name = "degreeCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String degreeCode;

	@FieldInfo(name = "参加工作年月")
	@Column(name = "takeJobYears", columnDefinition="nvarchar(16) defalut ''", nullable = true)
	private String takeJobYears;

	@FieldInfo(name = "来校年月")
	@Column(name = "comeSchoolYears", columnDefinition="nvarchar(16) defalut ''", nullable = true)
	private String comeSchoolYears;

	@FieldInfo(name = "从教年月")
	@Column(name = "teachYears", columnDefinition="nvarchar(16) defalut ''", nullable = true)
	private String teachYears;

	@FieldInfo(name = "编制类别码")
	@Column(name = "BZLBM", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String compileTypeCode;

	@FieldInfo(name = "档案编号")
	@Column(name = "fileNumber",columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String fileNumber;

	@FieldInfo(name = "档案文本")
	@Column(name = "textFiles", columnDefinition="nvarchar(128) defalut ''", nullable = true)
	private String textFiles;

	@FieldInfo(name = "通信地址")
	@Column(name = "mailingAddress", columnDefinition="nvarchar(180) defalut ''", nullable = true)
	private String mailingAddress;

	@FieldInfo(name = "联系电话")
	@Column(name = "telePhone", columnDefinition="nvarchar(30) defalut ''", nullable = true)
	private String telePhone;

	@FieldInfo(name = "邮政编码")
	@Column(name = "postCode", columnDefinition="nvarchar(6) defalut ''", nullable = true)
	private String postCode;

	@FieldInfo(name = "电子信箱")
	@Column(name = "email", columnDefinition="nvarchar(40) defalut ''", nullable = true)
	private String email;

	@FieldInfo(name = "主页地址")
	@Column(name = "homePage", columnDefinition="nvarchar(60) defalut ''", nullable = true)
	private String homePage;

	@FieldInfo(name = "特长")
	@Column(name = "speciality ", columnDefinition="nvarchar(128) defalut ''", nullable = true)
	private String speciality;

	@FieldInfo(name = "岗位职业码")
	@Column(name = "postJobCode", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String postJobCode;

	@FieldInfo(name = "主要任课学段")
	@Column(name = "mianCourseSection", columnDefinition="nvarchar(10) defalut ''", nullable = true)
	private String mianCourseSection;

	public String getIdentityTimeLimit() {
		return identityTimeLimit;
	}

	public void setIdentityTimeLimit(String identityTimeLimit) {
		this.identityTimeLimit = identityTimeLimit;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getLiveLocation() {
		return liveLocation;
	}

	public void setLiveLocation(String liveLocation) {
		this.liveLocation = liveLocation;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getTakeJobYears() {
		return takeJobYears;
	}

	public void setTakeJobYears(String takeJobYears) {
		this.takeJobYears = takeJobYears;
	}

	public String getComeSchoolYears() {
		return comeSchoolYears;
	}

	public void setComeSchoolYears(String comeSchoolYears) {
		this.comeSchoolYears = comeSchoolYears;
	}

	public String getTeachYears() {
		return teachYears;
	}

	public void setTeachYears(String teachYears) {
		this.teachYears = teachYears;
	}

	public String getCompileTypeCode() {
		return compileTypeCode;
	}

	public void setCompileTypeCode(String compileTypeCode) {
		this.compileTypeCode = compileTypeCode;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getTextFiles() {
		return textFiles;
	}

	public void setTextFiles(String textFiles) {
		this.textFiles = textFiles;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPostJobCode() {
		return postJobCode;
	}

	public void setPostJobCode(String postJobCode) {
		this.postJobCode = postJobCode;
	}

	public String getMianCourseSection() {
		return mianCourseSection;
	}

	public void setMianCourseSection(String mianCourseSection) {
		this.mianCourseSection = mianCourseSection;
	}

	public Date getBirthdDate() {
		return birthdDate;
	}

	public void setBirthdDate(Date birthdDate) {
		this.birthdDate = birthdDate;
	}

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getBloodType() {
		return bloodType;
	}

	@FieldInfo(name = "主讲课程")
	@Column(name = "mainCourse", length = 36, nullable = true)
	private String mainCourse;

	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}

	public String getMainCourse() {
		return mainCourse;
	}

	@Formula("(SELECT a.COURSE_NAME FROM  JW_T_BASECOURSE a WHERE a.BASECOURSE_ID=MAIN_COURSE )")
	private String mainCourseName;

	public void setMainCourseName(String mainCourse) {
		this.mainCourseName = mainCourse;
	}

	public String getMainCourseName() {
		return mainCourseName;
	}

	@FieldInfo(name = "出生日期")
	@Column(name = "birthdDate", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date birthdDate;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "老师工号")
	@Formula("(SELECT a.USER_NUMB FROM SYS_T_USER a WHERE a.USER_ID=USER_ID )")
	private String teacherNum;

	@Transient
	@FieldInfo(name = "老师姓名")
	// @Formula("(SELECT a.xm FROM SYS_T_USER a WHERE a.USER_ID=USER_ID )")
	private String teacherName;

}