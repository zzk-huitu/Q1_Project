package com.zd.school.jw.eduresources.model;

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
 * ClassName: JwGradeteacher Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 年级组长信息实体类. date: 2016-08-22
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_GradeTeacher")
@AttributeOverride(name = "gradeTeacherId", column = @Column(name = "gradeTeacherId", length = 20, nullable = false))
public class JwGradeteacher extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "gradeId", type = "varchar(20)", explain = "年级Id")
	@Column(name = "gradeId", length = 20, nullable = false)
	private String gradeId;

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	@FieldInfo(name = "teacherId", type = "varchar(20)", explain = "教职工Id")
	@Column(name = "teacherId", length = 20, nullable = false)
	private String teacherId;

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	@FieldInfo(name = "studyYear", type = "Integer", explain = "学年")
	@Column(name = "studyYear", nullable = false)
	private Integer studyYear;

	public Integer getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(Integer studyYear) {
		this.studyYear = studyYear;
	}

	@FieldInfo(name = "semester", type = "nvarchar(20)", explain = "学期")
	@Column(name = "semester", columnDefinition = "nvarchar(20)", nullable = false)
	private String semester;

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	@FieldInfo(name = "category", type = "Integer", explain = "身份")
	@Column(name = "category", nullable = false)
	private Integer category;

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return category;
	}

	@FieldInfo(name = "studyYearName", type = "nvarchar(20)", explain = "学年名称")
	@Column(name = "studyYearName", columnDefinition = "nvarchar(20)", nullable = false)
	private String studyYearName;

	public String getStudyYearName() {
		return studyYearName;
	}

	public void setStudyYearName(String studyYearName) {
		this.studyYearName = studyYearName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@FieldInfo(name = "年级名称")
	@Formula("(SELECT a.gradeName FROM T_PT_Grade a WHERE a.gradeId=gradeId )")
	private String gradeName;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@FieldInfo(name = "老师工号")
	@Formula("(SELECT a.userNumb FROM T_PT_User a WHERE a.usreId=teacherId )")
	private String userNumb;

	public String getUserNumb() {
		return userNumb;
	}

	public void setUserNumb(String userNumb) {
		this.userNumb = userNumb;
	}

	@FieldInfo(name = "老师姓名")
	@Formula("(SELECT a.name FROM T_PT_User a WHERE a.usreId=teacherId )")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@FieldInfo(name = "老师性别")
	@Formula("(SELECT a.genderCode FROM T_PT_User a WHERE a.usreId=teacherId )")
	private String genderCode;

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

}