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
 * ClassName: JwTGrade Function: TODO ADD FUNCTION. Reason: TODO ADD REASON(可选).
 * Description: 学校年级信息实体类. date: 2016-03-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Grade")
@AttributeOverride(name = "gradeId", column = @Column(name = "gradeId", length = 20, nullable = false))
public class JwTGrade extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "schoolId", type = "varchar(20)", explain = "学校Id")
	@Column(name = "schoolId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String schoolId;

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	@FieldInfo(name = "sectionCode", type = "nvarchar(20)", explain = "学段编码")
	@Column(name = "sectionCode", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String sectionCode;

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	@FieldInfo(name = "gradeCode", type = "nvarchar(20)", explain = "年级编码")
	@Column(name = "gradeCode", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String gradeCode;

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	@FieldInfo(name = "gradeName", type = "nvarchar(20)", explain = "年级名称")
	@Column(name = "gradeName", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String gradeName;

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeName() {
		return gradeName;
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

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@FieldInfo(name = "学校名称")
	@Formula("(SELECT a.nodeText from T_PT_Department a where a.deptId=schoolId)")
	private String schoolName;

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public JwTGrade() {

		super();
		// TODO Auto-generated constructor stub

	}

	public JwTGrade(String uuid) {

		super(uuid);
		// TODO Auto-generated constructor stub

	}
}