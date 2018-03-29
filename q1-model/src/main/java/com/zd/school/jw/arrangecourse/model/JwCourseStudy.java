package com.zd.school.jw.arrangecourse.model;

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
 * ClassName: JwCourseStudy Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 自习课程表实体类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_SelfStudyCourse")
@AttributeOverride(name = "selfStudyCourseId", column = @Column(name = "selfStudyCourseId", length = 36, nullable = false))
public class JwCourseStudy extends BaseEntity implements Serializable {
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

	@FieldInfo(name = "学校名称")
	@Column(name = "schoolName", length = 64, nullable = true)
	private String schoolName;

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	@FieldInfo(name = "学年")
	@Column(name = "schoolYear", length = 32, nullable = true)
	private String schoolYear;

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	@FieldInfo(name = "学期")
	@Column(name = "semester", length = 32, nullable = true)
	private String semester;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	@FieldInfo(name = "班级ID")
	@Column(name = "classId", length = 36, nullable = true)
	private String classId;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@FieldInfo(name = "班号")
	@Column(name = "classCode", length = 36, nullable = true)
	private String classCode;

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassCode() {
		return classCode;
	}

	@FieldInfo(name = "班级名称")
	@Column(name = "className", length = 36, nullable = true)
	private String className;

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	@FieldInfo(name = "自习类别（1为早自习。2，3为晚自习）")
	@Column(name = "selfStudyCategory", length = 64, nullable = true)
	private String selfStudyCategory;

	public String getSelfStudyCategory() {
		return selfStudyCategory;
	}

	public void setSelfStudyCategory(String selfStudyCategory) {
		this.selfStudyCategory = selfStudyCategory;
	}

	@FieldInfo(name = "课程ID1")
	@Column(name = "courseId01", length = 36, nullable = true)
	private String courseId01;

	public void setCourseId01(String courseId01) {
		this.courseId01 = courseId01;
	}

	public String getCourseId01() {
		return courseId01;
	}

	@FieldInfo(name = "课程名1")
	@Column(name = "courseName01", length = 64, nullable = true)
	private String courseName01;

	public void setCourseName01(String courseName01) {
		this.courseName01 = courseName01;
	}

	public String getCourseName01() {
		return courseName01;
	}

	@FieldInfo(name = "任课教师ID1")
	@Column(name = "teacherId01", length = 36, nullable = true)
	private String teacherId01;

	public String getTeacherId01() {
		return teacherId01;
	}

	public void setTeacherId01(String teacherId01) {
		this.teacherId01 = teacherId01;
	}

	@FieldInfo(name = "任课教师工号1")
	@Column(name = "teacherNumber01", length = 32, nullable = true)
	private String teacherNumber01;

	public String getTeacherNumber01() {
		return teacherNumber01;
	}

	public void setTeacherNumber01(String teacherNumber01) {
		this.teacherNumber01 = teacherNumber01;
	}

	@FieldInfo(name = "任课教师姓名1")
	@Column(name = "teacherName01", length = 64, nullable = true)
	private String teacherName01;

	public void setTeacherName01(String teacherName01) {
		this.teacherName01 = teacherName01;
	}

	public String getTeacherName01() {
		return teacherName01;
	}

	@FieldInfo(name = "课程ID2")
	@Column(name = "courseId02", length = 36, nullable = true)
	private String courseId02;

	public void setCourseId02(String courseId02) {
		this.courseId02 = courseId02;
	}

	public String getCourseId02() {
		return courseId02;
	}

	@FieldInfo(name = "课程名2")
	@Column(name = "courseName02", length = 64, nullable = true)
	private String courseName02;

	public void setCourseName02(String courseName02) {
		this.courseName02 = courseName02;
	}

	public String getCourseName02() {
		return courseName02;
	}

	@FieldInfo(name = "任课教师ID2")
	@Column(name = "teacherId02", length = 36, nullable = true)
	private String teacherId02;

	public String getTeacherId02() {
		return teacherId02;
	}

	public void setTeacherId02(String teacherId02) {
		this.teacherId02 = teacherId02;
	}

