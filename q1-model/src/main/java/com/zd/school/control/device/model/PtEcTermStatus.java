package com.zd.school.control.device.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;
import com.zd.school.excel.annotation.MapperCell;

/**
 * 电控使用状态
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_DeviceStatus")
@AttributeOverride(name = "deviceStatusId", column = @Column(name = "deviceStatusId", length = 20, nullable = false))
public class PtEcTermStatus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "deviceSn", type = "varchar(14)", explain = "设备序列号")
	@Column(name = "deviceSn", columnDefinition = "varchar(14) default ''", nullable = true)
	private String deviceSn;

	@FieldInfo(name = "roomId", type = "nvarchar(20)", explain = "房间编号")
	@Column(name = "roomId", columnDefinition = "nvarchar(20) default ''", nullable = true)
	private String roomId;

	@MapperCell(cellName = "状态的日期", order = 1)
	@FieldInfo(name = "statusDate", type = "date", explain = "状态的日期")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "statusDate", columnDefinition = "date")
	private Date statusDate;

	@MapperCell(cellName = "状态的小时", order = 2)
	@FieldInfo(name = "statusHour", type = "Integer", explain = "状态的小时")
	@Column(name = "statusHour")
	private Integer statusHour;

	@MapperCell(cellName = "当前小时用电量", order = 3)
	@FieldInfo(name = "useKwh", type = "double", explain = "当前小时用电量")
	@Column(name = "useKwh")
	private double useKwh;

	@MapperCell(cellName = "已购电总量", order = 4)
	@FieldInfo(name = "boughtKwh", type = "double", explain = "已购电总量")
	@Column(name = "boughtKwh")
	private double boughtKwh;

	@MapperCell(cellName = "已使用总电量", order = 5)
	@FieldInfo(name = "totalUsedKwh", type = "double", explain = "已使用总电量")
	@Column(name = "totalUsedKwh")
	private double totalUsedKwh;

	@MapperCell(cellName = "剩余总电量", order = 6)
	@FieldInfo(name = "surplusKwh", type = "double", explain = "剩余总电量")
	@Column(name = "surplusKwh")
	private double surplusKwh;

	@MapperCell(cellName = "电压", order = 7)
	@FieldInfo(name = "voltage", type = "Bigint", explain = "电压")
	@Column(name = "voltage")
	private long voltage;

	@MapperCell(cellName = "电流", order = 8)
	@FieldInfo(name = "currents", type = "Bigint", explain = "电流")
	@Column(name = "currents")
	private long currents;

	@MapperCell(cellName = "功率", order = 9)
	@FieldInfo(name = "power", type = "Bigint", explain = "功率")
	@Column(name = "power")
	private long power;

	@MapperCell(cellName = "状态的时间", order = 10)
	@FieldInfo(name = "statusTime", type = "datetime", explain = "状态的时间")
	@Column(name = "statusTime", columnDefinition = "datetime")
	private Date statusTime;

	@MapperCell(cellName = "房间名称", order = 11)
	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间名称")
	private String roomName;

	/* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.roomType FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间类型")
	private String roomType;

	@Transient
	@FieldInfo(name = "设备机号")
	private String deviceNo;

	@Transient
	@FieldInfo(name = "设备类型")
	private String deviceTypeId;

	@Transient
	@FieldInfo(name = "网关名称")
	private String gatewayName;

	@Transient
	@FieldInfo(name = "楼层名称")
	private String areaName;

	@Transient
	@FieldInfo(name = "起始电量")
	private String startDl;

	@Transient
	@FieldInfo(name = "结束电量")
	private String endDl;

	@Transient
	@FieldInfo(name = "总电量")
	private String sumDl;
	@JsonIgnore
	@Transient
	@FieldInfo(name = "条件一")
	private String wheresql1;
	@JsonIgnore
	@Transient
	@FieldInfo(name = "条件二")
	private String wheresql2;

	@MapperCell(cellName = "设备名称", order = 12)
	@Formula("(SELECT A.deviceName FROM dbo.T_PT_Term A WHERE A.deviceSn=deviceSn)")
	@FieldInfo(name = "设备名称")
	private String termName;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Integer getStatusHour() {
		return statusHour;
	}

	public void setStatusHour(Integer statusHour) {
		this.statusHour = statusHour;
	}

	public long getVoltage() {
		return voltage;
	}

	public void setVoltage(long voltage) {
		this.voltage = voltage;
	}

	public long getCurrents() {
		return currents;
	}

	public void setCurrents(long currents) {
		this.currents = currents;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public long getPower() {
		return power;
	}

	public void setPower(long power) {
		this.power = power;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStartDl() {
		return startDl;
	}

	public void setStartDl(String startDl) {
		this.startDl = startDl;
	}

	public double getUseKwh() {
		return useKwh;
	}

	public void setUseKwh(double useKwh) {
		this.useKwh = useKwh;
	}

	public double getBoughtKwh() {
		return boughtKwh;
	}

	public void setBoughtKwh(double boughtKwh) {
		this.boughtKwh = boughtKwh;
	}

	public double getTotalUsedKwh() {
		return totalUsedKwh;
	}

	public void setTotalUsedKwh(double totalUsedKwh) {
		this.totalUsedKwh = totalUsedKwh;
	}

	public double getSurplusKwh() {
		return surplusKwh;
	}

	public void setSurplusKwh(double surplusKwh) {
		this.surplusKwh = surplusKwh;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}

	public String getEndDl() {
		return endDl;
	}

	public void setEndDl(String endDl) {
		this.endDl = endDl;
	}

	public String getSumDl() {
		return sumDl;
	}

	public void setSumDl(String sumDl) {
		this.sumDl = sumDl;
	}

	public String getWheresql1() {
		return wheresql1;
	}

	public void setWheresql1(String wheresql1) {
		this.wheresql1 = wheresql1;
	}

	public String getWheresql2() {
		return wheresql2;
	}

	public void setWheresql2(String wheresql2) {
		this.wheresql2 = wheresql2;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}