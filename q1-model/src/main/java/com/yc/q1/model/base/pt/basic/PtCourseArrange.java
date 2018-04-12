package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 课程安排
 * 
 * @author ZZK
 *
 *         注：旧版本使用备用字段5作为 课表是否启用的标识
 */

@Entity
@Table(name = "T_PT_CourseArrange")
@AttributeOverride(name = "id", column = @Column(name = "courseArrangeId", length = 20, nullable = false) )
public class PtCourseArrange extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级ID", type = "varchar(20) NOT NULL", explain = "班级ID")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "班级名称", type = "nvarchar(16) NOT NULL", explain = "班级名称")
	@Column(name = "className", columnDefinition = "nvarchar(16)", nullable = false)
	private String className;

	@FieldInfo(name = "节次", type = "nvarchar(10) NOT NULL", explain = "节次")
	@Column(name = "sections", columnDefinition = "nvarchar(10) default ''", nullable = false)
	private String sections;

	@FieldInfo(name = "是否启用", type = "bit NOT NULL", explain = "是否启用该课表")
	@Column(name = "isUse", nullable = false)
	private Boolean isUse;

	@FieldInfo(name = "课程ID1", type = "varchar(20) default ''", explain = "课程ID1")
	@Column(name = "courseId01", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId01;

	@FieldInfo(name = "课程名1", type = "nvarchar(10) default ''", explain = "课程名1")
	@Column(name = "courseName01", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName01;

	@FieldInfo(name = "任课教师ID1", type = "varchar(20) default ''", explain = "任课教师ID1")
	@Column(name = "teacherId01", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId01;

	@FieldInfo(name = "任课教师工号1", type = "varchar(20) default ''", explain = "任课教师工号1")
	@Column(name = "teacherNumber01", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber01;

	@FieldInfo(name = "任课教师姓名1", type = "nvarchar(10) default ''", explain = "任课教师姓名1")
	@Column(name = "teacherName01", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName01;

	@FieldInfo(name = "课程ID2", type = "varchar(20) default ''", explain = "课程ID2")
	@Column(name = "courseId02", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId02;

	@FieldInfo(name = "课程名2", type = "nvarchar(10) default ''", explain = "课程名2")
	@Column(name = "courseName02", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName02;

	@FieldInfo(name = "任课教师ID2", type = "varchar(20) default ''", explain = "任课教师ID2")
	@Column(name = "teacherId02", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId02;

	@FieldInfo(name = "任课教师工号2", type = "varchar(20) default ''", explain = "任课教师工号2")
	@Column(name = "teacherNumber02", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber02;

	@FieldInfo(name = "任课教师姓名2", type = "nvarchar(10) default ''", explain = "任课教师姓名2")
	@Column(name = "teacherName02", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName02;

	@FieldInfo(name = "课程ID3", type = "varchar(20) default ''", explain = "课程ID3")
	@Column(name = "courseId03", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId03;

	@FieldInfo(name = "课程名3", type = "nvarchar(10) default ''", explain = "课程名3")
	@Column(name = "courseName03", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName03;

	@FieldInfo(name = "任课教师ID3", type = "varchar(20) default ''", explain = "任课教师ID3")
	@Column(name = "teacherId03", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId03;

	@FieldInfo(name = "任课教师工号3", type = "varchar(20) default ''", explain = "任课教师工号3")
	@Column(name = "teacherNumber03", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber03;

	@FieldInfo(name = "任课教师姓名3", type = "nvarchar(10) default ''", explain = "任课教师姓名3")
	@Column(name = "teacherName03", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName03;

	@FieldInfo(name = "课程ID4", type = "varchar(20) default ''", explain = "课程ID4")
	@Column(name = "courseId04", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId04;

	@FieldInfo(name = "课程名4", type = "nvarchar(10) default ''", explain = "课程名4")
	@Column(name = "courseName04", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName04;

	@FieldInfo(name = "任课教师ID4", type = "varchar(20) default ''", explain = "任课教师ID4")
	@Column(name = "teacherId04", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId04;

	@FieldInfo(name = "任课教师工号4", type = "varchar(20) default ''", explain = "任课教师工号4")
	@Column(name = "teacherNumber04", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber04;

	@FieldInfo(name = "任课教师姓名4", type = "nvarchar(10) default ''", explain = "任课教师姓名4")
	@Column(name = "teacherName04", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName04;

	@FieldInfo(name = "课程ID5", type = "varchar(20) default ''", explain = "课程ID5")
	@Column(name = "courseId05", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId05;

	@FieldInfo(name = "课程名5", type = "nvarchar(10) default ''", explain = "课程名5")
	@Column(name = "courseName05", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName05;

	@FieldInfo(name = "任课教师ID5", type = "varchar(20) default ''", explain = "任课教师ID5")
	@Column(name = "teacherId05", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId05;

	@FieldInfo(name = "任课教师工号5", type = "varchar(20) default ''", explain = "任课教师工号5")
	@Column(name = "teacherNumber05", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber05;

	@FieldInfo(name = "任课教师姓名5", type = "nvarchar(10) default ''", explain = "任课教师姓名5")
	@Column(name = "teacherName05", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName05;

	@FieldInfo(name = "课程ID6", type = "varchar(20) default ''", explain = "课程ID6")
	@Column(name = "courseId06", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId06;

	@FieldInfo(name = "课程名6", type = "nvarchar(10) default ''", explain = "课程名6")
	@Column(name = "courseName06", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName06;

	@FieldInfo(name = "任课教师ID6", type = "varchar(20) default ''", explain = "任课教师ID6")
	@Column(name = "teacherId06", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId06;

	@FieldInfo(name = "任课教师工号6", type = "varchar(20) default ''", explain = "任课教师工号6")
	@Column(name = "teacherNumber06", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber06;

	@FieldInfo(name = "任课教师姓名6", type = "nvarchar(10) default ''", explain = "任课教师姓名6")
	@Column(name = "teacherName06", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName06;

	@FieldInfo(name = "课程ID7", type = "varchar(20) default ''", explain = "课程ID7")
	@Column(name = "courseId07", columnDefinition = "varchar(20) default ''", nullable = true)
	private String courseId07;

	@FieldInfo(name = "课程名7", type = "nvarchar(10) default ''", explain = "课程名7")
	@Column(name = "courseName07", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String courseName07;

	@FieldInfo(name = "任课教师ID7", type = "varchar(20) default ''", explain = "任课教师ID7")
	@Column(name = "teacherId07", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId07;

	@FieldInfo(name = "任课教师工号7", type = "varchar(20) default ''", explain = "任课教师工号7")
	@Column(name = "teacherNumber07", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherNumber07;

	@FieldInfo(name = "任课教师姓名7", type = "nvarchar(10) default ''", explain = "任课教师姓名7")
	@Column(name = "teacherName07", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String teacherName07;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@Transient
	// @FieldInfo(name = "周一课程")
	private String weekOne;

	@Transient
	// @FieldInfo(name = "周二课程")
	private String weekTwo;

	@Transient
	// @FieldInfo(name = "周三课程")
	private String weekThree;

	@Transient
	// @FieldInfo(name = "周四课程")
	private String weekFour;

	@Transient
	// @FieldInfo(name = "周五课程")
	private String weekFive;

	@Transient
	// @FieldInfo(name = "周六课程")
	private String weekSix;

	@Transient
	// @FieldInfo(name = "周日课程")
	private String weekSeven;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSections() {
		return sections;
	}

	public void setSections(String sections) {
		this.sections = sections;
	}

	public String getCourseId01() {
		return courseId01;
	}

	public void setCourseId01(String courseId01) {
		this.courseId01 = courseId01;
	}

	public String getCourseName01() {
		return courseName01;
	}

	public void setCourseName01(String courseName01) {
		this.courseName01 = courseName01;
	}

	public String getTeacherId01() {
		return teacherId01;
	}

	public void setTeacherId01(String teacherId01) {
		this.teacherId01 = teacherId01;
	}

	public String getTeacherNumber01() {
		return teacherNumber01;
	}

	public void setTeacherNumber01(String teacherNumber01) {
		this.teacherNumber01 = teacherNumber01;
	}

	public String getTeacherName01() {
		return teacherName01;
	}

	public void setTeacherName01(String teacherName01) {
		this.teacherName01 = teacherName01;
	}

	public String getCourseId02() {
		return courseId02;
	}

	public void setCourseId02(String courseId02) {
		this.courseId02 = courseId02;
	}

	public String getCourseName02() {
		return courseName02;
	}

	public void setCourseName02(String courseName02) {
		this.courseName02 = courseName02;
	}

	public String getTeacherId02() {
		return teacherId02;
	}

	public void setTeacherId02(String teacherId02) {
		this.teacherId02 = teacherId02;
	}

	public String getTeacherNumber02() {
		return teacherNumber02;
	}

	public void setTeacherNumber02(String teacherNumber02) {
		this.teacherNumber02 = teacherNumber02;
	}

	public String getTeacherName02() {
		return teacherName02;
	}

	public void setTeacherName02(String teacherName02) {
		this.teacherName02 = teacherName02;
	}

	public String getCourseId03() {
		return courseId03;
	}

	public void setCourseId03(String courseId03) {
		this.courseId03 = courseId03;
	}

	public String getCourseName03() {
		return courseName03;
	}

	public void setCourseName03(String courseName03) {
		this.courseName03 = courseName03;
	}

	public String getTeacherId03() {
		return teacherId03;
	}

	public void setTeacherId03(String teacherId03) {
		this.teacherId03 = teacherId03;
	}

	public String getTeacherNumber03() {
		return teacherNumber03;
	}

	public void setTeacherNumber03(String teacherNumber03) {
		this.teacherNumber03 = teacherNumber03;
	}

	public String getTeacherName03() {
		return teacherName03;
	}

	public void setTeacherName03(String teacherName03) {
		this.teacherName03 = teacherName03;
	}

	public String getCourseId04() {
		return courseId04;
	}

	public void setCourseId04(String courseId04) {
		this.courseId04 = courseId04;
	}

	public String getCourseName04() {
		return courseName04;
	}

	public void setCourseName04(String courseName04) {
		this.courseName04 = courseName04;
	}

	public String getTeacherId04() {
		return teacherId04;
	}

	public void setTeacherId04(String teacherId04) {
		this.teacherId04 = teacherId04;
	}

	public String getTeacherNumber04() {
		return teacherNumber04;
	}

	public void setTeacherNumber04(String teacherNumber04) {
		this.teacherNumber04 = teacherNumber04;
	}

	public String getTeacherName04() {
		return teacherName04;
	}

	public void setTeacherName04(String teacherName04) {
		this.teacherName04 = teacherName04;
	}

	public String getCourseId05() {
		return courseId05;
	}

	public void setCourseId05(String courseId05) {
		this.courseId05 = courseId05;
	}

	public String getCourseName05() {
		return courseName05;
	}

	public void setCourseName05(String courseName05) {
		this.courseName05 = courseName05;
	}

	public String getTeacherId05() {
		return teacherId05;
	}

	public void setTeacherId05(String teacherId05) {
		this.teacherId05 = teacherId05;
	}

	public String getTeacherNumber05() {
		return teacherNumber05;
	}

	public void setTeacherNumber05(String teacherNumber05) {
		this.teacherNumber05 = teacherNumber05;
	}

	public String getTeacherName05() {
		return teacherName05;
	}

	public void setTeacherName05(String teacherName05) {
		this.teacherName05 = teacherName05;
	}

	public String getCourseId06() {
		return courseId06;
	}

	public void setCourseId06(String courseId06) {
		this.courseId06 = courseId06;
	}

	public String getCourseName06() {
		return courseName06;
	}

	public void setCourseName06(String courseName06) {
		this.courseName06 = courseName06;
	}

	public String getTeacherId06() {
		return teacherId06;
	}

	public void setTeacherId06(String teacherId06) {
		this.teacherId06 = teacherId06;
	}

	public String getTeacherNumber06() {
		return teacherNumber06;
	}

	public void setTeacherNumber06(String teacherNumber06) {
		this.teacherNumber06 = teacherNumber06;
	}

	public String getTeacherName06() {
		return teacherName06;
	}

	public void setTeacherName06(String teacherName06) {
		this.teacherName06 = teacherName06;
	}

	public String getCourseId07() {
		return courseId07;
	}

	public void setCourseId07(String courseId07) {
		this.courseId07 = courseId07;
	}

	public String getCourseName07() {
		return courseName07;
	}

	public void setCourseName07(String courseName07) {
		this.courseName07 = courseName07;
	}

	public String getTeacherId07() {
		return teacherId07;
	}

	public void setTeacherId07(String teacherId07) {
		this.teacherId07 = teacherId07;
	}

	public String getTeacherNumber07() {
		return teacherNumber07;
	}

	public void setTeacherNumber07(String teacherNumber07) {
		this.teacherNumber07 = teacherNumber07;
	}

	public String getTeacherName07() {
		return teacherName07;
	}

	public void setTeacherName07(String teacherName07) {
		this.teacherName07 = teacherName07;
	}

	public String getWeekOne() {
		return weekOne;
	}

	public void setWeekOne(String weekOne) {
		this.weekOne = weekOne;
	}

	public String getWeekTwo() {
		return weekTwo;
	}

	public void setWeekTwo(String weekTwo) {
		this.weekTwo = weekTwo;
	}

	public String getWeekThree() {
		return weekThree;
	}

	public void setWeekThree(String weekThree) {
		this.weekThree = weekThree;
	}

	public String getWeekFour() {
		return weekFour;
	}

	public void setWeekFour(String weekFour) {
		this.weekFour = weekFour;
	}

	public String getWeekFive() {
		return weekFive;
	}

	public void setWeekFive(String weekFive) {
		this.weekFive = weekFive;
	}

	public String getWeekSix() {
		return weekSix;
	}

	public void setWeekSix(String weekSix) {
		this.weekSix = weekSix;
	}

	public String getWeekSeven() {
		return weekSeven;
	}

	public void setWeekSeven(String weekSeven) {
		this.weekSeven = weekSeven;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public PtCourseArrange() {
		super();
	}

	public PtCourseArrange(String id) {
		super(id);
	}

}