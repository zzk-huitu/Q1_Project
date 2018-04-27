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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 在线补助表
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_SubsidyFillMoneyMain", catalog = "Q1_Storage", schema = "dbo")
@AttributeOverride(name = "id", column = @Column(name = "mainId", length = 32, nullable = false) )
public class PtSubsidyFillMoneyMain extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "补助金额")
	@Column(name = "fillMoney", columnDefinition = "decimal(18,2)", nullable = false)
	private BigDecimal fillMoney;

	@FieldInfo(name = "配置补助时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "fillDate", columnDefinition = "datetime", nullable = false)
	private Date fillDate;

	@FieldInfo(name = "是否领取", explain = "0—补助未领，1—补助已领")
	@Column(name = "isFill", columnDefinition = "bit default 0", nullable = true)
	private Boolean isFill;

	@FieldInfo(name = "是否已审核")
	@Column(name = "isAudit", columnDefinition = "bit default 0", nullable = true)
	private Boolean isAudit;

	@FieldInfo(name = "审核人", explain = "用户的主键id或姓名")
	@Column(name = "auditUser", columnDefinition = "varchar(20) default ''", nullable = true)
	private String auditUser;

	@FieldInfo(name = "审核时间")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	@Column(name = "auditDate", columnDefinition = "datetime", nullable = true)
	private Date auditDate;

	@FieldInfo(name = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(500) default ''", nullable = true)
	private String notes;

	@FieldInfo(name = "子类型")
	@Column(name = "subType", columnDefinition = "varchar(10) default ''", nullable = true)
	private String subType;

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

	public Boolean getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Boolean isAudit) {
		this.isAudit = isAudit;
	}

	public String getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public PtSubsidyFillMoneyMain() {
		super();
	}

	public PtSubsidyFillMoneyMain(String id) {
		super(id);
	}
}
