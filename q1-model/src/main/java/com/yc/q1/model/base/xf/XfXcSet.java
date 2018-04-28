package com.yc.q1.model.base.xf;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

/**
 * 限次参数设置
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_XF_XcSet", uniqueConstraints = { @UniqueConstraint(columnNames = { "cardTypeId" }) })
@AttributeOverride(name = "id", column = @Column(name = "xcSetId", length = 20, nullable = false) )
public class XfXcSet extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.XF; // 指定此对象生成的模块编码值。

	@FieldInfo(name = "卡类ID")
	@Column(name = "cardTypeId", columnDefinition = "int", nullable = false)
	private Integer cardTypeId;

	@FieldInfo(name = "限次状态", explain = "原UP中的XCZT字段")
	@Column(name = "xcStatus", columnDefinition = "bit default 0", nullable = true)
	private Boolean xcStatus;

	@FieldInfo(name = "每日次数")
	@Column(name = "dailyCount", columnDefinition = "int default 0", nullable = true)
	private Integer dailyCount;

	@FieldInfo(name = "就餐次数1")
	@Column(name = "meal1Count", columnDefinition = "int default 0", nullable = true)
	private Integer meal1Count;

	@FieldInfo(name = "就餐次数2")
	@Column(name = "meal2Count", columnDefinition = "int default 0", nullable = true)
	private Integer meal2Count;

	@FieldInfo(name = "就餐次数3")
	@Column(name = "meal3Count", columnDefinition = "int default 0", nullable = true)
	private Integer meal3Count;

	@FieldInfo(name = "就餐次数4")
	@Column(name = "meal4Count", columnDefinition = "int default 0", nullable = true)
	private Integer meal4Count;

	@FieldInfo(name = "备注")
	@Column(name = "notes", columnDefinition = "nvarchar(100) default ''", nullable = true)
	private String notes;

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Boolean getXcStatus() {
		return xcStatus;
	}

	public void setXcStatus(Boolean xcStatus) {
		this.xcStatus = xcStatus;
	}

	public Integer getDailyCount() {
		return dailyCount;
	}

	public void setDailyCount(Integer dailyCount) {
		this.dailyCount = dailyCount;
	}

	public Integer getMeal1Count() {
		return meal1Count;
	}

	public void setMeal1Count(Integer meal1Count) {
		this.meal1Count = meal1Count;
	}

	public Integer getMeal2Count() {
		return meal2Count;
	}

	public void setMeal2Count(Integer meal2Count) {
		this.meal2Count = meal2Count;
	}

	public Integer getMeal3Count() {
		return meal3Count;
	}

	public void setMeal3Count(Integer meal3Count) {
		this.meal3Count = meal3Count;
	}

	public Integer getMeal4Count() {
		return meal4Count;
	}

	public void setMeal4Count(Integer meal4Count) {
		this.meal4Count = meal4Count;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public XfXcSet() {
		super();
	}

	public XfXcSet(String id) {
		super(id);
	}

}