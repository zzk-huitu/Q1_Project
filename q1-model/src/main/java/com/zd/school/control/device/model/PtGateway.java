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
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_Gateway")
@AttributeOverride(name = "gatewayId", column = @Column(name = "gatewayId", length = 36, nullable = false) )
public class PtGateway extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "gatewayNo", type = "Integer", explain = "机号")
	@Column(name = "gatewayNo")
	private Integer gatewayNo;

	@FieldInfo(name = "gatewayName", type = "varchar(200)", explain = "网关名称")
	@Column(name = "gatewayName", length = 200, nullable = false)
	private String gatewayName;
	
	@FieldInfo(name = "frontServerId", type = "varchar(36", explain = "前置表ID")
	@Column(name = "frontServerId", columnDefinition = "varchar(36) default ''", nullable = true)
	private String frontServerId;
	
	@FieldInfo(name = "gatewaySN", type = "varchar(200)", explain = "序列号")
	@Column(name = "gatewaySN", columnDefinition = "varchar(200) default ''", nullable = true)
	private String gatewaySN;

	@FieldInfo(name = "programVersion", type = "varchar(8)", explain = "硬件程序版本号")
	@Column(name = "programVersion", columnDefinition = "varchar(8) default ''", nullable = true)
	private String programVersion;

	@FieldInfo(name = "gatewayIp", type = "varchar(100)", explain = "网关Ip")
	@Column(name = "gatewayIp", columnDefinition = "varchar(100) default ''", nullable = true)
	private String gatewayIP;

	@FieldInfo(name = "gatewayMask", type = "varchar(36)", explain = "设备掩码")
    @Column(name = "gatewayMask",columnDefinition = "varchar(36) default ''", nullable = true)
    private String gatewayMask;

    public String getGatewayMask() {
        return gatewayMask;
    }

    public void setGatewayMask(String gatewayMask) {
        this.gatewayMask = gatewayMask;
    }
    @FieldInfo(name = "gatewayMac", type = "varchar(36)", explain = "设备MAC")
    @Column(name = "gatewayMac",columnDefinition = "varchar(36) default ''", nullable = true)
    private String gatewayMac;

    public String getGatewayMac() {
        return gatewayMac;
    }

    public void setGatewayMac(String gatewayMac) {
        this.gatewayMac = gatewayMac;
    }
    
    @FieldInfo(name = "baseParam", type = "varbinary", explain = "基础参数")
	@Column(name = "baseParam",length=8000)
	private byte[] baseParam;

	@FieldInfo(name = "advParam", type = "varbinary", explain = "高级参数")
	@Column(name = "advParam",length=8000)
	private byte[] advParam;

	@FieldInfo(name = "netParam", type = "varbinary", explain = "网络参数")
	@Column(name = "netParam",length=8000)
	private byte[] netParam;

	@FieldInfo(name = "notes", type = "nvarchar(2000)", explain = "备注说明")
	@Column(name = "notes", columnDefinition = "nvarchar(2000) default ''", nullable = true)
	private String notes;
	
    @FieldInfo(name = "netGatewayIp", type = "varchar(50)", explain = "接入网关")
    @Column(name = "netGatewayIp",columnDefinition = "varchar(50) default ''", nullable = true)
    private String netGatewayIp;
	
	@FieldInfo(name = "gatewayStatus", type = "Integer", explain = "网关状态(1是启用 0是禁用)")//修正，数据弄反
	@Column(name = "gatewayStatus",columnDefinition = "default 0", nullable = true)
	private Integer gatewayStatus;

	@FieldInfo(name = "前置名称")
	@Formula("(SELECT A.frontServerName FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private String frontServerName;

	@FieldInfo(name = "前置IP")
	@Formula("(SELECT A.frontServerIp FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private String frontServerIP;

	@FieldInfo(name = "前置端口")
	@Formula("(SELECT A.frontServerPort FROM dbo.T_PT_FrontServer A WHERE A.frontServerId=frontServerId)")
	private Integer frontServerPort;
	
	@FieldInfo(name = "前置状态(1是启用 0是禁用)")//修正，数据弄反
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

	public String getGatewaySN() {
		return gatewaySN;
	}

	public void setGatewaySN(String gatewaySN) {
		this.gatewaySN = gatewaySN;
	}

	public String getGatewayIP() {
		return gatewayIP;
	}

	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
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

	public String getFrontServerId() {
		return frontServerId;
	}

	public void setFrontServerId(String frontServerId) {
		this.frontServerId = frontServerId;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}

	public String getNetGatewayIp() {
		return netGatewayIp;
	}

	public void setNetGatewayIp(String netGatewayIp) {
		this.netGatewayIp = netGatewayIp;
	}

}
