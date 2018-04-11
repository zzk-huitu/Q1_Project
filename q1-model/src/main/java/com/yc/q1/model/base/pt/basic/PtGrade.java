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
 * 年级信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Grade")
@AttributeOverride(name = "id", column = @Column(name = "gradeId", length = 20, nullable = false) )
public class PtGrade extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "年级名称", type = "nvarchar(16) NOT NULL", explain = "年级名称")
	@Column(name = "gradeName", columnDefinition = "nvarchar(16)", nullable = false)
	private String gradeName;

	@FieldInfo(name = "学段编码", type = "varchar(4 NOT NULL", explain = "学段编码（数据字典）")
	@Column(name = "sectionCode", columnDefinition = "varchar(4)", nullable = false)
	private String sectionCode;

	@FieldInfo(name = "年级", type = "varchar(4) NOT NULL", explain = "年级（数据字典）")
	@Column(name = "nj", length = 4, nullable = false)
	private String nj;

	@FieldInfo(name = "gradeCode", type = "varchar(8) default ''", explain = "年级编码(学段+年级)")
	@Column(name = "gradeCode", columnDefinition = "varchar(8) default ''", nullable = true)
	private String gradeCode;

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

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public PtGrade() {

		super();
		// TODO Auto-generated constructor stub

	}

	public PtGrade(String id) {

		super(id);
		// TODO Auto-generated constructor stub

	}
}