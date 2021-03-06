package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 网关表
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Gateway")
@AttributeOverride(name = "id", column = @Column(name = "gatewayId", length = 20, nullable = false) )
public class PtGateway extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "机号", type = "int NOT NULL", explain = "机号")
	@Column(name = "gatewayNo", nullable = false)
	private Integer gatewayNo;

	@FieldInfo(name = "网关名称", type = "nvarchar(20) NOT NULL", explain = "网关名称")
	@Column(name = "gatewayName", columnDefinition="nvarchar(20)", nullable = false)
	private String gatewayName;
	
	@FieldInfo(name = "前置表ID", type = "varchar(20) default ''", explain = "前置表ID")
	@Column(name = "frontServerId", length=20, columnDefinition = "default ''", nullable = true)
	private String frontServerId;
	
	@FieldInfo(name = "序列号", type = "varchar(128) default ''", explain = "序列号")
	@Column(name = "gatewaySn", length=128, columnDefinition = "default ''", nullable = true)
	private String gatewaySn;

	@FieldInfo(name = "硬件程序版本号", type = "varchar(8)  default ''", explain = "硬件程序版本号")
	@Column(name = "programVersion", length=8, columnDefinition = " default ''", nullable = true)
	private String programVersion;

	@FieldInfo(name = "网关Ip", type = "varchar(16) default ''", explain = "网关Ip")
	@Column(name = "gatewayIp", length=16, columnDefinition = "default ''", nullable = true)
	private String gatewayIP;

	@FieldInfo(name = "设备掩码", type = "varchar(32) default ''", explain = "设备掩码")
    @Column(name = "gatewayMask", length=32,columnDefinition = "default ''", nullable = true)
    private String gatewayMask;

    
    @FieldInfo(name = "设备MAC", type = "varchar(32) default ''", explain = "设备MAC")
    @Column(name = "gatewayMac", length=32,columnDefinition = "default ''", nullable = true)
    private String gatewayMac;

  
    @FieldInfo(name = "基础参数", type = "varbinary(255)", explain = "基础参数")
	@Column(name = "baseParam",length=255,nullable = true)
	private Byte[] baseParam;

	@FieldInfo(name = "高级参数", type = "varbinary(255)", explain = "高级参数")
	@Column(name = "advParam",length=255,nullable = true)
	private Byte[] advParam;

	@FieldInfo(name = "网络参数", type = "varbinary(255)", explain = "网络参数")
	@Column(name = "netParam",length=255,nullable = true)
	private Byte[] netParam;

	@FieldInfo(name = "notes", type = "nvarchar(500) default ''", explain = "备注说明")
	@Column(name = "notes", columnDefinition = "nvarchar(500) default ''", nullable = true)
	private String notes;
	
    @FieldInfo(name = "接入网关", type = "varchar(16) default ''", explain = "接入网关")
    @Column(name = "netGatewayIp",length=16, columnDefinition = "default ''", nullable = true)
    private String netGatewayIp;
	
	@FieldInfo(name = "网关状态", type = "int", explain = "网关状态(1是启用 0是禁用)")//修正，数据弄反
	@Column(name = "gatewayStatus",columnDefinition = "default 0", nullable = true)
	private Integer gatewayStatus;

	//@FieldInfo(name = "前置名称")
	@Formula("(SELECT A.frontServerName FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private String frontServerName;

	//@FieldInfo(name = "前置IP")
	@Formula("(SELECT A.frontServerIp FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private String frontServerIP;

	//@FieldInfo(name = "前置端口")
	@Formula("(SELECT A.frontServerPort FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private Integer frontServerPort;
	
	//@FieldInfo(name = "前置状态(1是启用 0是禁用)")//修正，数据弄反
	@Formula("(SELECT A.frontServerStatus FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private Integer frontServerStatus;

	public Integer getGatewayNo() {
		return gatewayNo;
	}

	public void setGatewayNo(Integer gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getFrontServerId() {
		return frontServerId;
	}

	public void setFrontServerId(String frontServerId) {
		this.frontServerId = frontServerId;
	}

	public String getGatewaySn() {
		return gatewaySn;
	}

	public void setGatewaySn(String gatewaySn) {
		this.gatewaySn = gatewaySn;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}

	public String getGatewayIP() {
		return gatewayIP;
	}

	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
	}

	public String getGatewayMask() {
		return gatewayMask;
	}

	public void setGatewayMask(String gatewayMask) {
		this.gatewayMask = gatewayMask;
	}

	public String getGatewayMac() {
		return gatewayMac;
	}

	public void setGatewayMac(String gatewayMac) {
		this.gatewayMac = gatewayMac;
	}

	public Byte[] getBaseParam() {
		return baseParam;
	}

	public void setBaseParam(Byte[] baseParam) {
		this.baseParam = baseParam;
	}

	public Byte[] getAdvParam() {
		return advParam;
	}

	public void setAdvParam(Byte[] advParam) {
		this.advParam = advParam;
	}

	public Byte[] getNetParam() {
		return netParam;
	}

	public void setNetParam(Byte[] netParam) {
		this.netParam = netParam;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNetGatewayIp() {
		return netGatewayIp;
	}

	public void setNetGatewayIp(String netGatewayIp) {
		this.netGatewayIp = netGatewayIp;
	}

	public Integer getGatewayStatus() {
		return gatewayStatus;
	}

	public void setGatewayStatus(Integer gatewayStatus) {
		this.gatewayStatus = gatewayStatus;
	}

	public String getFrontServerName() {
		return frontServerName;
	}

	public void setFrontServerName(String frontServerName) {
		this.frontServerName = frontServerName;
	}

	public String getFrontServerIP() {
		return frontServerIP;
	}

	public void setFrontServerIP(String frontServerIP) {
		this.frontServerIP = frontServerIP;
	}

	public Integer getFrontServerPort() {
		return frontServerPort;
	}

	public void setFrontServerPort(Integer frontServerPort) {
		this.frontServerPort = frontServerPort;
	}

	public Integer getFrontServerStatus() {
		return frontServerStatus;
	}

	public void setFrontServerStatus(Integer frontServerStatus) {
		this.frontServerStatus = frontServerStatus;
	}

	public PtGateway() {
		super();
	}
	
	public PtGateway(String id) {
		super(id);
	}
	

}
