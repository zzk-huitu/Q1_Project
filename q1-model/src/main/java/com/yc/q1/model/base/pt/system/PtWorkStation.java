package com.yc.q1.model.base.pt.system;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 工作站管理 ClassName: TcWorkstation Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: (TC_WorkStation)实体类. date: 2017-03-21
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_WorkStation")
@AttributeOverride(name = "id", column = @Column(name = "workStationId", length = 20, nullable = false) )
public class PtWorkStation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "图书编码")
	@Column(name = "adminId", columnDefinition = "int default 0", nullable = true)
	private Integer adminId;

	@FieldInfo(name = "工作站名称")
	@Column(name = "workStationName", columnDefinition = "nvarchar(30) default ''", nullable = true)
	private String workStationName;

	@FieldInfo(name = "工作站IP")
	@Column(name = "workStationIP", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String workStationIP;

	@FieldInfo(name = "MAC地址")
	@Column(name = "nic", columnDefinition = "varchar(100) default ''", nullable = true)
	private String nic;

	@FieldInfo(name = "计算机名称")
	@Column(name = "computerName", columnDefinition = "varchar(50) default ''", nullable = true)
	private String computerName;

	@FieldInfo(name = "可用串口")
	@Column(name = "availablePort", columnDefinition = "varchar(100) default ''", nullable = true)
	private String availablePort;

	@FieldInfo(name = "是否在线", explain = "1在线 0不在线")
	@Column(name = "onLine", columnDefinition = "bit default 0", nullable = true)
	private Boolean onLine;

	@FieldInfo(name = "服务器端口")
	@Column(name = "msServerPort", columnDefinition = "int default 0", nullable = true)
	private Integer msServerPort;

	@FieldInfo(name = "工作站描述")
	@Column(name = "workStationNotes", columnDefinition = "nvarchar(200) default ''", nullable = true)
	private String workStationNotes;

	@FieldInfo(name = "服务端口")
	@Column(name = "commPort", columnDefinition = "int default 0", nullable = true)
	private Integer commPort;

	@FieldInfo(name = "最大充值额度")
	@Column(name = "maxCardFree", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal maxCardFree;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getWorkStationName() {
		return workStationName;
	}

	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}

	public String getWorkStationIP() {
		return workStationIP;
	}

	public void setWorkStationIP(String workStationIP) {
		this.workStationIP = workStationIP;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getAvailablePort() {
		return availablePort;
	}

	public void setAvailablePort(String availablePort) {
		this.availablePort = availablePort;
	}

	public Boolean getOnLine() {
		return onLine;
	}

	public void setOnLine(Boolean onLine) {
		this.onLine = onLine;
	}

	public Integer getMsServerPort() {
		return msServerPort;
	}

	public void setMsServerPort(Integer msServerPort) {
		this.msServerPort = msServerPort;
	}

	public String getWorkStationNotes() {
		return workStationNotes;
	}

	public void setWorkStationNotes(String workStationNotes) {
		this.workStationNotes = workStationNotes;
	}

	public Integer getCommPort() {
		return commPort;
	}

	public void setCommPort(Integer commPort) {
		this.commPort = commPort;
	}

	public BigDecimal getMaxCardFree() {
		return maxCardFree;
	}

	public void setMaxCardFree(BigDecimal maxCardFree) {
		this.maxCardFree = maxCardFree;
	}

	public PtWorkStation() {
		super();
	}

	public PtWorkStation(String id) {
		super(id);
	}
}