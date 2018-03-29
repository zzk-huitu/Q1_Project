package com.zd.school.student.studentclass.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: StuDividerecord 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 学生分班记录实体类.
 * date: 2016-08-23
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_DivideRecode")
@AttributeOverride(name = "divideRecodeId", column = @Column(name = "divideRecodeId", length = 20, nullable = false))
public class StuDividerecord extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "学年")
    @Column(name = "schoolYear", columnDefinition="nvarchar(36) defalut ''", nullable = true)
    private String schoolYear;
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    public String getSchoolYear() {
        return schoolYear;
    }
        
    @FieldInfo(name = "学期")
    @Column(name = "semester", columnDefinition="nvarchar(8)", nullable = false)
    private String semester;
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getSemester() {
        return semester;
    }
        
    @FieldInfo(name = "标题")
    @Column(name = "divideTitle", columnDefinition="nvarchar(255)", nullable = false)
    private String divideTitle;
    public void setDivideTitle(String divideTitle) {
        this.divideTitle = divideTitle;
    }
    public String getDivideTitle() {
        return divideTitle;
    }
        
    @FieldInfo(name = "状态")
    @Column(name = "state",nullable = true)
    private boolean state;
    public void setState(boolean state) {
        this.state = state;
    }
    public boolean getState() {
        return state;
    }
        
    @FieldInfo(name = "当前步骤")
    @Column(name = "justStep",  columnDefinition="nvarchar(10)", nullable = false)
    private Integer justStep;
    public void setJustStep(Integer justStep) {
        this.justStep = justStep;
    }
    public Integer getJustStep() {
        return justStep;
    }
        

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}