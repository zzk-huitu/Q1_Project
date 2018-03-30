package com.zd.school.control.device.model;

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
 * 设备表
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_Deveice")
@AttributeOverride(name = "deveiceId", column = @Column(name = "deveiceId", length = 20, nullable = false))
public class PtTerm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间主键", type = "varchar(20)", explain = "房间主键")
	@Column(name = "roomId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "网关主键", type = "varchar(20)", explain = "网关主键")
	@Column(name = "gatewayId", length = 20, nullable = false)
	private String gatewayId;

	@FieldInfo(name = "deveiceNo", type = "Integer", explain = "机号(1~65536)")
	@Column(name = "deveiceNo", columnDefinition = "default 0", nullable = false)
	private Integer deveiceNo;

	@FieldInfo(name = "硬件程序版本号", type = "varchar(8)", explain = "硬件程序版本号")
	@Column(name = "programVersion", columnDefinition = "varchar(8) default ''", nullable = true)
	private String programVersion;

	@FieldInfo(name = "deveiceSn", type = "varchar(50)", explain = "设备序列号 编号规则为：001(3位设备类型  最大255)-001(3位品质员编号 最大255)"
			+ "-140226(6位日期 最大631231)-001(3位批次号 最大255)-00001(5位流水 最大65535)")
	@Column(name = "deveiceSn", length = 50, nullable = false)
	private String deveiceSn;

	@FieldInfo(name = "deveiceName", type = "nvarchar(25)", explain = "设备名称")
	@Column(name = "deveiceName", columnDefinition = "nvarchar(25)", nullable = false)
	private String deveiceName;

	@FieldInfo(name = "deveiceTypeId", type = "varchar(4)", explain = "设备类型（对应系统参数表）")
	@Column(name = "deveiceTypeId", length = 4, nullable = false)
	private String deveiceTypeId;

	@FieldInfo(name = "deveiceStatus", type = "Boolean", explain = "设备状态(1是启用 0是禁用)")
	@Column(name = "deveiceStatus", columnDefinition = "default 1", nullable = true)
	private Boolean deveiceStatus;

	@FieldInfo(name = "offlineUse", type = "Integer", explain = "是否允许脱机使用")
	@Column(name = "offlineUse", columnDefinition = "default 0", nullable = true)
	private Integer offlineUse;

	@FieldInfo(name = "baseParam", type = "varbinary", explain = "基础参数")
	@Column(name = "baseParam")
	private byte[] baseParam;

	@FieldInfo(name = "advParam", type = "varbinary", explain = "高级参数")
	@Column(name = "advParam")
	private byte[] advParam;

	@FieldInfo(name = "rateParam", type = "varbinary", explain = "费率参数")
	@Column(name = "rateParam")
	private byte[] rateParam;

	@FieldInfo(name = "netParam", type = "varbinary", explain = "网络参数")
	@Column(name = "netParam")
	private byte[] netParam;

	@FieldInfo(name = "notes", type = "nvarchar(100)", explain = "备注说明")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String notes;

	@FieldInfo(name = "statusId", type = "varchar(20)", explain = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "varchar(20) default ''", nullable = true)
	private Integer statusId;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	@FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT A.gatewayName FROM dbo.T_PT_Gateway A WHERE A.gatewayId=gatewayId)")
	@FieldInfo(name = "网关名称")
	private String gatewayName;

	@Transient
	@FieldInfo(name = "设置设备重启时间")
	private String restartTime;

	@Transient
	@FieldInfo(name = "用于接收来自于前台的组合数据")
	private String baseParamUi;

	@Formula("(select  cast((s.priceName+':'+cast(s.priceValue as varchar(10))) as varchar(100)  ) from dbo.T_PT_PriceBind b,dbo.T_SK_Price s where s.sKPriceId=b.priceId and b.deveiceId=deveiceId) ")
	@FieldInfo(name = "水控费率")
	private String skprice;

	@Formula("(select  cast((s.priceName+':'+cast(s.priceValue as varchar(10))) as varchar(100)  ) from dbo.T_PT_PriceBind b,dbo.PT_DK_PRICE s where s.dKPriceId=b.priceId and b.deveiceId=deveiceId) ")
	@FieldInfo(name = "电控费率")
	private String dkprice;

	@Formula("(select  cast(s.measure as varchar(100)  ) from dbo.T_PT_SKMeter s,dbo.T_PT_SkMeterBind b where s.sKMeterId=b.skMeterBindId and b.deveiceId=deveiceId)")
	@FieldInfo(name = "水控计量")
	private String skmeasure;

	public String getBaseParamUi() {
		return baseParamUi;
	}

	public void setBaseParamUi(String baseParamUi) {
		this.baseParamUi = baseParamUi;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getOfflineUse() {
		return offlineUse;
	}

	public void setOfflineUse(Integer offlineUse) {
		this.offlineUse = offlineUse;
	}

	public byte[] getBaseParam() {
		return baseParam;
	}

	public void setBaseParam(byte[] baseParam) {
		this.baseParam = baseParam;
	}

	public byte[] getAdvParam() {
		return advParam;
	}

	public void setAdvParam(byte[] advParam) {
		this.advParam = advParam;
	}

	public byte[] getNetParam() {
		return netParam;
	}

	public void setNetParam(byte[] netParam) {
		this.netParam = netParam;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}

	public Integer getDeveiceNo() {
		return deveiceNo;
	}

	public void setDeveiceNo(Integer deveiceNo) {
		this.deveiceNo = deveiceNo;
	}

	public String getDeveiceSn() {
		return deveiceSn;
	}

	public void setDeveiceSn(String deveiceSn) {
		this.deveiceSn = deveiceSn;
	}

	public String getDeveiceName() {
		return deveiceName;
	}

	public void setDeveiceName(String deveiceName) {
		this.deveiceName = deveiceName;
	}

	public String getDeveiceTypeId() {
		return deveiceTypeId;
	}

	public void setDeveiceTypeId(String deveiceTypeId) {
		this.deveiceTypeId = deveiceTypeId;
	}

	public Boolean getDeveiceStatus() {
		return deveiceStatus;
	}

	public void setDeveiceStatus(Boolean deveiceStatus) {
		this.deveiceStatus = deveiceStatus;
	}

	public byte[] getRateParam() {
		return rateParam;
	}

	public void setRateParam(byte[] rateParam) {
		this.rateParam = rateParam;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getSkprice() {
		return skprice;
	}

	public void setSkprice(String skprice) {
		this.skprice = skprice;
	}

	public String getDkprice() {
		return dkprice;
	}

	public void setDkprice(String dkprice) {
		this.dkprice = dkprice;
	}

	public String getSkmeasure() {
		return skmeasure;
	}

	public void setSkmeasure(String skmeasure) {
		this.skmeasure = skmeasure;
	}

	public String getRestartTime() {
		return restartTime;
	}

	public void setRestartTime(String restartTime) {
		this.restartTime = restartTime;
	}

}
