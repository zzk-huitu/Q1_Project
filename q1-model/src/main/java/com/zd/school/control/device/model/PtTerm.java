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
@Table(name = "T_PT_Term")
@AttributeOverride(name = "termId", column = @Column(name = "termId", length = 36, nullable = false) )
public class PtTerm extends BaseEntity implements Serializable {
	public String getRestartTime() {
		return restartTime;
	}

	public void setRestartTime(String restartTime) {
		this.restartTime = restartTime;
	}

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "房间主键")
	@Column(name = "roomId", length = 36, nullable = true)
	private String roomId;

	@FieldInfo(name = "网关主键")
	@Column(name = "gatewayId", length = 36, nullable = true)
	private String gatewayId;

	@FieldInfo(name = "机号(1~65536)")
	@Column(name = "termNo")
	private Integer termNo;

	@FieldInfo(name = "硬件程序版本号")
	@Column(name = "programVersion", length = 8, nullable = true)
	private String programVersion;
	
	@FieldInfo(name = "设备序列号 编号规则为：001(3位设备类型  最大255)-001(3位品质员编号 最大255)"
			+ "-140226(6位日期 最大631231)-001(3位批次号 最大255)-00001(5位流水 最大65535)")
	@Column(name = "termSn",length = 50, nullable = true)
	private String termSn;

	@FieldInfo(name = "设备名称")
	@Column(name = "termName", length = 50, nullable = true)
	private String termName;

	@FieldInfo(name = "设备类型（对应系统参数表）")
	@Column(name = "termTypeId")
	private String termTypeId;

	@FieldInfo(name = "设备状态(1是启用 0是禁用)")
	@Column(name = "termStatus")
	private Integer termStatus = 1;

	@FieldInfo(name = "是否允许脱机使用")
	@Column(name = "offlineUse")
	private Integer offlineUse;

	@FieldInfo(name = "基础参数")
	@Column(name = "baseParam")
	private byte[] baseParam;

	@FieldInfo(name = "高级参数")
	@Column(name = "advParam")
	private byte[] advParam;

	@FieldInfo(name = "费率参数")
	@Column(name = "rateParam")
	private byte[] rateParam;

	@FieldInfo(name = "网络参数")
	@Column(name = "netParam")
	private byte[] netParam;

	@FieldInfo(name = "备注说明")
	@Column(name = "notes", length = 200, nullable = true)
	private String notes;

	@FieldInfo(name = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId")
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

	public Integer getTermStatus() {
		return termStatus;
	}

	public void setTermStatus(Integer termStatus) {
		this.termStatus = termStatus;
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

}
