package com.yc.q1.model.base.pt.device;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 设备表（硬件设备）
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Term")
@AttributeOverride(name = "id", column = @Column(name = "termId", length = 20, nullable = false) )
public class PtTerm extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "房间主键", type = "varchar(20)  default ''", explain = "房间主键")
	@Column(name = "roomId", columnDefinition = "varchar(20)  default ''", nullable = true)
	private String roomId;

	@FieldInfo(name = "网关主键", type = "varchar(20) NOT NULL", explain = "网关主键")
	@Column(name = "gatewayId", length = 20, nullable = false)
	private String gatewayId;

	@FieldInfo(name = "机号", type = "int NOT NULL", explain = "机号(1~65536)")
	@Column(name = "termNo", columnDefinition = "int default 0", nullable = false)
	private Integer termNo;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号 编号规则为：001(3位设备类型  最大255)-001(3位品质员编号 最大255)"
			+ "-140226(6位日期 最大631231)-001(3位批次号 最大255)-00001(5位流水 最大65535)")
	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@FieldInfo(name = "设备名称", type = "nvarchar(16) NOT NULL", explain = "设备名称")
	@Column(name = "termName", columnDefinition = "nvarchar(16)", nullable = false)
	private String termName;

	@FieldInfo(name = "设备类型", type = "varchar(4) NOT NULL", explain = "设备类型（对应系统参数表）")
	@Column(name = "termTypeId", length = 4, nullable = false)
	private String termTypeId;

	@FieldInfo(name = "硬件程序版本号", type = "varchar(8)  default ''", explain = "硬件程序版本号")
	@Column(name = "programVersion", columnDefinition = "varchar(8) default ''", nullable = true)
	private String programVersion;

	@FieldInfo(name = "设备状态", type = "bit default 1", explain = "设备状态(0-禁用，1-启用 )")
	@Column(name = "termStatus", columnDefinition = "bit default 1", nullable = true)
	private Boolean termStatus;

	@FieldInfo(name = "offlineUse", type = "bit default 0", explain = "是否允许脱机使用(0-禁用，1-启用 )")
	@Column(name = "offlineUse", columnDefinition = "bit default 0", nullable = true)
	private Boolean offlineUse;

	@FieldInfo(name = "基础参数", type = "varbinary(255)", explain = "基础参数")
	@Column(name = "baseParam")
	private byte[] baseParam;

	@FieldInfo(name = "高级参数", type = "varbinary(255)", explain = "高级参数")
	@Column(name = "advParam")
	private byte[] advParam;

	@FieldInfo(name = "费率参数", type = "varbinary(255)", explain = "费率参数")
	@Column(name = "rateParam")
	private byte[] rateParam;

	@FieldInfo(name = "网络参数", type = "varbinary(255)", explain = "网络参数")
	@Column(name = "netParam")
	private byte[] netParam;

	@FieldInfo(name = "备注说明", type = "nvarchar(256) default ''", explain = "备注说明")
	@Column(name = "notes", columnDefinition = "nvarchar(256) default ''", nullable = true)
	private String notes;

	@FieldInfo(name = "statusId", type = "varchar(4)", explain = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "varchar(4) default ''", nullable = true)
	private String statusId;

	@Formula("(SELECT A.roomName FROM dbo.T_PT_RoomInfo A WHERE A.roomId=roomId)")
	// @FieldInfo(name = "房间名称")
	private String roomName;

	@Formula("(SELECT A.gatewayName FROM dbo.T_PT_Gateway A WHERE A.gatewayId=gatewayId)")
	// @FieldInfo(name = "网关名称")
	private String gatewayName;

	@Transient
	// @FieldInfo(name = "设置设备重启时间")
	private String restartTime;

	@Transient
	// @FieldInfo(name = "用于接收来自于前台的组合数据")
	private String baseParamUi;

	@Formula("(select  cast((s.priceName+':'+cast(s.priceValue as varchar(10))) as varchar(100)  ) from dbo.T_PT_PriceBind b,dbo.T_SK_Price s where s.sKPriceId=b.priceId and b.deveiceId=deveiceId) ")
	// @FieldInfo(name = "水控费率")
	private String skprice;

	@Formula("(select  cast((s.priceName+':'+cast(s.priceValue as varchar(10))) as varchar(100)  ) from dbo.T_PT_PriceBind b,dbo.PT_DK_PRICE s where s.dKPriceId=b.priceId and b.deveiceId=deveiceId) ")
	// @FieldInfo(name = "电控费率")
	private String dkprice;

	@Formula("(select  cast(s.measure as varchar(100)  ) from dbo.T_PT_SKMeter s,dbo.T_PT_SkMeterBind b where s.sKMeterId=b.skMeterBindId and b.deveiceId=deveiceId)")
	// @FieldInfo(name = "水控计量")
	private String skmeasure;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public Integer getTermNo() {
		return termNo;
	}

	public void setTermNo(Integer termNo) {
		this.termNo = termNo;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}

	public Boolean getTermStatus() {
		return termStatus;
	}

	public void setTermStatus(Boolean termStatus) {
		this.termStatus = termStatus;
	}

	public Boolean getOfflineUse() {
		return offlineUse;
	}

	public void setOfflineUse(Boolean offlineUse) {
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

	public byte[] getRateParam() {
		return rateParam;
	}

	public void setRateParam(byte[] rateParam) {
		this.rateParam = rateParam;
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

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getRestartTime() {
		return restartTime;
	}

	public void setRestartTime(String restartTime) {
		this.restartTime = restartTime;
	}

	public String getBaseParamUi() {
		return baseParamUi;
	}

	public void setBaseParamUi(String baseParamUi) {
		this.baseParamUi = baseParamUi;
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

	public PtTerm() {
		super();
	}

	public PtTerm(String id) {
		super(id);
	}

}