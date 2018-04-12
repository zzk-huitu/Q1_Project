package com.yc.q1.model.storage.pt;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 任务明细
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Task")
@AttributeOverride(name = "id", column = @Column(name = "taskId", length = 20, nullable = false) )
public class PtTask extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "任务编号", type = "varchar(8) NOT NULL", explain = "任务编号")
	@Column(name = "taskNo", length = 8, nullable = false)
	private String taskNo;

	@FieldInfo(name = "任务年月日", type = "varchar(8) NOT NULL", explain = "任务年月日")
	@Column(name = "taskDate", length = 8, nullable = false)
	private String taskDate;

	@FieldInfo(name = "任务类型", type = "varchar(4) NOT NULL", explain = "任务类型")
	@Column(name = "taskType", nullable = false)
	private String taskType;

	@FieldInfo(name = "设备类型", type = "varchar(4) NOT NULL", explain = "设备类型")
	@Column(name = "termType", nullable = false)
	private String termType;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "任务编号")
	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@FieldInfo(name = "任务数据", type = "varbinary(255)", explain = "任务数据")
	@Column(name = "taskData", nullable = true)
	private byte[] taskData;

	@FieldInfo(name = "超时时间", type = "int NOT NULL", explain = "超时时间")
	@Column(name = "timeOut", nullable = false)
	private Integer timeOut;

	@FieldInfo(name = "重试次数", type = "int NOT NULL", explain = "重试次数")
	@Column(name = "retryCount", nullable = false)
	private Integer retryCount;

	@FieldInfo(name = "应答间隔", type = "int DEFAULT 0", explain = "应答间隔")
	@Column(name = "tickSecend", columnDefinition = "int DEFAULT 0", nullable = true)
	private Integer tickSecond;

	@FieldInfo(name = "执行次数", type = "int NOT NULL", explain = "执行次数")
	@Column(name = "executeCount", nullable = false)
	private Integer executeCount;

	@FieldInfo(name = "执行时间", type = "datetime", explain = "执行时间")
	@Column(name = "executeTime", columnDefinition = "datetime", nullable = true)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date executeTime;

	@FieldInfo(name = "执行结果", type = "bit DEFAULT 0", explain = "执行结果")
	@Column(name = "executeResult", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean executeResult;

	@FieldInfo(name = "立刻执行", type = "bit DEFAULT 0", explain = "立刻执行")
	@Column(name = "executeImmediately", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean executeImmediately;

	@FieldInfo(name = "任务是否结束", type = "bit DEFAULT 0", explain = "任务是否结束")
	@Column(name = "isTaskOver", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean isTaskOver;

	@FieldInfo(name = "结果信息", type = "varchar(1000) DEFAULT ''", explain = "结果信息")
	@Column(name = "resultMsg", columnDefinition = "varchar(1000) DEFAULT ''", nullable = true)
	private String resultMsg;

	@FieldInfo(name = "用户id", type = "varchar(20) DEFAULT ''", explain = "用户id")
	@Column(name = "userId", columnDefinition = "varchar(20) DEFAULT ''", nullable = true)
	private String userId;

	@Formula("(SELECT A.termName FROM dbo.T_PT_Term A WHERE A.termSn=termSn)")
	@FieldInfo(name = "设备名称")
	private String termName;

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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
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

	public Integer getTickSecond() {
		return tickSecond;
	}

	public void setTickSecond(Integer tickSecond) {
		this.tickSecond = tickSecond;
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

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public PtTask() {
		super();
	}

	public PtTask(String id) {
		super(id);
	}

}
