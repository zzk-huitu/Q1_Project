package com.yc.q1.model.base.pt.system;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;
import com.yc.q1.core.util.DateTimeSerializer;

/**
 * 营业管理员
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_MealOperator")
@AttributeOverride(name = "id", column = @Column(name = "operatorId", length = 20, nullable = false) )
public class PtMealOperator extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。
	
	@FieldInfo(name = "营业员名称")
	@Column(name = "operatorName", columnDefinition = "nvarchar(20)", nullable = false)
	private String operatorName;

	@FieldInfo(name = "结算账户编号")
	@Column(name = "accountId", length = 20, nullable = false)
	private String accountId;

	@FieldInfo(name = "结算账户名称")
	@Formula("(SELECT a.accountName FROM T_PT_Account a WHERE a.accountId=accountId )")
	private String accountName;

	@FieldInfo(name = "营业员卡号")
	@Column(name = "operatorCardId", columnDefinition = "nvarchar(20) DEFAULT ''", nullable = true)
	private String operatorCardId;

	@FieldInfo(name = "物理卡号")
	@Column(name = "factoryFixId", columnDefinition = "nvarchar(30) DEFAULT ''", nullable = true)
	private String factoryFixId;

	@FieldInfo(name = "应用的系统类型", explain = "数据字典项")
	@Column(name = "useType", columnDefinition = "varchar(16) DEFAULT ''", nullable = true)
	private String useType;

	@FieldInfo(name = "营业员描述")
	@Column(name = "operatorNotes", columnDefinition = "nvarchar(100) DEFAULT ''", nullable = true)
	private String operatorNotes;

	@FieldInfo(name = "营业员卡密", explain = "与硬件对接")
	@Column(name = "password", columnDefinition = "varchar(10) DEFAULT ''", nullable = true)
	private String password;

	@FieldInfo(name = "有效期", explain = "与硬件对接")
	@Column(name = "validDate", nullable = true, columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date validDate;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOperatorCardId() {
		return operatorCardId;
	}

	public void setOperatorCardId(String operatorCardId) {
		this.operatorCardId = operatorCardId;
	}

	public String getFactoryFixId() {
		return factoryFixId;
	}

	public void setFactoryFixId(String factoryFixId) {
		this.factoryFixId = factoryFixId;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getOperatorNotes() {
		return operatorNotes;
	}

	public void setOperatorNotes(String operatorNotes) {
		this.operatorNotes = operatorNotes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public PtMealOperator(){
		super();
	}
	public PtMealOperator(String id) {
		super(id);
	}
}
