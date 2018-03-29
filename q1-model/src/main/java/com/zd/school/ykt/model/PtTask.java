package com.zd.school.ykt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * Function: TODO ADD FUNCTION. Reason: TODO ADD REASON(可选). Description:
 * (PT_TASK)实体类. date: 2017-05-16.
 * 
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_Task")
@AttributeOverride(name = "taskId", column = @Column(name = "taskId", length = 20, nullable = false) )
public class PtTask extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "taskNo", length = 8, nullable = false)
	private String taskNo;

	@Column(name = "taskDate", length = 8, nullable = false)
	private String taskDate;

	@Column(name = "taskType", nullable = false)
	private Integer taskType;

	@Column(name = "deviceType", nullable = false)
	private Integer deviceType;

	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@Column(name = "taskData", nullable = true)
	private byte[] taskData;

	@Column(name = "timeOut", nullable = false)
	private Integer timeOut;

	@Column(name = "retryCount", nullable = false)
	private Integer retryCount;

	@Column(name = "tickSecend", columnDefinition="defalut 0",nullable = true)
	private Integer tickSecend;

	@Column(name = "executeCount", nullable = false)
	private Integer executeCount;

	@Column(name = "executeTime", columnDefinition="datetime",nullable = true)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date executeTime;

	@Column(name = "executeResult", columnDefinition="defalut 0",nullable = true)
	private Boolean executeResult;

	@Column(name = "executeImmediately",columnDefinition="defalut 0", nullable = true)
	private Boolean executeImmediately;

	@Column(name = "isTaskOver",columnDefinition="defalut 0", nullable = true)
	private Boolean isTaskOver;

	@Column(name = "resultMsg", columnDefinition="varchar(1000) defalut ''", nullable = true)
	private String resultMsg;

	@Column(name = "userId", columnDefinition="varchar(20) defalut ''", nullable = true)
	private String userId;

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public byte[] getTaskData() {
		return taskData;
	}

	public void setTaskData(byte[] taskData) {
		this.taskData = taskData;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Integer getTickSecend() {
		return tickSecend;
	}

	public void setTickSecend(Integer tickSecend) {
		this.tickSecend = tickSecend;
	}

	public Integer getExecuteCount() {
		return executeCount;
	}

	public void setExecuteCount(Integer executeCount) {
		this.executeCount = executeCount;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public Boolean getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(Boolean executeResult) {
		this.executeResult = executeResult;
	}

	public Boolean getExecuteImmediately() {
		return executeImmediately;
	}

	public void setExecuteImmediately(Boolean executeImmediately) {
		this.executeImmediately = executeImmediately;
	}

	public Boolean getIsTaskOver() {
		return isTaskOver;
	}

	public void setIsTaskOver(Boolean isTaskOver) {
		this.isTaskOver = isTaskOver;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Formula("(SELECT A.TERMNAME FROM dbo.PT_TERM A WHERE A.TERMSN=TERMSN)")
	@FieldInfo(name = "设备名称")
	private String termName;

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}
