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

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 水控使用状态
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_SK_TermStatus")
@AttributeOverride(name = "id", column = @Column(name = "termStatusId", length = 20, nullable = false) )
public class SkTermStatus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14,  nullable = false)
	private String termSn;

	@FieldInfo(name = "房间ID", type = "varchar(20) NOT NULL", explain = "房间编号")
	@Column(name = "roomId", length = 20,  nullable = false)
	private String roomId;

	@FieldInfo(name = "状态的日期", type = "date NOT NULL", explain = "状态的日期")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "statusDate", columnDefinition = "date", nullable = false)
	private Date statusDate;

	@FieldInfo(name = "状态的小时", type = "int NOT NULL default 0", explain = "状态的小时")
	@Column(name = "statusHour", columnDefinition = "int default 0", nullable = false)
	private Integer statusHour;

	@FieldInfo(name = "测量单位", type = "int NOT NULL DEFAULT 0", explain = "测量单位（脉冲/升）")
	@Column(name = "measure", columnDefinition = "int DEFAULT 0", nullable = false)
	private Integer measure;

	@FieldInfo(name = "费率", type = "decimal NOT NULL DEFAULT 0DEFAULT 0", explain = "费率（元/升）")
	@Column(name = "price", columnDefinition = "decimal DEFAULT 0", nullable = false)
	private BigDecimal price;

	@FieldInfo(name = "冷水当前小时使用水量", type = "float NOT NULL DEFAULT 0", explain = "冷水当前小时使用水量（升）")
	@Column(name = "useLiter", columnDefinition = "float DEFAULT 0", nullable = false)
	private Float useLiter;

	@FieldInfo(name = "冷水已使用总水量", type = "float NOT NULL DEFAULT 0", explain = "冷水已使用总水量（升）")
	@Column(name = "totalUsedLiter", columnDefinition = "float DEFAULT 0", nullable = false)
	private Float totalUsedLiter;

	@FieldInfo(name = "冷水当前小时使用脉冲数", type = "bigint  NOT NULL DEFAULT 0", explain = "冷水当前小时使用脉冲数")
	@Column(name = "usePulse", columnDefinition = "bigint DEFAULT 0", nullable = false)
	private Long usePulse;

	@FieldInfo(name = "冷水总使用脉冲数", type = "bigint NOT NULL DEFAULT 0", explain = "冷水总使用脉冲数")
	@Column(name = "totalUsedPulse", columnDefinition = "bigint DEFAULT 0", nullable = false)
	private Long totalUsedPulse;

	@FieldInfo(name = "热水交易金额", type = "decimal NOT NULL DEFAULT 0", explain = "热水交易金额")
	@Column(name = "useMoney", columnDefinition = "decimal DEFAULT 0", nullable = false)
	private BigDecimal useMoney;

	@FieldInfo(name = "热水已交易总额", type = "decimal NOT NULL DEFAULT 0", explain = "热水已交易总额")
	@Column(name = "totalUsedMoney", columnDefinition = "decimal DEFAULT 0", nullable = false)
	private BigDecimal totalUsedMoney;

	@FieldInfo(name = "热水已交易流水", type = "bigint NOT NULL DEFAULT 0", explain = "热水已交易流水")
	@Column(name = "totalRecord", columnDefinition = "bigint DEFAULT 0", nullable = false)
	private Long totalRecord;

	@FieldInfo(name = "热水已上传流水", type = "bigint NOT NULL DEFAULT 0", explain = "热水已上传流水")
	@Column(name = "uploadRecord", columnDefinition = "bigint DEFAULT 0", nullable = false)
	private Long uploadRecord;

	@FieldInfo(name = "状态的时间", type = "datetime NOT NULL", explain = "状态的时间")
	@Column(name = "statusTime", columnDefinition = "datetime", nullable = false)
	private Date statusTime;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称", type = "varchar(16)", explain = "房间名称")
	private String roomName;

	@Formula("(SELECT A.termName FROM T_PT_Term A WHERE A.termSn=termSn)")
	// @FieldInfo(name = "设备名称", type = "varchar(25)", explain = "设备名称")
	private String termName;

	@Formula("(SELECT A.gatewayName FROM T_PT_Gateway A ,T_PT_Term t  WHERE A.gatewayId=t.gatewayId and t.termSn=termSn)")
	// @FieldInfo(name = "网关名称")
	private String gatewayName;

	// @FieldInfo(name = "区域名称", type = "nvarchar()", explain = "区域名称")
	@Formula("(SELECT isnull(a.nodeText,'ROOT') FROM T_PT_RoomArea a ,T_PT_RoomInfo r WHERE a.areaId=r.areaId and r.roomId=roomId)")
	private String areaName;

	/* 用于排除未定义的房间 0 */
	@Formula("(SELECT A.roomType FROM T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间类型", type = "varchar(4)", explain = "房间类型")
	private String roomType;

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

	public Integer getMeasure() {
		return measure;
	}

	public void setMeasure(Integer measure) {
		this.measure = measure;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getUseLiter() {
		return useLiter;
	}

	public void setUseLiter(Float useLiter) {
		this.useLiter = useLiter;
	}

	public Float getTotalUsedLiter() {
		return totalUsedLiter;
	}

	public void setTotalUsedLiter(Float totalUsedLiter) {
		this.totalUsedLiter = totalUsedLiter;
	}

	public Long getUsePulse() {
		return usePulse;
	}

	public void setUsePulse(Long usePulse) {
		this.usePulse = usePulse;
	}

	public Long getTotalUsedPulse() {
		return totalUsedPulse;
	}

	public void setTotalUsedPulse(Long totalUsedPulse) {
		this.totalUsedPulse = totalUsedPulse;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getTotalUsedMoney() {
		return totalUsedMoney;
	}

	public void setTotalUsedMoney(BigDecimal totalUsedMoney) {
		this.totalUsedMoney = totalUsedMoney;
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Long getUploadRecord() {
		return uploadRecord;
	}

	public void setUploadRecord(Long uploadRecord) {
		this.uploadRecord = uploadRecord;
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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public SkTermStatus() {
		super();
	}

	public SkTermStatus(String id) {
		super(id);
	}

}