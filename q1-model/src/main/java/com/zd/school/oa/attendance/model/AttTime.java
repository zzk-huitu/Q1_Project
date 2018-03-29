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
 * 
 * ClassName: AttTime 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 考勤时间(T_PT_AttendanceTime)实体类.
 * date: 2017-05-15
 *
 * @author  luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_AttendanceTime")
@AttributeOverride(name = "attendanceTimeId", column = @Column(name = "attendanceTimeId", length = 20, nullable = false))
public class AttTime extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "主题ID")
    @Column(name = "themeId", length = 20, nullable = false)
    private String themeId;
    public String getThemeId() {
 		return themeId;
 	}
 	public void setThemeId(String themeId) {
 		this.themeId = themeId;
 	}
        
    @FieldInfo(name = "星期")
    @Column(name = "weekDay", nullable = false)
    private byte weekDay;
    public void setWeekDay(byte weekDay) {
        this.weekDay = weekDay;
    }
    public byte getWeekDay() {
        return weekDay;
    }
        
    @FieldInfo(name = "选课开始日期")
    @Column(name = "beginDate", columnDefinition = "datetime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date beginDate;
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getBeginDate() {
        return beginDate;
    }
        
    @FieldInfo(name = "选课结束日期")
    @Column(name = "endDate", columnDefinition = "datetime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date endDate;
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return endDate;
    }
        
    @FieldInfo(name = "开始时间")
    @Column(name = "beginTime", columnDefinition = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date beginTime;
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    public Date getBeginTime() {
        return beginTime;
    }
        
    @FieldInfo(name = "结束时间")
    @Column(name = "endTime", columnDefinition = "datetime",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date endTime;
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    
    @FieldInfo(name = "节次")
    @Column(name = "period", length = 2, nullable = false)
    private String period ;
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}

    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}