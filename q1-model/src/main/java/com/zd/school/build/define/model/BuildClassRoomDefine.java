package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/*
 * 教室定义
 */

@Entity
@Table(name = "T_PT_ClassRoomDefine")
@AttributeOverride(name = "id", column = @Column(name = "classRoomId", length = 20, nullable = false) )
public class BuildClassRoomDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间Id", type = "varchar(20) NOT NULL", explain = "房间Id")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "区域Id", type = "varchar(20)  NOT NULL", explain = "区域Id")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	@FieldInfo(name = "状态", type = "bit NOT NULL default 0", explain = "状态,用于标识是否分配：0未分配。1已分配")
	@Column(name = "roomStatus", columnDefinition = "default 0", nullable = false)
	private Boolean roomStatus;

	@FieldInfo(name = "班级名称", type = "nvarchar(16) default ''", explain = "班级名称")
	@Column(name = "className", columnDefinition = "nvarchar(16) default ''", nullable = true)
	private String className;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId)")
	// @FieldInfo(name = "楼层名称")
	private String areaName;

	@Formula("(SELECT A.nodeText FROM dbo.T_PT_RoomArea A" + " WHERE A.areaId=(SELECT B.parentNode "
			+ " FROM dbo.T_PT_RoomArea B WHERE B.areaId=areaId))")
	// @FieldInfo(name = "楼栋名称")
	private String upAreaName;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Boolean getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(Boolean roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUpAreaName() {
		return upAreaName;
	}

	public void setUpAreaName(String upAreaName) {
		this.upAreaName = upAreaName;
	}

	public BuildClassRoomDefine() {
		super();
	}

	public BuildClassRoomDefine(String id) {
		super(id);
	}

}