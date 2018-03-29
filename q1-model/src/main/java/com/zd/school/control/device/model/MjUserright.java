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

	@FieldInfo(name = "设备主键")
	@Column(name = "termId", length = 20, nullable = false)
	private String termId;

	@FieldInfo(name = "人员主键")
	@Column(name = "stuId", length = 20, nullable = false)
	private String stuId;

	@FieldInfo(name = "卡流水号")
	@Column(name = "cardId", columnDefinition = "varchar(8) default ''", nullable = true)
	private String cardId;

	@FieldInfo(name = "物理卡号")
	@Column(name = "cardserNo", columnDefinition = "varchar(8) default ''", nullable = true)
	private String cardserNo;

	@FieldInfo(name = "时段ID")
	@Column(name = "controlsegId")
	private Integer controlsegId;

	@FieldInfo(name = "卡片状态，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "cardStatusId")
	private Integer cardStatusId;

	@FieldInfo(name = "是否下载（更新CardStatusID的同时更新此字段为False）")
	@Column(name = "isDownLoad")
	private Boolean isDownLoad;

	@FieldInfo(name = "数据状态对应数据字典（0正常，1	删除，2无效，3过期，4历史）")
	@Column(name = "statusId", columnDefinition = "default 0", nullable = true)
	private Integer statusId;

	@FieldInfo(name = "卡片状态日期，在卡片挂失、解挂、换卡、补卡、退卡、销户等操作时更新")
	@Column(name = "statusChangeTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date statusChangeTime;

	@FieldInfo(name = "人员姓名")
	@Formula("(SELECT A.XM FROM dbo.SYS_T_USER A WHERE A.USER_ID=STU_ID)")
	private String xm;

	@FieldInfo(name = "设备名称")
	@Formula("(SELECT A.TERMNAME FROM dbo.PT_TERM A WHERE A.TERM_ID=TERM_ID)")
	private String termName;

	@FieldInfo(name = "设备序列号")
	@Formula("(SELECT A.TERMSN FROM dbo.PT_TERM A WHERE A.TERM_ID=TERM_ID)")
	private String termSN;

	@FieldInfo(name = "房间名称")
	@Formula("(select a.ROOM_NAME from BUILD_T_ROOMINFO a where a.ROOM_ID=(select b.ROOM_ID from PT_TERM b where b.TERM_ID=TERM_ID) )")
	private String roomName;

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getTermId() {
		return termId;
	}

	public String getTermName() {
		return termName;
	}

	public String getTermSN() {
		return termSN;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public void setTermSN(String termSN) {
		this.termSN = termSN;
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