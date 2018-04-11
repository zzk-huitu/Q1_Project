package com.yc.q1.model.base.pt.device;

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
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 房间钱包
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_RoomBag")
@AttributeOverride(name = "id", column = @Column(name = "roomBagId", length = 20, nullable = false) )
public class RoomBag extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "房间ID", type = "varchar(20) NOT NULL", explain = "房间ID")
	@Column(name = "roomId", length = 20, nullable = false)
	private String roomId;

	@FieldInfo(name = "房间余额", type = "money NOT NULL default 0", explain = "房间余额")
	@Column(name = "roomValue", columnDefinition = "money default 0", nullable = false)
	private BigDecimal roomValue;

	@FieldInfo(name = "房间总用", type = "money NOT NULL default 0", explain = "房间总使用金额")
	@Column(name = "roomTotalUsed", columnDefinition = "money default 0", nullable = false)
	private BigDecimal roomTotalUsed;

	@FieldInfo(name = "房间总充", type = "money NOT NULL default 0", explain = "房间总充值金额")
	@Column(name = "roomTotalRecharge", columnDefinition = "money default 0", nullable = false)
	private BigDecimal roomTotalRecharge;

	@FieldInfo(name = "水总用", type = "money NOT NULL default 0", explain = "房间总用水金额")
	@Column(name = "waterTotalUsed", columnDefinition = "money default 0", nullable = false)
	private BigDecimal waterTotalUsed;

	@FieldInfo(name = "水改变时间", type = "datetime", explain = "房间最后用水时间")
	@Column(name = "waterUpdateTime", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date waterUpdateTime;

	@FieldInfo(name = "电总用", type = "money NOT NULL default 0", explain = "房间的总用电金额")
	@Column(name = "eleTotalUsed", columnDefinition = "money default 0", nullable = false)
	private BigDecimal eleTotalUsed;

	@FieldInfo(name = "电改变时间", type = "datetime", explain = "房间的最后用电时间")
	@Column(name = "eleUpdateTime", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date eleUpdateTime;

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

	public BigDecimal getWaterTotalUsed() {
		return waterTotalUsed;
	}

	public void setWaterTotalUsed(BigDecimal waterTotalUsed) {
		this.waterTotalUsed = waterTotalUsed;
	}

	public Date getWaterUpdateTime() {
		return waterUpdateTime;
	}

	public void setWaterUpdateTime(Date waterUpdateTime) {
		this.waterUpdateTime = waterUpdateTime;
	}

	public BigDecimal getEleTotalUsed() {
		return eleTotalUsed;
	}

	public void setEleTotalUsed(BigDecimal eleTotalUsed) {
		this.eleTotalUsed = eleTotalUsed;
	}

	public Date getEleUpdateTime() {
		return eleUpdateTime;
	}

	public void setEleUpdateTime(Date eleUpdateTime) {
		this.eleUpdateTime = eleUpdateTime;
	}

	public RoomBag() {
		super();
	}

	public RoomBag(String id) {
		super(id);
	}

}
