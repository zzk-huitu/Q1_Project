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
 * 用户操作日志信息
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_LOG_UserOprate")
@AttributeOverride(name = "id", column = @Column(name = "userOprateId", length = 20, nullable = false) )
public class LogUserOprate extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.LOG;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "用户ID", type = "varchar(20) DEFAULT ''", explain = "当前操作的用户ID")
	@Column(name = "userId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String userId;

	@FieldInfo(name = "IP地址", type = "varchar(20) DEFAULT ''", explain = "操作用户登录的IP地址")
	@Column(name = "ipHost", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String ipHost;

	@FieldInfo(name = "方法名", type = "varchar(500) DEFAULT ''", explain = "用户操作的方法名")
	@Column(name = "methodName", columnDefinition = "varchar(500) DEFAULT ''", nullable = true)
	private String methodName;

	@FieldInfo(name = "参数", type = "nvarchar(MAX) DEFAULT ''", explain = "用户操作的参数")
	@Column(name = "methodParams", columnDefinition = "nvarchar(MAX) DEFAULT ''", nullable = true)
	private String methodParams;

	@FieldInfo(name = "返回结果", type = "nvarchar(MAX) DEFAULT ''", explain = "用户操作的返回结果")
	@Column(name = "methodResult", columnDefinition = "nvarchar(MAX) DEFAULT ''", nullable = true)
	private String methodResult;

	@FieldInfo(name = "异常类型", type = "nvarchar(100) DEFAULT ''", explain = "用户操作的异常类型")
	@Column(name = "exceptionClass", columnDefinition = "nvarchar(100) DEFAULT ''", nullable = true)
	private String exceptionClass;

	@FieldInfo(name = "异常信息", type = "nvarchar(MAX)", explain = "用户操作的的异常信息")
	@Column(name = "exceptionDetail", columnDefinition = "nvarchar(MAX) DEFAULT ''", nullable = true)
	private String exceptionDetail;

	@FieldInfo(name = "操作时间", type = "datetime", explain = "用户操作的的操作时间")
	@Column(name = "operateDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date operateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIpHost() {
		return ipHost;
	}

	public void setIpHost(String ipHost) {
		this.ipHost = ipHost;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams) {
		this.methodParams = methodParams;
	}

	public String getMethodResult() {
		return methodResult;
	}

	public void setMethodResult(String methodResult) {
		this.methodResult = methodResult;
	}

	public String getExceptionClass() {
		return exceptionClass;
	}

	public void setExceptionClass(String exceptionClass) {
		this.exceptionClass = exceptionClass;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public LogUserOprate() {
		super();
	}

	public LogUserOprate(String id) {
		super(id);
	}

}
