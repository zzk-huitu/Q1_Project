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
import com.zd.school.excel.annotation.MapperCell;

/**
 * 门禁开门记录
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_MjOpenDoor")
@AttributeOverride(name = "mjOpenDoorId", column = @Column(name = "mjOpenDoorId", length = 20, nullable = false))
public class PtMjOpenDoor extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @FieldInfo(name = "deviceSn", type = "varchar(14)", explain = "设备序列号")
    @Column(name = "deviceSn", columnDefinition = "varchar(14) default ''", nullable = true)
    private String deviceSn;

    @MapperCell(cellName="设备名称",order=1)
    @FieldInfo(name = "termName", type = "nvarchar(25)", explain = "设备名称")
    @Column(name = "termName", columnDefinition = "nvarchar(25) default ''", nullable = true)
    private String termName;
    
    @FieldInfo(name = "roomId", type = "varchar(20)", explain = "房间编号")
    @Column(name = "roomId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String roomId;
   
    @MapperCell(cellName="开门人员姓名",order=2)
    @FieldInfo(name = "userName", type = "nvarchar(18)", explain = "开门人员姓名")
    @Column(name = "userName", columnDefinition = "nvarchar(18) default ''", nullable = true)
    private String userName;
    
    @MapperCell(cellName="开门时间",order=3)
    @FieldInfo(name = "openDate", type = "datetime", explain = "开门时间")
    @Column(name = "openDate", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
    private Date openDate;
    
    @MapperCell(cellName="房间名称",order=4)
    @FieldInfo(name = "roomName", type = "nvarchar(18)", explain = "房间名称")
    @Column(name = "roomName", columnDefinition = "nvarchar(18) default ''", nullable = true)
    private String roomName;
    
    @MapperCell(cellName="房间所在区域",order=5)
    @FieldInfo(name = "roomArea", type = "nvarchar(50)", explain = "房间所在区域")
    @Column(name = "roomArea", columnDefinition = "nvarchar(50) default ''", nullable = true)
    private String roomArea;
    
    @MapperCell(cellName="进出标识",order=6)
    @FieldInfo(name = "inOutType", type = "nvarchar(20)", explain = "进出标识")
    @Column(name = "inOutType", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String inOutType;
    
    @MapperCell(cellName="开门类型",order=7)
    @FieldInfo(name = "openType", type = "nvarchar(20)", explain = "开门类型")
    @Column(name = "openType", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String openType;
    
    @FieldInfo(name = "deviceId", type = "varchar(20)", explain = "设备Id")
    @Column(name = "deviceId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String deviceId;
    
    @FieldInfo(name = "userId", type = "varchar(20)", explain = "用户Id")
    @Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String userId;
    
    @FieldInfo(name = "areaId", type = "varchar(20)", explain = "区域Id")
    @Column(name = "areaId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String areaId;
    
    @FieldInfo(name = "recordId", type = "varchar(20)", explain = "记录编号")
    @Column(name = "recordId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String recordId;
    
    /* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.roomType FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间类型")
	private String roomType;
	

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

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	
    
    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}