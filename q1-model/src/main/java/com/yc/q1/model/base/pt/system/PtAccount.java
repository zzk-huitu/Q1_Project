package com.yc.q1.model.base.pt.system;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 结算账户
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_Account")
@AttributeOverride(name = "id", column = @Column(name = "accountId", length = 20, nullable = false) )
public class PtAccount extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。
	
	@FieldInfo(name = "账户名称")
	@Column(name = "accountName", columnDefinition = "nvarchar(20)", nullable = false)
	private String accountName;

	@FieldInfo(name = "查询密码")
	@Column(name = "accountPwd", columnDefinition = "nvarchar(20)	DEFAULT ''", nullable = true)
	private String accountPwd;

	@FieldInfo(name = "账户描述")
	@Column(name = "accountDescription", columnDefinition = "nvarchar(100) DEFAULT ''", nullable = true)
	private String accountDescription;

	@FieldInfo(name = "联系电话")
	@Column(name = "accountOwnerTel", columnDefinition = "varchar(50) DEFAULT ''", nullable = true)
	private String accountOwnerTel;

	@FieldInfo(name = "账户状态", explain = "0不启用 1启用")
	@Column(name = "accountStatus", columnDefinition = "bit DEFAULT 0", nullable = true)
	private Boolean accountStatus;

	@FieldInfo(name = "帐户编号")
	@Column(name = "accountNo", columnDefinition = "nvarchar(10) DEFAULT ''", nullable = true)
	private String accountNo;

	@FieldInfo(name = "管理员姓名")
	@Column(name = "adminName", columnDefinition = "nvarchar(50) DEFAULT ''", nullable = true)
	private String adminName;

	@FieldInfo(name = "证件编号")
	@Column(name = "idCard", columnDefinition = "nvarchar(50) DEFAULT ''", nullable = true)
	private String idCard;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public String getAccountOwnerTel() {
		return accountOwnerTel;
	}

	public void setAccountOwnerTel(String accountOwnerTel) {
		this.accountOwnerTel = accountOwnerTel;
	}

	public Boolean getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public PtAccount(){
		super();
	}
	public PtAccount(String id) {
		super(id);
	}
}