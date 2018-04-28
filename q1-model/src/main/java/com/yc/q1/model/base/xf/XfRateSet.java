package com.yc.q1.model.base.xf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 折扣参数设置
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_RateSet", uniqueConstraints = { @UniqueConstraint(columnNames = { "cardTypeId", "mealTypeId" }) })
@AttributeOverride(name = "id", column = @Column(name = "rateSetId", length = 20, nullable = false) )
public class XfRateSet extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡类ID")
	@Column(name = "cardTypeId", columnDefinition = "int", nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "营业就餐类型ID")
	@Column(name = "mealTypeId", columnDefinition = "int", nullable = false)
	private Integer mealTypeId;

	@FieldInfo(name = "折扣状态", explain = "原UP中的rateZt字段")
	@Column(name = "rateStatus", columnDefinition = "bit default 0", nullable = true)
	private Boolean rateStatus;

	@FieldInfo(name = "折扣类型", explain = "有什么类型？")
	@Column(name = "rateType", columnDefinition = "bit default 0", nullable = true)
	private Boolean rateType;

	@FieldInfo(name = "折扣金额1")
	@Column(name = "ratePrice1", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal ratePrice1;

	@FieldInfo(name = "折扣金额2")
	@Column(name = "ratePrice2", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal ratePrice2;

	@FieldInfo(name = "折扣金额3")
	@Column(name = "ratePrice3", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal ratePrice3;

	@FieldInfo(name = "折扣金额4")
	@Column(name = "ratePrice4", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal ratePrice4;

	@FieldInfo(name = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String notes;

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Integer getMealTypeId() {
		return mealTypeId;
	}

	public void setMealTypeId(Integer mealTypeId) {
		this.mealTypeId = mealTypeId;
	}

	public Boolean getRateStatus() {
		return rateStatus;
	}

	public void setRateStatus(Boolean rateStatus) {
		this.rateStatus = rateStatus;
	}

	public Boolean getRateType() {
		return rateType;
	}

	public void setRateType(Boolean rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getRatePrice1() {
		return ratePrice1;
	}

	public void setRatePrice1(BigDecimal ratePrice1) {
		this.ratePrice1 = ratePrice1;
	}

	public BigDecimal getRatePrice2() {
		return ratePrice2;
	}

	public void setRatePrice2(BigDecimal ratePrice2) {
		this.ratePrice2 = ratePrice2;
	}

	public BigDecimal getRatePrice3() {
		return ratePrice3;
	}

	public void setRatePrice3(BigDecimal ratePrice3) {
		this.ratePrice3 = ratePrice3;
	}

	public BigDecimal getRatePrice4() {
		return ratePrice4;
	}

	public void setRatePrice4(BigDecimal ratePrice4) {
		this.ratePrice4 = ratePrice4;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public XfRateSet() {
		super();
	}

	public XfRateSet(String id) {
		super(id);
	}
}