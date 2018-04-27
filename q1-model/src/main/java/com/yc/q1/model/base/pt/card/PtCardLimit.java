package com.yc.q1.model.base.pt.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.util.DateTimeSerializer;

@Entity
@Table(name = "T_PT_CardLimit")
public class PtCardLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@FieldInfo(name = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint", nullable = false)
	private Long cardNo;

	@FieldInfo(name = "每天限制消费次数")
	@Column(name = "dayLimitCount", columnDefinition = "smallint", nullable = false)
	private Integer dayLimitCount;

	@FieldInfo(name = "每天限制消费金额")
	@Column(name = "dayLimitValue", columnDefinition = "decimal(18,2)", nullable = false)
	private BigDecimal dayLimitValue;

	@FieldInfo(name = "每餐限制消费次数")
	@Column(name = "mealLimitCount", columnDefinition = "smallint", nullable = false)
	private Integer mealLimitCount;

	@FieldInfo(name = "每餐限制消费金额")
	@Column(name = "mealLimitValue", columnDefinition = "decimal(18,2)", nullable = false)
	private BigDecimal mealLimitValue;

	@FieldInfo(name = "限制消费次数")
	@Column(name = "zkLimitCount", columnDefinition = "smallint", nullable = false)
	private Integer zkLimitCount;

	@FieldInfo(name = "？？限制消费金额")
	@Column(name = "zkLimitValue", columnDefinition = "decimal(18,2)", nullable = false)
	private BigDecimal zkLimitValue;

	@FieldInfo(name = "？？最后交易时间", type = "datetime", explain = "最后交易时间")
	@Column(name = "lastPayDate", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date lastPayDate;

	@FieldInfo(name = "最后交易餐类", explain = "最后交易餐类，对应营业时段的编号")
	@Column(name = "lastPayMealType", columnDefinition = "smallint", nullable = false)
	private Integer lastPayMealType;

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getDayLimitCount() {
		return dayLimitCount;
	}

	public void setDayLimitCount(Integer dayLimitCount) {
		this.dayLimitCount = dayLimitCount;
	}

	public BigDecimal getDayLimitValue() {
		return dayLimitValue;
	}

	public void setDayLimitValue(BigDecimal dayLimitValue) {
		this.dayLimitValue = dayLimitValue;
	}

	public Integer getMealLimitCount() {
		return mealLimitCount;
	}

	public void setMealLimitCount(Integer mealLimitCount) {
		this.mealLimitCount = mealLimitCount;
	}

	public BigDecimal getMealLimitValue() {
		return mealLimitValue;
	}

	public void setMealLimitValue(BigDecimal mealLimitValue) {
		this.mealLimitValue = mealLimitValue;
	}

	public Integer getZkLimitCount() {
		return zkLimitCount;
	}

	public void setZkLimitCount(Integer zkLimitCount) {
		this.zkLimitCount = zkLimitCount;
	}

	public BigDecimal getZkLimitValue() {
		return zkLimitValue;
	}

	public void setZkLimitValue(BigDecimal zkLimitValue) {
		this.zkLimitValue = zkLimitValue;
	}

	public Date getLastPayDate() {
		return lastPayDate;
	}

	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	public Integer getLastPayMealType() {
		return lastPayMealType;
	}

	public void setLastPayMealType(Integer lastPayMealType) {
		this.lastPayMealType = lastPayMealType;
	}

}
