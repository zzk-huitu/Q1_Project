package com.yc.q1.base.pt.wisdomclass.model;

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
 * 班级星级
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_ClassStar")
@AttributeOverride(name = "id", column = @Column(name = "classStartId", length = 20, nullable = false) )
public class ClassStar extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级Id", type = "varchar(20) NOT NULL", explain = "班级Id")
	@Column(name = "classId",length = 20, nullable = false)
	private String classId;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

	@FieldInfo(name = "星级", type = "varchar(4) NOT NULL", explain = "星级")
	@Column(name = "starLevel", length = 4, nullable = false)
	private String starLevel;

	@FieldInfo(name = "评定日期", type = "datetime NOT NULL", explain = "评定日期")
	@Column(name = "doneDate", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date doneDate;

	@FieldInfo(name = "开始日期", type = "datetime", explain = "开始日期")
	@Column(name = "beginDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginDate;

	@FieldInfo(name = "结束日期", type = "datetime", explain = "结束日期")
	@Column(name = "endDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endDate;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ClassStar() {
		super();
	}
	public ClassStar(String id) {
		super(id);
	}

	
	
}