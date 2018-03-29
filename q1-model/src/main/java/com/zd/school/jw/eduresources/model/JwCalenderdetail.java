package com.zd.school.jw.eduresources.model;

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
 * ClassName: JwCalenderdetail Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 校历节次信息表(JW_T_CALENDERDETAIL)实体类. date: 2016-08-30
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_CalenderDetail")
@AttributeOverride(name = "calenderDetailId", column = @Column(name = "calenderDetailId", length = 20, nullable = false))
public class JwCalenderdetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "校历ID")
	@Column(name = "calenderId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String calenderId;

	public String getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(String calenderId) {
		this.calenderId = calenderId;
	}

	@FieldInfo(name = "节次名称")
	@Column(name = "senctionsName", columnDefinition = "nvarchar(20)", nullable = false)
	private String senctionsName;

	public String getSenctionsName() {
		return senctionsName;
	}

	public void setSenctionsName(String senctionsName) {
		this.senctionsName = senctionsName;
	}

	@FieldInfo(name = "上/下午标识,0-上午 1-下午")
	@Column(name = "amOrPm", nullable = false)
	private Integer amOrPm;

	public Integer getAmOrPm() {
		return amOrPm;
	}

	public void setAmOrPm(Integer amOrPm) {
		this.amOrPm = amOrPm;
	}

	@FieldInfo(name = "jcCode")
	@Column(name = "senctionsCode",columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String senctionsCode;

	public String getSenctionsCode() {
		return senctionsCode;
	}

	public void setSenctionsCode(String senctionsCode) {
		this.senctionsCode = senctionsCode;
	}

	@FieldInfo(name = "开始时间")
	@Column(name = "beginTime", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	@FieldInfo(name = "结束时间")
	@Column(name = "endTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	@FieldInfo(name = "需要考勤 0否 1是")
	@Column(name = "needSignIn", columnDefinition = "default 0", nullable = true)
	private Boolean needSignIn;

	public Boolean getNeedSignIn() {
		return needSignIn;
	}

	public void setNeedSignIn(Boolean needSignIn) {
		this.needSignIn = needSignIn;

	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}