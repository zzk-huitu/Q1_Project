package com.zd.school.plartform.system.model;


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
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: SysUserLoginLog 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选). 
 * Description: 用户登录日志信息(Sys_T_User_LoginLog)实体类.
 * date: 2017-03-07
 *
 * @author  zzk 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
 
@Entity
@Table(name = "T_PT_UserLoginLog")
@AttributeOverride(name = "userLoginLogId", column = @Column(name = "userLoginLogId", length = 20, nullable = false))
public class SysUserLoginLog extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @FieldInfo(name = "用户ID")
    @Column(name = "userId", columnDefinition="varchar(20)", nullable = false)
    private String userId;
       
    @FieldInfo(name = "会话ID（用于区别同一个用户在不同时刻的会话）")
    @Column(name = "sessionId", columnDefinition="varchar(36)", nullable = false)
    private String sessionId;
  
    @FieldInfo(name = "用户名")
    @Column(name = "userName", columnDefinition="nvarchar(36)", nullable = false)
    private String userName;
   
    @FieldInfo(name = "IP地址")
    @Column(name = "ipHost", columnDefinition="varchar(64) defalut ''", nullable = true)
    private String ipHost;
    
    @FieldInfo(name = "登录时间")
    @Column(name = "loginDate", columnDefinition="datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date loginDate;
        
    @FieldInfo(name = "最后访问时间")
    @Column(name = "LastAccessDate",columnDefinition="datetime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date LastAccessDate;
    
    @FieldInfo(name = "离线时间")
    @Column(name = "offlineDate", columnDefinition="datetime", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=DateTimeSerializer.class)
    private Date offlineDate;
        
    
    @FieldInfo(name = "登出说明")
    @Column(name = "offlineIntro", columnDefinition="nvarchar(32) defalut ''", nullable = true)
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

	public SysUserLoginLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysUserLoginLog(String uuid) {
		super(uuid);
		// TODO Auto-generated constructor stub
	}

	
	
}