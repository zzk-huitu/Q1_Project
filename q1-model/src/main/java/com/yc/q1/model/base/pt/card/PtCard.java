package com.yc.q1.model.base.pt.card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 卡片信息表
 * 
 * 卡片状态表：被放入到数据字典中
 * 
 * 卡流水号:由于信息要写入到卡片中，所以流水号是必须的
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_Card")
@AttributeOverride(name = "id", column = @Column(name = "cardId", length = 20, nullable = false) )
public class PtCard extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡流水号", type = "bigint NOT NULL", explain = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint IDENTITY(1,1)", nullable = false,updatable=false)
	private Long cardNo;

	@FieldInfo(name = "卡类型", type = "smallint NOT NULL", explain = "卡类型ID /*1:主卡 2：副卡*/")
	@Column(name = "cardTypeId", columnDefinition = "smallint", nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "物理卡号", type = "bigint default 0", explain = "物理卡号")
	@Column(name = "factoryFixId", columnDefinition = "bigint default 0", nullable = true)
	private Long factoryFixId;

	@FieldInfo(name = "用户ID", type = "varchar(20) default ''", explain = "关联user表")
	@Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String userId;

	@FieldInfo(name = "有效期", type = "datetime", explain = "有效期")
	@Column(name = "expiryDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date expiryDate;

	@FieldInfo(name = "卡押金", type = "decimal(18,2) default 0", explain = "卡押金")
	@Column(name = "deposit", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal deposit;

	@FieldInfo(name = "卡状态", type = "smallint default 0", explain = "数据字典：1正常2挂失3无卡4退卡 7冻结")
	@Column(name = "cardStatusId", columnDefinition = "smallint default 0", nullable = true)
	private Integer cardStatusId;

	@FieldInfo(name = "卡状态改变时间", type = "datetime", explain = "卡状态改变时间")
	@Column(name = "statusChangeTime", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date statusChangeTime = new Date();

	@FieldInfo(name = "当日消费次数", type = "int default 0", explain = "当日消费次数")
	@Column(name = "dayCount", columnDefinition = "int default 0", nullable = true)
	private Integer dayCount;

	@FieldInfo(name = "当餐消费次数", type = "int default 0", explain = "当餐消费次数")
	@Column(name = "mealCount", columnDefinition = "int default 0", nullable = true)
	private Integer mealCount;

	@FieldInfo(name = "当日交易金额", type = "decimal(18,2) default 0", explain = "当日交易金额")
	@Column(name = "dayValue", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal dayValue;

	@FieldInfo(name = "当餐交易金额", type = "decimal(18,2) default 0", explain = "当餐交易金额")
	@Column(name = "mealValue", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal mealValue;

	@FieldInfo(name = "最后交易时间", type = "datetime", explain = "最后交易时间")
	@Column(name = "lastPayDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date lastPayDate;

	@FieldInfo(name = "最后交易餐类", type = "smallint default 0", explain = "最后交易餐类，对应营业时段的编号")
	@Column(name = "lastPayMealType", columnDefinition = "smallint default 0", nullable = true)
	private Integer lastPayMealType;
	
	@Formula("(SELECT ISNULL(a.cardTypeName,'') FROM T_PT_CardType a WHERE a.cardTypeId=cardTypeId)")
	private String cardTypeName;

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Long getFactoryFixId() {
		return factoryFixId;
	}

	public void setFactoryFixId(Long factoryFixId) {
		this.factoryFixId = factoryFixId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public PtCard() {
		super();
	}

	public PtCard(String id) {
		super(id);
	}

}
