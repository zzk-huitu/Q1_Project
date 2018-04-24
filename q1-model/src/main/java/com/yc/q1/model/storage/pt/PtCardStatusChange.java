package com.yc.q1.model.storage.pt;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 卡片操作明细表
 * 
 * @author ZZK
 *
 */

@Entity
@Table(name = "T_PT_PtCardStatusChange",catalog="Q1_Storage",schema="dbo")
@AttributeOverride(name = "id", column = @Column(name = "cardStatusId", length = 20, nullable = false) )
public class PtCardStatusChange extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "用户id")
	@Column(name = "userId", length = 20, nullable = false)
	private String userId;

	@FieldInfo(name = "卡片流水号")
	@Column(name = "cardNo", columnDefinition = "bigint", nullable = false)
	private Long cardNo;

	@FieldInfo(name = "卡片更改状态",explain="原up中是中文值")
	@Column(name = "cardStatusName", columnDefinition = "nvarchar(20)", nullable = true)
	private String cardStatusName;

	@FieldInfo(name = "使用的卡类型",explain="原up中是中文值")
	@Column(name = "cardClassName", columnDefinition = "nvarchar(20)", nullable = true)
	private String cardClassName;

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

	

	public String getCardStatusName() {
		return cardStatusName;
	}

	public void setCardStatusName(String cardStatusName) {
		this.cardStatusName = cardStatusName;
	}

	public String getCardClassName() {
		return cardClassName;
	}

	public void setCardClassName(String cardClassName) {
		this.cardClassName = cardClassName;
	}

	public PtCardStatusChange() {
		super();
	}

	public PtCardStatusChange(String id) {
		super(id);
	}
}
