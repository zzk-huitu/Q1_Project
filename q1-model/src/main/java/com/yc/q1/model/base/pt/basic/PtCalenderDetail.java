package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 作息时间详情
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_CalenderDetail")
@AttributeOverride(name = "id", column = @Column(name = "calenderDetailId", length = 20, nullable = false) )
public class PtCalenderDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "日历Id", type = "varchar(20) NOT NULL", explain = "日历Id")
	@Column(name = "calenderId", length = 20, nullable = false)
	private String calenderId;

	@FieldInfo(name = "节次名称", type = "nvarchar(16)  NOT NULL", explain = "节次名称")
	@Column(name = "sectionName", columnDefinition = "nvarchar(16)", nullable = false)
	private String sectionName;

	@FieldInfo(name = "时段", type = "int NOT NULL", explain = "时段标识:0-上午 ;1-下午;2-晚上")
	@Column(name = "timeInterval", length = 1, nullable = false)
	private String timeInterval;

	@FieldInfo(name = "节次", type = "nvarchar(10)  default ''", explain = "节次")
	@Column(name = "sectionCode", columnDefinition = "nvarchar(10) default ''", nullable = true)
	private String sectionCode;

	@FieldInfo(name = "开始时间", type = "time(0) NOT NULL", explain = "开始时间")
	@Column(name = "beginTime", columnDefinition = "time(0)", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	@FieldInfo(name = "结束时间", type = "time(0)", explain = "结束时间")
	@Column(name = "endTime", columnDefinition = "time(0)", nullable = true)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	@FieldInfo(name = "需要考勤", type = "bit default 0", explain = "需要考勤 :0-否; 1-是")
	@Column(name = "needSignIn", columnDefinition = "bit default 0", nullable = true)
	private Boolean needSignIn;

	public String getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(String calenderId) {
		this.calenderId = calenderId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
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

	public PtCalenderDetail() {
		super();
	}

	public PtCalenderDetail(String id) {
		super(id);
	}

}