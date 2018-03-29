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
 * @author hucy
 *
 */
@Entity
@Table(name = "T_PT_PriceBind")
@AttributeOverride(name = "priceBindId", column = @Column(name = "priceBindId", length = 36, nullable = false))
public class PtPriceBind extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@FieldInfo(name = "设备编号")
    @Column(name = "termId", length = 36, nullable = true)
    private String termId; 
  
    @FieldInfo(name = "设备序列号")
    @Column(name = "termSn", length = 14, nullable = true)
    private String termSn;
   
    @FieldInfo(name = "费率编号")
    @Column(name = "priceId", length = 36, nullable = true)
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
    
	/**
	 * 以下为不需要持久化到数据库中的字段,根据项目的需要手工增加
	 * 
	 * @Transient
	 * @FieldInfo(name = "") private String field1;
	 */
}