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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 卡片钱包表
 * 
 * 钱包定义表：被放入到数据字典中
 * 
 * 目前此表采用 钱包码+用户id的唯一索引
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_CardBags", uniqueConstraints = { @UniqueConstraint(columnNames = { "bagCode", "userId" }) })
@AttributeOverride(name = "id", column = @Column(name = "cardBagId", length = 20, nullable = false) )
public class PtCardBags extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "钱包编码", explain = "数据字典：1消费2水控3消费补助4脱机信用5就餐钱包6配餐钱包")
	@Column(name = "bagCode", length = 10, nullable = false)
	private String bagCode;

	@FieldInfo(name = "关联用户表")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "钱包余额")
	@Column(name = "cardValue", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal cardValue;

	@FieldInfo(name = "消费总额")
	@Column(name = "xfMoneyTotal", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal xfMoneyTotal;

	@FieldInfo(name = "消费次数")
	@Column(name = "xfCount", columnDefinition = "int default 0", nullable = true)
	private Integer xfCount;

	@FieldInfo(name = "充值总额")
	@Column(name = "czMoneyTotal", columnDefinition = "decimal(18,2) default 0", nullable = true)
	private BigDecimal czMoneyTotal;

	@FieldInfo(name = "充值次数")
	@Column(name = "czCount", columnDefinition = "int default 0", nullable = true)
	private Integer czCount;

	@FieldInfo(name = "钱包金额最后更新时间")
	@Column(name = "bagUpdateDate")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date bagUpdateDate = new Date();

	@FieldInfo(name = "钱包状态", explain = "1正常 0未启用")
	@Column(name = "bagStatus", columnDefinition = "smallint default 0", nullable = true)
	private Integer bagStatus;

	@FieldInfo(name = "卡片余额密文")
	@Column(name = "cardValueEncrypt", columnDefinition = "Varbinary(512)", nullable = true)
	private String cardValueEncrypt;

	public String getBagCode() {
		return bagCode;
	}

	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public BigDecimal getXfMoneyTotal() {
		return xfMoneyTotal;
	}

	public void setXfMoneyTotal(BigDecimal xfMoneyTotal) {
		this.xfMoneyTotal = xfMoneyTotal;
	}

	public Integer getXfCount() {
		return xfCount;
	}

	public void setXfCount(Integer xfCount) {
		this.xfCount = xfCount;
	}

	public BigDecimal getCzMoneyTotal() {
		return czMoneyTotal;
	}

	public void setCzMoneyTotal(BigDecimal czMoneyTotal) {
		this.czMoneyTotal = czMoneyTotal;
	}

	public Integer getCzCount() {
		return czCount;
	}

	public void setCzCount(Integer czCount) {
		this.czCount = czCount;
	}

	public Date getBagUpdateDate() {
		return bagUpdateDate;
	}

	public void setBagUpdateDate(Date bagUpdateDate) {
		this.bagUpdateDate = bagUpdateDate;
	}

	public Integer getBagStatus() {
		return bagStatus;
	}

	public void setBagStatus(Integer bagStatus) {
		this.bagStatus = bagStatus;
	}

	public String getCardValueEncrypt() {
		return cardValueEncrypt;
	}

	public void setCardValueEncrypt(String cardValueEncrypt) {
		this.cardValueEncrypt = cardValueEncrypt;
	}

	public PtCardBags() {
		super();
	}

	public PtCardBags(String id) {
		super(id);
	}
}
