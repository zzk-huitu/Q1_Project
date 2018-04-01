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
public class PtSkTermStatus extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备序列号", type = "varchar(14) default ''", explain = "设备序列号")
	@Column(name = "termSn", length = 14, columnDefinition = "default ''", nullable = true)
	private String termSn;

	@FieldInfo(name = "房间ID", type = "varchar(20) default ''", explain = "房间编号")
	@Column(name = "roomId", length = 20, columnDefinition = "default ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "状态的日期", type = "date", explain = "状态的日期")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "statusDate", columnDefinition = "date", nullable = true)
	private Date statusDate;

	@FieldInfo(name = "状态的小时", type = "int default 0", explain = "状态的小时")
	@Column(name = "statusHour", columnDefinition = "default 0", nullable = true)
	private Integer statusHour;

	@FieldInfo(name = "测量单位", type = "int defalut 0", explain = "测量单位（脉冲/升）")
	@Column(name = "measure", columnDefinition = "defalut 0", nullable = true)
	private Integer measure;

	@FieldInfo(name = "费率", type = "decimal defalut 0defalut 0", explain = "费率（元/升）")
	@Column(name = "price", columnDefinition = "defalut 0", nullable = true)
	private BigDecimal price;

	@FieldInfo(name = "冷水当前小时使用水量", type = "float defalut 0", explain = "冷水当前小时使用水量（升）")
	@Column(name = "useLiter", columnDefinition = "defalut 0", nullable = true)
	private Float useLiter;

	@FieldInfo(name = "冷水已使用总水量", type = "float defalut 0", explain = "冷水已使用总水量（升）")
	@Column(name = "totalUsedLiter", columnDefinition = "defalut 0", nullable = true)
	private Float totalUsedLiter;

	@FieldInfo(name = "冷水当前小时使用脉冲数", type = "bigint defalut 0", explain = "冷水当前小时使用脉冲数")
	@Column(name = "usePulse", columnDefinition = "defalut 0", nullable = true)
	private Long usePulse;

	@FieldInfo(name = "冷水总使用脉冲数", type = "bigint defalut 0", explain = "冷水总使用脉冲数")
	@Column(name = "totalUsedPulse", columnDefinition = "defalut 0", nullable = true)
	private Long totalUsedPulse;

	@FieldInfo(name = "热水交易金额", type = "decimal defalut 0", explain = "热水交易金额")
	@Column(name = "useMoney", columnDefinition = "defalut 0", nullable = true)
	private BigDecimal useMoney;

	@FieldInfo(name = "热水已交易总额", type = "decimal defalut 0", explain = "热水已交易总额")
	@Column(name = "totalUsedMoney", columnDefinition = "defalut 0", nullable = true)
	private BigDecimal totalUsedMoney;

	@FieldInfo(name = "热水已交易流水", type = "bigint defalut 0", explain = "热水已交易流水")
	@Column(name = "totalRecord", columnDefinition = "defalut 0", nullable = true)
	private Long totalRecord;

	@FieldInfo(name = "热水已上传流水", type = "bigint defalut 0", explain = "热水已上传流水")
	@Column(name = "uploadRecord", columnDefinition = "defalut 0", nullable = true)
	private Long uploadRecord;

	@FieldInfo(name = "状态的时间", type = "datetime", explain = "状态的时间")
	@Column(name = "statusTime", columnDefinition = "datetime")
	private Date statusTime;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称", type = "varchar(16)", explain = "房间名称")
	private String roomName;

	@Formula("(SELECT A.deveiceName FROM T_PT_Deveice A WHERE A.deveiceSn=deveiceSn)")
	// @FieldInfo(name = "设备名称", type = "varchar(25)", explain = "设备名称")
	private String deveiceName;

	@Formula("(SELECT A.GATEWAYNAME FROM PT_GATEWAY A ,PT_TERM t  WHERE   A.GATEWAY_ID=t.GATEWAY_ID and t.TERMSN=TERMSN)")
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

	public String getDeveiceName() {
		return deveiceName;
	}

	public void setDeveiceName(String deveiceName) {
		this.deveiceName = deveiceName;
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

	public PtSkTermStatus() {
		super();
	}

	public PtSkTermStatus(String id) {
		super(id);
	}

}