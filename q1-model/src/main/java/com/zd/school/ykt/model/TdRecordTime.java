package com.zd.school.ykt.model;

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

@Entity
@Table(name = "T_PT_RecordTime")
@AttributeOverride(name = "recordTimeId", column = @Column(name = "recordTimeId", length = 20, nullable = false))
public class TdRecordTime extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "特殊刷卡时间",type="datetime",explain="特殊的刷卡时间")
	@Column(name = "brushTime", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date brushTime;
	
	@FieldInfo(name = "特殊是否需要刷卡类型 0不需要 1需要",type="boolean",explain="特殊的是否需要刷卡类型")
	@Column(name = "brushType",nullable = true)
	private boolean brushType;

	public Date getBrushTime() {
		return brushTime;
	}

	public void setBrushTime(Date brushTime) {
		this.brushTime = brushTime;
	}

	public boolean getBrushType() {
		return brushType;
	}

	public void setBrushType(boolean brushType) {
		this.brushType = brushType;
	}

}
