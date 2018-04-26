package com.yc.q1.model.storage.xf;

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
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 资金收支表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_CreditAccount", catalog = "Q1_Storage", schema = "dbo")
@AttributeOverride(name = "id", column = @Column(name = "creditAccountId", length = 20, nullable = false) )
public class XfCreditAccount extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "收支金额")
	@Column(name = "receiptOutMoney", columnDefinition = "money default 0", nullable = false)
	private BigDecimal receiptOutMoney;

	@FieldInfo(name = "交易时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "creditTime", columnDefinition = "datetime", nullable = false)
	private Date creditTime;
	
	@FieldInfo(name = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint default 0", nullable = true)
	private Long cardNo;

	@FieldInfo(name = "卡上余额")
	@Column(name = "cardValue", columnDefinition = "money default 0", nullable = true)
	private BigDecimal cardValue;

	@FieldInfo(name = "手续费")
	@Column(name = "commissionCharge", columnDefinition = "money default 0", nullable = true)
	private BigDecimal commissionCharge;

	@FieldInfo(name = "？")
	@Column(name = "creditFactor", columnDefinition = "money default 0", nullable = true)
	private BigDecimal creditFactor;

	@FieldInfo(name = "使用类型", explain = "100为消费，101为水控")
	@Column(name = "useType", columnDefinition = "int default 0", nullable = true)
	private Integer useType;

	@FieldInfo(name = "工作站ID")
	@Column(name = "workStationId", columnDefinition = "int default 0", nullable = true)
	private Integer workStationId;

	@FieldInfo(name = "机器号")
	@Column(name = "termNo", columnDefinition = "int default 0", nullable = true)
	private Integer termNo;

	@FieldInfo(name = "收支类型", explain = "数据字典")
	@Column(name = "creditType", columnDefinition = "varchar(10) default ''", nullable = true)
	private String creditType;

	@FieldInfo(name = "营业操作员ID")
	@Column(name = "operatorId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String operatorId;

	@FieldInfo(name = "资金类型", explain = "数据字典")
	@Column(name = "payStyle", columnDefinition = "varchar(30) default ''", nullable = true)
	private String payStyle;

	@FieldInfo(name = "机器流水号")
	@Column(name = "termRecordId", columnDefinition = "bigint default 0", nullable = true)
	private Long termRecordId;

	@FieldInfo(name = "消费记录标志位")
	@Column(name = "posRecordState", columnDefinition = "int default 0", nullable = true)
	private Integer posRecordState;

	@FieldInfo(name = "卡使用次数")
	@Column(name = "consumeCountor", columnDefinition = "bigint default 0", nullable = true)
	private Long consumeCountor;

	@FieldInfo(name = "人员ID")
	@Column(name = "userId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String userId;

	@FieldInfo(name = "原始记录ID")
	@Column(name = "oriRecordId", columnDefinition = "varchar(32)  default ''", nullable = true)
	private String oriRecordId;

	@FieldInfo(name = "钱包类型编码", explain = "数据字典：1消费2水控3消费补助4脱机信用5就餐钱包6配餐钱包")
	@Column(name = "bagType", columnDefinition = "varchar(10) default ''", nullable = true)
	private String bagType;

	@FieldInfo(name = "是否发送短信")
	@Column(name = "isSmsSend", columnDefinition = "bit default 0", nullable = true)
	private Boolean isSmsSend;

	@FieldInfo(name = "团充id")
	@Column(name = "groupCreditId", columnDefinition = "varchar(20) default ''", nullable = true)
	private String groupCreditId;


	@FieldInfo(name = "备注")
	@Column(name = "manualNotes", columnDefinition = "nvarchar(200) default ''", nullable = true)
	private String manualNotes;

	@FieldInfo(name = "数字签名")
	@Column(name = "digitalSign", columnDefinition = "varchar(1000) default ''", nullable = true)
	private String digitalSign;

	public BigDecimal getReceiptOutMoney() {
		return receiptOutMoney;
	}

	public void setReceiptOutMoney(BigDecimal receiptOutMoney) {
		this.receiptOutMoney = receiptOutMoney;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public BigDecimal getCardValue() {
		return cardValue;
	}

	public void setCardValue(BigDecimal cardValue) {
		this.cardValue = cardValue;
	}

	public BigDecimal getCommissionCharge() {
		return commissionCharge;
	}

	public void setCommissionCharge(BigDecimal commissionCharge) {
		this.commissionCharge = commissionCharge;
	}

	public BigDecimal getCreditFactor() {
		return creditFactor;
	}

	public void setCreditFactor(BigDecimal creditFactor) {
		this.creditFactor = creditFactor;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	

	public Integer getTermNo() {
		return termNo;
	}

	public void setTermNo(Integer termNo) {
		this.termNo = termNo;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getPayStyle() {
		return payStyle;
	}

	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}

	public Long getTermRecordId() {
		return termRecordId;
	}

	public void setTermRecordId(Long termRecordId) {
		this.termRecordId = termRecordId;
	}

	public Integer getPosRecordState() {
		return posRecordState;
	}

	public void setPosRecordState(Integer posRecordState) {
		this.posRecordState = posRecordState;
	}

	public Long getConsumeCountor() {
		return consumeCountor;
	}

	public void setConsumeCountor(Long consumeCountor) {
		this.consumeCountor = consumeCountor;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getBagType() {
		return bagType;
	}

	public void setBagType(String bagType) {
		this.bagType = bagType;
	}

	public Boolean getIsSmsSend() {
		return isSmsSend;
	}

	public void setIsSmsSend(Boolean isSmsSend) {
		this.isSmsSend = isSmsSend;
	}

	

	public Integer getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(Integer workStationId) {
		this.workStationId = workStationId;
	}

	public String getOriRecordId() {
		return oriRecordId;
	}

	public void setOriRecordId(String oriRecordId) {
		this.oriRecordId = oriRecordId;
	}

	public String getGroupCreditId() {
		return groupCreditId;
	}

	public void setGroupCreditId(String groupCreditId) {
		this.groupCreditId = groupCreditId;
	}

	public Date getCreditTime() {
		return creditTime;
	}

	public void setCreditTime(Date creditTime) {
		this.creditTime = creditTime;
	}

	public String getManualNotes() {
		return manualNotes;
	}

	public void setManualNotes(String manualNotes) {
		this.manualNotes = manualNotes;
	}

	public String getDigitalSign() {
		return digitalSign;
	}

	public void setDigitalSign(String digitalSign) {
		this.digitalSign = digitalSign;
	}

	public XfCreditAccount() {
		super();
	}

	public XfCreditAccount(String id) {
		super(id);
	}
}
