package com.yc.q1.model.base.pt.basic;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_SchoolCalendar")
@AttributeOverride(name = "id", column = @Column(name = "schoolCalendarId", length = 20, nullable = false) )
public class PtSchoolCalendar  extends BaseEntity implements Serializable{
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。

	
	@FieldInfo(name = "标题")
	@Column(name = "title", length = 200, nullable = true)
	private String title;

	@FieldInfo(name = "开始时间",type = "varchar(36) DEFAULT ''",explain = "开始时间")
	@Column(name = "startDate", length = 36, nullable = true)
	private String start;

	@FieldInfo(name = "结束时间",type = "varchar(36) DEFAULT ''",explain = "开始时间")
	@Column(name = "endDate", length = 36, nullable = true)
	private String end;

	@FieldInfo(name = "位置",type = "varchar(200) DEFAULT ''",explain = "位置")
	@Column(name = "location", length = 200, nullable = true)
	private String loc;

	@FieldInfo(name = "WEB链接",type = "varchar(100) DEFAULT ''",explain = "WEB链接")
	@Column(name = "url", length = 100, nullable = true)
	private String url;

	@FieldInfo(name = "便签",type = "varchar(100) DEFAULT ''",explain = "便签")
	@Column(name = "notes", length = 100, nullable = true)
	private String notes;

	@FieldInfo(name = "提醒器",type = "varchar(36) DEFAULT ''",explain = "提醒器")
	@Column(name = "reminder", length = 36, nullable = true)
	private String rem;

	@FieldInfo(name = "是否全天",type = "bit DEFAULT 0",explain = "是否全天 1:是全天 0：不是全天")
	@Column(name = "isAllDay", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean ad;
	
    //@FieldInfo(name = "id")
	@Formula("(select a.schoolCalendarId from T_PT_SchoolCalendar a where a.schoolCalendarId=schoolCalendarId)")
	private String id;
	
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
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

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public Boolean getAd() {
		return ad;
	}

	public void setAd(Boolean ad) {
		this.ad = ad;
	}




	

}
