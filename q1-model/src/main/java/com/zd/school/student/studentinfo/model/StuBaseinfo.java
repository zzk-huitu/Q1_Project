package com.zd.school.student.studentinfo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.school.plartform.system.model.SysUser;

/**
 * 
 * ClassName: StuBaseinfo Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学生基本信息实体类. date: 2016-07-19
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_StudentBaseInfo")
// @AttributeOverride(name = "studentBaseInfoId", column = @Column(name =
// "studentBaseInfoId", length = 36, nullable = false))
public class StuBaseinfo extends SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "英文姓名", type = "varchar(60)", explain = "学生英文姓名")
	@Column(name = "englishName", columnDefinition = "varchar(60) defalut ''", nullable = true)
	private String englishName;

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@FieldInfo(name = "姓名拼音", type = "varchar(60)", explain = "学生的姓名拼音")
	@Column(name = "spellName", columnDefinition = "varchar(60) defalut ''", nullable = true)
	private String spellName;

	@FieldInfo(name = "曾用名", type = "nvarchar(36)", explain = "学生的曾用名")
	@Column(name = "userdName", columnDefinition = "nvarchar(36) defalut ''", nullable = true)
	private String userdName;

	@FieldInfo(name = "出生地码", type = "nvarchar(10)", explain = "学生的出生地码")
	@Column(name = "bornplace", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String bornplace;

	@FieldInfo(name = "籍贯", type = "nvarchar(20)", explain = "学生的籍贯")
	@Column(name = "nativePlace", columnDefinition = "nvarchar(20) defalut ''", nullable = true)
	private String nativePlace;

	@FieldInfo(name = "民族码", type = "nvarchar(10)", explain = "学生的民族码")
	@Column(name = "folkCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String folkCode;

	@FieldInfo(name = "国籍/地区码", type = "nvarchar(10)", explain = "学生的国籍/地区码")
	@Column(name = "nationality", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String nationality;

	@FieldInfo(name = "身份证件类型码", type = "nvarchar(10)", explain = "学生的身份证件类型码")
	@Column(name = "identityTypeCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String identityTypeCode;

	@FieldInfo(name = "身份证件号", type = "varchar(20)", explain = "学生的身份证件号")
	@Column(name = "personalIdentityDocument", columnDefinition = "varchar(20) defalut ''", nullable = true)
	private String personalIdentityDocument;

	@FieldInfo(name = "婚姻状况码", type = "nvarchar(10)", explain = "学生婚姻状况码")
	@Column(name = "marriageCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String marriageCode;

	@FieldInfo(name = "港澳台侨外码", type = "nvarchar(10)", explain = "学生的港澳台侨外码")
	@Column(name = "foreignCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String foreignCode;

	@FieldInfo(name = "政治面貌码", type = "nvarchar(10))", explain = "学生的政治面貌码")
	@Column(name = "politicsCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String politicsCode;

	@FieldInfo(name = "健康状况码", type = "nvarchar(10)", explain = "学生的健康状况码")
	@Column(name = "healthCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String healthCode;

	@FieldInfo(name = "信仰宗教码", type = "nvarchar(10)", explain = "学生的信仰宗教码")
	@Column(name = "religiousCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String religiousCode;

	@FieldInfo(name = "血型码", type = "nvarchar(10)", explain = "学生的血型码")
	@Column(name = "bloodType", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String bloodType;

	@FieldInfo(name = "照片", type = "nvarchar(220)", explain = "学生的照片")
	@Column(name = "photo", columnDefinition = "nvarchar(200) defalut ''", nullable = true)
	private String photo;

	@FieldInfo(name = "身份证件有效期", type = "nvarchar(17)", explain = "学生的身份证件有效期")
	@Column(name = "identityTimeLimit", columnDefinition = "nvarchar(17) defalut ''", nullable = true)
	private String identityTimeLimit;

	@FieldInfo(name = "是否独生子女", type = "boolean", explain = "学生是否独生子女")
	@Column(name = "onlyChild", nullable = true)
	private boolean onlyChild;

	@FieldInfo(name = "户口所在地行政区划码", type = "nvarchar(10)", explain = "学生的户口所在地行政区划码")
	@Column(name = "administrativeAreaCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String administrativeAreaCode;

	@FieldInfo(name = "户口类别码", type = "nvarchar(10)", explain = "学生的户口类别码")
	@Column(name = "accountTypeCode", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private String accountTypeCode;

	@FieldInfo(name = "是否流动人口", type = "nvarchar(10)", explain = "学生是否流动人口")
	@Column(name = "floatpopulation", columnDefinition = "nvarchar(10) defalut ''", nullable = true)
	private boolean floatpopulation;

	@FieldInfo(name = "特长", type = "nvarchar(128)", explain = "学生的特长")
	@Column(name = "speciality ", columnDefinition = "nvarchar(128) defalut ''", nullable = true)
	private String speciality;

	@FieldInfo(name = "学籍号", type = "nvarchar(30)", explain = "学生的学籍号")
	@Column(name = "studentCode", columnDefinition = "nvarchar(30) defalut ''", nullable = true)
	private String studentCode;

	@FieldInfo(name = "目前居住地", type = "nvarchar(120)", explain = "学生的目前居住地")
	@Column(name = "liveLocation", columnDefinition = "nvarchar(120) defalut ''", nullable = true)
	public String liveLocation;

	@FieldInfo(name = "开户银行", type = "nvarchar(128)", explain = "学生的开户银行")
	@Column(name = "depositBank", columnDefinition = "nvarchar(128) defalut ''", nullable = true)
	private String depositBank;

	@FieldInfo(name = "银行账号", type = "nvarchar(32)", explain = "学生的银行账号")
	@Column(name = "bankAccount", columnDefinition = "nvarchar(32) defalut ''", nullable = true)
	private String bankAccount;

	@FieldInfo(name = "学生状态", type = "nvarchar(4)", explain = "学生的状态")
	@Column(name = "studentState", columnDefinition = "nvarchar(4) defalut ''", nullable = true)
	private String studentState;

	/**
	 * 广州城建导入数据需要，增加如下字段 luoyibo 2016-07-19
	 */
	@FieldInfo(name = "年级ID", type = "varchar(20)", explain = "学生的年级ID")
	@Column(name = "gradeId", columnDefinition = "varchar(20) defalut ''", nullable = true)
	private String gradeId;

	@FieldInfo(name = "班级ID", type = "varchar(20)", explain = "学生的班级ID")
	@Column(name = "classId", columnDefinition = "varchar(20) defalut ''", nullable = true)
	private String classId;

	/**
	 * 00-收费 10-免费 11-全免
	 */
	@FieldInfo(name = "学生的缴费类型", type = "varchar(2)", explain = "学生的缴费类型")
	@Column(name = "stuPayType", columnDefinition = "varchar(2) defalut ''", nullable = true)
	private String stuPayType;

	@FieldInfo(name = "专业ID", type = "nvarchar(36)", explain = "学生的专业ID")
	@Column(name = "majorId", columnDefinition = "nvarchar(36) defalut ''", nullable = true)
	private String majorId;

	@FieldInfo(name = "借读方式ID", type = "nvarchar(36)", explain = "学生的借读方式ID")
	@Column(name = "transferWayId", columnDefinition = "nvarchar(36) defalut ''", nullable = true)
	private String transferWayId;

	@FieldInfo(name = "是否住校", type = "boolean", explain = "学生是否住校")
	@Column(name = "onCampus", nullable = true)
	public boolean onCampus;

	@FieldInfo(name = "毕业学校", type = "nvarchar(120)", explain = "学生是否住校")
	@Column(name = "schoolTag", columnDefinition = "nvarchar(120) defalut ''", nullable = true)
	public String schoolTag;

	@FieldInfo(name = "片区生", type = "nvarchar(36)", explain = "学生是否是片区生")
	@Column(name = "areaStudent", columnDefinition = "nvarchar(36) defalut ''", nullable = true)

	private String areaStudent;
	@FieldInfo(name = "班级名称", type = "varchar(60)", explain = "学生英文姓名")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a where a.classId=classId)")
	private String className;

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

	public String getStudentState() {
		return studentState;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getFolkCode() {
		return folkCode;
	}

	public void setFolkCode(String folkCode) {
		this.folkCode = folkCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdentityTypeCode() {
		return identityTypeCode;
	}

	public void setIdentityTypeCode(String identityTypeCode) {
		this.identityTypeCode = identityTypeCode;
	}

	public String getPersonalIdentityDocument() {
		return personalIdentityDocument;
	}

	public void setPersonalIdentityDocument(String personalIdentityDocument) {
		this.personalIdentityDocument = personalIdentityDocument;
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

	public String getPoliticsCode() {
		return politicsCode;
	}

	public void setPoliticsCode(String politicsCode) {
		this.politicsCode = politicsCode;
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

	public boolean getOnlyChild() {
		return onlyChild;
	}

	public void setOnlyChild(boolean onlyChild) {
		this.onlyChild = onlyChild;
	}

	public String getAdministrativeAreaCode() {
		return administrativeAreaCode;
	}

	public void setAdministrativeAreaCode(String administrativeAreaCode) {
		this.administrativeAreaCode = administrativeAreaCode;
	}

	public String getAccountTypeCode() {
		return accountTypeCode;
	}

	public void setAccountTypeCode(String accountTypeCode) {
		this.accountTypeCode = accountTypeCode;
	}

	public boolean getFloatpopulation() {
		return floatpopulation;
	}

	public void setFloatpopulation(boolean floatpopulation) {
		this.floatpopulation = floatpopulation;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getLiveLocation() {
		return liveLocation;
	}

	public void setLiveLocation(String liveLocation) {
		this.liveLocation = liveLocation;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getStuPayType() {
		return stuPayType;
	}

	public void setStuPayType(String stuPayType) {
		this.stuPayType = stuPayType;
	}

	public String getTransferWayId() {
		return transferWayId;
	}

	public void setTransferWayId(String transferWayId) {
		this.transferWayId = transferWayId;
	}

	public boolean getOnCampus() {
		return onCampus;
	}

	public void setOnCampus(boolean onCampus) {
		this.onCampus = onCampus;
	}

	public String getSchoolTag() {
		return schoolTag;
	}

	public void setSchoolTag(String schoolTag) {
		this.schoolTag = schoolTag;
	}

	public String getAreaStudent() {
		return areaStudent;
	}

	public void setAreaStudent(String areaStudent) {
		this.areaStudent = areaStudent;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public StuBaseinfo() {
		super();
	}

	public StuBaseinfo(String uuid) {
		super(uuid);
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

}