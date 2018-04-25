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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

@Entity
@Table(name = "T_XF_UserXfAndCreditTotal", catalog = "Q1_Storage", schema = "dbo", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "cardNo", "useType", "dateDay" }) })
@AttributeOverride(name = "id", column = @Column(name = "totalId", length = 20, nullable = false) )
public class XfUserXfAndCreditTotal extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡流水号")
	@Column(name = "userId", columnDefinition = "varchar(20)", nullable = false)
	private String userId;

	@FieldInfo(name = "卡流水号")
	@Column(name = "cardNo", columnDefinition = "bigint", nullable = false)
	private Long cardNo;

	@FieldInfo(name = "使用类型", explain = "100为消费，101为水控")
	@Column(name = "useType", columnDefinition = "int", nullable = false)
	private Integer useType;

	@FieldInfo(name = "消费充值日期")
	@Column(name = "dateDay", columnDefinition = "Varchar(10)", nullable = false)
	private String dateDay;

	@FieldInfo(name = "充值金额")
	@Column(name = "creditMoney", columnDefinition = "money default 0", nullable = false)
	private BigDecimal creditMoney;

	@FieldInfo(name = "消费金额")
	@Column(name = "xfMoney", columnDefinition = "money default 0", nullable = false)
	private BigDecimal xfMoney;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getCardNo() {
		return cardNo;
	}

	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public BigDecimal getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(BigDecimal creditMoney) {
		this.creditMoney = creditMoney;
	}

	public BigDecimal getXfMoney() {
		return xfMoney;
	}

	public void setXfMoney(BigDecimal xfMoney) {
		this.xfMoney = xfMoney;
	}

	public XfUserXfAndCreditTotal() {
		super();
	}

	public XfUserXfAndCreditTotal(String id) {
		super(id);
	}
}
