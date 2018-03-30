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
 * 
 * ClassName: BuildRoominfo Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 教室信息实体类. date: 2016-08-23
 * 使用备用字段1-5，作为房间的门牌号1-5
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_RoomInfo")
@AttributeOverride(name = "roomInfoId", column = @Column(name = "roomInfoId", length = 20, nullable = false) )
public class BuildRoominfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "areaId", type = "varchar(20)", explain = "区域ID")
	@Column(name = "areaId", length = 20, nullable = false)
	private String areaId;

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaId() {
		return areaId;
	}

	@FieldInfo(name = "roomCode", type = "varchar(20)", explain = "房间编码")
	@Column(name = "roomCode", length = 20, nullable = false)
	private String roomCode;

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	@FieldInfo(name = "roomName", type = "nvarchar(16)", explain = "房间名称")
	@Column(name = "roomName", columnDefinition = "nvarchar(20)", nullable = false)
	private String roomName;

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	@FieldInfo(name = "roomType", type = "varchar(4)", explain = "房间类型：宿舍-1，办公室-2，教室-3，功能室-5")//参考数据字典表｛｝
	@Column(name = "roomType", length = 4, nullable = false)
	private String roomType = "0";

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return roomType;
	}

	@FieldInfo(name = "roomCapacity", type = "nvarchar(20)", explain = "班额代码")
	@Column(name = "roomCapacity", columnDefinition = "nvarchar(20)  default ''", nullable = true)
	private String roomCapacity;

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public String getRoomCapacity() {
		return roomCapacity;
	}

	@FieldInfo(name = "isMediaRoom", type = "Boolean", explain = "是否多媒体教室-0是,1否")
	@Column(name = "isMediaRoom", columnDefinition = "default 1", nullable = true)
	private Boolean isMediaRoom ;

	public Boolean getIsMediaRoom() {
		return isMediaRoom;
	}

	public void setIsMediaRoom(Boolean isMediaRoom) {
		this.isMediaRoom = isMediaRoom;
	}

	@FieldInfo(name = "roomNet", type = "Boolean", explain = "网络状态：0有网络，1无网络")
	@Column(name = "roomNet", columnDefinition = "default 0", nullable = true)
	private Boolean roomNet;

	public void setRoomNet(Boolean roomNet) {
		this.roomNet = roomNet;
	}

	public Boolean getRoomNet() {
		return roomNet;
	}

	@FieldInfo(name = "areaStatu", type = "varchar(10)", explain = "房间状态")
	@Column(name = "areaStatu", columnDefinition = "varchar(10)  default ''", nullable = true)
	private Integer areaStatu;

	public void setAreaStatu(Integer areaStatu) {
		this.areaStatu = areaStatu;
	}

	public Integer getAreaStatu() {
		return areaStatu;
	}

	@FieldInfo(name = "roomExplains", type = "nvarchar(128)", explain = "教室说明")
	@Column(name = "roomExplains", columnDefinition = "nvarchar(128)  default ''", nullable = true)
	private String roomExplains;

	public String getRoomExplains() {
		return roomExplains;
	}

	public void setRoomExplains(String roomExplains) {
		this.roomExplains = roomExplains;
	}

	@FieldInfo(name = "roomPhone", type = "varchar(11)", explain = "房间电话")
	@Column(name = "roomPhone", columnDefinition = "varchar(11)  default ''", nullable = true)
	private String roomPhone;

	public String getRoomPhone() {
		return roomPhone;
	}

	public void setRoomPhone(String roomPhone) {
		this.roomPhone = roomPhone;
	}
	
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
	@FieldInfo(name = "区域名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_RoomArea a WHERE a.areaId=areaId)")
	private String areaName;

	@Transient
	@FieldInfo(name = "批量添加房间数量")
	private int roomCount;

	@FieldInfo(name = "上级名称")
	@Formula("(SELECT R.nodeText  FROM dbo.T_PT_RoomArea R WHERE R.areaId="
			+ "(SELECT A.parentNode FROM dbo.T_PT_RoomArea A WHERE A.areaId=areaId))")
	private String areaUpName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaUpName() {
		return areaUpName;
	}

	public void setAreaUpName(String areaUpName) {
		this.areaUpName = areaUpName;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

}