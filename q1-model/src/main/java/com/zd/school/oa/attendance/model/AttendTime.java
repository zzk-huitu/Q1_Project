package com.zd.school.oa.attendance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 特殊考勤时间
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_AttendTime")
@AttributeOverride(name = "id", column = @Column(name = "attendTimeId", length = 20, nullable = false) )
public class AttendTime extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "考勤主题ID", type = "varchar(20) NOT NULL", explain = "考勤主题Id")
	@Column(name = "attendThemeId", length = 20, nullable = false)
	private String attendThemeId;

	@FieldInfo(name = "周星期几", type = "tinyint NOT NULL", explain = "考勤时间的周星期几（1-7）")
	@Column(name = "weekDay", columnDefinition = "tinyint", nullable = false)
	private Integer weekDay;

	@FieldInfo(name = "节次", type = "varchar(2) NOT NULL", explain = "考勤时间的第几节课")
	@Column(name = "period", length = 2, nullable = false)
	private String period;

	@FieldInfo(name = "开始时间", type = "time(0) NOT NULL", explain = "考勤时间的结束日期")
	@Column(name = "beginTime", columnDefinition = "time(0)", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginTime;

	@FieldInfo(name = "结束时间", type = "time(0) NOT NULL", explain = "考勤时间的结束日期")
	@Column(name = "endTime", columnDefinition = "time(0)", nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endTime;

	@FieldInfo(name = "选课开始日期", type = "datetime", explain = "考勤时间的选课开始日期")
	@Column(name = "beginDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date beginDate;

	@FieldInfo(name = "选课结束日期", type = "datetime", explain = "考勤时间的选课结束日期")
	@Column(name = "endDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date endDate;

	public String getAttendThemeId() {
		return attendThemeId;
	}

	public void setAttendThemeId(String attendThemeId) {
		this.attendThemeId = attendThemeId;
	}

	public Integer getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public AttendTime() {
		super();
	}

	public AttendTime(String id) {
		super(id);
	}

}