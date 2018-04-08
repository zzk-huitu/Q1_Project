package com.zd.school.control.device.model;

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
 * 门禁开门记录
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_MJ_OpenDoor")
@AttributeOverride(name = "id", column = @Column(name = "openDoorId", length = 20, nullable = false) )
public class MjOpenDoor extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@FieldInfo(name = "设备名称", type = "nvarchar(16)  NOT NULL", explain = "设备名称")
	@Column(name = "termName", columnDefinition = "nvarchar(16)", nullable = false)
	private String termName;

	@FieldInfo(name = "房间编号", type = "varchar(20)  NOT NULL", explain = "房间编号")
	@Column(name = "roomId", length = 20,  nullable = false)
	private String roomId;

	@FieldInfo(name = "开门人员姓名", type = "nvarchar(10)  NOT NULL", explain = "开门人员姓名")
	@Column(name = "userName", columnDefinition = "nvarchar(10)", nullable = false)
	private String userName;

	@FieldInfo(name = "开门时间", type = "datetime  NOT NULL", explain = "开门时间")
	@Column(name = "openDate", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date openDate;

	@FieldInfo(name = "房间名称", type = "nvarchar(16)  NOT NULL", explain = "房间名称")
	@Column(name = "roomName", columnDefinition = "nvarchar(16)", nullable = false)
	private String roomName;

	@FieldInfo(name = "房间所在区域", type = "nvarchar(64)  NOT NULL", explain = "房间所在区域")
	@Column(name = "roomArea", columnDefinition = "nvarchar(64)", nullable = false)
	private String roomArea;

	@FieldInfo(name = "进出标识", type = "nvarchar(16)  NOT NULL", explain = "进出标识")
	@Column(name = "inOutType", columnDefinition = "nvarchar(16)", nullable = false)
	private String inOutType;

	@FieldInfo(name = "开门类型", type = "nvarchar(16)  NOT NULL", explain = "开门类型")
	@Column(name = "openType", columnDefinition = "nvarchar(16)", nullable = false)
	private String openType;

	@FieldInfo(name = "设备Id", type = "varchar(20) NOT NULL", explain = "设备Id")
	@Column(name = "termId", length = 20,  nullable = false)
	private String termId;

	@FieldInfo(name = "用户Id", type = "varchar(20) NOT NULL", explain = "用户Id")
	@Column(name = "userId", length = 20,  nullable = false)
	private String userId;

	@FieldInfo(name = "区域Id", type = "varchar(20) NOT NULL", explain = "区域Id")
	@Column(name = "areaId", length = 20,  nullable = false)
	private String areaId;

	@FieldInfo(name = "记录编号", type = "varchar(20)  NOT NULL", explain = "记录编号")
	@Column(name = "recordId", length = 20, nullable = false)
	private String recordId;

	/* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.roomType FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间类型")
	private String roomType;

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public MjOpenDoor() {
		super();
	}

	public MjOpenDoor(String id) {
		super(id);
	}

}