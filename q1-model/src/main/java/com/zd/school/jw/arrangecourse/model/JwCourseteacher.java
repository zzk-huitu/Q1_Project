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
 * 教师任课信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_CourseTeacher")
@AttributeOverride(name = "id", column = @Column(name = "courseTeacherId", length = 20, nullable = false) )
public class JwCourseteacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "班级Id", type = "varchar(20) NOT NULL", explain = "班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "教职工Id", type = "varchar(20) NOT NULL", explain = "教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	@FieldInfo(name = "学年", type = "int NOT NULL", explain = "学年")
	@Column(name = "studyYear", nullable = false)
	private Integer studyYear;

	@FieldInfo(name = "学年名称", type = "nvarchar(20)  default ''", explain = "学年名称")
	@Column(name = "studyYearName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String studyYearName;

	@FieldInfo(name = "学期", type = "nvarchar(10)", explain = "学期")
	@Column(name = "semester", columnDefinition = "nvarchar(10)", nullable = false)
	private String semester;

	@FieldInfo(name = "周节数", type = "int default 0", explain = "周节数: acs_zjs一周上多少节课")
	@Column(name = "courseCountWeek", columnDefinition = "int default 0", nullable = true)
	private Integer courseCountWeek;

	@FieldInfo(name = "课程Id", type = "varchar(20) NOT NULL", explain = "课程Id")
	@Column(name = "courseId", length=20, nullable = false)
	private String courseId;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	// @FieldInfo(name = "课程名称")
	@Formula("(SELECT a.courseName FROM T_PT_BaseCourse a WHERE a.baseCourseId=courseId )")
	private String courseName;

	// @FieldInfo(name = "老师工号")
	@Formula("(SELECT a.userNumb FROM T_PT_User a WHERE a.userId=teacherId )")
	private String userNumb;

	// @FieldInfo(name = "老师姓名")
	@Formula("(SELECT a.name FROM T_PT_User a WHERE a.userId=teacherId )")
	private String name;

	// @FieldInfo(name = "老师性别")
	@Formula("(SELECT a.sex FROM T_PT_User a WHERE a.userId=teacherId )")
	private String sex;

	
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	public String getStudyYearName() {
		return studyYearName;
	}

	public void setStudyYearName(String studyYearName) {
		this.studyYearName = studyYearName;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Integer getCourseCountWeek() {
		return courseCountWeek;
	}

	public void setCourseCountWeek(Integer courseCountWeek) {
		this.courseCountWeek = courseCountWeek;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	

	public JwCourseteacher() {
		super();
	}

	public JwCourseteacher(String id) {
		super(id);
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