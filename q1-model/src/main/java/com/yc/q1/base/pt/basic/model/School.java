package com.yc.q1.base.pt.basic.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 学校信息（一个系统中只存在一个学校信息）
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_School")
@AttributeOverride(name = "id", column = @Column(name = "schoolId", length = 20, nullable = false) )
public class School extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "学校名称", type = "nvarchar(32) NOT NULL", explain = "学校的名称")
	@Column(name = "schoolName", columnDefinition = "nvarchar(32)", nullable = false)
	private String schoolName;

	@FieldInfo(name = "学校代码", type = "varchar(32) DEFAULT ''", explain = "学校代码")
	@Column(name = "schoolCode", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String schoolCode;

	@FieldInfo(name = "学校英文名", type = "varchar(64) DEFAULT ''", explain = "学校的英文名")
	@Column(name = "schoolEng", columnDefinition = "varchar(64) DEFAULT ''", nullable = true)
	private String schoolEng;

	@FieldInfo(name = "学校地址", type = "nvarchar(128) DEFAULT ''", explain = "学校的地址")
	@Column(name = "schoolAddr", columnDefinition = "nvarchar(128) DEFAULT ''", nullable = true)
	private String schoolAddr;

	@FieldInfo(name = "建校年月", type = "nvarchar(16) DEFAULT ''", explain = "学校的建校年月")
	@Column(name = "foundYear", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String foundYear;

	@FieldInfo(name = "邮政编码", type = "varchar(6) DEFAULT ''", explain = "学校的邮政编码")
	@Column(name = "mailCode", columnDefinition = "varchar(6) DEFAULT ''", nullable = true)
	private String mailCode;

	@FieldInfo(name = "行政区划", type = "nvarchar(32) DEFAULT ''", explain = "学校的行政区划（数据字典）")
	@Column(name = "administration", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String administration;

	@FieldInfo(name = "校庆日", type = "nvarchar(16) DEFAULT ''", explain = "学校的校庆日")
	@Column(name = "anniversaryDay", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String anniversaryDay;

	@FieldInfo(name = "办学类型", type = "varchar(4) DEFAULT ''", explain = "学校的办学类型（数据字典）")
	@Column(name = "officeType", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String officeType;

	@FieldInfo(name = "学校主管部门", type = "nvarchar(16) DEFAULT ''", explain = "学校的学校主管部门")
	@Column(name = "chargeDept", columnDefinition = "nvarchar(16) DEFAULT ''", nullable = true)
	private String chargeDept;

	@FieldInfo(name = "法定代表人工号", type = "varchar(32) DEFAULT ''", explain = "学校的法定代表人工号")
	@Column(name = "legalPerson", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String legalPerson;

	@FieldInfo(name = "法人证书号", type = "varchar(64) DEFAULT ''", explain = "学校的法人证书号")
	@Column(name = "legalCertificate", columnDefinition = "varchar(64) DEFAULT ''", nullable = true)
	private String legalCertificate;

	@FieldInfo(name = "校长工号", type = "varchar(32)  DEFAULT ''", explain = "学校的校长工号")
	@Column(name = "headMasterNum", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String headMasterNum;

	@FieldInfo(name = "校长姓名", type = "nvarchar(10)  DEFAULT ''", explain = "学校的校长姓名")
	@Column(name = "headMasterName", columnDefinition = "nvarchar(10) DEFAULT ''", nullable = true)
	private String headMasterName;

	@FieldInfo(name = "党委负责人工号", type = "varchar(32) DEFAULT ''", explain = "学校的党委负责人工号")
	@Column(name = "partyPersonNo", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String partyPersonNo;

	@FieldInfo(name = "组织机构码", type = "varchar(32)  DEFAULT ''", explain = "学校的组织机构码")
	@Column(name = "orgCode", columnDefinition = "varchar(32) DEFAULT ''", nullable = true)
	private String orgCode;

	@FieldInfo(name = "联系电话", type = "varchar(16) DEFAULT ''", explain = "学校的联系电话")
	@Column(name = "telephone", columnDefinition = "varchar(16) DEFAULT ''", nullable = true)
	private String telephone;

	@FieldInfo(name = "传真电话", type = "varchar(16) DEFAULT ''", explain = "学校的传真电话")
	@Column(name = "faxNum", columnDefinition = "varchar(16) DEFAULT ''", nullable = true)
	private String faxNum;

	@FieldInfo(name = "电子邮箱", type = "varchar(128) DEFAULT ''", explain = "学校的电子邮箱")
	@Column(name = "email", columnDefinition = "varchar(128) DEFAULT ''", nullable = true)
	private String email;

	@FieldInfo(name = "主页地址", type = "varchar(128) DEFAULT ''", explain = "学校的主页地址")
	@Column(name = "homepage", columnDefinition = "varchar(128) DEFAULT ''", nullable = true)
	private String homepage;

	@FieldInfo(name = "历史沿革", type = "nvarchar(MAX) DEFAULT ''", explain = "学校的历史沿革")
	@Column(name = "historyEvolution", columnDefinition = "nvarchar(MAX) DEFAULT ''", nullable = true)
	private String historyEvolution;

	@FieldInfo(name = "学校办别", type = "varchar(4) DEFAULT ''", explain = "学校的办别（数据字典）")
	@Column(name = "schoolType", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String schoolType;

	@FieldInfo(name = "所属主管单位", type = "nvarchar(32) DEFAULT ''", explain = "学校的所属主管单位")
	@Column(name = "chargeUnit", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String chargeUnit;

	@FieldInfo(name = "所在地城乡类型", type = "varchar(4) DEFAULT ''", explain = "学校的所在地城乡类型（数据字典）")
	@Column(name = "cityAndCountryType", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String cityAndCountryType;

	@FieldInfo(name = "所在地经济属性码", type = "varchar(4) DEFAULT ''", explain = "学校的所在地经济属性码（数据字典）")
	@Column(name = "economicCode", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String economicCode;

	@FieldInfo(name = "所在地民族属性", type = "varchar(4) DEFAULT ''", explain = "学校的所在地民族属性")
	@Column(name = "raceProperty", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String raceProperty;

	@FieldInfo(name = "学制", type = "varchar(4) DEFAULT ''", explain = "学校的学制")
	@Column(name = "eductionalSystme", columnDefinition = "varchar(4) DEFAULT ''", nullable = true)
	private String eductionalSystme;

	@FieldInfo(name = "入学年龄", type = "int DEFAULT 0", explain = "入学年龄")
	@Column(name = "admissionAge", columnDefinition = "int DEFAULT 0", nullable = true)
	private Integer admissionAge;

	@FieldInfo(name = "备注", type = "nvarchar(1024) DEFAULT ''", explain = "备注说明")
	@Column(name = "remark", columnDefinition = "nvarchar(1024) DEFAULT ''", nullable = true)
	private String remark;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getSchoolEng() {
		return schoolEng;
	}

	public void setSchoolEng(String schoolEng) {
		this.schoolEng = schoolEng;
	}

	public String getSchoolAddr() {
		return schoolAddr;
	}

	public void setSchoolAddr(String schoolAddr) {
		this.schoolAddr = schoolAddr;
	}

	public String getFoundYear() {
		return foundYear;
	}

	public void setFoundYear(String foundYear) {
		this.foundYear = foundYear;
	}

	public String getMailCode() {
		return mailCode;
	}

	public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}

	public String getAnniversaryDay() {
		return anniversaryDay;
	}

	public void setAnniversaryDay(String anniversaryDay) {
		this.anniversaryDay = anniversaryDay;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getChargeDept() {
		return chargeDept;
	}

	public void setChargeDept(String chargeDept) {
		this.chargeDept = chargeDept;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalCertificate() {
		return legalCertificate;
	}

	public void setLegalCertificate(String legalCertificate) {
		this.legalCertificate = legalCertificate;
	}

	public String getHeadMasterNum() {
		return headMasterNum;
	}

	public void setHeadMasterNum(String headMasterNum) {
		this.headMasterNum = headMasterNum;
	}

	public String getHeadMasterName() {
		return headMasterName;
	}

	public void setHeadMasterName(String headMasterName) {
		this.headMasterName = headMasterName;
	}

	public String getPartyPersonNo() {
		return partyPersonNo;
	}

	public void setPartyPersonNo(String partyPersonNo) {
		this.partyPersonNo = partyPersonNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getHistoryEvolution() {
		return historyEvolution;
	}

	public void setHistoryEvolution(String historyEvolution) {
		this.historyEvolution = historyEvolution;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	public String getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public String getCityAndCountryType() {
		return cityAndCountryType;
	}

	public void setCityAndCountryType(String cityAndCountryType) {
		this.cityAndCountryType = cityAndCountryType;
	}

	public String getEconomicCode() {
		return economicCode;
	}

	public void setEconomicCode(String economicCode) {
		this.economicCode = economicCode;
	}

	public String getRaceProperty() {
		return raceProperty;
	}

	public void setRaceProperty(String raceProperty) {
		this.raceProperty = raceProperty;
	}

	public String getEductionalSystme() {
		return eductionalSystme;
	}

	public void setEductionalSystme(String eductionalSystme) {
		this.eductionalSystme = eductionalSystme;
	}

	public Integer getAdmissionAge() {
		return admissionAge;
	}

	public void setAdmissionAge(Integer admissionAge) {
		this.admissionAge = admissionAge;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public School() {
		super();
	}

	public School(String id) {
		super(id);
	}

}