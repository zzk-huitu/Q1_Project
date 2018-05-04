package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 用户绑定结算账户表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_UserAccountBind", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "userId", "accountId" }) })
@AttributeOverride(name = "id", column = @Column(name = "bindId", length = 20, nullable = false))
public class PtUserAccountBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "用户ID")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@FieldInfo(name = "结算账户ID")
	@Column(name = "accountId", length = 30, nullable = false)
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/*
	 * 以下字段不需要持续化到数据库中
	 */
	@Formula("(SELECT a.accountName FROM T_PT_Account a WHERE a.accountId=accountId )")
	private String accountName;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Formula("(SELECT a.accountStatus FROM T_PT_Account a WHERE a.accountId=accountId )")
	private Boolean accountStatus;

	public Boolean getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public PtUserAccountBind() {
		super();
	}

	public PtUserAccountBind(String id) {
		super(id);
	}
}