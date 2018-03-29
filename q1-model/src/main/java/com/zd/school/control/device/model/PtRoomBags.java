package com.zd.school.control.device.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

@Entity
@Table(name = "T_PT_RoomBag")
@AttributeOverride(name = "roomBagId", column = @Column(name = "roomBagId", length = 36, nullable = false))
public class PtRoomBags extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "房间ID")
	@Column(name = "roomId", length = 36, nullable = true)
	private String roomId;
	
	@FieldInfo(name = "房间余额")
	@Column(name = "roomValue")
	private BigDecimal roomValue;
	
	@FieldInfo(name = "房间总用")
	@Column(name = "roomTotalUsed")
	private BigDecimal roomTotalUsed;
	
	@FieldInfo(name = "房间总充")
	@Column(name = "roomTotalRecharge")
	private BigDecimal roomTotalRecharge;
	
	@FieldInfo(name = "水总用")
	@Column(name = "waterTotalUsed")
	private BigDecimal waterTotalUsed;
	
	@FieldInfo(name = "水改变时间")
	@Column(name = "waterUpdateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date waterUpdateTime;
	
	@FieldInfo(name = "电总用")
	@Column(name = "electricityTotalUsed")
	private BigDecimal electricityTotalUsed;
	
	@FieldInfo(name = "电改变时间")
	@Column(name = "electricityUpdateTime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date electricityUpdateTime;
	
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public BigDecimal getRoomValue() {
		return roomValue;
	}

	public void setRoomValue(BigDecimal roomValue) {
		this.roomValue = roomValue;
	}

	public BigDecimal getRoomTotalUsed() {
		return roomTotalUsed;
	}

	public void setRoomTotalUsed(BigDecimal roomTotalUsed) {
		this.roomTotalUsed = roomTotalUsed;
	}

	public BigDecimal getRoomTotalRecharge() {
		return roomTotalRecharge;
	}

	public void setRoomTotalRecharge(BigDecimal roomTotalRecharge) {
		this.roomTotalRecharge = roomTotalRecharge;
	}

	public Date getWaterUpdateTime() {
		return waterUpdateTime;
	}

	public void setWaterUpdateTime(Date waterUpdateTime) {
		this.waterUpdateTime = waterUpdateTime;
	}

	public BigDecimal getWaterTotalUsed() {
		return waterTotalUsed;
	}

	public void setWaterTotalUsed(BigDecimal waterTotalUsed) {
		this.waterTotalUsed = waterTotalUsed;
	}

	public BigDecimal getElectricityTotalUsed() {
		return electricityTotalUsed;
	}

	public void setElectricityTotalUsed(BigDecimal electricityTotalUsed) {
		this.electricityTotalUsed = electricityTotalUsed;
	}

	public Date getElectricityUpdateTime() {
		return electricityUpdateTime;
	}

	public void setElectricityUpdateTime(Date electricityUpdateTime) {
		this.electricityUpdateTime = electricityUpdateTime;
	}

}