	@FieldInfo(name = "任课教师工号2")
	@Column(name = "teacherNumber02", length = 32, nullable = true)
	private String teacherNumber02;

	public String getTeacherNumber02() {
		return teacherNumber02;
	}

	public void setTeacherNumber02(String teacherNumber02) {
		this.teacherNumber02 = teacherNumber02;
	}

	@FieldInfo(name = "任课教师姓名2")
	@Column(name = "teacherName02", length = 64, nullable = true)
	private String teacherName02;

	public void setTeacherName02(String teacherName02) {
		this.teacherName02 = teacherName02;
	}

	public String getTeacherName02() {
		return teacherName02;
	}

	@FieldInfo(name = "课程ID3")
	@Column(name = "courseId03", length = 36, nullable = true)
	private String courseId03;

	public void setCourseId03(String courseId03) {
		this.courseId03 = courseId03;
	}

	public String getCourseId03() {
		return courseId03;
	}

	@FieldInfo(name = "课程名3")
	@Column(name = "courseName03", length = 64, nullable = true)
	private String courseName03;

	public void setCourseName03(String courseName03) {
		this.courseName03 = courseName03;
	}

	public String getCourseName03() {
		return courseName03;
	}

	@FieldInfo(name = "任课教师ID3")
	@Column(name = "teacherId03", length = 36, nullable = true)
	private String teacherId03;

	public String getTeacherId03() {
		return teacherId03;
	}

	public void setTeacherId03(String teacherId03) {
		this.teacherId03 = teacherId03;
	}

	@FieldInfo(name = "任课教师工号3")
	@Column(name = "teacherNumber03", length = 32, nullable = true)
	private String teacherNumber03;

	public String getTeacherNumber03() {
		return teacherNumber03;
	}

	public void setTeacherNumber03(String teacherNumber03) {
		this.teacherNumber03 = teacherNumber03;
	}

	@FieldInfo(name = "任课教师姓名3")
	@Column(name = "teacherName03", length = 64, nullable = true)
	private String teacherName03;

	public void setTeacherName03(String teacherName03) {
		this.teacherName03 = teacherName03;
	}

	public String getTeacherName03() {
		return teacherName03;
	}

	@FieldInfo(name = "课程ID4")
	@Column(name = "courseId04", length = 36, nullable = true)
	private String courseId04;

	public void setCourseId04(String courseId04) {
		this.courseId04 = courseId04;
	}

	public String getCourseId04() {
		return courseId04;
	}

	@FieldInfo(name = "课程名4")
	@Column(name = "courseName04", length = 64, nullable = true)
	private String courseName04;

	public void setCourseName04(String courseName04) {
		this.courseName04 = courseName04;
	}

	public String getCourseName04() {
		return courseName04;
	}

	@FieldInfo(name = "任课教师ID4")
	@Column(name = "teacherId04", length = 36, nullable = true)
	private String teacherId04;

	public String getTeacherId04() {
		return teacherId04;
	}

	public void setTeacherId04(String teacherId04) {
		this.teacherId04 = teacherId04;
	}

	@FieldInfo(name = "任课教师工号4")
	@Column(name = "teacherNumber04", length = 32, nullable = true)
	private String teacherNumber04;

	public String getTeacherNumber04() {
		return teacherNumber04;
	}

	public void setTeacherNumber04(String teacherNumber04) {
		this.teacherNumber04 = teacherNumber04;
	}

	@FieldInfo(name = "任课教师姓名4")
	@Column(name = "teacherName04", length = 64, nullable = true)
	private String teacherName04;

	public void setTeacherName04(String teacherName04) {
		this.teacherName04 = teacherName04;
	}

	public String getTeacherName04() {
		return teacherName04;
	}

	@FieldInfo(name = "课程ID5")
	@Column(name = "courseId05", length = 36, nullable = true)
	private String courseId05;

	public void setCourseId05(String courseId05) {
		this.courseId05 = courseId05;
	}

	public String getCourseId05() {
		return courseId05;
	}

	@FieldInfo(name = "课程名5")
	@Column(name = "courseName05", length = 64, nullable = true)
	private String courseName05;

