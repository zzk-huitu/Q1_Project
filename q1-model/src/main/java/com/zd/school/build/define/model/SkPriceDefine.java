package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 水控费率定义
 * 
 * @author hucy
 *
 */
@Entity
@Table(name = "T_SK_Price")
@AttributeOverride(name = "sKPriceId", column = @Column(name = "sKPriceId", length = 20, nullable = false) )
public class SkPriceDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "费率名称")
	@Column(name = "priceName", columnDefinition = "nvarchar(36)", nullable = false)
	private String priceName;

	@FieldInfo(name = "费率价格")
	@Column(name = "priceValue", nullable = false)
	private double priceValue;

	@FieldInfo(name = "状态,用于标识是否分配：0禁用。1启用")
	@Column(name = "priceStatus", columnDefinition = "default 0", nullable = true)
	private Boolean priceStatus;

	@FieldInfo(name = "货币种类")
	@Column(name = "currencyType", columnDefinition = "nvarchar(10) default 'RMB(人民币)'", nullable = true)
	private String currencyType;

	@FieldInfo(name = "备注")
	@Column(name = "priceNotes", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String priceNotes;

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public double getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(double priceValue) {
		this.priceValue = priceValue;
	}

	public Boolean getPriceStatus() {
		return priceStatus;
	}

	public void setPriceStatus(Boolean priceStatus) {
		this.priceStatus = priceStatus;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getPriceNotes() {
		return priceNotes;
	}

	public void setPriceNotes(String priceNotes) {
		this.priceNotes = priceNotes;
	}


	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}