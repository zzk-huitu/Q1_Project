package com.zd.school.jw.eduresources.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 
 * ClassName: JwTBasecourse Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 基础课程信息实体类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_BaseCourse")
@AttributeOverride(name = "baseCourseId", column = @Column(name = "baseCourseId", length = 36, nullable = false))
public class JwTBasecourse extends BaseEntity implements Serializable {
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

	@FieldInfo(name = "课程编码")
	@Column(name = "courseCode", length = 32, nullable = true)
	private String courseCode;

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	@FieldInfo(name = "课程名称")
	@Column(name = "courseName", length = 60, nullable = true)
	private String courseName;

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	@FieldInfo(name = "课程类别码")
	@Column(name = "courseType", length = 10, nullable = true)
	private String courseType;

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseType() {
		return courseType;
	}

	@FieldInfo(name = "课程等级码")
	@Column(name = "courseLevel", length = 10, nullable = true)
	private String courseLevel;

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	@FieldInfo(name = "课程别名")
	@Column(name = "aliasName", length = 60, nullable = true)
	private String aliasName;

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getAliasName() {
		return aliasName;
	}

	@FieldInfo(name = "总学时")
	@Column(name = "totalHour", length = 3, nullable = true)
	private BigDecimal totalHour;

	public void setTotalHour(BigDecimal totalHour) {
		this.totalHour = totalHour;
	}

	public BigDecimal getTotalHour() {
		return totalHour;
	}

	@FieldInfo(name = "周学时")
	@Column(name = "weekHour", length = 2, nullable = true)
	private BigDecimal weekHour;

	public void setWeekHour(BigDecimal weekHour) {
		this.weekHour = weekHour;
	}

	public BigDecimal getWeekHour() {
		return weekHour;
	}

	@FieldInfo(name = "自学学时")
	@Column(name = "selfStudyHour", length = 3, nullable = true)
	private BigDecimal selfStudyHour;

	public BigDecimal getSelfStudyHour() {
		return selfStudyHour;
	}

	public void setSelfStudyHour(BigDecimal selfStudyHour) {
		this.selfStudyHour = selfStudyHour;
	}

	@FieldInfo(name = "授课方式码")
	@Column(name = "teachWay", length = 10, nullable = true)
	private String teachWay;

	public void setTeachWay(String teachWay) {
		this.teachWay = teachWay;
	}

	public String getTeachWay() {
		return teachWay;
	}

	@FieldInfo(name = "课程简介")
	@Column(name = "courseExplain", length = 1024, nullable = true)
	private String courseExplain;

	public String getCourseExplain() {
		return courseExplain;
	}

	public void setCourseExplain(String courseExplain) {
		this.courseExplain = courseExplain;
	}

	@FieldInfo(name = "课程要求")
	@Column(name = "courseRequest", length = 1024, nullable = true)
	private String courseRequest;

	public void setCourseRequest(String courseRequest) {
		this.courseRequest = courseRequest;
	}

	public String getCourseRequest() {
		return courseRequest;
	}

	public JwTBasecourse() {

		super();
		// TODO Auto-generated constructor stub

	}

	public JwTBasecourse(String uuid) {

		super(uuid);
		// TODO Auto-generated constructor stub

	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "学校名称")
	@Formula("(SELECT a.NODE_TEXT from BASE_T_ORG a where a.DEPT_ID=SCHOOL_ID)")
	private String schoolName;

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}

}