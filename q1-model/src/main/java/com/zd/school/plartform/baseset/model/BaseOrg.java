package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.TreeNodeEntity;

/**
 * 
 * ClassName: BaseOrg Function: TODO ADD FUNCTION. Reason: TODO ADD REASON(可选).
 * Description: BASE_T_ORG实体类. date: 2016-07-26
 * saveEntity.setExtField01(courseId); // 对于部门是学科时，绑定已有学科对应的ID
 * ExtField04	副ID
 * ExtField05	父级副ID
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Department")
@AttributeOverride(name = "deptId", column = @Column(name = "deptId", length = 20, nullable = false))
public class BaseOrg extends TreeNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "传真",type="nvarchar(64)",explain="部门的传真号码")
    @Column(name = "fax", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String fax;

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    @FieldInfo(name = "内线电话",type="varchar(64)",explain="部门的内线电话")
    @Column(name = "inPhone", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String inPhone;

    public void setInPhone(String inPhone) {
        this.inPhone = inPhone;
    }

    public String getInPhone() {
        return inPhone;
    }

    @FieldInfo(name = "是否系统内置 1-系统内置 0-非系统内置",type="boolean",explain="部门是否系统内置")
    @Column(name = "issystem", nullable = true)
    private boolean issystem;

    public void setIssystem(boolean issystem) {
        this.issystem = issystem;
    }

    public boolean getIssystem() {
        return issystem;
    }


    @FieldInfo(name = "外线电话",type="varchar(64)",explain="部门的外线电话")
    @Column(name = "outPhone", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String outPhone;

    public void setOutPhone(String outPhone) {
        this.outPhone = outPhone;
    }

    public String getOutPhone() {
        return outPhone;
    }
    
    /*2017-10-20新加入*/
    @FieldInfo(name = "学段编码",type="varchar(32)",explain="年级的学段编码")
    @Column(name = "sectionCode", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String sectionCode;

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }
    /*2017-10-20新加入*/
    @FieldInfo(name = "年级",type="varchar(32)",explain="年级")
    @Column(name = "grade", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    

    @FieldInfo(name = "备注",type="varchar(255)",explain="备注说明")
    @Column(name = "remark", columnDefinition="nvarchar(255) defalut ''", nullable = true)
    private String remark;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
    
    @FieldInfo(name = "副负责岗位",type="nvarchar(64)",explain="副负责岗位")
	@Column(name = "viceLeader",columnDefinition="nvarchar(64) defalut ''", nullable = true)
	private String viceLeader;

	public void setViceLeader(String viceLeader) {
		this.viceLeader = viceLeader;
	}

	public String getViceLeader() {
		return viceLeader;
	}

    @FieldInfo(name = "部门类型 01-学校 02-校区 03-部门  04-年级  05-班级　06-学科",type="varchar(2)",explain="部门类型")
    @Column(name = "deptType", length = 2, nullable = true)
    private String deptType;

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public String getDeptType() {
        return deptType;
    }

    @FieldInfo(name = "上级主管部门",type="nvarchar(64)",explain="上级主管部门")
    @Column(name = "superDept", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String superDept;

    public String getSuperDept() {
        return superDept;
    }

    @FieldInfo(name = "上级主管部门名称",type="nvarchar(64)",explain="上级主管部门名称")
    @Column(name = "superdeptName", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String superdeptName;

    public String getSuperdeptName() {
        return superdeptName;
    }

    public void setSuperdeptName(String superdeptName) {
        this.superdeptName = superdeptName;
    }

    public void setSuperDept(String superDept) {
        this.superDept = superDept;
    }

    @FieldInfo(name = "上级主管岗位",type="nvarchar(64)",explain="上级主管岗位")
    @Column(name = "superJob", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String superJob;

    public String getSuperJob() {
        return superJob;
    }

    public void setSuperJob(String superJob) {
        this.superJob = superJob;
    }

    @FieldInfo(name = "上级主管岗位名称",type="nvarchar(64)",explain="上级主管岗位名称")
    @Column(name = "superjobName", columnDefinition="nvarchar(64) defalut ''", nullable = true)
    private String superjobName;

    public String getSuperjobName() {
        return superjobName;
    }

    public void setSuperjobName(String superjobName) {
        this.superjobName = superjobName;
    }

    @FieldInfo(name = "部门全称",type="nvarchar(500)",explain="部门全称")
    @Column(name = "allDeptName", columnDefinition="nvarchar(500) defalut ''", nullable = true)
    //@Formula("(SELECT isnull(a.ALL_DEPTNAME+'/','')+NODE_TEXT FROM BASE_T_ORG a WHERE a.DEPT_ID=PARENT_NODE)")
    private String allDeptName;

    public String getAllDeptName() {
        return allDeptName;
    }
    
    public void setAllDeptName(String allDeptName) {
        this.allDeptName = allDeptName;
    }
    public BaseOrg() {

        super();
        // TODO Auto-generated constructor stub

    }

    public BaseOrg(String uuid) {

        super(uuid);
        // TODO Auto-generated constructor stub

    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
    @FieldInfo(name = "上级部门名称",type="nvarchar(36)",explain="上级部门名称")
    @Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_Department a WHERE a.deptId=parentNode)")
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @FieldInfo(name = "上级部门类型",type="nvarchar(36)",explain="上级部门类型")
    @Formula("(SELECT isnull(a.deptType,'01') FROM T_PT_Department a WHERE a.deptId=parentNode)")
    private String parentType;

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }


	@FieldInfo(name = "主负责岗位名称",type="nvarchar(36)",explain="主负责岗位名称")
	@Formula("(SELECT a.jobName FROM T_PT_DeptJob a WHERE a.jobType=0 and a.deptId=deptId)")
	private String mainLeaderName;

	public String getMainLeaderName() {
		return mainLeaderName;
	}

	public void setMainLeaderName(String mainLeaderName) {
		this.mainLeaderName = mainLeaderName;
	}	

}