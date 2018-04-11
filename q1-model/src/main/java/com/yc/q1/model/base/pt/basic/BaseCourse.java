package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;

/**
 * 基础课程
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_BaseCourse")
@AttributeOverride(name = "id", column = @Column(name = "baseCourseId", length = 20, nullable = false) )
public class BaseCourse extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "课程名称", type = "nvarchar(16) NOT NULL", explain = "课程名称")
	@Column(name = "courseName", columnDefinition = "nvarchar(16)", nullable = false)
	private String courseName;

	@FieldInfo(name = "课程类别码", type = "varchar(4) NOT NULL", explain = "课程类别码")
	@Column(name = "courseType", length = 4, nullable = false)
	private String courseType;

	@FieldInfo(name = "课程编码", type = "varchar(10) default ''", explain = "课程编码")
	@Column(name = "courseCode",  columnDefinition = "varchar(10) default ''", nullable = true)
	private String courseCode;

	@FieldInfo(name = "课程等级码", type = "varchar(10) default ''", explain = "课程等级码")
	@Column(name = "courseLevel", columnDefinition = "varchar(10) default ''", nullable = true)
	private String courseLevel;

	@FieldInfo(name = "课程别名", type = "nvarchar(16) default ''", explain = "课程别名")
	@Column(name = "aliasName", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String aliasName;

	@FieldInfo(name = "总学时", type = "int default 0", explain = "总学时")
	@Column(name = "totalHour", columnDefinition = "int default 0", nullable = true)
	private Integer totalHour;

	@FieldInfo(name = "周学时", type = "int default 0", explain = "周学时")
	@Column(name = "weekHour", columnDefinition = "int default 0", nullable = true)
	private Integer weekHour;

	@FieldInfo(name = "授课方式码", type = "varchar(4)  default ''", explain = "授课方式码")
	@Column(name = "teachWay", columnDefinition = "varchar(4) default ''", nullable = true)
	private String teachWay;

	@FieldInfo(name = "课程简介", type = "nvarchar(256) default ''", explain = "课程简介")
	@Column(name = "courseExplain", columnDefinition = "nvarchar(256) default ''", nullable = true)
	private String courseExplain;

	@FieldInfo(name = "课程要求", type = "nvarchar(256) default ''", explain = "课程要求")
	@Column(name = "courseRequire", columnDefinition = "nvarchar(256) default ''", nullable = true)
	private String courseRequire;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(Integer totalHour) {
		this.totalHour = totalHour;
	}

	public Integer getWeekHour() {
		return weekHour;
	}

	public void setWeekHour(Integer weekHour) {
		this.weekHour = weekHour;
	}

	public String getTeachWay() {
		return teachWay;
	}

	public void setTeachWay(String teachWay) {
		this.teachWay = teachWay;
	}

	public String getCourseExplain() {
		return courseExplain;
	}

	public void setCourseExplain(String courseExplain) {
		this.courseExplain = courseExplain;
	}

	public String getCourseRequire() {
		return courseRequire;
	}

	public void setCourseRequire(String courseRequire) {
		this.courseRequire = courseRequire;
	}

	public BaseCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseCourse(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

}