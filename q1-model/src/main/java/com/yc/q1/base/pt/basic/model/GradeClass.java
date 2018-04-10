package com.yc.q1.base.pt.basic.model;

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
 * 班级表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_GradeClass")
@AttributeOverride(name = "id", column = @Column(name = "classId", length = 20, nullable = false) )
public class GradeClass extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "年级ID", type = "varchar(20) NOT NULL", explain = "年级ID")
	@Column(name = "gradeId", length = 20, nullable = false)
	private String gradeId;

	@FieldInfo(name = "班级名称", type = "nvarchar(16) NOT NULL", explain = "班级名称")
	@Column(name = "className", columnDefinition = "nvarchar(16) ", nullable = false)
	private String className;

	@FieldInfo(name = "班级类型", type = "varchar(4) default ''", explain = "班级类型（数据字典）")
	@Column(name = "classType", columnDefinition = "varchar(4) default ''", nullable = true)
	private String classType;

	@FieldInfo(name = "文理类型", type = "varchar(4)  default ''", explain = "文理类型")
	@Column(name = "artsSciencesType", columnDefinition = "varchar(4) default ''", nullable = true)
	private String artsSciencesType;

	@FieldInfo(name = "双语教学模式", type = "varchar(4)  default ''", explain = "双语教学模式")
	@Column(name = "bilingualModel", columnDefinition = "varchar(4) default ''", nullable = true)
	private String bilingualModel;

	@FieldInfo(name = "班号", type = "varchar(16) default ''", explain = "班号")
	@Column(name = "classCode", columnDefinition = "varchar(16) default ''", nullable = true)
	private String classCode;

	@FieldInfo(name = "班主任ID", type = "varchar(20) default ''", explain = "班主任ID")
	@Column(name = "teacherId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId;

	@FieldInfo(name = "毕业日期", type = "datetime", explain = "毕业日期")
	@Column(name = "graduateDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date graduateDate;

	@FieldInfo(name = "班训", type = "nvarchar(128) default ''", explain = "班训")
	@Column(name = "classMotto", nullable = true, columnDefinition = "nvarchar(128) default ''")
	private String classMotto;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "年级名称")
	@Formula("(SELECT a.gradeName from T_PT_Grade a where a.gradeId=gradeId)")
	private String gradeName;

	// @FieldInfo(name = "学段")
	@Formula("(SELECT a.sectionCode from T_PT_Grade a where a.gradeId=gradeId)")
	private String sectionCode;

	@Formula("(SELECT A.gradeCode FROM dbo.T_PT_Grade A WHERE A.gradeId=gradeId)")
	// @FieldInfo(name = "年级编码")
	private String gradeCode;

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getArtsSciencesType() {
		return artsSciencesType;
	}

	public void setArtsSciencesType(String artsSciencesType) {
		this.artsSciencesType = artsSciencesType;
	}

	public String getBilingualModel() {
		return bilingualModel;
	}

	public void setBilingualModel(String bilingualModel) {
		this.bilingualModel = bilingualModel;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public String getClassMotto() {
		return classMotto;
	}

	public void setClassMotto(String classMotto) {
		this.classMotto = classMotto;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public GradeClass() {

		super();
		// TODO Auto-generated constructor stub

	}

	public GradeClass(String id) {

		super(id);
		// TODO Auto-generated constructor stub

	}

}