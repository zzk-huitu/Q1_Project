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
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_ClassRedFlag")
@AttributeOverride(name = "id", column = @Column(name = "classRedFlagId", length = 20, nullable = false) )
public class PtClassRedFlag extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "班级Id", type = "varchar(20)  NOT NULL", explain = "班级Id")
	@Column(name = "classId",length = 20, nullable = false)
	private String classId;

	@FieldInfo(name = "红旗类型", type = "varchar(4) NOT NULL", explain = "红旗类型")
	@Column(name = "redFlagType", length = 4, nullable = false)
	private String redFlagType;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.className FROM T_PT_GradeClass a WHERE a.classId=classId )")
	private String className;

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

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	// @FieldInfo(name = "红旗类型名称")
	@Formula("(SELECT a.itemName FROM T_PT_DdicItem a WHERE a.itemCode=redFlagType AND a.ddicId=(SELECT b.ddicId FROM T_PT_Ddic b WHERE b.dicCode='REDFLAG'))")
	private String redflagTypeName;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getRedFlagType() {
		return redFlagType;
	}

	public void setRedFlagType(String redFlagType) {
		this.redFlagType = redFlagType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getRedflagTypeName() {
		return redflagTypeName;
	}

	public void setRedflagTypeName(String redflagTypeName) {
		this.redflagTypeName = redflagTypeName;
	}

	public PtClassRedFlag() {
		super();
	}

	public PtClassRedFlag(String id) {
		super(id);
	}

}