package com.zd.school.ykt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

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
@AttributeOverride(name = "workStationId", column = @Column(name = "workStationId", length = 36, nullable = false) )
public class TcWorkstation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "workStationNo")
	@Column(name = "workStationNo", length = 5, updatable = false, insertable = false, columnDefinition = " smallint  IDENTITY(1,1) NOT NULL")
	private Short workStationNo;

	@FieldInfo(name = "adminId")
	@Column(name = "adminId", length = 10, nullable = true)
	private Integer adminId;

	@FieldInfo(name = "workStationName")
	@Column(name = "workStationName", length = 30, nullable = true)
	private String workStationName;

	@FieldInfo(name = "workStationIp")
	@Column(name = "workStationIp", length = 100, nullable = true)
	private String workStationIp;

	@FieldInfo(name = "nic")
	@Column(name = "nic", length = 100, nullable = true)
	private String nic;

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getNic() {
		return nic;
	}

	@FieldInfo(name = "computerName")
	@Column(name = "computerName", length = 50, nullable = true)
	private String computerName;

	@FieldInfo(name = "availablePort")
	@Column(name = "availablePort", length = 100, nullable = true)
	private String availablePort;

	@FieldInfo(name = "onLine")
	@Column(name = "onLine", length = 1, nullable = true)
	private Boolean onLine;

	@FieldInfo(name = "msServerPort")
	@Column(name = "msServerPort", length = 10, nullable = true)
	private Integer msServerPort;

	@FieldInfo(name = "~工作站描述")
	@Column(name = "workStationnNotes", length = 200, nullable = true)
	private String workStationnNotes;

	@FieldInfo(name = "端口号")
	@Column(name = "commPort", length = 10, nullable = true)
	private Integer commPort;

	@FieldInfo(name = "最大金额")
	@Column(name = "maxCarFree", length = 19, nullable = true)
	private BigDecimal maxCarFree;

	public Short getWorkStationNo() {
		return workStationNo;
	}

	public void setWorkStationNo(Short workStationNo) {
		this.workStationNo = workStationNo;
	}

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

	public String getWorkStationIp() {
		return workStationIp;
	}

	public void setWorkStationIp(String workStationIp) {
		this.workStationIp = workStationIp;
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

	public String getWorkStationnNotes() {
		return workStationnNotes;
	}

	public void setWorkStationnNotes(String workStationnNotes) {
		this.workStationnNotes = workStationnNotes;
	}

	public Integer getCommPort() {
		return commPort;
	}

	public void setCommPort(Integer commPort) {
		this.commPort = commPort;
	}

	public BigDecimal getMaxCarFree() {
		return maxCarFree;
	}

	public void setMaxCarFree(BigDecimal maxCarFree) {
		this.maxCarFree = maxCarFree;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}