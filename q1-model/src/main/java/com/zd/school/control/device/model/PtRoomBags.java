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
@AttributeOverride(name = "roomBagId", column = @Column(name = "roomBagId", length = 20, nullable = false))
public class PtRoomBags extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "房间ID",type="varchar(20)",explain="房间ID")
	@Column(name = "roomId",columnDefinition = "varchar(20) default ''", nullable = true)
	private String roomId;
	
	@FieldInfo(name = "房间余额",type="BigDecimal",explain="房间余额")
	@Column(name = "roomValue",columnDefinition = "default 0",nullable = true)
	private BigDecimal roomValue;
	
	@FieldInfo(name = "房间总用",type="BigDecimal",explain="房间总使用金额")
	@Column(name = "roomTotalUsed",columnDefinition = "default 0",nullable = true)
	private BigDecimal roomTotalUsed;
	
	@FieldInfo(name = "房间总充",type="BigDecimal",explain="房间总充值金额")
	@Column(name = "roomTotalRecharge",columnDefinition = "default 0",nullable = true)
	private BigDecimal roomTotalRecharge;
	
	@FieldInfo(name = "水总用",type="BigDecimal",explain="房间总用水金额")
	@Column(name = "waterTotalUsed",columnDefinition = "default 0",nullable = true)
	private BigDecimal waterTotalUsed;
	
	@FieldInfo(name = "水改变时间",type="datetime",explain="房间最后用水时间")
	@Column(name = "waterUpdateTime",columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date waterUpdateTime;
	
	@FieldInfo(name = "电总用",type="BigDecimal",explain="房间的总用电金额")
	@Column(name = "electricityTotalUsed",columnDefinition = "default 0",nullable = true)
	private BigDecimal electricityTotalUsed;
	
	@FieldInfo(name = "电改变时间",type="datetime",explain="房间的最后用电时间")
	@Column(name = "electricityUpdateTime", columnDefinition = "datetime")
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
