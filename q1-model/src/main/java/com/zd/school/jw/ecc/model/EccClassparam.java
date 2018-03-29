package com.zd.school.jw.ecc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: EccClassparam Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 班牌参数设置表(ECC_T_CLASSPARAM)实体类. date: 2016-11-28
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_ClassParam")
@AttributeOverride(name = "classParamId", column = @Column(name = "classParamId", length = 20, nullable = false))
public class EccClassparam extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "签到模式")
	@Column(name = "signMode", columnDefinition = "varchar(4) default ''", nullable = true)
	private String signMode;

	public void setSignMode(String signMode) {
		this.signMode = signMode;
	}

	public String getSignMode() {
		return signMode;
	}

	@FieldInfo(name = "作息节次标识")
	@Column(name = "sectionsId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String sectionsId;

	public String getSectionsId() {
		return sectionsId;
	}

	public void setSectionsId(String sectionsId) {
		this.sectionsId = sectionsId;
	}

	@FieldInfo(name = "签到提前时间")
	@Column(name = "signBefore", columnDefinition = "default 0", nullable = true)
	private Integer signBefore;

	public void setSignBefore(Integer signBefore) {
		this.signBefore = signBefore;
	}

	public Integer getSignBefore() {
		return signBefore;
	}

	@FieldInfo(name = "自动切换考试模式时间")
	@Column(name = "examBefore", columnDefinition = "default 0", nullable = true)
	private Integer examBefore;

	public void setExamBefore(Integer examBefore) {
		this.examBefore = examBefore;
	}

	public Integer getExamBefore() {
		return examBefore;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}