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
@Table(name = "T_PT_RecordManage")
@AttributeOverride(name = "recordManageId", column = @Column(name = "recordManageId", length = 20, nullable = false))
public class TdRecordManage  extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "上午正常刷卡时间开始")
	@Column(name = "morningNormalStart", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date morningNormalStart;

    @FieldInfo(name = "上午正常刷卡时间结束")
   	@Column(name = "morningNormalEnd", columnDefinition="datetime", nullable = true)
   	@Temporal(TemporalType.TIMESTAMP)
   	@JsonSerialize(using = DateTimeSerializer.class)
   	private Date morningNormalEnd;
    
    @FieldInfo(name = "上午迟到刷卡时间开始")
	@Column(name = "morningLateStart", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date morningLateStart;

    @FieldInfo(name = "上午正常刷卡时间结束")
   	@Column(name = "morningLateEnd", columnDefinition="datetime", nullable = true)
   	@Temporal(TemporalType.TIMESTAMP)
   	@JsonSerialize(using = DateTimeSerializer.class)
   	private Date morningLateEnd;
    
    
    @FieldInfo(name = "下午正常刷卡时间开始")
	@Column(name = "afternoonNormalStart", columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date afternoonNormalStart;

    @FieldInfo(name = "下午正常刷卡时间结束")
   	@Column(name = "afternoonNormalEnd", columnDefinition="datetime", nullable = true)
   	@Temporal(TemporalType.TIMESTAMP)
   	@JsonSerialize(using = DateTimeSerializer.class)
   	private Date afternoonNormalEnd;
    
    @FieldInfo(name = "下午迟到刷卡时间开始")
	@Column(name = "afternoonLateStart",columnDefinition="datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date afternoonLateStart;

    @FieldInfo(name = "下午正常刷卡时间结束")
   	@Column(name = "afternoonLateEnd", columnDefinition="datetime", nullable = true)
   	@Temporal(TemporalType.TIMESTAMP)
   	@JsonSerialize(using = DateTimeSerializer.class)
   	private Date afternoonLateEnd;

	public Date getMorningNormalStart() {
		return morningNormalStart;
	}

	public void setMorningNormalStart(Date morningNormalStart) {
		this.morningNormalStart = morningNormalStart;
	}

	public Date getMorningNormalEnd() {
		return morningNormalEnd;
	}

	public void setMorningNormalEnd(Date morningNormalEnd) {
		this.morningNormalEnd = morningNormalEnd;
	}

	public Date getMorningLateStart() {
		return morningLateStart;
	}

	public void setMorningLateStart(Date morningLateStart) {
		this.morningLateStart = morningLateStart;
	}

	public Date getMorningLateEnd() {
		return morningLateEnd;
	}

	public void setMorningLateEnd(Date morningLateEnd) {
		this.morningLateEnd = morningLateEnd;
	}

	public Date getAfternoonNormalStart() {
		return afternoonNormalStart;
	}

	public void setAfternoonNormalStart(Date afternoonNormalStart) {
		this.afternoonNormalStart = afternoonNormalStart;
	}

	public Date getAfternoonNormalEnd() {
		return afternoonNormalEnd;
	}

	public void setAfternoonNormalEnd(Date afternoonNormalEnd) {
		this.afternoonNormalEnd = afternoonNormalEnd;
	}

	public Date getAfternoonLateStart() {
		return afternoonLateStart;
	}

	public void setAfternoonLateStart(Date afternoonLateStart) {
		this.afternoonLateStart = afternoonLateStart;
	}

	public Date getAfternoonLateEnd() {
		return afternoonLateEnd;
	}

	public void setAfternoonLateEnd(Date afternoonLateEnd) {
		this.afternoonLateEnd = afternoonLateEnd;
	}
}
