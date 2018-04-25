package com.yc.q1.model.base.pt.card;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;

/**
 * 卡片类型表
 * 
 * 64位卡类，数据在初始化时生成，基本不会新增和修改
 * 
 * ID采用只增长的方式生成
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_CardType")
public class PtCardType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@FieldInfo(name = "卡类ID", explain = "卡类id值(1 - 64)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cardTypeId", columnDefinition = "smallint", nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "卡类名称")
	@Column(name = "cardTypeName", columnDefinition = "nvarchar(20)", nullable = false)
	private String cardTypeName;

	@FieldInfo(name = "卡管理费比率")
	@Column(name = "commissionCharge", columnDefinition = "money DEFAULT 0", nullable = true)
	private BigDecimal commissionCharge;

	@FieldInfo(name = "卡工本费(押金)")
	@Column(name = "deposit", columnDefinition = "money DEFAULT 0", nullable = true)
	private BigDecimal deposit;

	@FieldInfo(name = "是否启用", explain = "1为启用，0为不启用")
	@Column(name = "useFlag", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean useFlag;

	@FieldInfo(name = "卡类描述")
	@Column(name = "cardNotes", columnDefinition = "nvarchar(100) DEFAULT ''", nullable = true)
	private String cardNotes;

	@FieldInfo(name = "工本费")
	@Column(name = "issueFee", columnDefinition = "money DEFAULT 0", nullable = true)
	private BigDecimal issueFee;

	@FieldInfo(name = "折旧费")
	@Column(name = "zheJiuFee", columnDefinition = "money DEFAULT 0", nullable = true)
	private BigDecimal zheJiuFee;

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public BigDecimal getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(BigDecimal commissionCharge) {
		this.commissionCharge = commissionCharge;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Boolean getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	public String getCardNotes() {
		return cardNotes;
	}

	public void setCardNotes(String cardNotes) {
		this.cardNotes = cardNotes;
	}

	public BigDecimal getIssueFee() {
		return issueFee;
	}

	public void setIssueFee(BigDecimal issueFee) {
		this.issueFee = issueFee;
	}

	public BigDecimal getZheJiuFee() {
		return zheJiuFee;
	}

	public void setZheJiuFee(BigDecimal zheJiuFee) {
		this.zheJiuFee = zheJiuFee;
	}

	public PtCardType() {
	}
}