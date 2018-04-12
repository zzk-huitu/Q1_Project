package com.yc.q1.model.base.pt.device;

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
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 房间钱包规则
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_RoomBagRule")
@AttributeOverride(name = "id", column = @Column(name = "roomBagRuleId", length = 20, nullable = false) )
public class PtRoomBagRule extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "规则名称", type = "nvarchar(20) NOT NULL", explain = "房间钱包的规则名称")
	@Column(name = "roomBagRuleName", columnDefinition = "nvarchar(20)", nullable = false)
	private String roomRuleName;

	@FieldInfo(name = "扣费模式", type = "varchar(1) NOT NULL", explain = "扣费模式（0：不扣费，1：平均扣费，2：指定扣费）")
	@Column(name = "deductionMode", length = 1, nullable = false)
	private String deductionMode;

	@FieldInfo(name = "扣费金额", type = "decimal NOT NUL", explain = "扣费金额（每次扣费的金额）")
	@Column(name = "deductionValue", nullable = false)
	private BigDecimal deductionValue;

	@FieldInfo(name = "无余额控制方式", type = "varchar(1) NOT NULL", explain = "无余额控制方式（1：不许使用，2：继续使用）")
	@Column(name = "noMoneyMode", length = 1, nullable = false)
	private String noMoneyMode;

	@FieldInfo(name = "报警金额", type = "decimal  NOT NULL", explain = "报警金额（低于此金额后开始尝试扣费）")
	@Column(name = "warnValue", nullable = false)
	private BigDecimal warnValue;

	@FieldInfo(name = "允许关电开始时间", type = "time(0) NOT NULL", explain = "允许关电开始时间")
	@Column(name = "allowOffStartTime", columnDefinition = "time(0)",nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date allowOffStartTime;

	@FieldInfo(name = "允许关电结束时间", type = "time(0) NOT NULL", explain = "允许关电结束时间")
	@Column(name = "allowOffEndTime", columnDefinition = "time(0)" ,nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date allowOffEndTime;

	@FieldInfo(name = "是否启用", type = "bit NOT NULL default 0", explain = "是否启用：0-不启用，1-启用")
	@Column(name = "isEnable", columnDefinition = "bit default 0", nullable = false)
	private Boolean isEnable;

	public String getRoomRuleName() {
		return roomRuleName;
	}

	public void setRoomRuleName(String roomRuleName) {
		this.roomRuleName = roomRuleName;
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

	public String getNoMoneyMode() {
		return noMoneyMode;
	}

	public void setNoMoneyMode(String noMoneyMode) {
		this.noMoneyMode = noMoneyMode;
	}

	public BigDecimal getWarnValue() {
		return warnValue;
	}

	public void setWarnValue(BigDecimal warnValue) {
		this.warnValue = warnValue;
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

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public PtRoomBagRule() {
		super();
	}

	public PtRoomBagRule(String id) {
		super(id);
	}

}