package com.zd.school.jw.eduresources.model;

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
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: JwClassteacher Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班主任信息实体类. date: 2016-08-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassTeacher")
@AttributeOverride(name = "classTeacherId", column = @Column(name = "classTeacherId", length = 20, nullable = false))
public class JwClassteacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classId", type = "varcha(20)", explain = "班级Id")
	@Column(name = "classId", length = 20, nullable = false)
	private String classId;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@FieldInfo(name = "teacherId", type = "varcha(20)", explain = "教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@FieldInfo(name = "studyYear", type = "Integer", explain = "学年")
	@Column(name = "studyYear", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private Integer studyYear;

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	@FieldInfo(name = "semester", type = "nvarchar(20)", explain = "学期")
	@Column(name = "semester", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String semester;

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	@FieldInfo(name = "category", type = "Integer", explain = "身份") // 0-正班主任
																	// 1-副班主任
	@Column(name = "category", nullable = false)
	private Integer category;

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return category;
	}

	@FieldInfo(name = "studyYearName", type = "nvarchar(20", explain = "学年名称")
	@Column(name = "studyYearName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String studyYearName;

	public String getStudyYearName() {
		return studyYearName;
	}

	public void setStudyYearName(String studyYearName) {
		this.studyYearName = studyYearName;
	}

	@FieldInfo(name = "beginTime", type = "datetime", explain = "开始时间")
	@Column(name = "beginTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	@FieldInfo(name = "endTime", type = "datetime", explain = "结束时间")
	@Column(name = "endTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
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

	@FieldInfo(name = "年级ID")
	@Formula("(SELECT a.GRAI_ID FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String graiId;

	public String getGraiId() {
		return graiId;
	}

	public void setGraiId(String graiId) {
		this.graiId = graiId;
	}

	@FieldInfo(name = "年级ID")
	@Formula("(SELECT a.nj FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String nj;

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

}