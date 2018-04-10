package com.yc.q1.base.pt.basic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.yc.q1.base.pt.system.model.User;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;

/**
 * 学生基本信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_StudentBaseInfo")
public class StudentBaseInfo extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "英文姓名", type = "varchar(32)  DEFAULT ''", explain = "学生英文姓名")
	@Column(name = "englishName", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String englishName;

	@FieldInfo(name = "姓名拼音", type = "varchar(32)  DEFAULT ''", explain = "学生的姓名拼音")
	@Column(name = "spellName", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String spellName;

	@FieldInfo(name = "曾用名", type = "nvarchar(16) DEFAULT ''", explain = "学生的曾用名")
	@Column(name = "userdName", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String userdName;

	@FieldInfo(name = "籍贯", type = "varchar(10) DEFAULT ''", explain = "学生的籍贯（字典码）")
	@Column(name = "nativePlace", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String nativePlace;

	@FieldInfo(name = "民族码", type = "varchar(10) DEFAULT ''", explain = "学生的民族码（字典码）")
	@Column(name = "folkCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String folkCode;

	@FieldInfo(name = "国籍/地区码", type = "varchar(10) DEFAULT ''", explain = "学生的国籍/地区码（字典码）")
	@Column(name = "nationality", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String nationality;


	@FieldInfo(name = "健康状况码", type = "varchar(10) DEFAULT ''", explain = "学生的健康状况码（字典码）")
	@Column(name = "healthCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String healthCode;

	@FieldInfo(name = "血型码", type = "varchar(10) DEFAULT ''", explain = "学生的血型码（字典码）")
	@Column(name = "bloodType", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String bloodType;

	@FieldInfo(name = "照片", type = "nvarchar(220) DEFAULT ''", explain = "学生的照片")
	@Column(name = "photo", columnDefinition = "nvarchar(200) DEFAULT ''", nullable = true)
	private String photo;

	@FieldInfo(name = "身份证件有效期", type = "nvarchar(17) DEFAULT ''", explain = "学生的身份证件有效期")
	@Column(name = "identityTimeLimit", columnDefinition = "nvarchar(17) DEFAULT ''", nullable = true)
	private String identityTimeLimit;

	@FieldInfo(name = "是否独生子女", type = "bit DEFAULT ''", explain = "学生是否独生子女")
	@Column(name = "onlyChild", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean onlyChild;

	@FieldInfo(name = "户口所在地行政区划码", type = "varchar(10) DEFAULT ''", explain = "学生的户口所在地行政区划码（字典码）")
	@Column(name = "administrativeAreaCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String administrativeAreaCode;

	@FieldInfo(name = "户口类别码", type = "varchar(10) DEFAULT ''", explain = "学生的户口类别码（字典码）")
	@Column(name = "accountTypeCode", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String accountTypeCode;

	@FieldInfo(name = "是否流动人口", type = "bit DEFAULT 0", explain = "学生是否流动人口")
	@Column(name = "floatpopulation", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean floatpopulation;

	@FieldInfo(name = "特长", type = "nvarchar(128) DEFAULT ''", explain = "学生的特长")
	@Column(name = "speciality ", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String speciality;

	@FieldInfo(name = "学籍号", type = "varchar(32) DEFAULT ''", explain = "学生的学籍号")
	@Column(name = "studentCode", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String studentCode;

	@FieldInfo(name = "目前居住地", type = "nvarchar(120) DEFAULT ''", explain = "学生的目前居住地")
	@Column(name = "liveLocation", columnDefinition = "nvarchar(120) DEFAULT ''", nullable = true)
	public String liveLocation;

	@FieldInfo(name = "开户银行", type = "nvarchar(32) DEFAULT ''", explain = "学生的开户银行")
	@Column(name = "depositBank", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String depositBank;

	@FieldInfo(name = "银行账号", type = "varchar(32) DEFAULT ''", explain = "学生的银行账号")
	@Column(name = "bankAccount", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String bankAccount;

	@FieldInfo(name = "学生状态", type = "varchar(4) DEFAULT ''", explain = "学生的状态（字典码）")
	@Column(name = "studentState", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String studentState;

	// @FieldInfo(name = "班级名称", type = "varchar(60)", explain = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a where a.classId=classId)")
	private String className;

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



	public String getHealthCode() {
		return healthCode;
	}

	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
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

	public Boolean getOnlyChild() {
		return onlyChild;
	}

	public void setOnlyChild(Boolean onlyChild) {
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

	public Boolean getFloatpopulation() {
		return floatpopulation;
	}

	public void setFloatpopulation(Boolean floatpopulation) {
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

	public String getStudentState() {
		return studentState;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public StudentBaseInfo() {
		super();
	}

	public StudentBaseInfo(String id) {
		super(id);
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

}