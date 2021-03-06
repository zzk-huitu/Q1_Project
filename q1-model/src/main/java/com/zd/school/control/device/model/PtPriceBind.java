package com.zd.school.control.device.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zd.core.annotation.FieldInfo;
import com.zd.core.model.BaseEntity;

/**
 * 水控、电控费率绑定
 * 
 * @author ZZK
 *
 */
@Entity
@Table(name = "T_PT_PriceBind")
@AttributeOverride(name = "id", column = @Column(name = "priceBindId", length = 20, nullable = false) )
public class PtPriceBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@FieldInfo(name = "设备编号", type = "varchar(20) NOT NULL", explain = "设备编号")
	@Column(name = "termId", length = 20, nullable = false)
	private String termId;

	@FieldInfo(name = "设备序列号", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@FieldInfo(name = "费率编号", type = "varchar(20) NOT NULL", explain = "费率编号")
	@Column(name = "priceId", length = 20,  nullable = false)
	private String priceId;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public PtPriceBind() {
		super();
	}

	public PtPriceBind(String id) {
		super(id);
	}

}