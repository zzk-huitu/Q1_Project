package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_SchoolCalendar")
@AttributeOverride(name = "id", column = @Column(name = "id", length = 20, nullable = false) )
public class PtSchoolCalendar  extends BaseEntity implements Serializable{
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。

	@FieldInfo(name = "标题")
	@Column(name = "title", length = 200, nullable = true)
	private String title;

	@FieldInfo(name = "开始时间",type = "varchar(36) DEFAULT ''",explain = "开始时间")
	@Column(name = "startDate", length = 36, nullable = true)
	private String startDate;

	@FieldInfo(name = "结束时间",type = "varchar(36) DEFAULT ''",explain = "开始时间")
	@Column(name = "endDate", length = 36, nullable = true)
	private String endDate;

	@FieldInfo(name = "位置",type = "varchar(200) DEFAULT ''",explain = "位置")
	@Column(name = "location", length = 200, nullable = true)
	private String location;

	@FieldInfo(name = "WEB链接",type = "varchar(100) DEFAULT ''",explain = "WEB链接")
	@Column(name = "url", length = 100, nullable = true)
	private String url;

	@FieldInfo(name = "便签",type = "varchar(100) DEFAULT ''",explain = "便签")
	@Column(name = "notes", length = 100, nullable = true)
	private String notes;

	@FieldInfo(name = "提醒器",type = "varchar(36) DEFAULT ''",explain = "提醒器")
	@Column(name = "reminder", length = 36, nullable = true)
	private String reminder;

	@FieldInfo(name = "是否全天",type = "bit DEFAULT 0",explain = "是否全天 1:是全天 0：不是全天")
	@Column(name = "isAllDay", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean isAllDay;



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public Boolean getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(Boolean isAllDay) {
		this.isAllDay = isAllDay;
	}

	

}
