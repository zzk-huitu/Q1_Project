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
@AttributeOverride(name = "roomInfoId", column = @Column(name = "roomInfoId", length = 36, nullable = false) )
public class BuildRoominfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "区域ID")
	@Column(name = "areaId", length = 36, nullable = false)
	private String areaId;

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaId() {
		return areaId;
	}

	@FieldInfo(name = "教室编码")
	@Column(name = "roomCode", length = 32, nullable = true)
	private String roomCode;

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	@FieldInfo(name = "房间名称")
	@Column(name = "roomName", length = 32, nullable = true)
	private String roomName;

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	@FieldInfo(name = "房间类型")//参考数据字典表｛宿舍-1，办公室-2，教室-3，功能室-5｝
	@Column(name = "roomType", length = 16, nullable = true)
	private String roomType = "0";

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return roomType;
	}

	@FieldInfo(name = "班额代码")
	@Column(name = "roomCapacity", length = 32, nullable = true)
	private String roomCapacity;

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public String getRoomCapacity() {
		return roomCapacity;
	}

	@FieldInfo(name = "是否多媒体教室-0是,1否")
	@Column(name = "isMediaRoom", length = 10, nullable = true)
	private String isMediaRoom = "1";

	public String getIsMediaRoom() {
		return isMediaRoom;
	}

	public void setIsMediaRoom(String isMediaRoom) {
		this.isMediaRoom = isMediaRoom;
	}

	@FieldInfo(name = "网络状态：0有网络，1无网络")
	@Column(name = "roomNet", length = 10, nullable = true)
	private String roomNet = "0";

	public void setRoomNet(String roomNet) {
		this.roomNet = roomNet;
	}

	public String getRoomNet() {
		return roomNet;
	}

	@FieldInfo(name = "房间状态")
	@Column(name = "areaStatu", length = 10, nullable = true)
	private Integer areaStatu;

	public void setAreaStatu(Integer areaStatu) {
		this.areaStatu = areaStatu;
	}

	public Integer getAreaStatu() {
		return areaStatu;
	}

	@FieldInfo(name = "教室说明")
	@Column(name = "roomExplains", length = 128, nullable = true)
	private String roomExplains;

	public String getRoomExplains() {
		return roomExplains;
	}

	public void setRoomExplains(String roomExplains) {
		this.roomExplains = roomExplains;
	}

	@FieldInfo(name = "房间电话")
	@Column(name = "roomPhone", length = 200, nullable = true)
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
	@Formula("(SELECT isnull(a.NODE_TEXT,'ROOT') FROM BUILD_T_ROOMAREA a WHERE a.AREA_ID=AREA_ID)")
	private String areaName;

	@Transient
	@FieldInfo(name = "批量添加房间数量")
	private int roomCount;

	@FieldInfo(name = "上级名称")
	@Formula("(SELECT R.NODE_TEXT  FROM dbo.BUILD_T_ROOMAREA R WHERE R.AREA_ID="
			+ "(SELECT A.PARENT_NODE FROM dbo.BUILD_T_ROOMAREA A WHERE A.AREA_ID=AREA_ID))")
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