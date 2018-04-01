package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 综合前置服务器
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_FrontServer")
@AttributeOverride(name = "id", column = @Column(name = "frontServerId", length = 20, nullable = false) )
public class SysFrontServer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "服务器名称", type = "nvarchar(20) NOT NULL", explain = "服务器名称")
	@Column(name = "frontServerName", columnDefinition = "nvarchar(20)", nullable = false)
	private String frontServerName;

	@FieldInfo(name = "服务IP", type = "varchar(20) NOT NULL", explain = "服务IP")
	@Column(name = "frontServerIp", length = 20, nullable = false)
	private String frontServerIp;

	@FieldInfo(name = "服务端口", type = "int NOT NULL", explain = "服务端口")
	@Column(name = "frontServerPort", nullable = false)
	private Integer frontServerPort;

	@FieldInfo(name = "是否启用", type = "bit NOT NULL default 0", explain = "是否启用 0禁用，1启用")
	@Column(name = "frontServerStatus", columnDefinition = "default 0", nullable = false)
	private Boolean frontServerStatus;

	@FieldInfo(name = "请求任务URL", type = "varchar(50) default ''", explain = "请求任务URL")
	@Column(name = "frontServerUrl", length = 50, columnDefinition = "default ''", nullable = true)
	private String frontServerUrl;

	@FieldInfo(name = "备注", type = "nvarchar(128) default ''", explain = "备注")
	@Column(name = "frontServerNotes", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String frontServerNotes;

	public String getFrontServerName() {
		return frontServerName;
	}

	public void setFrontServerName(String frontServerName) {
		this.frontServerName = frontServerName;
	}

	public String getFrontServerIp() {
		return frontServerIp;
	}

	public void setFrontServerIp(String frontServerIp) {
		this.frontServerIp = frontServerIp;
	}

	public Integer getFrontServerPort() {
		return frontServerPort;
	}

	public void setFrontServerPort(Integer frontServerPort) {
		this.frontServerPort = frontServerPort;
	}

	public Boolean getFrontServerStatus() {
		return frontServerStatus;
	}

	public void setFrontServerStatus(Boolean frontServerStatus) {
		this.frontServerStatus = frontServerStatus;
	}

	public String getFrontServerUrl() {
		return frontServerUrl;
	}

	public void setFrontServerUrl(String frontServerUrl) {
		this.frontServerUrl = frontServerUrl;
	}

	public String getFrontServerNotes() {
		return frontServerNotes;
	}

	public void setFrontServerNotes(String frontServerNotes) {
		this.frontServerNotes = frontServerNotes;
	}

	public SysFrontServer() {
		super();
	}

	public SysFrontServer(String id) {
		super(id);
	}

}