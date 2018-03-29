package com.zd.school.ykt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 卡类管理 ClassName: TcCardtype Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: (TC_CardType)实体类. date: 2017-03-20
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

@Entity
@Table(name = "T_PT_CardType")
@AttributeOverride(name = "cardTypeId", column = @Column(name = "cardTypeId", length = 20, nullable = false) )
public class TcCardtype extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "cardTypeNO")
	@Column(name = "cardTypeNO",  updatable = false, insertable = false, columnDefinition = " smallint  IDENTITY(1,1) NOT NULL")
	private Short cardTypeNO;

	@FieldInfo(name = "cardType")
	@Column(name = "cardType", columnDefinition="nvarchar(50) defalut ''", nullable = true)
	private String cardType;

	@FieldInfo(name = "commissionCharge")
	@Column(name = "commissionCharge", columnDefinition="defalut 0", nullable = true)
	private BigDecimal commissionCharge;

	@FieldInfo(name = "deposit")
	@Column(name = "deposit", columnDefinition="defalut 0", nullable = true)
	private BigDecimal deposit;

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	@FieldInfo(name = "useFlag")
	@Column(name = "useFlag", columnDefinition="defalut 0", nullable = true)
	private Boolean useFlag;

	@FieldInfo(name = "cardNotes")
	@Column(name = "cardNotes", columnDefinition="nvarchar(500) defalut ''", nullable = true)
	private String cardNotes;
	@FieldInfo(name = "issueFee")
	@Column(name = "issueFee",columnDefinition="defalut 0", nullable = true)
	private BigDecimal issueFee;

	@FieldInfo(name = "zheJiuFee")
	@Column(name = "zheJiuFee", columnDefinition="defalut 0", nullable = true)
	private BigDecimal zheJiuFee;

	public Short getCardTypeNO() {
		return cardTypeNO;
	}

	public void setCardTypeNO(Short cardTypeNO) {
		this.cardTypeNO = cardTypeNO;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public BigDecimal getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(BigDecimal commissionCharge) {
		this.commissionCharge = commissionCharge;
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
	

	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}