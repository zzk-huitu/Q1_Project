package com.zd.school.plartform.baseset.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: BaseCampus Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 校区信息实体类. date: 2016-08-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Campus")
@AttributeOverride(name = "campusId", column = @Column(name = "campusId", length = 20, nullable = false))
public class BaseCampus extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "学校主键",type="varchar(20)",explain="学校主键")
    @Column(name = "schoolId", length = 20, nullable = false)
    private String schoolId;


	public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    @FieldInfo(name = "校区编码",type="nvarchar(32)",explain="校区的编码")
    @Column(name = "campusCode", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String campusCode;

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getCampusCode() {
        return campusCode;
    }

    @FieldInfo(name = "校区名称",type="nvarchar(64)",explain="校区的名称")
    @Column(name = "campusName", columnDefinition="nvarchar(64)", nullable = false)
    private String campusName;

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusName() {
        return campusName;
    }

    @FieldInfo(name = "校区地址",type="nvarchar(180)",explain="校区的地址")
    @Column(name = "campusAddr", columnDefinition="nvarchar(180) defalut ''", nullable = true)
    private String campusAddr;

    public void setCampusAddr(String campusAddr) {
        this.campusAddr = campusAddr;
    }

    public String getCampusAddr() {
        return campusAddr;
    }

    @FieldInfo(name = "邮政编码",type="nvarchar(16)",explain="校区的邮政编码")
    @Column(name = "mailCode", columnDefinition="nvarchar(16) defalut ''", nullable = true)
    private String mailCode;

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getMailCode() {
        return mailCode;
    }

    @FieldInfo(name = "校区联系电话",type="nvarchar(30)",explain="校区的联系电话")
    @Column(name = "campusPhone", columnDefinition="nvarchar(30) defalut ''", nullable = true)
    private String campusPhone;

    public void setCampusPhone(String campusPhone) {
        this.campusPhone = campusPhone;
    }

    public String getCampusPhone() {
        return campusPhone;
    }

    @FieldInfo(name = "校区传真电话",type="nvarchar(30)",explain="校区的传真电话")
    @Column(name = "campusFax", columnDefinition="nvarchar(30) defalut ''", nullable = true)
    private String campusFax;

    public void setCampusFax(String campusFax) {
        this.campusFax = campusFax;
    }

    public String getCampusFax() {
        return campusFax;
    }

    @FieldInfo(name = "校区负责人号",type="nvarchar(30)",explain="校区的负责人号")
    @Column(name = "campusPrincipal", columnDefinition="nvarchar(32) defalut ''", nullable = true)
    private String campusPrincipal;

    public void setCampusPrincipal(String campusPrincipal) {
        this.campusPrincipal = campusPrincipal;
    }

    public String getCampusPrincipal() {
        return campusPrincipal;
    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
    @FieldInfo(name = "学校名称",type="nvarchar(64)",explain="学校名称")
    @Formula("(SELECT a.schoolName FROM T_PT_School a WHERE a.schoolId=schoolId )")
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    
    public BaseCampus() {

        super();
        // TODO Auto-generated constructor stub
    }

    public BaseCampus(String uuid) {

        super(uuid);
        // TODO Auto-generated constructor stub

    }

}