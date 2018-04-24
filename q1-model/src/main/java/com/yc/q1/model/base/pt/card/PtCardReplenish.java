package com.yc.q1.model.base.pt.card;

import java.io.Serializable;
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
 * 补卡充值表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_CardReplenish")
@AttributeOverride(name = "id", column = @Column(name = "replenishId", length = 20, nullable = false) )
public class PtCardReplenish extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "新卡流水号")
	@Column(name = "cardIdNew", columnDefinition = "bigint", nullable = false)
	private Long cardIdNew;

	@FieldInfo(name = "旧卡流水号")
	@Column(name = "cardIdOld", columnDefinition = "bigint", nullable = false)
	private Long cardIdOld;

	@FieldInfo(name = "补卡充值领取状态", explain = "1已领取 0未领取")
	@Column(name = "status", columnDefinition = "bit DEFAULT 0", nullable = false)
	private Boolean status;

	@FieldInfo(name = "补卡充值领取时间")
	@Column(name = "fillDate", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date fillDate;

	public Long getCardIdNew() {
		return cardIdNew;
	}

	public void setCardIdNew(Long cardIdNew) {
		this.cardIdNew = cardIdNew;
	}

	public Long getCardIdOld() {
		return cardIdOld;
	}

	public void setCardIdOld(Long cardIdOld) {
		this.cardIdOld = cardIdOld;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public PtCardReplenish(){
		super();
	}
	public PtCardReplenish(String id) {
		super(id);
	}
}