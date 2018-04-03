package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 房间信息表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_RoomInfo")
@AttributeOverride(name = "id", column = @Column(name = "roomId", length = 20, nullable = false) )
public class BuildRoominfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "区域ID", type = "varchar(20) NOT NULL", explain = "区域ID")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	@FieldInfo(name = "房间编码", type = "varchar(16) NOT NULL", explain = "房间编码")
	@Column(name = "roomCode", length = 16, nullable = false)
	private String roomCode;

	@FieldInfo(name = "房间名称", type = "nvarchar(16) NOT NULL", explain = "房间名称")
	@Column(name = "roomName", columnDefinition = "nvarchar(16)", nullable = false)
	private String roomName;

	@FieldInfo(name = "房间类型", type = "varchar(4) NOT NULL", explain = "房间类型：宿舍-1，办公室-2，教室-3，功能室-5") // 参考数据字典表｛｝
	@Column(name = "roomType", length = 4, nullable = false)
	private String roomType;

	@FieldInfo(name = "是否多媒体教室", type = "bit default 0", explain = "是否多媒体教室：0-否,1-是")
	@Column(name = "isMediaRoom", columnDefinition = "default 1", nullable = true)
	private Boolean isMediaRoom;

	@FieldInfo(name = "网络状态", type = "bit default 0", explain = "网络状态：0-无网络，1-有网络")
	@Column(name = "roomNet", columnDefinition = "default 0", nullable = true)
	private Boolean roomNet;

	@FieldInfo(name = "房间状态", type = "varchar(4) default ''", explain = "房间状态")
	@Column(name = "areaStatu", length = 4, columnDefinition = "default ''", nullable = true)
	private String areaStatu;

	@FieldInfo(name = "教室说明", type = "nvarchar(128) default ''", explain = "教室说明")
	@Column(name = "roomExplain", columnDefinition = "nvarchar(128)  default ''", nullable = true)
	private String roomExplain;

	@FieldInfo(name = "房间电话", type = "varchar(16)  default ''", explain = "房间电话")
	@Column(name = "roomPhone", columnDefinition = "varchar(16)  default ''", nullable = true)
	private String roomPhone;

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	// @FieldInfo(name = "区域名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_RoomArea a WHERE a.areaId=areaId)")
	private String areaName;

	@Transient
	// @FieldInfo(name = "批量添加房间数量")
	private Integer roomCount;

	// @FieldInfo(name = "上级名称")
	@Formula("(SELECT R.nodeText  FROM dbo.T_PT_RoomArea R WHERE R.areaId="
			+ "(SELECT A.parentNode FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId))")
	private String areaUpName;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Boolean getIsMediaRoom() {
		return isMediaRoom;
	}

	public void setIsMediaRoom(Boolean isMediaRoom) {
		this.isMediaRoom = isMediaRoom;
	}

	public Boolean getRoomNet() {
		return roomNet;
	}

	public void setRoomNet(Boolean roomNet) {
		this.roomNet = roomNet;
	}

	public String getAreaStatu() {
		return areaStatu;
	}

	public void setAreaStatu(String areaStatu) {
		this.areaStatu = areaStatu;
	}

	public String getRoomExplain() {
		return roomExplain;
	}

	public void setRoomExplain(String roomExplain) {
		this.roomExplain = roomExplain;
	}

	public String getRoomPhone() {
		return roomPhone;
	}

	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public String getAreaUpName() {
		return areaUpName;
	}

	public void setAreaUpName(String areaUpName) {
		this.areaUpName = areaUpName;
	}

	public BuildRoominfo() {
		super();
	}

	public BuildRoominfo(String id) {
		super(id);
	}

}