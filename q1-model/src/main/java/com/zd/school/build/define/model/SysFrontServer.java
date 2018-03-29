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
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_FrontServer")
@AttributeOverride(name = "frontServerId", column = @Column(name = "frontServerId", length = 36, nullable = false) )
public class SysFrontServer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "服务器名称")
	@Column(name = "frontServerName", length = 200, nullable = true)
	private String frontServerName;

	@FieldInfo(name = "服务IP")
	@Column(name = "frontServerIp", length = 200, nullable = true)
	private String frontServerIp;

	@FieldInfo(name = "服务端口")
	@Column(name = "frontServerPort")
	private Integer frontServerPort;

	@FieldInfo(name = "请求任务URL")
	@Column(name = "frontServerUrl", length = 100, nullable = true)
	private String frontServerUrl;

	@FieldInfo(name = "是否启用 0启用1禁用")
	@Column(name = "frontServerStatus")
	private Integer frontServerStatus;

	@FieldInfo(name = "备注")
	@Column(name = "frontServerNotes", length = 500, nullable = true)
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

	public String getFrontServerUrl() {
		return frontServerUrl;
	}

	public void setFrontServerUrl(String frontServerUrl) {
		this.frontServerUrl = frontServerUrl;
	}

	public Integer getFrontServerStatus() {
		return frontServerStatus;
	}

	public void setFrontServerStatus(Integer frontServerStatus) {
		this.frontServerStatus = frontServerStatus;
	}

	public String getFrontServerNotes() {
		return frontServerNotes;
	}

	public void setFrontServerNotes(String frontServerNotes) {
		this.frontServerNotes = frontServerNotes;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}