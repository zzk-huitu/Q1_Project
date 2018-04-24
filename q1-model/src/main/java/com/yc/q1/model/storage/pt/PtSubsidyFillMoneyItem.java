package com.yc.q1.model.storage.pt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 在线补助明细表
 * 
 * 部分字段做了冗余
 * 
 * 目前此表采用 补助id+用户id的唯一索引
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_SubsidyFillMoneyItem",catalog="Q1_Storage",schema="dbo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "mainId", "userId" }) })
@AttributeOverride(name = "id", column = @Column(name = "itemId", length = 20, nullable = false) )
public class PtSubsidyFillMoneyItem extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "补助id")
	@Column(name = "mainId", length = 20, nullable = false)
	private String mainId;

	@FieldInfo(name = "用户id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "补助金额")
	@Column(name = "fillMoney", columnDefinition = "Money", nullable = true)
	private BigDecimal fillMoney;

	@FieldInfo(name = "配置补助时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "fillDate", columnDefinition = "datetime", nullable = true)
	private Date fillDate;

	@FieldInfo(name = "标识字段 是否领取", explain = "0—补助未领，1—补助已领")
	@Column(name = "isFill", columnDefinition = "bit default 0", nullable = true)
	private Boolean isFill;

	@FieldInfo(name = "领取补助时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "getFillDate", columnDefinition = "datetime", nullable = true)
	private Date getFillDate;

	@FieldInfo(name = "设备id")
	@Column(name = "termId", length = 20, nullable = true)
	private String termId;

	@FieldInfo(name = "设备流水号")
	@Column(name = "termNo", columnDefinition = "bigint", nullable = true)
	private Long termNo;

	@FieldInfo(name = "标识字段 用户是否领取", explain = "0—未领，1—已领")
	@Column(name = "fillStats", columnDefinition = "bit default 0", nullable = true)
	private Boolean fillStats;

	@FieldInfo(name = "领取补助后卡余")
	@Column(name = "cardValue", columnDefinition = "Money", nullable = true)
	private BigDecimal cardValue;

	@FieldInfo(name = "领取补助前卡余")
	@Column(name = "cardValueBefore", columnDefinition = "Money", nullable = true)
	private BigDecimal cardValueBefore;

	@FieldInfo(name = "原始记录ID")
	@Column(name = "originalID", columnDefinition = "bigint", nullable = true)
	private Long originalID;

	@FieldInfo(name = "备注")
	@Column(name = "itemNotes", columnDefinition = "nvarchar(500) default ''", nullable = true)
	private String itemNotes;

	@FieldInfo(name = "子类型")
	@Column(name = "subType", length = 10, nullable = true)
	private String subType;

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getFillMoney() {
		return fillMoney;
	}

	public void setFillMoney(BigDecimal fillMoney) {
		this.fillMoney = fillMoney;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public Boolean getIsFill() {
		return isFill;
	}

	public void setIsFill(Boolean isFill) {
		this.isFill = isFill;
	}

	public Date getGetFillDate() {
		return getFillDate;
	}

	public void setGetFillDate(Date getFillDate) {
		this.getFillDate = getFillDate;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public Long getTermNo() {
		return termNo;
	}

	public void setTermNo(Long termNo) {
		this.termNo = termNo;
	}

	public Boolean getFillStats() {
		return fillStats;
	}

	public void setFillStats(Boolean fillStats) {
		this.fillStats = fillStats;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public BigDecimal getCardValueBefore() {
		return cardValueBefore;
	}

	public void setCardValueBefore(BigDecimal cardValueBefore) {
		this.cardValueBefore = cardValueBefore;
	}

	public Long getOriginalID() {
		return originalID;
	}

	public void setOriginalID(Long originalID) {
		this.originalID = originalID;
	}

	public String getItemNotes() {
		return itemNotes;
	}

	public void setItemNotes(String itemNotes) {
		this.itemNotes = itemNotes;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public PtSubsidyFillMoneyItem() {
		super();
	}

	public PtSubsidyFillMoneyItem(String id) {
		super(id);
	}
}
