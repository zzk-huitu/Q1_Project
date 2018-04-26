package com.yc.q1.model.base.pt.device;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.yc.q1.core.annotation.FieldInfo;
import com.yc.q1.core.constant.ModuleNumType;
import com.yc.q1.core.model.BaseEntity;

@Entity
@Table(name = "T_PT_TermBag")
@AttributeOverride(name = "id", column = @Column(name = "termBagId", length = 20, nullable = false) )
public class PtTermBag extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ModuleType = ModuleNumType.PT;	//指定此对象生成的模块编码值。
	
	@FieldInfo(name = "termSn", type = "varchar(14) NOT NULL", explain = "设备序列号")
	@Column(name = "termSn", length = 14, nullable = false)
	private String termSn;

	@FieldInfo(name = "设备类型", type = "varchar(4) NOT NULL", explain = "设备类型（对应系统参数表）")
	@Column(name = "termTypeId", length = 4, nullable = false)
	private String termTypeId;

	@FieldInfo(name = "bagValue", type = "decimal(18,2) NOT NULL default 0", explain = "设备余额")
	@Column(name = "bagValue", columnDefinition = "decimal(18,2) default 0", nullable = false)
	private BigDecimal bagValue;

	@FieldInfo(name = "totalBoughtValue", type = "decimal(18,2) NOT NULL default 0", explain = "总买量")
	@Column(name = "totalBoughtValue", columnDefinition = "decimal(18,2) default 0", nullable = false)
	private BigDecimal totalBoughtValue;

	@FieldInfo(name = "totalUsedValue", type = "decimal(18,2) NOT NULL default 0", explain = "总用量")
	@Column(name = "totalUsedValue", columnDefinition = "decimal(18,2) default 0", nullable = false)
	private BigDecimal totalUsedValue;

	@FieldInfo(name = "totalClearValue", type = "decimal(18,2) NOT NULL default 0", explain = "总计清除补助量")
	@Column(name = "totalClearValue", columnDefinition = "decimal(18,2) default 0", nullable = false)
	private BigDecimal totalClearValue;

	@FieldInfo(name = "surplusValue", type = "decimal(18,2) NOT NULL default 0", explain = "补助剩余量")
	@Column(name = "surplusValue", columnDefinition = "decimal(18,2) default 0", nullable = false)
	private BigDecimal surplusValue;

	// @FieldInfo(name="设备名称")
	@Formula("(SELECT A.termName FROM dbo.T_PT_Term A WHERE A.termSn=termSn)")
	private String termName;

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getTermTypeId() {
		return termTypeId;
	}

	public void setTermTypeId(String termTypeId) {
		this.termTypeId = termTypeId;
	}

	public BigDecimal getBagValue() {
		return bagValue;
	}

	public void setBagValue(BigDecimal bagValue) {
		this.bagValue = bagValue;
	}

	public BigDecimal getTotalBoughtValue() {
		return totalBoughtValue;
	}

	public void setTotalBoughtValue(BigDecimal totalBoughtValue) {
		this.totalBoughtValue = totalBoughtValue;
	}

	public BigDecimal getTotalUsedValue() {
		return totalUsedValue;
	}

	public void setTotalUsedValue(BigDecimal totalUsedValue) {
		this.totalUsedValue = totalUsedValue;
	}

	public BigDecimal getTotalClearValue() {
		return totalClearValue;
	}

	public void setTotalClearValue(BigDecimal totalClearValue) {
		this.totalClearValue = totalClearValue;
	}

	public BigDecimal getSurplusValue() {
		return surplusValue;
	}

	public void setSurplusValue(BigDecimal surplusValue) {
		this.surplusValue = surplusValue;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public PtTermBag() {
		super();
	}

	public PtTermBag(String id) {
		super(id);
	}
}
