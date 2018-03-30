package com.zd.school.control.device.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;
import com.zd.core.util.DateTimeSerializer;

/**
 * 
 * ClassName: MjUserright Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 门禁权限表(PT_MJ_USERRIGHT)实体类. date: 2016-09-08
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_MJ_UserRight")
@AttributeOverride(name = "mjUserRightId", column = @Column(name = "mjUserRightId", length = 20, nullable = false))
public class MjUserright extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "deviceId",type="varchar(20)",explain="设备主键Id")
	@Column(name = "deviceId", length = 20, nullable = false)
	private String deviceId;

	@FieldInfo(name = "userId",type="varchar(20)",explain="人员主键Id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "cardId",type="varchar(8)",explain="卡流水号")
	@Column(name = "cardId", columnDefinition = "varchar(8) default ''", nullable = true)
	private String cardId;

	@FieldInfo(name = "cardserNo",type="varchar(8)",explain="物理卡号")
	@Column(name = "cardserNo", columnDefinition = "varchar(8) default ''", nullable = true)
	private String cardserNo;

	@FieldInfo(name = "controlsegId",type="Integer",explain="时段ID")
	@Column(name = "controlsegId")
	private Integer controlsegId;

	@FieldInfo(name = "cardStatusId",type="Integer",explain="卡片状态，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "cardStatusId")
	private Integer cardStatusId;

	@FieldInfo(name = "isDownLoad",type="Boolean",explain="是否下载（更新CardStatusID的同时更新此字段为False）d")
	@Column(name = "isDownLoad")
	private Boolean isDownLoad;

	@FieldInfo(name = "statusId",type="Integer",explain="数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "default 0", nullable = true)
	private Integer statusId;

	@FieldInfo(name = "statusChangeTime",type="varchar(20)",explain="卡片状态日期，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "statusChangeTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date statusChangeTime;

	@FieldInfo(name = "人员姓名")
	@Formula("(SELECT A.name FROM dbo.T_PT_User A WHERE A.userId=userId)")
	private String name;

	@FieldInfo(name = "设备名称")
	@Formula("(SELECT A.deviceName FROM dbo.T_PT_Term A WHERE A.deviceId=deviceId)")
	private String deviceName;

	@FieldInfo(name = "设备序列号")
	@Formula("(SELECT A.deviceSn FROM dbo.T_PT_Term A WHERE A.deviceId=deviceId)")
	private String deviceSN;

	@FieldInfo(name = "房间名称")
	@Formula("(select a.roomName from T_PT_RoomInfo a where a.roomId=(select b.roomId from T_PT_Term b where b.deviceId=deviceId) )")
	private String roomName;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardserNo() {
		return cardserNo;
	}

	public void setCardserNo(String cardserNo) {
		this.cardserNo = cardserNo;
	}

	public Integer getControlsegId() {
		return controlsegId;
	}

	public void setControlsegId(Integer controlsegId) {
		this.controlsegId = controlsegId;
	}

	public Integer getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(Integer cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public Boolean getIsDownLoad() {
		return isDownLoad;
	}

	public void setIsDownLoad(Boolean isDownLoad) {
		this.isDownLoad = isDownLoad;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Date getStatusChangeTime() {
		return statusChangeTime;
	}

	public void setStatusChangeTime(Date statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceSN() {
		return deviceSN;
	}

	public void setDeviceSN(String deviceSN) {
		this.deviceSN = deviceSN;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}