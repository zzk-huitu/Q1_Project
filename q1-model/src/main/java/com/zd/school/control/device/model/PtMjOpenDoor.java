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
    @FieldInfo(name = "设备序列号")
    @Column(name = "termSn", columnDefinition = "varchar(14) default ''", nullable = true)
    private String termSn;

    @MapperCell(cellName="设备名称",order=1)
    @FieldInfo(name = "设备名称")
    @Column(name = "termName", columnDefinition = "nvarchar(25) default ''", nullable = true)
    private String termName;
    
    @FieldInfo(name = "房间编号")
    @Column(name = "roomId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String roomId;
   
    @MapperCell(cellName="开门人员姓名",order=2)
    @FieldInfo(name = "开门人员姓名")
    @Column(name = "userName", columnDefinition = "nvarchar(18) default ''", nullable = true)
    private String userName;
    
    @MapperCell(cellName="开门时间",order=3)
    @FieldInfo(name = "开门时间")
    @Column(name = "openDate", columnDefinition = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
    private Date openDate;
    
    @MapperCell(cellName="房间名称",order=4)
    @FieldInfo(name = "房间名称")
    @Column(name = "roomName", columnDefinition = "nvarchar(18) default ''", nullable = true)
    private String roomName;
    
    @MapperCell(cellName="房间所在区域",order=5)
    @FieldInfo(name = "房间所在区域")
    @Column(name = "roomArea", columnDefinition = "nvarchar(50) default ''", nullable = true)
    private String roomArea;
    
    @MapperCell(cellName="进出标识",order=6)
    @FieldInfo(name = "进出标识")
    @Column(name = "inOutType", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String inOutType;
    
    @MapperCell(cellName="开门类型",order=7)
    @FieldInfo(name = "开门类型")
    @Column(name = "openType", columnDefinition = "nvarchar(20) default ''", nullable = true)
    private String openType;
    
    @FieldInfo(name = "设备Id")
    @Column(name = "termId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String termId;
    
    @FieldInfo(name = "用户Id")
    @Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String userId;
    
    @FieldInfo(name = "区域Id")
    @Column(name = "areaId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String areaId;
    
    @FieldInfo(name = "记录编号")
    @Column(name = "recordId", columnDefinition = "varchar(20) default ''", nullable = true)
    private String recordId;
    
    /* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.ROOM_TYPE FROM dbo.BUILD_T_ROOMINFO A WHERE A.ROOM_ID=ROOM_ID)")
	@FieldInfo(name = "房间类型")
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
	
    
    /** 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加 
    *@Transient
    *@FieldInfo(name = "")
    *private String field1;
    */
}