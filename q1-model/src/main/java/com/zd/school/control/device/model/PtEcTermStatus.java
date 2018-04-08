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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;
import com.zd.school.excel.annotation.MapperCell;

/**
 * 电控使用状态
 * 
 * @author ZZK Term：terminal 终端机
 */
@Entity
@Table(name = "T_DK_TermStatus")
@AttributeOverride(name = "id", column = @Column(name = "termStatusId", length = 20, nullable = false) )
public class PtEcTermStatus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14,  nullable = false)
	private String termSn;

	@FieldInfo(name = "房间ID", type = "varchar(20) NOT NULL", explain = "房间编号")
	@Column(name = "roomId", length = 20,  nullable = false)
	private String roomId;

	@FieldInfo(name = "状态的日期", type = "date  NOT NULL", explain = "状态的日期")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "statusDate", columnDefinition = "date", nullable = false)
	private Date statusDate;

	@FieldInfo(name = "状态的小时", type = "int NOT NULL default 0", explain = "状态的小时")
	@Column(name = "statusHour", columnDefinition = "int default 0", nullable = false)
	private Integer statusHour;

	@FieldInfo(name = "当前小时用电量", type = "float NOT NULL default 0", explain = "当前小时用电量")
	@Column(name = "useKwh", columnDefinition = "float default 0", nullable = false)
	private Float useKwh;

	@FieldInfo(name = "已购电总量", type = "float NOT NULL default 0", explain = "已购电总量")
	@Column(name = "buyedKwh", columnDefinition = "float default 0", nullable = false)
	private Float buyedKwh;

	@FieldInfo(name = "已使用总电量", type = "float NOT NULL default 0", explain = "已使用总电量")
	@Column(name = "totalUsedKwh", columnDefinition = "float default 0", nullable = false)
	private Float totalUsedKwh;

	@FieldInfo(name = "剩余总电量", type = "float NOT NULL default 0", explain = "剩余总电量")
	@Column(name = "surplusKwh", columnDefinition = "float default 0", nullable = false)
	private Float surplusKwh;

	@FieldInfo(name = "电压", type = "Bigint NOT NULL default 0", explain = "电压")
	@Column(name = "voltage", columnDefinition = "Bigint default 0", nullable = false)
	private Long voltage;

	@FieldInfo(name = "电流", type = "Bigint NOT NULL default 0", explain = "电流")
	@Column(name = "currents", columnDefinition = "Bigint default 0", nullable = false)
	private Long currents;

	@FieldInfo(name = "功率", type = "Bigint NOT NULL default 0", explain = "功率")
	@Column(name = "power", columnDefinition = "Bigint default 0", nullable = false)
	private Long power;

	@MapperCell(cellName = "状态的时间", order = 10)
	@FieldInfo(name = "statusTime", type = "datetime NOT NULL", explain = "状态的时间")
	@Column(name = "statusTime", columnDefinition = "datetime", nullable = false)
	private Date statusTime;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	/* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.roomType FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间类型")
	private String roomType;

	@Transient
	// @FieldInfo(name = "设备机号")
	private String deviceNo;

	@Transient
	// @FieldInfo(name = "设备类型")
	private String deviceTypeId;

	@Transient
	// @FieldInfo(name = "网关名称")
	private String gatewayName;

	@Transient
	// @FieldInfo(name = "楼层名称")
	private String areaName;

	@Transient
	// @FieldInfo(name = "起始电量")
	private String startDl;

	@Transient
	// @FieldInfo(name = "结束电量")
	private String endDl;

	@Transient
	// @FieldInfo(name = "总电量")
	private String sumDl;

	@Formula("(SELECT A.deviceName FROM dbo.T_PT_Term A WHERE A.termSn=termSn)")
	// @FieldInfo(name = "设备名称")
	private String termName;

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

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

	public Float getUseKwh() {
		return useKwh;
	}

	public void setUseKwh(Float useKwh) {
		this.useKwh = useKwh;
	}

	public Float getBuyedKwh() {
		return buyedKwh;
	}

	public void setBuyedKwh(Float buyedKwh) {
		this.buyedKwh = buyedKwh;
	}

	public Float getTotalUsedKwh() {
		return totalUsedKwh;
	}

	public void setTotalUsedKwh(Float totalUsedKwh) {
		this.totalUsedKwh = totalUsedKwh;
	}

	public Float getSurplusKwh() {
		return surplusKwh;
	}

	public void setSurplusKwh(Float surplusKwh) {
		this.surplusKwh = surplusKwh;
	}

	public Long getVoltage() {
		return voltage;
	}

	public void setVoltage(Long voltage) {
		this.voltage = voltage;
	}

	public Long getCurrents() {
		return currents;
	}

	public void setCurrents(Long currents) {
		this.currents = currents;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(Long power) {
		this.power = power;
	}

	public Date getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public PtEcTermStatus() {
		super();
	}

	public PtEcTermStatus(String id) {
		super(id);
	}

}