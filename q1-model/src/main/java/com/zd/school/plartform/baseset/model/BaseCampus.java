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
@AttributeOverride(name = "campusId", column = @Column(name = "campusId", length = 36, nullable = false))
public class BaseCampus extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "学校主键")
    @Column(name = "schoolId", length = 36, nullable = true)
    private String schoolId;


	public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    @FieldInfo(name = "校区编码")
    @Column(name = "campusCode", length = 32, nullable = true)
    private String campusCode;

    public void setCampusCode(String campusCode) {
        this.campusCode = campusCode;
    }

    public String getCampusCode() {
        return campusCode;
    }

    @FieldInfo(name = "校区名称")
    @Column(name = "campusName", length = 64, nullable = false)
    private String campusName;

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getCampusName() {
        return campusName;
    }

    @FieldInfo(name = "校区地址")
    @Column(name = "campusAddr", length = 180, nullable = true)
    private String campusAddr;

    public void setCampusAddr(String campusAddr) {
        this.campusAddr = campusAddr;
    }

    public String getCampusAddr() {
        return campusAddr;
    }

    @FieldInfo(name = "邮政编码")
    @Column(name = "mailCode", length = 16, nullable = true)
    private String mailCode;

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getMailCode() {
        return mailCode;
    }

    @FieldInfo(name = "校区联系电话")
    @Column(name = "campusPhone", length = 30, nullable = true)
    private String campusPhone;

    public void setCampusPhone(String campusPhone) {
        this.campusPhone = campusPhone;
    }

    public String getCampusPhone() {
        return campusPhone;
    }

    @FieldInfo(name = "校区传真电话")
    @Column(name = "campusFax", length = 30, nullable = true)
    private String campusFax;

    public void setCampusFax(String campusFax) {
        this.campusFax = campusFax;
    }

    public String getCampusFax() {
        return campusFax;
    }

    @FieldInfo(name = "校区负责人号")
    @Column(name = "campusPrincipal", length = 32, nullable = true)
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
    @FieldInfo(name = "schoolName")
    @Formula("(SELECT a.SCHOOL_NAME FROM BASE_T_SCHOOL a WHERE a.SCHOOL_ID=SCHOOL_ID )")
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