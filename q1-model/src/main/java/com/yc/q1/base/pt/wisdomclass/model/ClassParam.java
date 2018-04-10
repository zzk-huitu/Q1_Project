package com.yc.q1.base.pt.wisdomclass.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 班牌参数设置
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_ClassParam")
@AttributeOverride(name = "id", column = @Column(name = "classParamId", length = 20, nullable = false) )
public class ClassParam extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "签到模式", type = "varchar(4) NOT NULL", explain = "签到模式")
	@Column(name = "signMode", length=4, nullable = false)
	private String signMode;

	@FieldInfo(name = "作息", type = "varchar(20)  NOT NULL", explain = "作息节次标识")
	@Column(name = "sectionId", length=20, nullable = false)
	private String sectionId;

	@FieldInfo(name = "签到提前时间", type = "int  NOT NULL", explain = "签到提前时间")
	@Column(name = "signBefore", nullable = false)
	private Integer signBefore;

	@FieldInfo(name = "自动切换考试模式时间", type = "int NOT NULL", explain = "自动切换考试模式时间")
	@Column(name = "examBefore", nullable = false)
	private Integer examBefore;

	public String getSignMode() {
		return signMode;
	}

	public void setSignMode(String signMode) {
		this.signMode = signMode;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getSignBefore() {
		return signBefore;
	}

	public void setSignBefore(Integer signBefore) {
		this.signBefore = signBefore;
	}

	public Integer getExamBefore() {
		return examBefore;
	}

	public void setExamBefore(Integer examBefore) {
		this.examBefore = examBefore;
	}

	public ClassParam() {
		super();
	}

	public ClassParam(String id) {
		super(id);
	}

}