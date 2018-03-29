package com.zd.school.jw.eduresources.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_SchoolClander")
@AttributeOverride(name = "uuid", column = @Column(name = "schoolClanderId", length = 36, nullable = false))
public class JwSchoolCalender extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "等同UUID")
	@Column(name = "id", length = 36, nullable = true)
	private String id;

	@FieldInfo(name = "标题")
	@Column(name = "title", length = 200, nullable = true)
	private String title;

	@FieldInfo(name = "开始时间")
	@Column(name = "beginDate", length = 36, nullable = true)
	private String beginDate;

	@FieldInfo(name = "结束时间")
	@Column(name = "endDate", length = 36, nullable = true)
	private String endDate;

	@FieldInfo(name = "位置")
	@Column(name = "location", length = 200, nullable = true)
	private String location;

	@FieldInfo(name = "WEB链接")
	@Column(name = "url", length = 1000, nullable = true)
	private String url;

	@FieldInfo(name = "便签")
	@Column(name = "notes", length = 1000, nullable = true)
	private String notes;

	@FieldInfo(name = "提醒器")
	@Column(name = "remind", length = 36, nullable = true)
	private String remind;

	@FieldInfo(name = "是否全天")
	@Column(name = "allDay", length = 36, nullable = true)
	private String allDay;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
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

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}

}
