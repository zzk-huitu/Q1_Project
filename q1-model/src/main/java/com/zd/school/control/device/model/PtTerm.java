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
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_Deveice")
@AttributeOverride(name = "deveiceId", column = @Column(name = "deveiceId", length = 20, nullable = false) )
public class PtTerm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间主键", type = "varchar(20)", explain = "房间主键")
	@Column(name = "roomId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "网关主键", type = "varchar(20)", explain = "网关主键")
	@Column(name = "gatewayId", length =20, nullable = false)
	private String gatewayId;

	@FieldInfo(name = "机号(1~65536)", type = "Integer", explain = "")
	@Column(name = "termNo",nullable = false)
	private Integer termNo;

	@FieldInfo(name = "硬件程序版本号", type = "varchar(8)", explain = "硬件程序版本号")
	@Column(name = "programVersion", columnDefinition = "varchar(8) default ''", nullable = true)
	private String programVersion;
	
	@FieldInfo(name = "设备序列号 编号规则为：001(3位设备类型  最大255)-001(3位品质员编号 最大255)"
			+ "-140226(6位日期 最大631231)-001(3位批次号 最大255)-00001(5位流水 最大65535)", type = "", explain = "")
	@Column(name = "termSn",length = 50, nullable = false)
	private String termSn;

	@FieldInfo(name = "设备名称", type = "nvarchar(25)", explain = "设备名称")
	@Column(name = "deveiceName",  columnDefinition = "nvarchar(25)", nullable = false)
	private String deveiceName;

	@FieldInfo(name = "设备类型（对应系统参数表）", type = "", explain = "")
	@Column(name = "termTypeId", length = 4, nullable = false)
	private String termTypeId;

	@FieldInfo(name = "设备状态(1是启用 0是禁用)", type = "Boolean", explain = "")
	@Column(name = "termStatus",columnDefinition = "default 1", nullable = true)
	private Boolean termStatus;

	@FieldInfo(name = "", type = "Integer", explain = "是否允许脱机使用")
	@Column(name = "offlineUse",columnDefinition = "default 0", nullable = true)
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

	@FieldInfo(name = "备注说明", type = "nvarchar(100)", explain = "")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''",nullable=true)
	private String notes;

	@FieldInfo(name = "", type = "varchar(20)", explain = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "varchar(20) default ''",nullable=true)
	private Integer statusId;
	
	

	@Formula("(SELECT A.ROOM_NAME FROM dbo.BUILD_T_ROOMINFO A WHERE A.ROOM_ID=ROOM_ID)")
	@FieldInfo(name = "房间名称")
	private String roomName;
	
	@Formula("(SELECT A.GATEWAYNAME FROM dbo.PT_GATEWAY A WHERE A.GATEWAY_ID=GATEWAY_ID)")
	@FieldInfo(name = "网关名称")
	private String gatewayName;
	
	@Transient
	@FieldInfo(name = "设置设备重启时间")
	private String restartTime;
	
	@Transient
	@FieldInfo(name = "用于接收来自于前台的组合数据")
	private String baseParamUi;
	
	

	@Formula("(select  cast((s.PRICE_NAME+':'+cast(s.PRICE_VALUE as varchar(10))) as varchar(100)  ) from dbo.PT_PRICEBIND b,dbo.PT_SK_PRICE s where s.SK_PRICEID=b.PRICEID and b.TERM_ID=TERM_ID) ")
	@FieldInfo(name = "水控费率")
	private String skprice;
	
	@Formula("(select  cast((s.PRICE_NAME+':'+cast(s.PRICE_VALUE as varchar(10))) as varchar(100)  ) from dbo.PT_PRICEBIND b,dbo.PT_DK_PRICE s where s.DK_PRICEID=b.PRICEID and b.TERM_ID=TERM_ID) ")
	@FieldInfo(name = "电控费率")
	private String dkprice;
	
	@Formula("(select  cast(s.MEASURE as varchar(100)  ) from dbo.PT_SK_METER s,dbo.PT_SK_METERBIND b where s.METER_ID=b.METER_ID and b.TERM_ID=TERM_ID)")
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


	public Integer getTermNo() {
		return termNo;
	}

	public void setTermNo(Integer termNo) {
		this.termNo = termNo;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
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

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
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
