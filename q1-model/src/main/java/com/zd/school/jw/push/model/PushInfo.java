package com.zd.school.jw.push.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

@Entity
@Table(name = "T_PT_PushInfo")
@AttributeOverride(name = "pushInfoId", column = @Column(name = "pushInfoId", length = 36, nullable = false))
public class PushInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //@Column(name = "PUSH_TYPE", length = 128, nullable = false)
    //private String pushType;	//推送的类型

    @Column(name = "eventType", length = 128, nullable = false)
    private String eventType;

    @Column(name = "regStatus", length = 1024, nullable = false)
    private String regStatus;

    @Column(name = "pushWay", nullable = false)
    private Integer pushWay; //1:微信   2:APP  3:短信

    @Column(name = "regTime", columnDefinition = "datetime",nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)    
    private Date regTime;

    @Column(name = "empleeNo", length = 20, nullable = false)
    private String empleeNo;

    @Column(name = "empleeName", length = 20, nullable = false)
    private String empleeName;

    @Column(name = "pushStatus", nullable = false)
    private Integer pushStatus; //0未发送 1发送成功 -1发送失败

    @Column(name = "pushUrl", length = 512, nullable = true)
    private String pushUrl;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public Integer getPushWay() {
        return pushWay;
    }

    public void setPushWay(Integer pushWay) {
        this.pushWay = pushWay;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getEmpleeNo() {
        return empleeNo;
    }

    public void setEmpleeNo(String empleeNo) {
        this.empleeNo = empleeNo;
    }

    public String getEmpleeName() {
        return empleeName;
    }

    public void setEmpleeName(String empleeName) {
        this.empleeName = empleeName;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

}
