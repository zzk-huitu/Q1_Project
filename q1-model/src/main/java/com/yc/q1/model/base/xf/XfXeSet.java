package com.yc.q1.model.base.xf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 限额参数设置
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_XeSet", uniqueConstraints = { @UniqueConstraint(columnNames = { "cardTypeId" }) })
@AttributeOverride(name = "id", column = @Column(name = "xeSetId", length = 20, nullable = false) )
public class XfXeSet extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡类ID")
	@Column(name = "cardTypeId", columnDefinition = "int", nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "折扣状态", explain = "原UP中的XEZT字段")
	@Column(name = "xeStatus", columnDefinition = "bit default 0", nullable = true)
	private Boolean xeStatus;

	@FieldInfo(name = "每日金额")
	@Column(name = "dailyMoney", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal dailyMoney;

	@FieldInfo(name = "就餐金额1")
	@Column(name = "meal1Money", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal meal1Money;

	@FieldInfo(name = "就餐金额2")
	@Column(name = "meal2Money", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal meal2Money;

	@FieldInfo(name = "就餐金额3")
	@Column(name = "meal3Money", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal meal3Money;

	@FieldInfo(name = "就餐金额4")
	@Column(name = "meal4Money", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal meal4Money;

	@FieldInfo(name = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String notes;
	
	@FieldInfo(name = "卡类名称")
	@Formula("(select ISNULL(a.cardTypeName,'') from T_PT_CardType a where a.cardTypeId=cardTypeId)")
	private String cardTypeName;
	
	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Boolean getXeStatus() {
		return xeStatus;
	}

	public void setXeStatus(Boolean xeStatus) {
		this.xeStatus = xeStatus;
	}

	public BigDecimal getDailyMoney() {
		return dailyMoney;
	}

	public void setDailyMoney(BigDecimal dailyMoney) {
		this.dailyMoney = dailyMoney;
	}

	public BigDecimal getMeal1Money() {
		return meal1Money;
	}

	public void setMeal1Money(BigDecimal meal1Money) {
		this.meal1Money = meal1Money;
	}

	public BigDecimal getMeal2Money() {
		return meal2Money;
	}

	public void setMeal2Money(BigDecimal meal2Money) {
		this.meal2Money = meal2Money;
	}

	public BigDecimal getMeal3Money() {
		return meal3Money;
	}

	public void setMeal3Money(BigDecimal meal3Money) {
		this.meal3Money = meal3Money;
	}

	public BigDecimal getMeal4Money() {
		return meal4Money;
	}

	public void setMeal4Money(BigDecimal meal4Money) {
		this.meal4Money = meal4Money;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public XfXeSet() {
		super();
	}

	public XfXeSet(String id) {
		super(id);
	}

}