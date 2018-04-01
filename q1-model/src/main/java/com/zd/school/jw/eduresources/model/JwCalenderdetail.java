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
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_CalenderDetail")
@AttributeOverride(name = "id", column = @Column(name = "calenderDetailId", length = 20, nullable = false) )
public class JwCalenderdetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "calenderId", type = "varchar(20) NOT NULL", explain = "校历Id")
	@Column(name = "calenderId", length = 20, nullable = false)
	private String calenderId;

	@FieldInfo(name = "senctionsName", type = "nvarchar(20)  NOT NULL", explain = "节次名称")
	@Column(name = "senctionsName", columnDefinition = "nvarchar(20)", nullable = false)
	private String senctionsName;

	@FieldInfo(name = "时段", type = "int NOT NULL", explain = "时段标识:0-上午 ;1-下午;2-晚上")
	@Column(name = "timeInterval", length = 1, nullable = false)
	private String timeInterval;

	@FieldInfo(name = "senctionCode", type = "nvarchar(10)  default ''", explain = "节次")
	@Column(name = "senctionCode", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String senctionCode;

	@FieldInfo(name = "beginTime", type = "datetime NOT NULL", explain = "开始时间")
	@Column(name = "beginTime", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	@FieldInfo(name = "endTime", type = "datetime", explain = "结束时间")
	@Column(name = "endTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	@FieldInfo(name = "needSignIn", type = "bit default 0", explain = "需要考勤 :0-否; 1-是")
	@Column(name = "needSignIn", columnDefinition = "default 0", nullable = true)
	private Boolean needSignIn;

	public String getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(String calenderId) {
		this.calenderId = calenderId;
	}

	public String getSenctionsName() {
		return senctionsName;
	}

	public void setSenctionsName(String senctionsName) {
		this.senctionsName = senctionsName;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getSenctionCode() {
		return senctionCode;
	}

	public void setSenctionCode(String senctionCode) {
		this.senctionCode = senctionCode;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getNeedSignIn() {
		return needSignIn;
	}

	public void setNeedSignIn(Boolean needSignIn) {
		this.needSignIn = needSignIn;
	}

	public JwCalenderdetail() {
		super();
	}

	public JwCalenderdetail(String id) {
		super(id);
	}

}