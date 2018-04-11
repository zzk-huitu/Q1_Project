package com.yc.q1.model.base.pt.wisdomclass;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 班主任
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_ClassTeacher")
@AttributeOverride(name = "id", column = @Column(name = "classTeacherId", length = 20, nullable = false))
public class ClassTeacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级Id", type = "varcha(20) NOT NULL", explain = "班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "教职工Id", type = "varcha(20) NOT NULL", explain = "教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	@FieldInfo(name = "学年", type = "int default 0", explain = "学年")
	@Column(name = "studyYear", columnDefinition = "int default 0", nullable = true)
	private Integer studyYear;

	@FieldInfo(name = "studyYearName", type = "nvarchar(20", explain = "学年名称")
	@Column(name = "studyYearName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String studyYearName;

	@FieldInfo(name = "学期", type = "nvarchar(10) default ''", explain = "学期")
	@Column(name = "semester", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String semester;

	@FieldInfo(name = "身份", type = "int NOT NULL", explain = "身份(0-正班主任/1-副班主任)")
	@Column(name = "category", nullable = false)
	private Integer category;

	@FieldInfo(name = "beginTime", type = "datetime", explain = "开始时间")
	@Column(name = "beginTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	@FieldInfo(name = "endTime", type = "datetime", explain = "结束时间")
	@Column(name = "endTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

	// @FieldInfo(name = "年级ID")
	@Formula("(SELECT a.gradeId FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String gradeId;

	// @FieldInfo(name = "年级")
	@Formula("(SELECT a.nj FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String nj;

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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public ClassTeacher() {
		super();
	}

	public ClassTeacher(String id) {
		super(id);
	}

}