package com.yc.q1.base.pt.basic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.base.pt.system.model.User;
import com.zd.core.annotation.FieldInfo;

/**
 * 教职工基本信息
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_TeacherBaseInfo")
public class TeacherBaseInfo extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "英文姓名", type = "varchar(32) DEFAULT ''", explain = "教师的英文姓名")
	@Column(name = "englishName", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String englishName;

	@FieldInfo(name = "姓名拼音", type = "varchar(32) DEFAULT ''", explain = "教师的姓名拼音")
	@Column(name = "spellName", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String spellName;

	@FieldInfo(name = "曾用名", type = "nvarchar(16) DEFAULT ''", explain = "教师的曾用名")
	@Column(name = "userdName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String userdName;

	@FieldInfo(name = "出生地码", type = "varchar(10) DEFAULT ''", explain = "教师的出生地码（字典码）")
	@Column(name = "bornplace", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String bornplace;

	@FieldInfo(name = "国籍/地区码", type = "varchar(10) DEFAULT ''", explain = "教师的国籍/地区码（字典码）")
	@Column(name = "nationality", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String nationality;

	@FieldInfo(name = "籍贯", type = "varchar(10) DEFAULT ''", explain = "教师的籍贯（字典码）")
	@Column(name = "nativePlace", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String nativePlace;

	@FieldInfo(name = "户口所在地", type = "nvarchar(128) DEFAULT ''", explain = "教师的户口所在地")
	@Column(name = "hkadr", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String hkadr;

	@FieldInfo(name = "户口性质码", type = "varchar(10) DEFAULT ''", explain = "教师的户口性质码（字典码）")
	@Column(name = "AccountPropertyCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String AccountPropertyCode;

	@FieldInfo(name = "民族码", type = "varchar(10) DEFAULT ''", explain = "教师的民族码（字典码）")
	@Column(name = "folkCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String folkCode;

	@FieldInfo(name = "身份证件类型码", type = "varchar(10) DEFAULT ''", explain = "教师的身份证件类型码（字典码）")
	@Column(name = "identityTypeCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String identityTypeCode;

	@FieldInfo(name = "婚姻状况码", type = "varchar(10) DEFAULT ''", explain = "教师婚姻状况码（字典码）")
	@Column(name = "marriageCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String marriageCode;

	@FieldInfo(name = "港澳台侨外码", type = "varchar(10) DEFAULT ''", explain = "教师的港澳台侨外码（字典码）")
	@Column(name = "foreignCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String foreignCode;

	@FieldInfo(name = "健康状况码", type = "varchar(10) DEFAULT ''", explain = "教师的健康状况码（字典码）")
	@Column(name = "healthCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String healthCode;

	@FieldInfo(name = "信仰宗教码", type = "varchar(10) DEFAULT ''", explain = "教师的信仰宗教码（字典码）")
	@Column(name = "religiousCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String religiousCode;

	@FieldInfo(name = "血型码", type = "varchar(10) DEFAULT ''", explain = "教师的血型码（字典码）")
	@Column(name = "bloodType", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String bloodType;

	@FieldInfo(name = "照片", type = "varchar(220) DEFAULT ''", explain = "学生的照片")
	@Column(name = "photo", columnDefinition = "varchar(200) DEFAULT ''", nullable = true)
	private String photo;

	@FieldInfo(name = "身份证件有效期", type = "nvarchar(16) DEFAULT ''", explain = "教师的身份证件有效期")
	@Column(name = "identityTimeLimit", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String identityTimeLimit;

	@FieldInfo(name = "家庭住址", type = "nvarchar(128) DEFAULT ''", explain = "教师的家庭住址")
	@Column(name = "homeAddress", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String homeAddress;

	@FieldInfo(name = "目前居住地", type = "nvarchar(128) DEFAULT ''", explain = "教师的目前居住地")
	@Column(name = "liveLocation", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String liveLocation;

	@FieldInfo(name = "学历码", type = "varchar(10) DEFAULT ''", explain = "教师的学历码（字典码）")
	@Column(name = "degreeCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String degreeCode;

	@FieldInfo(name = "参加工作年月", type = "nvarchar(16) DEFAULT ''", explain = "教师的参加工作年月")
	@Column(name = "takeJobYears", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String takeJobYears;

	@FieldInfo(name = "来校年月", type = "nvarchar(16) DEFAULT ''", explain = "教师的来校年月")
	@Column(name = "comeSchoolYears", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String comeSchoolYears;

	@FieldInfo(name = "从教年月", type = "nvarchar(16) DEFAULT ''", explain = "教师的从教年月")
	@Column(name = "teachYears", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String teachYears;

	@FieldInfo(name = "编制类别码", type = "varchar(10) DEFAULT ''", explain = "教师的编制类别码（字典码）")
	@Column(name = "BZLBM", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String compileTypeCode;

	@FieldInfo(name = "档案编号", type = "nvarchar(10) DEFAULT ''", explain = "教师的档案编号")
	@Column(name = "fileNumber", columnDefinition = "nvarchar(10) DEFAULT ''", nullable = true)
	private String fileNumber;

	@FieldInfo(name = "档案文本", type = "nvarchar(1024) DEFAULT ''", explain = "教师的档案文本")
	@Column(name = "textFiles", columnDefinition = "nvarchar(1024) DEFAULT ''", nullable = true)
	private String textFiles;

	@FieldInfo(name = "通信地址", type = "nvarchar(128) DEFAULT ''", explain = "教师的通信地址")
	@Column(name = "mailingAddress", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String mailingAddress;

	@FieldInfo(name = "联系电话", type = "varchar(16) DEFAULT ''", explain = "教师的联系电话")
	@Column(name = "telePhone", columnDefinition = "varchar(16) DEFAULT ''", nullable = true)
	private String telePhone;

	@FieldInfo(name = "邮政编码", type = "varchar(6) DEFAULT ''", explain = "教师的邮政编码（字典码）")
	@Column(name = "postCode", columnDefinition = "varchar(6) DEFAULT ''", nullable = true)
	private String postCode;

	@FieldInfo(name = "主页地址", type = "varchar(64) DEFAULT ''", explain = "教师的主页地址")
	@Column(name = "homePage", columnDefinition = "varchar(64) DEFAULT ''", nullable = true)
	private String homePage;

	@FieldInfo(name = "特长", type = "nvarchar(128) DEFAULT ''", explain = "教师的特长")
	@Column(name = "speciality ", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String speciality;

	@FieldInfo(name = "岗位职业码", type = "varchar(10) DEFAULT ''", explain = "教师的岗位职业码（字典码）")
	@Column(name = "postJobCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String postJobCode;

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public String getUserdName() {
		return userdName;
	}

	public void setUserdName(String userdName) {
		this.userdName = userdName;
	}

	public String getBornplace() {
		return bornplace;
	}

	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getHkadr() {
		return hkadr;
	}

	public void setHkadr(String hkadr) {
		this.hkadr = hkadr;
	}

	public String getAccountPropertyCode() {
		return AccountPropertyCode;
	}

	public void setAccountPropertyCode(String accountPropertyCode) {
		AccountPropertyCode = accountPropertyCode;
	}

	public String getFolkCode() {
		return folkCode;
	}

	public void setFolkCode(String folkCode) {
		this.folkCode = folkCode;
	}

	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public void setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
	}

	public String getMarriageCode() {
		return marriageCode;
	}

	public void setMarriageCode(String marriageCode) {
		this.marriageCode = marriageCode;
	}

	public String getForeignCode() {
		return foreignCode;
	}

	public void setForeignCode(String foreignCode) {
		this.foreignCode = foreignCode;
	}

	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}

	public String getReligiousCode() {
		return religiousCode;
	}

	public void setReligiousCode(String religiousCode) {
		this.religiousCode = religiousCode;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

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

	public TeacherBaseInfo() {
		super();
	}

	public TeacherBaseInfo(String id) {
		super(id);
	}

}