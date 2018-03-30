package com.zd.school.jw.ecc.model;

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
 * ClassName: EccClassregflag Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班级流动红旗(ECC_T_CLASSREGFLAG)实体类. date: 2016-12-13
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassRedFlag")
@AttributeOverride(name = "classRedFlagId", column = @Column(name = "classRedFlagId", length = 20, nullable = false))
public class EccClassredflag extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "classId",type="varchar(20)",explain="班级Id")
	@Column(name = "classId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String classId;

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassId() {
		return classId;
	}

	@FieldInfo(name = "redFlagType",type="varchar(4)",explain="红旗类型")
	@Column(name = "redFlagType", length = 4, nullable = false)
	private String redFlagType;

	public void setRedFlagType(String redFlagType) {
		this.redFlagType = redFlagType;
	}

	public String getRedFlagType() {
		return redFlagType;
	}

	@FieldInfo(name = "className",type="nvarchar(20)",explain="班级名称")
	@Column(name = "className", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String className;

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	@FieldInfo(name = "beginDate",type="datetime",explain="开始日期")
	@Column(name = "beginDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginDate;

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	@FieldInfo(name = "endDate",type="datetime",explain="结束日期")
	@Column(name = "endDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endDate;

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@FieldInfo(name = "红旗类型名称")
	@Formula("(SELECT a.itemName FROM T_PT_DdicItem a WHERE a.itemCode=redFlagType AND a.ddicId=(SELECT b.ddicId FROM T_PT_Ddic b WHERE b.dicCode='REDFLAG'))")
	private String redflagTypeName;

	public String getRedflagTypeName() {
		return redflagTypeName;
	}

	public void setRedflagTypeName(String redflagTypeName) {
		this.redflagTypeName = redflagTypeName;
	}

}