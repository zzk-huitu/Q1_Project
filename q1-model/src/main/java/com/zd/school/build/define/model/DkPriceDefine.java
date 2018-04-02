package com.zd.school.build.define.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 电控费率定义
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_DK_PriceDefine")
@AttributeOverride(name = "id", column = @Column(name = "priceId", length = 20, nullable = false) )
public class DkPriceDefine extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "费率名称", type = "nvarchar(20) NOT NULL", explain = "费率名称")
	@Column(name = "priceName", columnDefinition = "nvarchar(20)", nullable = false)
	private String priceName;

	@FieldInfo(name = "费率价格", type = "float NOT NULL", explain = "费率价格")
	@Column(name = "priceValue", nullable = false)
	private Float priceValue;

	@FieldInfo(name = "费率状态", type = "bit NOT NULL default 0", explain = "状态,用于标识是否启用：0禁用。1启用")
	@Column(name = "priceStatus", columnDefinition = "default 0", nullable = false)
	private Boolean priceStatus;

	@FieldInfo(name = "货币种类", type = "nvarchar(20) default 'RMB(人民币)'", explain = "货币种类")
	@Column(name = "currencyType", columnDefinition = "nvarchar(20) default 'RMB(人民币)'", nullable = true)
	private String currencyType;

	@FieldInfo(name = "备注", type = "nvarchar(128) default ''", explain = "备注")
	@Column(name = "priceNotes", columnDefinition = "nvarchar(128) default ''", nullable = true)
	private String priceNotes;

	

	public String getPriceName() {
		return priceName;
	}

	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}

	public Float getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(Float priceValue) {
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

	public DkPriceDefine() {
		super();
	}

	public DkPriceDefine(String id) {
		super(id);
	}

}