	public void setCourseName05(String courseName05) {
		this.courseName05 = courseName05;
	}

	public String getCourseName05() {
		return courseName05;
	}

	@FieldInfo(name = "任课教师ID5")
	@Column(name = "teacherId05", length = 36, nullable = true)
	private String teacherId05;

	public String getTeacherId05() {
		return teacherId05;
	}

	public void setTeacherId05(String teacherId05) {
		this.teacherId05 = teacherId05;
	}

	@FieldInfo(name = "任课教师工号5")
	@Column(name = "teacherNumber05", length = 32, nullable = true)
	private String teacherNumber05;

	public String getTeacherNumber05() {
		return teacherNumber05;
	}

	public void setTeacherNumber05(String teacherNumber05) {
		this.teacherNumber05 = teacherNumber05;
	}

	@FieldInfo(name = "任课教师姓名5")
	@Column(name = "teacherName05", length = 64, nullable = true)
	private String teacherName05;

	public void setTeacherName05(String teacherName05) {
		this.teacherName05 = teacherName05;
	}

	public String getTeacherName05() {
		return teacherName05;
	}

	@FieldInfo(name = "课程ID6")
	@Column(name = "courseId06", length = 36, nullable = true)
	private String courseId06;

	public void setCourseId06(String courseId06) {
		this.courseId06 = courseId06;
	}

	public String getCourseId06() {
		return courseId06;
	}

	@FieldInfo(name = "课程名6")
	@Column(name = "courseName06", length = 64, nullable = true)
	private String courseName06;

	public void setCourseName06(String courseName06) {
		this.courseName06 = courseName06;
	}

	public String getCourseName06() {
		return courseName06;
	}

	@FieldInfo(name = "任课教师ID6")
	@Column(name = "teacherId06", length = 36, nullable = true)
	private String teacherId06;

	public String getTeacherId06() {
		return teacherId06;
	}

	public void setTeacherId06(String teacherId06) {
		this.teacherId06 = teacherId06;
	}

	@FieldInfo(name = "任课教师工号6")
	@Column(name = "teacherNumber06", length = 32, nullable = true)
	private String teacherNumber06;

	public String getTeacherNumber06() {
		return teacherNumber06;
	}

	public void setTeacherNumber06(String teacherNumber06) {
		this.teacherNumber06 = teacherNumber06;
	}

	@FieldInfo(name = "任课教师姓名6")
	@Column(name = "teacherName06", length = 64, nullable = true)
	private String teacherName06;

	public void setTeacherName06(String teacherName06) {
		this.teacherName06 = teacherName06;
	}

	public String getTeacherName06() {
		return teacherName06;
	}

	@FieldInfo(name = "课程ID7")
	@Column(name = "courseId07", length = 36, nullable = true)
	private String courseId07;

	public void setCourseId07(String courseId07) {
		this.courseId07 = courseId07;
	}

	public String getCourseId07() {
		return courseId07;
	}

	@FieldInfo(name = "课程名7")
	@Column(name = "courseName07", length = 64, nullable = true)
	private String courseName07;

	public void setCourseName07(String courseName07) {
		this.courseName07 = courseName07;
	}

	public String getCourseName07() {
		return courseName07;
	}

	@FieldInfo(name = "任课教师ID7")
	@Column(name = "teacherId07", length = 36, nullable = true)
	private String teacherId07;

	public String getTeacherId07() {
		return teacherId07;
	}

	public void setTeacherId07(String teacherId07) {
		this.teacherId07 = teacherId07;
	}

	@FieldInfo(name = "任课教师工号7")
	@Column(name = "teacherNumber07", length = 32, nullable = true)
	private String teacherNumber07;

	public String getTeacherNumber07() {
		return teacherNumber07;
	}

	public void setTeacherNumber07(String teacherNumber07) {
		this.teacherNumber07 = teacherNumber07;
	}

	@FieldInfo(name = "任课教师姓名7")
	@Column(name = "teacherName07", length = 64, nullable = true)
	private String teacherName07;

	public void setTeacherName07(String teacherName07) {
		this.teacherName07 = teacherName07;
	}

	public String getTeacherName07() {
		return teacherName07;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}