package com.yc.q1.model.storage.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.constant.ModuleNumType;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 用户登录日志
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_LOG_UserLoginLog")
@AttributeOverride(name = "id", column = @Column(name = "loginLogId", length = 20, nullable = false) )
public class UserLoginLog extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.LOG;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "用户ID", type = "varchar(20) NOT NULL", explain = "当前登录用户的id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "会话ID", type = "varchar(36) NOT NULL", explain = "当前登录用户的会话ID（用于区别同一个用户在不同时刻的会话）")
	@Column(name = "sessionId", length = 36, nullable = false)
	private String sessionId;

	@FieldInfo(name = "用户名", type = "varchar(16) NOT NULL", explain = "当前登录用户的用户名")
	@Column(name = "userName", columnDefinition = "varchar(16)", nullable = false)
	private String userName;

	@FieldInfo(name = "IP地址", type = "varchar(20) DEFAULT ''", explain = "当前登录用户的IP地址")
	@Column(name = "ipHost", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String ipHost;

	@FieldInfo(name = "登录时间", type = "datetime NOT NULL", explain = "当前用户的登录时间")
	@Column(name = "loginDate", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date loginDate;

	@FieldInfo(name = "最后访问时间", type = "datetime", explain = "当前登录用户的最后访问时间")
	@Column(name = "LastAccessDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date LastAccessDate;

	@FieldInfo(name = "离线时间", type = "datetime", explain = "当前登录用户的离线时间")
	@Column(name = "offlineDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date offlineDate;

	@FieldInfo(name = "登出说明", type = "nvarchar(32)  DEFAULT ''", explain = "当前登录用户的退出描述")
	@Column(name = "offlineIntro", columnDefinition = "nvarchar(32) DEFAULT ''", nullable = true)
	private String offlineIntro;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpHost() {
		return ipHost;
	}

	public void setIpHost(String ipHost) {
		this.ipHost = ipHost;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLastAccessDate() {
		return LastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		LastAccessDate = lastAccessDate;
	}

	public Date getOfflineDate() {
		return offlineDate;
	}

	public void setOfflineDate(Date offlineDate) {
		this.offlineDate = offlineDate;
	}

	public String getOfflineIntro() {
		return offlineIntro;
	}

	public void setOfflineIntro(String offlineIntro) {
		this.offlineIntro = offlineIntro;
	}

	public UserLoginLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginLog(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

}