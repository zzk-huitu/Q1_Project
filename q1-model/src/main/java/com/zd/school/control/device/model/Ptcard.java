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
 * 发卡表
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_Card")
@AttributeOverride(name = "cardId", column = @Column(name = "cardId", length = 20, nullable = false))
public class Ptcard extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "cardNo",type="Bigint",explain="卡流水号")
	@Column(name = "cardNo", nullable = false)
	private Long cardNo;

	@FieldInfo(name = "userId",type="varchar(20)",explain="关联user表")
	@Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String userId;

	@FieldInfo(name = "factoryFixId",type="Bigint",explain="物理卡号")
	@Column(name = "factoryFixId",columnDefinition = "default 0", nullable = true)
	private Long factoryFixId;

	@FieldInfo(name = "cardTypeId",type="Integer",explain="卡类型")
	@Column(name = "cardTypeId",nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "expiryDate",type="datetime",explain="有效期")
	@Column(name = "expiryDate",columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date expiryDate = new Date();

	@FieldInfo(name = "deposit",type="BigDecimal",explain="卡押金")
	@Column(name = "deposit")
	private BigDecimal deposit;

	@FieldInfo(name = "cardStatusId",type="Integer",explain="卡状态 1正常 2挂失 3注销 4换卡 7冻结")
	@Column(name = "cardStatusId")
	private Integer cardStatusId;

	@FieldInfo(name = "statusChangeTime",type="datetime",explain="卡状态改变时间")
	@Column(name = "statusChangeTime",columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date statusChangeTime = new Date();

	@FieldInfo(name = "dayCount",type="Integer",explain="当日消费次数")
	@Column(name = "dayCount")
	private Integer dayCount;

	@FieldInfo(name = "mealCount",type="Integer",explain="当餐消费次数")
	@Column(name = "mealCount")
	private Integer mealCount;

	@FieldInfo(name = "dayValue",type="BigDecimal",explain="当日交易金额")
	@Column(name = "dayValue")
	private BigDecimal dayValue;
	
	@FieldInfo(name = "mealValue",type="BigDecimal",explain="当餐交易金额")
	@Column(name = "mealValue")
	private BigDecimal mealValue;

	@FieldInfo(name = "lastPayDate",type="datetime",explain="最后交易时间")
	@Column(name = "lastPayDate",columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date lastPayDate = new Date();

	@FieldInfo(name = "lastPayMealType",type="Integer",explain="最后交易餐类")
	@Column(name = "lastPayMealType")
	private Integer lastPayMealType;

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Long getFactoryFixId() {
		return factoryFixId;
	}

	public void setFactoryFixId(Long factoryFixId) {
		this.factoryFixId = factoryFixId;
	}

	public Integer getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(Integer cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public Date getStatusChangeTime() {
		return statusChangeTime;
	}

	public void setStatusChangeTime(Date statusChangeTime) {
		this.statusChangeTime = statusChangeTime;
	}

	public Integer getDayCount() {
		return dayCount;
	}

	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}

	public Integer getMealCount() {
		return mealCount;
	}

	public void setMealCount(Integer mealCount) {
		this.mealCount = mealCount;
	}

	public BigDecimal getDayValue() {
		return dayValue;
	}

	public void setDayValue(BigDecimal dayValue) {
		this.dayValue = dayValue;
	}

	public BigDecimal getMealValue() {
		return mealValue;
	}

	public void setMealValue(BigDecimal mealValue) {
		this.mealValue = mealValue;
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
