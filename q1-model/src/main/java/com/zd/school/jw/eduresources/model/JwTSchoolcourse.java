package com.zd.school.jw.eduresources.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: JwTSchoolcourse Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 校本课程实体类. date: 2016-03-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_SchoolCourse")
@AttributeOverride(name = "schoolCourseId", column = @Column(name = "schoolCourseId", length = 20, nullable = false))
public class JwTSchoolcourse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @FieldInfo(name = "courseCode", type = "nvarchar(20)", explain = "课程编码")
    @Column(name = "courseCode", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String courseCode;

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @FieldInfo(name = "courseType", type = "varchar(20)", explain = "课程类型")
    @Column(name = "courseType", columnDefinition = "varchar(20) default ''", nullable = true)
    private String courseType;

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseType() {
        return courseType;
    }

    @FieldInfo(name = "courseName", type = "nvarchar(20)", explain = "课程名称")
    @Column(name = "courseName", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String courseName;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    @FieldInfo(name = "teachWay", type = "varchar(20)", explain = "授课方式码")
    @Column(name = "teachWay", columnDefinition = "varchar(20) default ''", nullable = true)
    private String teachWay;

    public void setTeachWay(String teachWay) {
        this.teachWay = teachWay;
    }

    public String getTeachWay() {
        return teachWay;
    }

    @FieldInfo(name = "teacherId", type = "varchar(20)", explain = "讲师ID")
    @Column(name = "teacherId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String teacherId;

    public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@FieldInfo(name = "teacherName", type = "nvarchar(20)", explain = "讲师姓名")
    @Column(name = "teacherName", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String teacherName;

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    @FieldInfo(name = "teachstudent", type = "nvarchar(200)", explain = "授课对象")
    @Column(name = "teachstudent",columnDefinition = "nvarchar(200) default ''", nullable = true)
    private String teachstudent;

    public void setTeachstudent(String teachstudent) {
        this.teachstudent = teachstudent;
    }

    public String getTeachstudent() {
        return teachstudent;
    }

    @FieldInfo(name = "courseTarget", type = "nvarchar(500)", explain = "课程目标")
    @Column(name = "courseTarget", columnDefinition = "nvarchar(500) default ''", nullable = true)
    private String courseTarget;

    public void setCourseTarget(String courseTarget) {
        this.courseTarget = courseTarget;
    }

    public String getCourseTarget() {
        return courseTarget;
    }

    @FieldInfo(name = "courseContent", type = "nvarchar(500)", explain = "主要内容")
    @Column(name = "courseContent", columnDefinition = "nvarchar(500) default ''", nullable = true)
    private String courseContent;

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public String getCourseContent() {
        return courseContent;
    }

    @FieldInfo(name = "remark", type = "nvarchar(500)", explain = "备注")
    @Column(name = "remark", columnDefinition = "nvarchar(500) default ''", nullable = true)
    private String remark;

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    @FieldInfo(name = "schoolId", type = "varchar(20)", explain = "学校Id")
    @Column(name = "schoolId", length = 20, nullable = false)
    private String schoolId;

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    @FieldInfo(name = "schoolName", type = "nvarchar(20)", explain = "学校名称")
    @Column(name = "schoolName", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String schoolName;

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @FieldInfo(name = "state", type = "Integer", explain = "课程状态: 0-草稿; 1-待审核; 2-审核不通过 3-审核通过")
    @Column(name = "state")
    private Integer state;
        
    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public JwTSchoolcourse() {

        super();
        // TODO Auto-generated constructor stub

    }

    public JwTSchoolcourse(String uuid) {

        super(uuid);
        // TODO Auto-generated constructor stub

    }

    /**
     * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
     * 
     * @Transient
     * @FieldInfo(name = "") private String field1;
     */
}