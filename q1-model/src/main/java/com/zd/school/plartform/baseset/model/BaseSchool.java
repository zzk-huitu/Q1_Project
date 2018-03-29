package com.zd.school.plartform.baseset.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: BaseSchool 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学校信息实体类.
 * date: 2016-08-13
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_School")
@AttributeOverride(name = "schoolId", column = @Column(name = "schoolId", length = 36, nullable = false))
public class BaseSchool extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "学校代码")
    @Column(name = "schoolCode", length = 32, nullable = true)
    private String schoolCode;
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
    public String getSchoolCode() {
        return schoolCode;
    }
        
    @FieldInfo(name = "学校名称")
    @Column(name = "schoolName", length = 64, nullable = true)
    private String schoolName;
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getSchoolName() {
        return schoolName;
    }
        
    @FieldInfo(name = "学校英文名")
    @Column(name = "schoolEng", length = 32, nullable = true)
    private String schoolEng;
    public void setSchoolEng(String schoolEng) {
        this.schoolEng = schoolEng;
    }
    public String getSchoolEng() {
        return schoolEng;
    }
        
    @FieldInfo(name = "学校地址")
    @Column(name = "schoolAddr", length = 512, nullable = true)
    private String schoolAddr;
    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }
    public String getSchoolAddr() {
        return schoolAddr;
    }
        
    @FieldInfo(name = "建校年月")
    @Column(name = "foundYear", length = 32, nullable = true)
    private String foundYear;
    public void setFoundYear(String foundYear) {
        this.foundYear = foundYear;
    }
    public String getFoundYear() {
        return foundYear;
    }
        
    @FieldInfo(name = "邮政编码")
    @Column(name = "mailCode", length = 16, nullable = true)
    private String mailCode;
   
    public String getMailCode() {
        return mailCode;
    }
        
    public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	@FieldInfo(name = "行政区划")
    @Column(name = "administration", length = 128, nullable = true)
    private String administration;
    public void setAdministration(String administration) {
        this.administration = administration;
    }
    public String getAdministration() {
        return administration;
    }
        
    @FieldInfo(name = "校庆日")
    @Column(name = "anniversaryDay", length = 32, nullable = true)
    private String anniversaryDay;
    public void setAnniversaryDay(String anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
    }
    public String getAnniversaryDay() {
        return anniversaryDay;
    }
        
    @FieldInfo(name = "办学类型")
    @Column(name = "officeType", length = 2, nullable = true)
    private String officeType;
    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }
    public String getOfficeType() {
        return officeType;
    }
        
    @FieldInfo(name = "学校主管部门")
    @Column(name = "chargeDept", length = 32, nullable = true)
    private String chargeDept;
    public void setChargeDept(String chargeDept) {
        this.chargeDept = chargeDept;
    }
    public String getChargeDept() {
        return chargeDept;
    }
        
    @FieldInfo(name = "法定代表人号")
    @Column(name = "legalPerson", length = 64, nullable = true)
    private String legalPerson;
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    public String getLegalPerson() {
        return legalPerson;
    }
        
    @FieldInfo(name = "法人证书号")
    @Column(name = "legalCertificate", length = 64, nullable = true)
    private String legalCertificate;
    public void setLegalCertificate(String legalCertificate) {
        this.legalCertificate = legalCertificate;
    }
    public String getLegalCertificate() {
        return legalCertificate;
    }
        
    @FieldInfo(name = "校长工号")
    @Column(name = "headMasterNum", length = 32, nullable = true)
    private String headMasterNum;
    
    public String getHeadMasterNum() {
		return headMasterNum;
	}
	public void setHeadMasterNum(String headMasterNum) {
		this.headMasterNum = headMasterNum;
	}

	@FieldInfo(name = "校长姓名")
    @Column(name = "headMasterName", length = 32, nullable = true)
    private String headMasterName;
 
        
    public String getHeadMasterName() {
		return headMasterName;
	}
	public void setHeadMasterName(String headMasterName) {
		this.headMasterName = headMasterName;
	}

	@FieldInfo(name = "党委负责人号")
    @Column(name = "partyPersonNo", length = 32, nullable = true)
    private String partyPersonNo;
    public void setPartyPersonNo(String partyPersonNo) {
        this.partyPersonNo = partyPersonNo;
    }
    public String getPartyPersonNo() {
        return partyPersonNo;
    }
        
    @FieldInfo(name = "组织机构码")
    @Column(name = "orgCode", length = 32, nullable = true)
    private String orgCode;
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getOrgCode() {
        return orgCode;
    }
        
    @FieldInfo(name = "联系电话")
    @Column(name = "telephone", length = 255, nullable = true)
    private String telephone;
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }
        
    @FieldInfo(name = "传真电话")
    @Column(name = "faxNum", length = 32, nullable = true)
    private String faxNum;
    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }
    public String getFaxNum() {
        return faxNum;
    }
        
    @FieldInfo(name = "电子邮箱")
    @Column(name = "email", length = 128, nullable = true)
    private String email;
   
        
    @FieldInfo(name = "主页地址")
    @Column(name = "homepage", length = 128, nullable = true)
    private String homepage;
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public String getHomepage() {
        return homepage;
    }
        
    @FieldInfo(name = "历史沿革")
    @Column(name = "historyEvolution", length = 2048, nullable = true)
    private String historyEvolution;
    public void setHistoryEvolution(String historyEvolution) {
        this.historyEvolution = historyEvolution;
    }
    public String getHistoryEvolution() {
        return historyEvolution;
    }
        
    @FieldInfo(name = "学校办别")
    @Column(name = "schoolType", length = 2, nullable = true)
    private String schoolType;
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
    public String getSchoolType() {
        return schoolType;
    }
        
    @FieldInfo(name = "所属主管单位")
    @Column(name = "chargeUnit", length = 64, nullable = true)
    private String chargeUnit;
    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }
    public String getChargeUnit() {
        return chargeUnit;
    }
        
    @FieldInfo(name = "所在地城乡类型")
    @Column(name = "cityAndCountryType", length = 3, nullable = true)
    private String cityAndCountryType; 
      
    public String getCityAndCountryType() {
		return cityAndCountryType;
	}
	public void setCityAndCountryType(String cityAndCountryType) {
		this.cityAndCountryType = cityAndCountryType;
	}

	@FieldInfo(name = "所在地经济属性码")
    @Column(name = "economicCode", length = 2, nullable = true)
    private String economicCode;
    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }
    public String getEconomicCode() {
        return economicCode;
    }
        
    @FieldInfo(name = "所在地民族属性")
    @Column(name = "raceProperty", length = 2, nullable = true)
    private String raceProperty;

        
    public String getRaceProperty() {
		return raceProperty;
	}
	public void setRaceProperty(String raceProperty) {
		this.raceProperty = raceProperty;
	}

	@FieldInfo(name = "学制")
    @Column(name = "eductionalSystme", length = 32, nullable = true)
    private String eductionalSystme;
    public void setEductionalSystme(String eductionalSystme) {
        this.eductionalSystme = eductionalSystme;
    }
    public String getEductionalSystme() {
        return eductionalSystme;
    }
        
    @FieldInfo(name = "入学年龄")
    @Column(name = "admissionAge", length = 32, nullable = true)
    private String admissionAge;
    public String getAdmissionAge() {
		return admissionAge;
	}
	public void setAdmissionAge(String admissionAge) {
		this.admissionAge = admissionAge;
	}

	@FieldInfo(name = "主教学语言码")
    @Column(name = "mainLangueCode", length = 2, nullable = true)
    private String mainLangueCode;
    public String getMainLangueCode() {
		return mainLangueCode;
	}
	public void setMainLangueCode(String mainLangueCode) {
		this.mainLangueCode = mainLangueCode;
	}

	@FieldInfo(name = "辅教学语言码")
    @Column(name = "assistedLangueCode", length = 2, nullable = true)
    private String assistedLangueCode;
    public void setAssistedLangueCode(String assistedLangueCode) {
        this.assistedLangueCode = assistedLangueCode;
    }
    public String getAssistedLangueCode() {
        return assistedLangueCode;
    }
        
    @FieldInfo(name = "招生半径")
    @Column(name = "recruitScope", length = 32, nullable = true)
    private String recruitScope;
    public void setRecruitScope(String recruitScope) {
        this.recruitScope = recruitScope;
    }
    public String getRecruitScope() {
        return recruitScope;
    }
        
    @FieldInfo(name = "remark")
    @Column(name = "remark", length = 1024, nullable = true)
    private String remark;
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}