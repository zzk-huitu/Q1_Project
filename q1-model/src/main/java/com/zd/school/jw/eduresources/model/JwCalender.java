package com.zd.school.jw.eduresources.model;

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
 * 作息时间目录
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Calender")
@AttributeOverride(name = "id", column = @Column(name = "calenderId", length = 20, nullable = false) )
public class JwCalender extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "校历名称", type = "nvarchar(20) NOT NULL", explain = "校历名称")
	@Column(name = "calenderName", columnDefinition = "nvarchar(20)", nullable = false)
	private String calenderName;

	@FieldInfo(name = "适用校区Id", type = "varchar(20) NOT NULL", explain = "适用校区Id")
	@Column(name = "campusId", length = 20, nullable = false)
	private String campusId;

	// @FieldInfo(name = "班级名称")
	@Formula("(SELECT a.campusName FROM T_PT_Campus a WHERE a.campusId=campusId )")
	private String campusName;

	@FieldInfo(name = "学段编码", type = "nvarchar(20)  NOT NULL", explain = "学段编码")
	@Column(name = "stageCode", columnDefinition = "nvarchar(20)", nullable = false)
	private String stageCode;

	@FieldInfo(name = "生效状态", type = "bit NOT NULL default 0", explain = "生效状态")
	@Column(name = "activityState", columnDefinition = "default 0", nullable = false)
	private Boolean activityState;

	@FieldInfo(name = "生效时间", type = "datetime NOT NULL", explain = "生效时间")
	@Column(name = "activityTime", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date activityTime;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "作息时间明细数")
	@Formula("(SELECT COUNT(a.calenderId) FROM T_PT_CalenderDetail a WHERE a.calenderId=calenderId )")
	private Integer detailCount;

	public String getCalenderName() {
		return calenderName;
	}

	public void setCalenderName(String calenderName) {
		this.calenderName = calenderName;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public Boolean getActivityState() {
		return activityState;
	}

	public void setActivityState(Boolean activityState) {
		this.activityState = activityState;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}

	public JwCalender() {
		super();
	}

	public JwCalender(String id) {
		super(id);
	}

}