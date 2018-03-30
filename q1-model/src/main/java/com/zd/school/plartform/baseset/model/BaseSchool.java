package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

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
@AttributeOverride(name = "schoolId", column = @Column(name = "schoolId", length = 20, nullable = false))
public class BaseSchool extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "学校代码",type="nvarchar(32)",explain="学校代码")
    @Column(name = "schoolCode", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String schoolCode;
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
    public String getSchoolCode() {
        return schoolCode;
    }
        
    @FieldInfo(name = "学校名称",type="nvarchar(64)",explain="学校的名称")
    @Column(name = "schoolName", columnDefinition="nvarchar(64)", nullable = false)
    private String schoolName;
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getSchoolName() {
        return schoolName;
    }
        
    @FieldInfo(name = "学校英文名",type="nvarchar(32)",explain="学校的英文名")
    @Column(name = "schoolEng", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String schoolEng;
    public void setSchoolEng(String schoolEng) {
        this.schoolEng = schoolEng;
    }
    public String getSchoolEng() {
        return schoolEng;
    }
        
    @FieldInfo(name = "学校地址",type="nvarchar(512)",explain="学校的地址")
    @Column(name = "schoolAddr", columnDefinition="nvarchar(512) defalut ''", nullable = true)
    private String schoolAddr;
    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }
    public String getSchoolAddr() {
        return schoolAddr;
    }
        
    @FieldInfo(name = "建校年月",type="nvarchar(32)",explain="学校的建校年月")
    @Column(name = "foundYear", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String foundYear;
    public void setFoundYear(String foundYear) {
        this.foundYear = foundYear;
    }
    public String getFoundYear() {
        return foundYear;
    }
        
    @FieldInfo(name = "邮政编码",type="nvarchar(16)",explain="学校的邮政编码")
    @Column(name = "mailCode",columnDefinition="nvarchar(16) defalut ''", nullable = true)
    private String mailCode;
   
    public String getMailCode() {
        return mailCode;
    }
        
    public void setMailCode(String mailCode) {
		this.mailCode = mailCode;
	}

	@FieldInfo(name = "行政区划",type="nvarchar(32)",explain="学校的行政区划")
    @Column(name = "administration", columnDefinition="nvarchar(128) defalut ''", nullable = true)
    private String administration;
    public void setAdministration(String administration) {
        this.administration = administration;
    }
    public String getAdministration() {
        return administration;
    }
        
    @FieldInfo(name = "校庆日",type="nvarchar(32)",explain="学校的校庆日")
    @Column(name = "anniversaryDay", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String anniversaryDay;
    public void setAnniversaryDay(String anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
    }
    public String getAnniversaryDay() {
        return anniversaryDay;
    }
        
    @FieldInfo(name = "办学类型",type="nvarchar(2)",explain="学校的办学类型")
    @Column(name = "officeType", columnDefinition="nvarchar(2) defalut ''", nullable = true)
    private String officeType;
    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }
    public String getOfficeType() {
        return officeType;
    }
        
    @FieldInfo(name = "学校主管部门",type="nvarchar(32)",explain="学校的学校主管部门")
    @Column(name = "chargeDept", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String chargeDept;
    public void setChargeDept(String chargeDept) {
        this.chargeDept = chargeDept;
    }
    public String getChargeDept() {
        return chargeDept;
    }
        
    @FieldInfo(name = "法定代表人号",type="nvarchar(64)",explain="学校的法定代表人号")
    @Column(name = "legalPerson", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String legalPerson;
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    public String getLegalPerson() {
        return legalPerson;
    }
        
    @FieldInfo(name = "法人证书号",type="nvarchar(64)",explain="学校的法人证书号")
    @Column(name = "legalCertificate", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String legalCertificate;
    public void setLegalCertificate(String legalCertificate) {
        this.legalCertificate = legalCertificate;
    }
    public String getLegalCertificate() {
        return legalCertificate;
    }
        
    @FieldInfo(name = "校长工号",type="nvarchar(32)",explain="学校的校长工号")
    @Column(name = "headMasterNum",columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String headMasterNum;
    
    public String getHeadMasterNum() {
		return headMasterNum;
	}
	public void setHeadMasterNum(String headMasterNum) {
		this.headMasterNum = headMasterNum;
	}

	@FieldInfo(name = "校长姓名",type="nvarchar(32)",explain="学校的校长姓名")
    @Column(name = "headMasterName", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String headMasterName;
 
        
    public String getHeadMasterName() {
		return headMasterName;
	}
	public void setHeadMasterName(String headMasterName) {
		this.headMasterName = headMasterName;
	}

	@FieldInfo(name = "党委负责人号",type="nvarchar(32)",explain="学校的党委负责人号")
    @Column(name = "partyPersonNo",columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String partyPersonNo;
    public void setPartyPersonNo(String partyPersonNo) {
        this.partyPersonNo = partyPersonNo;
    }
    public String getPartyPersonNo() {
        return partyPersonNo;
    }
        
    @FieldInfo(name = "组织机构码",type="nvarchar(32)",explain="学校的组织机构码")
    @Column(name = "orgCode", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String orgCode;
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getOrgCode() {
        return orgCode;
    }
        
    @FieldInfo(name = "联系电话",type="nvarchar(32)",explain="学校的联系电话")
    @Column(name = "telephone", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String telephone;
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }
        
    @FieldInfo(name = "传真电话",type="nvarchar(32)",explain="学校的传真电话")
    @Column(name = "faxNum",columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String faxNum;
    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }
    public String getFaxNum() {
        return faxNum;
    }
        
    @FieldInfo(name = "电子邮箱",type="nvarchar(128)",explain="学校的电子邮箱")
    @Column(name = "email", columnDefinition="nvarchar(128) defalut ''", nullable = true)
    private String email;
   
        
    @FieldInfo(name = "主页地址",type="varchar(128)",explain="学校的主页地址")
    @Column(name = "homepage", length = 128, nullable = true)
    private String homepage;
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public String getHomepage() {
        return homepage;
    }
        
    @FieldInfo(name = "历史沿革",type="nvarchar(MAX)",explain="学校的历史沿革")
    @Column(name = "historyEvolution", columnDefinition="nvarchar(MAX) defalut ''", nullable = true)
    private String historyEvolution;
    public void setHistoryEvolution(String historyEvolution) {
        this.historyEvolution = historyEvolution;
    }
    public String getHistoryEvolution() {
        return historyEvolution;
    }
        
    @FieldInfo(name = "学校办别",type="nvarchar(32)",explain="学校的办别")
    @Column(name = "schoolType", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String schoolType;
    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
    public String getSchoolType() {
        return schoolType;
    }
        
    @FieldInfo(name = "所属主管单位",type="nvarchar(64)",explain="学校的所属主管单位")
    @Column(name = "chargeUnit",columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String chargeUnit;
    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit;
    }
    public String getChargeUnit() {
        return chargeUnit;
    }
        
    @FieldInfo(name = "所在地城乡类型",type="nvarchar(3)",explain="学校的所在地城乡类型")
    @Column(name = "cityAndCountryType", columnDefinition="nvarchar(3) defalut ''", nullable = true)
    private String cityAndCountryType; 
      
    public String getCityAndCountryType() {
		return cityAndCountryType;
	}
	public void setCityAndCountryType(String cityAndCountryType) {
		this.cityAndCountryType = cityAndCountryType;
	}

	@FieldInfo(name = "所在地经济属性码",type="nvarchar(2)",explain="学校的所在地经济属性码")
    @Column(name = "economicCode", columnDefinition="nvarchar(2) defalut ''", nullable = true)
    private String economicCode;
    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }
    public String getEconomicCode() {
        return economicCode;
    }
        
    @FieldInfo(name = "所在地民族属性",type="nvarchar(3)",explain="学校的所在地民族属性")
    @Column(name = "raceProperty", columnDefinition="nvarchar(2) defalut ''", nullable = true)
    private String raceProperty;

        
    public String getRaceProperty() {
		return raceProperty;
	}
	public void setRaceProperty(String raceProperty) {
		this.raceProperty = raceProperty;
	}

	@FieldInfo(name = "学制",type="nvarchar(32)",explain="学校的学制")
    @Column(name = "eductionalSystme",columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String eductionalSystme;
    public void setEductionalSystme(String eductionalSystme) {
        this.eductionalSystme = eductionalSystme;
    }
    public String getEductionalSystme() {
        return eductionalSystme;
    }
        
    @FieldInfo(name = "入学年龄",type="nvarchar(32)",explain="入学年龄")
    @Column(name = "admissionAge", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String admissionAge;
    public String getAdmissionAge() {
		return admissionAge;
	}
	public void setAdmissionAge(String admissionAge) {
		this.admissionAge = admissionAge;
	}

	@FieldInfo(name = "主教学语言码",type="nvarchar(2)",explain="主教学语言码")
    @Column(name = "mainLangueCode", columnDefinition="nvarchar(2) defalut ''", nullable = true)
    private String mainLangueCode;
    public String getMainLangueCode() {
		return mainLangueCode;
	}
	public void setMainLangueCode(String mainLangueCode) {
		this.mainLangueCode = mainLangueCode;
	}

	@FieldInfo(name = "辅教学语言码",type="nvarchar(2)",explain="辅教学语言码")
    @Column(name = "assistedLangueCode", columnDefinition="nvarchar(2) defalut ''", nullable = true)
    private String assistedLangueCode;
    public void setAssistedLangueCode(String assistedLangueCode) {
        this.assistedLangueCode = assistedLangueCode;
    }
    public String getAssistedLangueCode() {
        return assistedLangueCode;
    }
        
    @FieldInfo(name = "招生半径",type="nvarchar(32)",explain="学校的招生半径")
    @Column(name = "recruitScope", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String recruitScope;
    public void setRecruitScope(String recruitScope) {
        this.recruitScope = recruitScope;
    }
    public String getRecruitScope() {
        return recruitScope;
    }
        
    @FieldInfo(name = "备注",type="nvarchar(1024)",explain="备注说明")
    @Column(name = "remark", columnDefinition="nvarchar(1024) defalut ''", nullable = true)
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