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

	@FieldInfo(name = "机号")
	@Column(name = "gatewayNo")
	private Integer gatewayNo;

	@FieldInfo(name = "网关名称")
	@Column(name = "gatewayName", length = 200, nullable = true)
	private String gatewayName;
	
	@FieldInfo(name = "前置表ID")
	@Column(name = "frontServerId", length = 36, nullable = true)
	private String frontServerId;
	
	@FieldInfo(name = "序列号")
	@Column(name = "gatewaySN", length = 200, nullable = true)
	private String gatewaySN;

	@FieldInfo(name = "硬件程序版本号")
	@Column(name = "programVersion", length = 8, nullable = true)
	private String programVersion;

	@FieldInfo(name = "网关Ip")
	@Column(name = "gatewayIp", length = 100, nullable = true)
	private String gatewayIP;

	@FieldInfo(name = "设备掩码")
    @Column(name = "gatewayMask",length = 36,nullable = true)
    private String gatewayMask;

    public String getGatewayMask() {
        return gatewayMask;
    }

    public void setGatewayMask(String gatewayMask) {
        this.gatewayMask = gatewayMask;
    }
    @FieldInfo(name = "设备MAC")
    @Column(name = "gatewayMac",length = 36,nullable = true)
    private String gatewayMac;

    public String getGatewayMac() {
        return gatewayMac;
    }

    public void setGatewayMac(String gatewayMac) {
        this.gatewayMac = gatewayMac;
    }
    @FieldInfo(name = "接入网关")
    @Column(name = "netGatewayIp")
    private String netGatewayIp;
	
	@FieldInfo(name = "网关状态(1是启用 0是禁用)")//修正，数据弄反
	@Column(name = "gatewayStatus")
	private Integer gatewayStatus;

	@FieldInfo(name = "前置名称")
	@Formula("(SELECT A.FRONTSERVER_NAME FROM dbo.SYS_FRONTSERVER A WHERE A.FRONTSERVER_ID=FRONTSERVER_ID)")
	private String frontServerName;

	@FieldInfo(name = "前置IP")
	@Formula("(SELECT A.FRONTSERVER_IP FROM dbo.SYS_FRONTSERVER A WHERE A.FRONTSERVER_ID=FRONTSERVER_ID)")
	private String frontServerIP;

	@FieldInfo(name = "前置端口")
	@Formula("(SELECT A.FRONTSERVER_PORT FROM dbo.SYS_FRONTSERVER A WHERE A.FRONTSERVER_ID=FRONTSERVER_ID)")
	private Integer frontServerPort;
	
	@FieldInfo(name = "前置状态(1是启用 0是禁用)")//修正，数据弄反
	@Formula("(SELECT A.FRONTSERVER_STATUS FROM dbo.SYS_FRONTSERVER A WHERE A.FRONTSERVER_ID=FRONTSERVER_ID)")
	private Integer frontServerStatus;
	
	@FieldInfo(name = "基础参数")
	@Column(name = "BASEPARAM",length=8000)
	private byte[] baseParam;

	@FieldInfo(name = "高级参数")
	@Column(name = "ADVPARAM",length=8000)
	private byte[] advParam;

	@FieldInfo(name = "网络参数")
	@Column(name = "NETPARAM",length=8000)
	private byte[] netParam;

	@FieldInfo(name = "备注说明")
	@Column(name = "NOTES", length = 2000, nullable = true)
	private String notes;

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
