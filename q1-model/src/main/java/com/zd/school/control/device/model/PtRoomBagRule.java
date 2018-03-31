package com.zd.school.control.device.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * 房间钱包规则
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_RoomBagsRule")
@AttributeOverride(name = "roomBagsRuleId", column = @Column(name = "roomBagsRuleId", length = 20, nullable = false))
public class PtRoomBagRule extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "规则名称",type="nvarchar(40)",explain="房间钱包的规则名称")
	@Column(name = "roomRuleName", columnDefinition = "nvarchar(40) default ''", nullable = true)
	private String roomRuleName;

	@FieldInfo(name = "允许关电开始时间",type="datetime",explain="允许关电开始时间")
	@Column(name = "allowOffStartTime", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date allowOffStartTime;

	@FieldInfo(name = "允许关电结束时间",type="datetime",explain="允许关电结束时间")
	@Column(name = "allowOffEndTime", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date allowOffEndTime;


	@FieldInfo(name = "无余额控制方式（1：不许使用，2：继续使用）",type="varchar(2)",explain="无余额控制方式")
	@Column(name = "noMoneyMode",columnDefinition = "varchar(2) default ''",nullable=true)
	private String noMoneyMode;

	@FieldInfo(name = "报警金额（低于此金额后开始尝试扣费）",type="BigDecimal",explain="报警金额")
	@Column(name = "warnValue")
	private BigDecimal warnValue;

	@FieldInfo(name = "扣费模式（0：不扣费，1：平均扣费，2：指定扣费）",type="varchar(2)",explain="扣费模式")
	@Column(name = "deductionMode",columnDefinition = "varchar(2) default ''",nullable=true)
	private String deductionMode;

	@FieldInfo(name = "扣费金额（每次扣费的总额）",type="BigDecimal",explain="扣费金额")
	@Column(name = "deductionValue")
	private BigDecimal deductionValue;

	@FieldInfo(name = "是否启用",type="boolean",explain="是否启用")
	@Column(name = "isEnable",columnDefinition = "default 0",nullable=true)
	private boolean isEnable;

	public String getRoomRuleName() {
		return roomRuleName;
	}

	public void setRoomRuleName(String roomRuleName) {
		this.roomRuleName = roomRuleName;
	}

	public String getNoMoneyMode() {
		return noMoneyMode;
	}
	
	public Date getAllowOffStartTime() {
		return allowOffStartTime;
	}

	public void setAllowOffStartTime(Date allowOffStartTime) {
		this.allowOffStartTime = allowOffStartTime;
	}

	public Date getAllowOffEndTime() {
		return allowOffEndTime;
	}

	public void setAllowOffEndTime(Date allowOffEndTime) {
		this.allowOffEndTime = allowOffEndTime;
	}

	public BigDecimal getWarnValue() {
		return warnValue;
	}

	public void setWarnValue(BigDecimal warnValue) {
		this.warnValue = warnValue;
	}

	public String getDeductionMode() {
		return deductionMode;
	}

	public void setDeductionMode(String deductionMode) {
		this.deductionMode = deductionMode;
	}

	public BigDecimal getDeductionValue() {
		return deductionValue;
	}

	public void setDeductionValue(BigDecimal deductionValue) {
		this.deductionValue = deductionValue;
	}

	public void setNoMoneyMode(String noMoneyMode) {
		this.noMoneyMode = noMoneyMode;
	}

	public boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}