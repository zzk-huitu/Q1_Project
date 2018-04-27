package com.yc.q1.model.storage.mj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 门禁刷卡记录
 * 
 * recordId：主键由俊哥程序中生成32位的id
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_MJ_Record", catalog = "Q1_Storage", schema = "dbo")
@AttributeOverride(name = "id", column = @Column(name = "recordId", length = 32, nullable = false) )
public class MjRecord extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.MJ; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "物理卡号")
	@Column(name = "factoryFixId", columnDefinition = "bigint default 0", nullable = true)
	private Long factoryFixId;

	@FieldInfo(name = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint default 0", nullable = true)
	private Long cardNo;

	@FieldInfo(name = "门禁控制器序列号")
	@Column(name = "controllerSn", columnDefinition = "varchar(30) default ''", nullable = false)
	private String controllerSn;

	@FieldInfo(name = "用户Id")
	@Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String userId;

	@FieldInfo(name = "门禁读头Id")
	@Column(name = "readerId", columnDefinition = "int default 0", nullable = true)
	private Integer readerId;

	@FieldInfo(name = "刷卡时间")
	@Column(name = "openDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date openDate;

	@FieldInfo(name = "事件ID")
	@Column(name = "eventTypeId", columnDefinition = "varchar(10) default ''", nullable = true)
	private String eventTypeId;

	@FieldInfo(name = "事件")
	@Column(name = "events", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String events;

	@FieldInfo(name = "事件详细描述")
	@Column(name = "detailEvents", columnDefinition = "nvarchar(500) default ''", nullable = true)
	private String detailEvents;

	@FieldInfo(name = "进出标识")
	@Column(name = "inOutType", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String inOutType;

	public Long getFactoryFixId() {
		return factoryFixId;
	}

	public void setFactoryFixId(Long factoryFixId) {
		this.factoryFixId = factoryFixId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public String getControllerSn() {
		return controllerSn;
	}

	public void setControllerSn(String controllerSn) {
		this.controllerSn = controllerSn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getDetailEvents() {
		return detailEvents;
	}

	public void setDetailEvents(String detailEvents) {
		this.detailEvents = detailEvents;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public MjRecord() {
		super();
	}

	public MjRecord(String id) {
		super(id);
	}

}