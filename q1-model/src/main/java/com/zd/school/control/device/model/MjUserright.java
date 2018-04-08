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
 * 门禁权限表（部分字段是否无用）
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_MJ_UserRight")
@AttributeOverride(name = "id", column = @Column(name = "userRightId", length = 20, nullable = false) )
public class MjUserright extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备主键Id", type = "varchar(20)", explain = "设备主键Id")
	@Column(name = "termId", length = 20, nullable = false)
	private String termId;

	@FieldInfo(name = "人员主键Id", type = "varchar(20)", explain = "人员主键Id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "卡流水号", type = "varchar(16) default ''", explain = "卡流水号")
	@Column(name = "cardId", columnDefinition = "varchar(16) default ''", nullable = true)
	private String cardId;

	@FieldInfo(name = "物理卡号", type = "varchar(16) default ''", explain = "物理卡号")
	@Column(name = "cardNo",  columnDefinition = "varchar(16) default ''", nullable = true)
	private String cardNo;

	@FieldInfo(name = "时段ID", type = "int default 0", explain = "时段ID")
	@Column(name = "controlsegId", columnDefinition = "int default 0", nullable = true)
	private Integer controlsegId;

	@FieldInfo(name = "卡片状态", type = "int default 0", explain = "卡片状态，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "cardStatusId", columnDefinition = "int default 0", nullable = true)
	private Integer cardStatusId;

	@FieldInfo(name = "是否下载", type = "bit default 0", explain = "是否下载（更新CardStatusID的同时更新此字段为False）")
	@Column(name = "isDownLoad", columnDefinition = "bit default 0", nullable = true)
	private Boolean isDownLoad;

	@FieldInfo(name = "数据状态", type = "int default 0", explain = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "int default 0", nullable = true)
	private Integer statusId;

	@FieldInfo(name = "卡片状态日期", type = "datetime", explain = "卡片状态日期，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "statusChangeTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date statusChangeTime;

	// @FieldInfo(name = "人员姓名")
	@Formula("(SELECT A.name FROM dbo.T_PT_User A WHERE A.userId=userId)")
	private String name;

	// @FieldInfo(name = "设备名称")
	@Formula("(SELECT A.deviceName FROM dbo.T_PT_Term A WHERE A.deviceId=deviceId)")
	private String deviceName;

	// @FieldInfo(name = "设备序列号")
	@Formula("(SELECT A.deviceSn FROM dbo.T_PT_Term A WHERE A.deviceId=deviceId)")
	private String deviceSN;

	// @FieldInfo(name = "房间名称")
	@Formula("(select a.roomName from T_PT_RoomInfo a where a.roomId=(select b.roomId from T_PT_Term b where b.deviceId=deviceId) )")
	private String roomName;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MjUserright() {
		super();
	}

	public MjUserright(String id) {
		super(id);
	}

}