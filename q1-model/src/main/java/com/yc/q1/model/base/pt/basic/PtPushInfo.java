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
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 信息推送表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_PushInfo")
@AttributeOverride(name = "id", column = @Column(name = "pushInfoId", length = 20, nullable = false) )
public class PtPushInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	// @Column(name = "PUSH_TYPE", length = 128, nullable = false)
	// private String pushType; //推送的类型

	@FieldInfo(name = "推送事件", type = "varchar(128) NOT NULL", explain = "推送事件类型")
	@Column(name = "eventType", length = 128, nullable = false)
	private String eventType;

	@FieldInfo(name = "推送信息", type = "nvarchar(1024) NOT NULL", explain = "推送信息")
	@Column(name = "regStatus", columnDefinition = "nvarchar(1024)", nullable = false)
	private String regStatus;

	@FieldInfo(name = "推送方式", type = "int NOT NULL", explain = "推送方式(1:微信   2:APP  3:短信)")
	@Column(name = "pushWay", nullable = false)
	private Integer pushWay; // 1:微信 2:APP 3:短信

	@FieldInfo(name = "推送时间", type = "datetime NOT NULL", explain = "推送时间")
	@Column(name = "regTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date regTime;

	@FieldInfo(name = "推送编号", type = "varchar(16) NOT NULL", explain = "推送编号：手机号、微信号")
	@Column(name = "empleeNo", length = 16, nullable = false)
	private String empleeNo;

	@FieldInfo(name = "推送姓名", type = "varchar(16) NOT NULL", explain = "推送")
	@Column(name = "empleeName", length = 16, nullable = false)
	private String empleeName;

	@FieldInfo(name = "推送状态", type = "int NOT NULL", explain = "推送状态：0未发送 1正在发送 2发送成功 -1发送失败")
	@Column(name = "pushStatus", nullable = false)
	private Integer pushStatus; // 0未发送 1发送成功 -1发送失败

	@FieldInfo(name = "推送路径", type = "varchar(512) default ''", explain = "推送路径")
	@Column(name = "pushUrl",  columnDefinition = "varchar(512) default ''", nullable = true)
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

	public PtPushInfo() {
		super();
	}

	public PtPushInfo(String id) {
		super(id);
	}

}
