package com.zd.school.jw.arrangecourse.model;

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
 * ClassName: JwCourseteacher Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 教师任课信息(JW_T_COURSETEACHER)实体类. date: 2016-08-26
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_CourseTeacher")
@AttributeOverride(name = "courseTeacherId", column = @Column(name = "courseTeacherId", length = 20, nullable = false))
public class JwCourseteacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classId",type="varchar(20)",explain="班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@FieldInfo(name = "teacherId",type="varchar(20)",explain="教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@FieldInfo(name = "studyYear",type="nvarchar(20)",explain="学年")
	@Column(name = "studyYear", columnDefinition = "nvarchar(20)", nullable = false)
	private Integer studyYear;

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	public Integer getStudyYear() {
		return studyYear;
	}

	@FieldInfo(name = "studyYearName",type="nvarchar(20)",explain="学年名称")
	@Column(name = "studyYearName", columnDefinition = "varchar(20) default ''", nullable = true)
	private String studyYearName;

	public String getStudyYearName() {
		return studyYearName;
	}

	public void setStudyYearName(String studyYearName) {
		this.studyYearName = studyYearName;
	}

	@FieldInfo(name = "semester",type="nvarchar(20)",explain="学期")
	@Column(name = "semester", columnDefinition = "nvarchar(20)", nullable = false)
	private String semester;

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	@FieldInfo(name = "courseCountWeek",type="Integer",explain="周节数: acs_zjs一周上多少节课")
	@Column(name = "courseCountWeek", columnDefinition = "default 0", nullable = true)
	private Integer courseCountWeek;

	public Integer getCourseCountWeek() {
		return courseCountWeek;
	}

	public void setCourseCountWeek(Integer courseCountWeek) {
		this.courseCountWeek = courseCountWeek;
	}

	@FieldInfo(name = "laboratoryId",type="varchar(20)",explain="实验室Id") // 暂未用到
	@Column(name = "laboratoryId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String laboratoryId;

	public String getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(String laboratoryId) {
		this.laboratoryId = laboratoryId;
	}

	@FieldInfo(name = "courseId",type="varchar(20)",explain="课程Id")
	@Column(name = "courseId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@FieldInfo(name = "课程名称")
	@Formula("(SELECT a.courseName FROM T_PT_BaseCourse a WHERE a.baseCourseId=courseId )")
	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@FieldInfo(name = "老师工号")
	@Formula("(SELECT a.userNumb FROM T_PT_User a WHERE a.userId=teacherId )")
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	@FieldInfo(name = "老师姓名")
	@Formula("(SELECT a.name FROM T_PT_User a WHERE a.userId=teacherId )")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@FieldInfo(name = "老师性别")
	@Formula("(SELECT a.genderCode FROM T_PT_User a WHERE a.userId=teacherId )")
	private String genderCode;

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public JwCourseteacher() {
	}

	public JwCourseteacher(String classId, String teacherId, String courseId, Integer studyYear, String semester) {
		super();
		this.classId = classId;
		this.teacherId = teacherId;
		this.courseId = courseId;
		this.studyYear = studyYear;
		this.semester = semester;
	}
}