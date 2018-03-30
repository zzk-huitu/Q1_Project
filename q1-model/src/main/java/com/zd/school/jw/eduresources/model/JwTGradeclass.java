package com.zd.school.jw.eduresources.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: JwTGradeclass Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 学校班级信息实体类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_GradeClass")
@AttributeOverride(name = "gradeClassId", column = @Column(name = "gradeClassId", length = 20, nullable = false))
public class JwTGradeclass extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "gradeId", type = "varchar(20)", explain = "年级ID")
	@Column(name = "gradeId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String gradeId;

	public String getGradeId() {
		return gradeId;
	}

	public void setGraiId(String gradeId) {
		this.gradeId = gradeId;
	}

	@FieldInfo(name = "classType", type = "nvarchar(20)", explain = "班级类型")
	@Column(name = "classType", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String classType;

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClassType() {
		return classType;
	}

	@FieldInfo(name = "artsSciences", type = "varchar(20)", explain = "文理类型")
	@Column(name = "artsSciences", columnDefinition = "varchar(20) default ''", nullable = true)
	private String artsSciences;

	public void setArtsSciences(String artsSciences) {
		this.artsSciences = artsSciences;
	}

	public String getArtsSciences() {
		return artsSciences;
	}

	@FieldInfo(name = "minorityBilingual", type = "varchar(10)", explain = "少数民族双语教学班")
	@Column(name = "minorityBilingual", columnDefinition = "varchar(10) default ''", nullable = true)
	private String minorityBilingual;

	public void setMinorityBilingual(String minorityBilingual) {
		this.minorityBilingual = minorityBilingual;
	}

	public String getMinorityBilingual() {
		return minorityBilingual;
	}

	@FieldInfo(name = "bilingualModel", type = "varchar(10)", explain = "双语教学模式")
	@Column(name = "bilingualModel", columnDefinition = "varchar(10) default ''", nullable = true)
	private String bilingualModel;

	public String getBilingualModel() {
		return bilingualModel;
	}

	public void setBilingualModel(String bilingualModel) {
		this.bilingualModel = bilingualModel;
	}

	@FieldInfo(name = "classCode", type = "nvarchar(20)", explain = "班号")
	@Column(name = "classCode", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String classCode;

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassCode() {
		return classCode;
	}

	@FieldInfo(name = "className", type = "nvarchar(20)", explain = "班级名称")
	@Column(name = "className", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String className;

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	@FieldInfo(name = "teacherId", type = "varchar(20)", explain = "班主任ID")
	@Column(name = "teacherId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String teacherId;

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	@FieldInfo(name = "foundDate", type = "datetime", explain = "建班年月")
	@Column(name = "foundDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date foundDate;

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	@FieldInfo(name = "classYear", type = "nvarchar(20)", explain = "年度")
	@Column(name = "classYear", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String classYear;

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	@FieldInfo(name = "graduateDate", type = "nvarchar(20)", explain = "毕业日期")
	@Column(name = "graduateDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date graduateDate;

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public Date getGraduateDate() {
		return graduateDate;
	}

	@FieldInfo(name = "nj", type = "varchar(10)", explain = "年级")
	@Column(name = "nj", columnDefinition = "varchar(10) default ''", nullable = true)
	private String nj;

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	@FieldInfo(name = "classMotto", type = "nvarchar(50)", explain = "班训")
	@Column(name = "classMotto", nullable = true, columnDefinition = "nvarchar(50)") // 一个汉字占两个字符，所以是50个字
	private String classMotto;

	public String getClassMotto() {
		return classMotto;
	}

	public void setClassMotto(String classMotto) {
		this.classMotto = classMotto;
	}

	public JwTGradeclass() {

		super();
		// TODO Auto-generated constructor stub

	}

	public JwTGradeclass(String uuid) {

		super(uuid);
		// TODO Auto-generated constructor stub

	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "年级名称")
	@Formula("(SELECT a.gradeName from T_PT_Grade a where a.gradeId=gradeId)")
	private String gradeName;

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeName() {
		return gradeName;
	}

	@FieldInfo(name = "学段")
	@Formula("(SELECT a.sectionCode from T_PT_Grade a where a.gradeId=gradeId)")
	private String sectionCode;

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	@Formula("(SELECT A.gradeCode FROM dbo.T_PT_Grade A WHERE A.gradeId=gradeId)")
	@FieldInfo(name = "年级编码")
	private String gradeCode;

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

}